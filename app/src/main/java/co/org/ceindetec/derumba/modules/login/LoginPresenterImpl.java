package co.org.ceindetec.derumba.modules.login;

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
    private LoginUserDeRumbaView loginUserDeRumbaView;
    private LoginMainView loginMainView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginUserDeRumbaView loginUserDeRumbaView) {
        this.loginUserDeRumbaView = loginUserDeRumbaView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }


    public LoginPresenterImpl(LoginMainView loginMainView) {
        this.loginMainView = loginMainView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        loginUserDeRumbaView = null;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if(loginUserDeRumbaView != null){
            loginUserDeRumbaView.disableInputs();
            loginUserDeRumbaView.showProgress();
        }

        loginInteractor.checkSession();
    }

    @Override
    public void validateLoginUserDeRumba(String email, String password) {
        if(loginUserDeRumbaView != null){
            loginUserDeRumbaView.disableInputs();
            loginUserDeRumbaView.showProgress();
        }

        loginInteractor.doSignIn(email, password);
    }

    @Override
    public void loginFacebook() {

    }

    @Override
    public void registerNewUser(String email, String nick, String password) {
        if(loginUserDeRumbaView != null){
            loginUserDeRumbaView.disableInputs();
            loginUserDeRumbaView.showProgress();
        }
        loginInteractor.doSignUp(email,nick, password);
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onFailedToRecoverSession() {
        if(loginUserDeRumbaView != null){
            loginUserDeRumbaView.hideProgress();
            loginUserDeRumbaView.enableInputs();
        }
    }

    private void onSignInSuccess(){
        if(loginUserDeRumbaView != null){
            loginUserDeRumbaView.navigateToMainScreen();
        }
    }

    private void onSignUpSuccess(){
        if(loginUserDeRumbaView != null){
            loginUserDeRumbaView.newUserSuccess();
        }
    }

    private void onSignInError(String error){
        if(loginUserDeRumbaView != null){
            loginUserDeRumbaView.hideProgress();
            loginUserDeRumbaView.enableInputs();
            loginUserDeRumbaView.loginError(error);
        }
    }

    private void onSignUpError(String error){
        if(loginUserDeRumbaView != null){
            loginUserDeRumbaView.hideProgress();
            loginUserDeRumbaView.enableInputs();
            loginUserDeRumbaView.newUserError(error);
        }
    }
}
