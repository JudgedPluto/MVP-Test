package org.firengine.mvp.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.user.DashboardActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.user.DashboardActivityPresenter;
import org.firengine.mvp.view.constant.ViewCodes;
import org.firengine.mvp.view.payment.ListPaymentsFragment;

import java.util.Map;

public class DashboardActivity extends AppCompatActivity implements DashboardActivityContract.View {
    private DashboardActivityContract.Presenter presenter;

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        presenter = new DashboardActivityPresenter(this, new Injector());

        navigationView = findViewById(R.id.navigation_view);

        presenter.onActivityCreated();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ViewCodes.DASHBOARD_CODE) {
            switch (resultCode) {
                case ViewCodes.DASHBOARD_OK:
                    presenter.onAddInfoActivitySuccess();
                    break;
                case ViewCodes.DASHBOARD_CANCEL:
                    presenter.onAddInfoActivityFailure();
                    break;
            }
        }
    }

    @Override
    public void updateNavigationBar(final Map<String, Object> user) {
        switch (user.get("user_type").toString()) {
            case "Landlord":
                navigationView.inflateMenu(R.menu.menu_landlord);
                navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.item_places:
                            case R.id.item_reviews:
                                return true;
                            case R.id.item_payments:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, new ListPaymentsFragment(
                                                "landlord_id",
                                                user.get("user_uid").toString())
                                        ).commit();
                                return true;
                            case R.id.item_user:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, new UserInfoFragment(
                                                user.get("user_first_name").toString() + " " + user.get("user_last_name").toString(),
                                                user.get("user_type").toString())
                                        ).commit();
                                return true;
                        }
                        return true;
                    }
                });
                break;
            case "Student":
                navigationView.inflateMenu(R.menu.menu_student);
                navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.item_places:
                                return true;
                            case R.id.item_payments:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, new ListPaymentsFragment(
                                                "student_id",
                                                user.get("user_uid").toString())
                                        ).commit();
                                return true;
                            case R.id.item_user:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, new UserInfoFragment(
                                                user.get("user_first_name").toString() + " " + user.get("user_last_name").toString(),
                                                user.get("user_type").toString())
                                        ).commit();
                                return true;
                        }
                        return true;
                    }
                });
                break;
        }
    }

    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void startAddInfoActivity(String userId) {
        Intent intent = new Intent(this, AddInfoActivity.class);
        intent.putExtra("user_id", userId);
        startActivityForResult(intent, ViewCodes.DASHBOARD_CODE);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void onLogout(View view) {
        presenter.onLogoutButtonClicked();
    }
}
