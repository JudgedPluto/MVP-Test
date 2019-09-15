package org.firengine.mvp.view.user;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.user.RegisterActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.user.RegisterActivityPresenter;

public class RegisterActivity extends AppCompatActivity implements RegisterActivityContract.View {
    private RegisterActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        presenter = new RegisterActivityPresenter(this, new Injector());
    }
}
