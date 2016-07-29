package co.org.ceindetec.derumba.modules.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import co.org.ceindetec.derumba.domain.FirebaseHelper;
import co.org.ceindetec.derumba.entities.Session;
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

    @Override
    public void checkSession() {
        if (firebaseAuth.getCurrentUser() != null) {
            initSignIn();
        } else {
            postEvent(LoginEvent.onFailedToRecoverSession);
        }
    }

    @Override
    public void signUp(final String email, final String nick, final String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        postEvent(LoginEvent.onSignUpSuccess);
                        signIn(email, password);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        postEvent(LoginEvent.onSignUpError, e.getMessage());
                    }
                });
    }

    @Override
    public void signIn(String email, String password) {
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
                        postEvent(LoginEvent.onSignInError, e.getMessage());
                    }
                });
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
                firebaseHelper.changeUserConnectionStatus(User.ONLINE);
                postEvent(LoginEvent.onSignInSuccess);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void registerNewUser() {
        String email = firebaseHelper.getAuthUserEmail();
        String nick= Session.getInstance().getNick();

        if (email != null) {
            User currentUser = new User();
            currentUser.setEmail(email);
            currentUser.setNick(nick);
            databaseReference.setValue(currentUser);
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
}
