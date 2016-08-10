package co.org.ceindetec.derumba.modules.login;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by avalo.
 */
public interface LoginInteractor {
    void checkSession();

    void doSignUp(String email, String nick, String password);

    void doSignIn(String email, String password);

    void signInFacebookAccessToken(AccessToken token);

}
