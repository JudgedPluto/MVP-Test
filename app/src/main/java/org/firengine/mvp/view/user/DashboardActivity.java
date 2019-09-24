package org.firengine.mvp.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.user.DashboardActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.user.DashboardActivityPresenter;
import org.firengine.mvp.view.constant.ViewCodes;
import org.firengine.mvp.view.payment.ListPaymentsFragment;
import org.firengine.mvp.view.place.AddPlaceActivity;
import org.firengine.mvp.view.place.ListPlacesFragment;

import java.util.Map;

public class DashboardActivity extends AppCompatActivity implements DashboardActivityContract.View {
    private DashboardActivityContract.Presenter presenter;

    private BottomNavigationView navigationView;

    private ListPlacesFragment listPlacesFragment;
    private ListPaymentsFragment listPaymentsFragment;
    private UserInfoFragment userInfoFragment;
    private Fragment activeFragment;

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
                break;
            case "Student":
                navigationView.inflateMenu(R.menu.menu_student);
                break;
        }
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_places:
                        presenter.onItemPlacesClicked();
                        return true;
                    case R.id.item_reviews:
                        presenter.onItemReviewsClicked();
                        return true;
                    case R.id.item_payments:
                        presenter.onItemPaymentsClicked();
                        return true;
                    case R.id.item_user:
                        presenter.onItemUserClicked();
                        return true;
                }
                return true;
            }
        });
        navigationView.setSelectedItemId(R.id.item_places);
    }

    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void startAddPlaceActivity(String id) {
        Intent intent = new Intent(this, AddPlaceActivity.class);
        intent.putExtra("user_id", id);
        startActivity(intent);
    }

    @Override
    public void startAddInfoActivity(String userId) {
        Intent intent = new Intent(this, AddInfoActivity.class);
        intent.putExtra("user_id", userId);
        startActivityForResult(intent, ViewCodes.DASHBOARD_CODE);
    }

    @Override
    public void startListPlacesFragment(String userId) {
        FragmentManager manager = getSupportFragmentManager();
        if (listPlacesFragment == null) {
            listPlacesFragment = new ListPlacesFragment(userId);
            manager.beginTransaction().add(R.id.fragment_container, listPlacesFragment).hide(listPlacesFragment).commit();

        }
        if (activeFragment != null) {
            manager.beginTransaction().hide(activeFragment).commit();
        }
        manager.beginTransaction().show(listPlacesFragment).commit();
        activeFragment = listPlacesFragment;
    }

    @Override
    public void startListReviewsFragment(String userId) {
        FragmentManager manager = getSupportFragmentManager();

    }

    @Override
    public void startListPaymentsFragment(String userId) {
        FragmentManager manager = getSupportFragmentManager();
        if (listPaymentsFragment == null) {
            listPaymentsFragment = new ListPaymentsFragment(userId);
            manager.beginTransaction().add(R.id.fragment_container, listPaymentsFragment).hide(listPaymentsFragment).commit();
        }
        if (activeFragment != null) {
            manager.beginTransaction().hide(activeFragment).commit();
        }
        manager.beginTransaction().show(listPaymentsFragment).commit();
        activeFragment = listPaymentsFragment;
    }

    @Override
    public void startUserInfoFragment(String userId) {
        FragmentManager manager = getSupportFragmentManager();
        if (userInfoFragment == null) {
            userInfoFragment = new UserInfoFragment(userId);
            manager.beginTransaction().add(R.id.fragment_container, userInfoFragment).hide(userInfoFragment).commit();
        }
        if (activeFragment != null) {
            manager.beginTransaction().hide(activeFragment).commit();
        }
        manager.beginTransaction().show(userInfoFragment).commit();
        activeFragment = userInfoFragment;
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void onLogout(View view) {
        presenter.onLogoutButtonClicked();
    }

    public void onAdd(View view) {
        presenter.onAddButtonClicked();
    }
}
