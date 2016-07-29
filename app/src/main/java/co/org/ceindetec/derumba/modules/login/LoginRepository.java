package co.org.ceindetec.derumba.modules.login;

/**
 * Created by avalo.
 */
public interface LoginRepository {
    void signUp(String email, String nick, String password);

    void signIn(String email, String password);

    void checkSession();
}
