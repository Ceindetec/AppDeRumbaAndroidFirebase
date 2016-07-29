package co.org.ceindetec.derumba.modules.login;

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
        loginRepository.signUp(email, nick, password);
    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.signIn(email, password);
    }
}
