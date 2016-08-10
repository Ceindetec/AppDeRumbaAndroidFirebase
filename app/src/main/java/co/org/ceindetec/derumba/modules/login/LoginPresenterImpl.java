package co.org.ceindetec.derumba.modules.login;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.greenrobot.eventbus.Subscribe;

import co.org.ceindetec.derumba.lib.EventBus;
import co.org.ceindetec.derumba.lib.GreenRobotEventBus;
import co.org.ceindetec.derumba.modules.login.events.LoginEvent;
import co.org.ceindetec.derumba.modules.login.ui.LoginMainView;
import co.org.ceindetec.derumba.modules.login.ui.LoginUserDeRumbaView;

/**
 * Created by avalo.
 */
public class LoginPresenterImpl implements LoginPresenter {
    private EventBus eventBus;
    private LoginMainView loginMainView;
    private LoginUserDeRumbaView loginUserDeRumbaView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginMainView loginMainView) {
        this.loginMainView = loginMainView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    public LoginPresenterImpl(LoginUserDeRumbaView loginUserDeRumbaView) {
        this.loginUserDeRumbaView = loginUserDeRumbaView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    /**
     * Metodo evento del presentador que se ejecuta en la correcta autenticacion
     */
    private void onSignInSuccess() {
        if (loginMainView != null) {
            loginMainView.navigateToMainScreen();
        }
    }

    /**
     * Metodo evento del presentador que se ejecuta en la correcta autenticacion de un usuario de DeRumba
     */
    private void onSignInUserDeRumbaError(String error) {
        if (loginMainView != null) {
            loginMainView.hideProgressLogin();
            loginMainView.enableInputs();
            loginMainView.loginUserDeRumbaError(error);
        }
    }

    /**
     * Metodo evento del presentador que se ejecuta en la correcta autenticacion de un usuario de DeRumba
     */
    private void onSignUpSuccess() {
        if (loginMainView != null) {
            loginMainView.newUserSuccess();
        }
    }

    /**
     * Metodo evento del presentador que se ejecuta en la correcta creacion de un usuario de DeRumba
     */
    private void onSignUpUserDeRumbaError(String error) {
        if (loginMainView != null) {
            loginMainView.hideProgressLogin();
            loginMainView.enableInputs();
            loginMainView.newUserError(error);
        }
    }

    /**
     * Metodo evento del presentador que se ejecuta en el error de autenticacion
     * de un usuario por una cuenta de Facebook
     */
    private void onSignInFacebookError(String error) {
        if (loginMainView != null) {
            loginMainView.hideProgressDialog();
            loginMainView.loginFacebookError(error);
        }
    }

    /**
     * Metodo evento del presentador que se ejecuta en la fallo de recuperar la sesion de usuario
     */
    private void onFailedToRecoverSession() {
        if (loginMainView != null) {
            loginMainView.hideProgressLogin();
            loginMainView.enableInputs();
        }
    }

    /**
     * Metodo impementado de la inferface LoginPresenter
     */
    @Override
    public void onCreate() {

    }

    /**
     * Metodo impementado de la inferface LoginPresenter
     */
    @Override
    public void onDestroy() {
        loginMainView = null;
    }

    /**
     * Metodo impementado de la inferface LoginPresenter
     */
    @Override
    public void onResume() {
        eventBus.register(this);
    }

    /**
     * Metodo impementado de la inferface LoginPresenter
     */
    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    /**
     * Metodo impementado de la inferface LoginPresenter
     */
    @Override
    public void checkForAuthenticatedUser() {
        if (loginMainView != null) {
            loginMainView.disableInputs();
            loginMainView.showProgressLogin();
        }

        loginInteractor.checkSession();
    }

    /**
     * Metodo impementado de la inferface LoginPresenter
     */
    @Override
    public void validateLoginUserDeRumba(String email, String password) {
        if (loginMainView != null) {
            loginMainView.disableInputs();
            loginMainView.showProgressLogin();
        }
        loginInteractor.doSignIn(email, password);
    }

    /**
     * Metodo impementado de la inferface LoginPresenter
     */
    @Override
    public void signInFacebookAccessToken(AccessToken token) {
        if (loginMainView != null) {
            loginMainView.showProgressDialog();
        }
        loginInteractor.signInFacebookAccessToken(token);
    }

    /**
     * Metodo impementado de la inferface LoginPresenter
     */
    @Override
    public void registerNewUser(String email, String nick, String password) {
        if (loginMainView != null) {
            loginMainView.disableInputs();
            loginMainView.showProgressLogin();
        }
        loginInteractor.doSignUp(email, nick, password);
    }

    /**
     * Metodo impementado de la inferface LoginPresenter
     */
    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignUpUSuccess:
                onSignUpSuccess();
                break;
            case LoginEvent.onSignInUserDeRumbaError:
                onSignInUserDeRumbaError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpUserDeRumbaError:
                onSignUpUserDeRumbaError(event.getErrorMessage());
                break;
            case LoginEvent.onSignInFacebookError:
                onSignInFacebookError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }


}
