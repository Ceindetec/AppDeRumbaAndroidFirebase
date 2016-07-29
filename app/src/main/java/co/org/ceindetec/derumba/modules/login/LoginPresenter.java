package co.org.ceindetec.derumba.modules.login;

import co.org.ceindetec.derumba.modules.login.events.LoginEvent;

/**
 * Created by avalo.
 */
public interface LoginPresenter {
    void onCreate();

    void onDestroy();

    void onResume();

    void onPause();

    void checkForAuthenticatedUser();

    void validateLoginUserDeRumba(String email, String password);

    void loginFacebook();

    void registerNewUser(String email, String nick, String password);

    void onEventMainThread(LoginEvent event);
}
