package co.org.ceindetec.derumba.modules.login.ui;

/**
 * Created by Ceindetec02 on 28/07/2016.
 */
public interface LoginUserDeRumbaView {

    void enableInputs();

    void disableInputs();

    void showProgress();

    void hideProgress();

    void navigateToMainScreen();

    void newUserSuccess();

    void newUserError(String error);

    void loginError(String error);

}
