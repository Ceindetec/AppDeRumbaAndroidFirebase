package co.org.ceindetec.derumba.modules.login.ui;

/**
 * Created by avalo.
 */
public interface LoginUserDeRumbaView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
}
