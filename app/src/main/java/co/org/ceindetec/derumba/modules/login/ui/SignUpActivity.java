package co.org.ceindetec.derumba.modules.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.org.ceindetec.derumba.R;
import co.org.ceindetec.derumba.modules.login.LoginPresenter;
import co.org.ceindetec.derumba.modules.login.LoginPresenterImpl;
import co.org.ceindetec.derumba.modules.playlist.ui.PlayListActivity;


public class SignUpActivity extends AppCompatActivity implements LoginUserDeRumbaView {

    @Bind(R.id.edtLoginEmail)
    EditText inputEmail;
    @Bind(R.id.editTxtNick)
    EditText inputNick;
    @Bind(R.id.edtLoginPass)
    EditText inputPassword;
    @Bind(R.id.btnLoginUserDeRumbaSignUp)
    Button btnSignUp;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer)
    RelativeLayout container;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        setTitle(getString(R.string.signup_activity_title));

        loginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void onResume() {
        loginPresenter.onResume();
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

    @OnClick(R.id.btnLoginUserDeRumbaSignUp)
    public void handleSignUp() {
        loginPresenter.registerNewUser(inputEmail.getText().toString(),inputNick.getText().toString(), inputPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, PlayListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void loginError(String error) {
        throw new UnsupportedOperationException("Operation is not valid in SignUpActivity");
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(container, R.string.login_messageSuccess_signUp, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_messageError_signUp), error);
        inputPassword.setError(msgError);
    }

    private void setInputs(boolean enabled){
        inputEmail.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
        btnSignUp.setEnabled(enabled);
    }
}
