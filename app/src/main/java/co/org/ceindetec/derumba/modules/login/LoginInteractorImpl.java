package co.org.ceindetec.derumba.modules.login;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by avalo.
 */
public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImpl() {

        loginRepository = new LoginRepositoryImpl();

    }

    @Override
    public void checkSession() {

        loginRepository.checkSession();

    }

    @Override
    public void doSignUp(String email, String nick, String password) {

        loginRepository.signUpUserDeRumba(email, nick, password);

    }

    @Override
    public void doSignIn(String email, String password) {

        loginRepository.signInUserDeRumba(email, password);

    }

    @Override
    public void signInFacebookAccessToken(AccessToken token) {

        loginRepository.signInFacebookAccessToken(token);

    }

}
