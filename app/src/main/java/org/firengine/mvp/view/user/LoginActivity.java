package org.firengine.mvp.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.user.LoginActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.user.LoginActivityPresenter;

public class LoginActivity extends AppCompatActivity implements LoginActivityContract.View {
    private LoginActivityContract.Presenter presenter;

    private EditText inputEmail;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginActivityPresenter(this, new Injector());

        inputEmail = findViewById(R.id.input_email_login);
        inputPassword = findViewById(R.id.input_password_login);

        presenter.onActivityCreated();
    }

    @Override
    public void startDashboardActivity() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void startRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onLogin(View view) {
        presenter.onLoginButtonClicked(
                inputEmail.getText().toString(),
                inputPassword.getText().toString()
        );
    }

    public void onRegisterNavigate(View view) {
        presenter.onRegisterNavigateButtonClicked();
    }
}
