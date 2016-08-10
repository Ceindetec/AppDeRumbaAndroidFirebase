package co.org.ceindetec.derumba.modules.login.ui;

/**
 * Created by Ceindetec02 on 28/07/2016.
 */
public interface LoginMainView {

    void enableInputs();

    void disableInputs();

    void showProgressLogin();

    void hideProgressLogin();

    void showProgressDialog();

    void hideProgressDialog();

    void navigateToMainScreen();

    void newUserSuccess();

    void newUserError(String error);

    void loginUserDeRumbaError(String error);

    void loginFacebookError(String error);

}
