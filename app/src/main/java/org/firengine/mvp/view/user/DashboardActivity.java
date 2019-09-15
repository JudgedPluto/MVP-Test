package org.firengine.mvp.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.user.DashboardActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.user.DashboardActivityPresenter;

public class DashboardActivity extends AppCompatActivity implements DashboardActivityContract.View {
    private DashboardActivityContract.Presenter presenter;

    private TextView userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        presenter = new DashboardActivityPresenter(this, new Injector());

        userId = findViewById(R.id.user_id);

        presenter.onActivityCreated();
    }

    @Override
    public void updateTextView(String userId) {
        this.userId.setText(userId);
    }

    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void onLogout(View view) {
        presenter.onLogoutButtonClicked();
    }
}
