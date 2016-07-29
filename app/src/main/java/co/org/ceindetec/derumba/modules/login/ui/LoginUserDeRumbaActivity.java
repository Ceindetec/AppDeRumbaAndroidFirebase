package co.org.ceindetec.derumba.modules.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.org.ceindetec.derumba.R;
import co.org.ceindetec.derumba.modules.login.LoginPresenter;
import co.org.ceindetec.derumba.modules.login.LoginPresenterImpl;
import co.org.ceindetec.derumba.modules.playlist.ui.PlayListActivity;

public class LoginUserDeRumbaActivity extends AppCompatActivity implements LoginUserDeRumbaView {

    @Bind(R.id.editTxtEmail)
    EditText inputEmail;
    @Bind(R.id.editTxtPassword)
    EditText inputPassword;
    @Bind(R.id.btnSignIn)
    Button btnSignIn;
    @Bind(R.id.btnSignUp)
    Button btnSignUp;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_de_rumba);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void onResume() {
        loginPresenter.onResume();
        loginPresenter.checkForAuthenticatedUser();
        super.onResume();
    }

    @Override
    protected void onPause() {
        loginPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void enableInputs() {
        setInputs(true);
        hideProgress();
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnSignUp)
    @Override
    public void handleSignUp() {
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    @OnClick(R.id.btnSignIn)
    @Override
    public void handleSignIn() {
        loginPresenter.validateLoginUserDeRumba(inputEmail.getText().toString(), inputPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, PlayListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void loginError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_sign_in), error);
        inputPassword.setError(msgError);
    }

    @Override
    public void newUserSuccess() {
        throw new UnsupportedOperationException("Operation is not valid in LoginUserDeRumbaActivity");
    }

    @Override
    public void newUserError(String error) {
        throw new UnsupportedOperationException("Operation is not valid in LoginUserDeRumbaActivity");
    }

    private void setInputs(boolean enabled){
        inputEmail.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
        btnSignIn.setEnabled(enabled);
        btnSignUp.setEnabled(enabled);
    }
}
