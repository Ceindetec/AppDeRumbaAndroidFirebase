package co.org.ceindetec.derumba.modules.login;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;

import co.org.ceindetec.derumba.domain.FirebaseHelper;
import co.org.ceindetec.derumba.entities.User;
import co.org.ceindetec.derumba.lib.EventBus;
import co.org.ceindetec.derumba.lib.GreenRobotEventBus;
import co.org.ceindetec.derumba.modules.login.events.LoginEvent;


/**
 * Created by avalo.
 */
public class LoginRepositoryImpl implements LoginRepository {
    private final FirebaseAuth firebaseAuth;
    private FirebaseHelper firebaseHelper;
    private DatabaseReference databaseReference;

    public LoginRepositoryImpl() {
        this.firebaseHelper = FirebaseHelper.getInstance();
        this.databaseReference = firebaseHelper.getMyUserReference();
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    private void initSignIn() {
        databaseReference = firebaseHelper.getMyUserReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);

                if (currentUser == null) {
                    registerNewUser();
                }
                postEvent(LoginEvent.onSignInSuccess);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void registerNewUser() {
        String email = firebaseHelper.getAuthUserEmail();
        if (email != null) {
            User currentUser = new User();
            currentUser.setEmail(email);
            currentUser.setUsername(firebaseHelper.getAuthUserName());
            firebaseHelper.setNewUser(currentUser,firebaseAuth.getCurrentUser().getUid());
        }
    }

    private void postEvent(int type, String errorMessage) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type) {

        postEvent(type, null);

    }

    @Override
    public void checkSession() {
        if (firebaseAuth.getCurrentUser() != null) {
            initSignIn();
        } else {
            postEvent(LoginEvent.onFailedToRecoverSession);
        }
    }

    @Override
    public void signUpUserDeRumba(final String email, final String nick, final String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        postEvent(LoginEvent.onSignUpUSuccess);
                        signInUserDeRumba(email, password);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        postEvent(LoginEvent.onSignUpUserDeRumbaError, e.getMessage());
                    }
                });
    }

    @Override
    public void signInUserDeRumba(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        initSignIn();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        postEvent(LoginEvent.onSignInUserDeRumbaError, e.getMessage());
                    }
                });
    }

    @Override
    public void signInFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            initSignIn();
                        } else {
                            postEvent(LoginEvent.onSignInFacebookError);
                        }
                    }
                });
    }

}
