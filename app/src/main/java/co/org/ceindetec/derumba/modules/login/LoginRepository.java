package co.org.ceindetec.derumba.modules.login;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by avalo.
 */
public interface LoginRepository {
    void signUpUserDeRumba(String email, String nick, String password);

    void signInUserDeRumba(String email, String password);

    void signInFacebookAccessToken(AccessToken token);

    void checkSession();
}
