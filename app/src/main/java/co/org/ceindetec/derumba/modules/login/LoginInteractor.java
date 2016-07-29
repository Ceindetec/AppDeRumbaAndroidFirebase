package co.org.ceindetec.derumba.modules.login;

/**
 * Created by avalo.
 */
public interface LoginInteractor {
    void checkSession();

    void doSignUp(String email, String nick, String password);

    void doSignIn(String email, String password);
}
