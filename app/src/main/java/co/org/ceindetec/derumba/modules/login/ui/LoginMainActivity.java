package co.org.ceindetec.derumba.modules.login.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.org.ceindetec.derumba.R;
import co.org.ceindetec.derumba.modules.login.LoginPresenter;
import co.org.ceindetec.derumba.modules.login.LoginPresenterImpl;
import co.org.ceindetec.derumba.modules.playlist.ui.PlayListActivity;

/**
 * Created by Ceindetec02 on 28/07/2016.
 */
public class LoginMainActivity extends AppCompatActivity implements LoginMainView, GoogleApiClient.OnConnectionFailedListener {

    @Bind(R.id.rlyLoginContainerLogo)
    RelativeLayout rlyLoginContainerLogo;

    //@Bind(R.id.edtLoginEmail) EditText edtLoginEmail;
    // @Bind(R.id.edtLoginPass) EditText edtLoginPass;

    // @Bind(R.id.btnLoginUserDeRumbaSignIn) Button btnLoginUserDeRumbaSignIn;
    // @Bind(R.id.btnLoginUserDeRumbaSignUp) Button btnLoginUserDeRumbaSignUp;

    @Bind(R.id.bflButtonFacebookLogin)
    LoginButton bflButtonFacebookLogin;

    @Bind(R.id.pgbLoginUserDeRumbaProgress)
    ProgressBar pgbLoginUserDeRumbaProgress;

    private ProgressDialog mProgressDialog;

    //Declaracion del presentador
    private LoginPresenter loginPresenter;

    private static final int RC_FACEBOOK_SIGN_IN = 64206;

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

        //Metodo que llama la funci
        checkSession();

        //Inicializacion Manejador de respuesta del Facebook
        callbackManager = CallbackManager.Factory.create();

        // Inicializacion Boton de Facebook
        bflButtonFacebookLogin.setReadPermissions("email", "public_profile");
        bflButtonFacebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginPresenter.signInFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Snackbar.make(rlyLoginContainerLogo, R.string.login_message_cancel_facebook, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Snackbar.make(rlyLoginContainerLogo, R.string.login_message_error, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    /** Sobrecarga de los metodos propios de la actividad**/

    /**
     * Sobrecarga del metodo onResume de la actividad
     */
    @Override
    protected void onResume() {
        loginPresenter.onResume();
        checkSession();
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

    /**
     * Metodo que valida la sesion anterior
     */
    private void checkSession() {

        loginPresenter.checkForAuthenticatedUser();

    }

    /**
     * Metodo que muestra los errores de Login
     */
    private void loginError() {

        Snackbar.make(rlyLoginContainerLogo, R.string.login_message_error, Snackbar.LENGTH_SHORT).show();

    }


    /** Sobrecarga de Metodos de la actividad de Login por Faceebook/Google **/

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
        if (requestCode == RC_FACEBOOK_SIGN_IN) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } else {
            loginError();
        }
    }


    /**
     * Metodo de la clase LoginMain que habilita / Deshabilita los controles de la actividad
     *
     * @param enabled
     */
    private void setInputs(boolean enabled) {

        //edtLoginEmail.setEnabled(enabled);
        //edtLoginPass.setEnabled(enabled);

        //btnLoginUserDeRumbaSignIn.setEnabled(enabled);
        //btnLoginUserDeRumbaSignUp.setEnabled(enabled);

        bflButtonFacebookLogin.setEnabled(enabled);

    }

    /**
     * Metodo de la clase LoginMain para iniciar sesion
     */
    /*
    @OnClick(R.id.btnLoginUserDeRumbaSignIn)
    public void handleSignIn() {
        loginPresenter.validateLoginUserDeRumba(edtLoginEmail.getText().toString(), edtLoginPass.getText().toString());
    }
    */
    /**
     * Metodo de la clase LoginMain para llamar la pantalla de creacion de usuario
     */
    /*
    @OnClick(R.id.btnLoginUserDeRumbaSignUp)
    public void handleSignUp() {
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }
    */

    /**Metodos implementados desde la interface LoginMainView **/

    /**
     * Metodo implementado desde la interface LoginMainView para la navegacion hacia la vista principal
     */
    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, PlayListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * Metodo implementado desde la interface LoginMainView para el
     * manejo de evento de error de creacion usuario
     */
    @Override
    public void newUserSuccess() {
        //throw new UnsupportedOperationException("Operation is not valid in LoginUserDeRumbaActivity");
    }

    /**
     * Metodo implementado desde la interface LoginMainView para el
     * manejo de evento de error de creacion usuario
     *
     * @param error
     */
    @Override
    public void newUserError(String error) {
        //throw new UnsupportedOperationException("Operation is not valid in LoginUserDeRumbaActivity");
    }

    /**
     * Metodo implementado desde la interface LoginMainView para el
     * manejo de evento de error en el login de usuario
     */
    @Override
    public void loginUserDeRumbaError(String error) {
        /*
        edtLoginPass.setText("");
        String msgError = String.format(getString(R.string.login_error_message_sign_in), error);
        edtLoginPass.setError(msgError);
        */
    }

    /**
     * Metodo implementado desde la interface LoginMainView para
     * la habilitacion de los controles de la actividad
     */
    @Override
    public void enableInputs() {
        setInputs(true);
    }

    /**
     * Metodo implementado desde la interface LoginMainView para
     * la deshabilitacion de los controles de la actividad
     */
    @Override
    public void disableInputs() {
        setInputs(false);
    }

    /**
     * Metodo implementado desde la interface LoginMainView para
     * la habilitacion de la barra de progreso
     */
    @Override
    public void showProgressLogin() {
        pgbLoginUserDeRumbaProgress.setVisibility(View.VISIBLE);
    }

    /**
     * Metodo implementado desde la interface LoginMainView para
     * la deshabilitacion de la barra de progreso
     */
    @Override
    public void hideProgressLogin() {
        pgbLoginUserDeRumbaProgress.setVisibility(View.GONE);
    }

    /**
     * Metodo implementado desde la interface LoginMainView para la navegacion hacia la vista principal
     */
    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.general_message_loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    /**
     * Metodo implementado desde la interface LoginMainView para la navegacion hacia la vista principal
     */
    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * Metodo implementado desde la interface LoginMainView en error de login en Facebbok
     */
    @Override
    public void loginFacebookError(String error) {

    }

    /**
     * Metodo implementado desde la interface del escuchador
     *
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        loginError();
    }
}
