package co.org.ceindetec.derumba.modules.login.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.org.ceindetec.derumba.R;
import co.org.ceindetec.derumba.modules.login.LoginPresenter;
import co.org.ceindetec.derumba.modules.login.LoginPresenterImpl;
import co.org.ceindetec.derumba.modules.playlist.ui.PlayListActivity;

/**
 * Created by Ceindetec02 on 28/07/2016.
 */
public class LoginMainActivity extends AppCompatActivity implements LoginMainView {

    //Inyeccion de controles en ButterKnife
    @Bind(R.id.txvStatusUser)
    TextView txvStatusUser;
    @Bind(R.id.txvDetailUser)
    TextView txvDetailUser;
    @Bind(R.id.bflButtonFacebookLogin)
    LoginButton bflButtonFacebookLogin;
    @Bind(R.id.btnButtonFacebookSignOut)
    Button btnButtonFacebookSignOut;
    @Bind(R.id.rlyLoginContainerLogo)
    RelativeLayout rlyLoginContainerLogo;

    private ProgressDialog mProgressDialog;

    //Declaracion del presentador
    private LoginPresenter loginPresenter;

    private static final String TAG = "FacebookLogin";


    // [START MVP]
    ///////////////////////////////////////////////////////
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]
    ///////////////////////////////////////////////////////
    // [END MVP]

    //Manejador de respuesta de Facebook
    private CallbackManager callbackManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login_main);
        ButterKnife.bind(this);

        //Implementacion del presentador
        loginPresenter = new LoginPresenterImpl(this);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
                updateUI(user);
                // [END_EXCLUDE]
            }
        };
        // [END auth_state_listener]

        //Inicializacion Manejador de respuesta
        callbackManager = CallbackManager.Factory.create();

        // Inicializacion Boton de Facebook
        bflButtonFacebookLogin.setReadPermissions("email", "public_profile");
        bflButtonFacebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
                navigateToMainScreen();
            }

            @Override
            public void onCancel() {
                Snackbar.make(rlyLoginContainerLogo, R.string.login_message_cancel_login, Snackbar.LENGTH_SHORT).show();
                Log.d(TAG, "facebook:onCancel");
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        });
    }
    /** Sobrecarga de los metodos propios de la actividad**/

    /**
     * Sobrecarga del metodo onResume de la actividad     *
     */
    @Override
    protected void onResume() {
        loginPresenter.onResume();
        loginPresenter.checkForAuthenticatedUser();
        super.onResume();
    }

    /**
     * Sobrecarga del metodo onPause de la actividad
     */
    @Override
    protected void onPause() {
        loginPresenter.onPause();
        super.onPause();
    }

    /**
     * Sobrecarga del metodo onDestroy de la actividad
     */
    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    /** Sobrecarga de Metodos de la actividad de Login por Faceebook **/

    /**
     * Sobrecarga del metodo onStart de la actividad
     */
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    /**
     * Sobrecarga del metodo onStop de la actividad
     */
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    /**
     * Sobrecarga del metodo onActivityResult de la vista
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    // [START auth_with_facebook]
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginMainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_facebook]


    //Fin de sesion de facebook
    public void signOut() {
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        updateUI(null);
    }

    /**
     * Metodo para la actualizacion de la Interfaz Grafica
     *
     * @param user
     */
    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            txvStatusUser.setText(getString(R.string.facebook_status_fmt, user.getDisplayName()));
            txvDetailUser.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            bflButtonFacebookLogin.setVisibility(View.GONE);
            btnButtonFacebookSignOut.setVisibility(View.VISIBLE);
        } else {
            txvStatusUser.setText(R.string.signed_out);
            txvDetailUser.setText(null);

            bflButtonFacebookLogin.setVisibility(View.VISIBLE);
            btnButtonFacebookSignOut.setVisibility(View.GONE);
            navigateToMainScreen();
        }
    }

    /**Metodos implementados de4sde la interface LoginMainView **/

    /**
     * Metodo implementado desde la interfacer LoginMainView para la navegacion hacia la vista principal
     */
    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, PlayListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * Metodo implementado desde la interfacer LoginMainView para la navegacion hacia la vista principal
     */
    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.login_message_loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }


    /**
     * Metodo implementado desde la interfacer LoginMainView para la navegacion hacia la vista principal
     */
    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
