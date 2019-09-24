package org.firengine.mvp.view.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.user.SplashActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.user.SplashActivityPresenter;

public class SplashActivity extends AppCompatActivity implements SplashActivityContract.View {
    private SplashActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter = new SplashActivityPresenter(this, new Injector());

        presenter.onActivityCreated();
    }

    @Override
    public void startDashboardActivity(String userId) {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra("user_id", userId);
        startActivity(intent);
    }

    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
