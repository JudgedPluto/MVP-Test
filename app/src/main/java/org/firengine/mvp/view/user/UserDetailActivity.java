package org.firengine.mvp.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.user.UserDetailActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.user.UserDetailActivityPresenter;
import org.firengine.mvp.view.constants.ViewCodes;

import java.util.HashMap;
import java.util.Map;

public class UserDetailActivity extends AppCompatActivity implements UserDetailActivityContract.View {
    private UserDetailActivityContract.Presenter presenter;

    private FrameLayout loadingDetail;
    private TextView username;
    private TextView password;

    private boolean isPresenterAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        presenter = new UserDetailActivityPresenter(this, new Injector());

        loadingDetail = findViewById(R.id.loading_detail);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Intent intent = getIntent();
        HashMap<String, Object> user = (HashMap<String, Object>) intent.getSerializableExtra("reference_user");

        presenter.onActivityStarted(user);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPresenterAvailable = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ViewCodes.DELETE_CODE && resultCode == ViewCodes.DELETE_OK) {
            presenter.onUserDeleted();
        } else if (requestCode == ViewCodes.UPDATE_CODE && resultCode == ViewCodes.UPDATE_OK) {
            presenter.onUserUpdated();
        }
    }

    @Override
    public void updateTextViews(Map<String, Object> user) {
        this.username.setText(user.get("username").toString());
        this.password.setText(user.get("password").toString());
    }

    @Override
    public void showLoadingBar() {
        loadingDetail.setVisibility(View.VISIBLE);
        loadingDetail.setClickable(true);
    }

    @Override
    public void hideLoadingBar() {
        loadingDetail.setVisibility(View.INVISIBLE);
        loadingDetail.setClickable(false);
    }

    @Override
    public void startUserAddEditActivity(Map<String, Object> user) {
        Intent intent = new Intent(this, UserAddEditActivity.class);
        HashMap<String, Object> extra = new HashMap<>(user);
        intent.putExtra("reference_user", extra);
        startActivityForResult(intent, ViewCodes.UPDATE_CODE);
    }

    @Override
    public void startUserDeleteActivity(String userId) {
        Intent intent = new Intent(this, UserDeleteActivity.class);
        intent.putExtra("reference_user_id", userId);
        startActivityForResult(intent, ViewCodes.DELETE_CODE);
    }

    @Override
    public void notifyUserListActivity() {
        setResult(ViewCodes.MODIFIED_OK);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void onEdit(View view) {
        if (isPresenterAvailable) {
            isPresenterAvailable = false;
            presenter.onEditButtonClicked();
        }
    }

    public void onDelete(View view) {
        if (isPresenterAvailable) {
            isPresenterAvailable = false;
            presenter.onDeleteButtonClicked();
        }
    }
}
