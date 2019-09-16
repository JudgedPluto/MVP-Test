package org.firengine.mvp.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.user.RegisterActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.user.RegisterActivityPresenter;

public class RegisterActivity extends AppCompatActivity implements RegisterActivityContract.View {
    private RegisterActivityContract.Presenter presenter;

    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        presenter = new RegisterActivityPresenter(this, new Injector());

        inputEmail = findViewById(R.id.input_email_register);
        inputPassword = findViewById(R.id.input_password_register);
        inputConfirmPassword = findViewById(R.id.input_confirm_password_register);
    }

    public void onRegisterCreate(View view) {
        presenter.onRegisterCreateButton(
                inputEmail.getText().toString(),
                inputPassword.getText().toString(),
                inputConfirmPassword.getText().toString()
        );
    }

    @Override
    public void startDashboardActivity() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}
