package org.firengine.mvp.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.user.UserAddEditActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.user.UserAddEditActivityPresenter;
import org.firengine.mvp.view.constants.ViewCodes;

import java.util.HashMap;
import java.util.Map;

public class UserAddEditActivity extends AppCompatActivity implements UserAddEditActivityContract.View {
    private UserAddEditActivityContract.Presenter presenter;

    private FrameLayout loadingAddEdit;
    private EditText inputUsername;
    private EditText inputPassword;

    private boolean isPresenterAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_edit);

        presenter = new UserAddEditActivityPresenter(this, new Injector());

        loadingAddEdit = findViewById(R.id.loading_add_edit);
        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);

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
    public void updateEditTexts(Map<String, Object> user) {
        inputUsername.setText(user.get("username").toString());
        inputPassword.setText(user.get("password").toString());
    }

    @Override
    public void showLoadingBar() {
        loadingAddEdit.setVisibility(View.VISIBLE);
        loadingAddEdit.setClickable(true);
    }

    @Override
    public void hideLoadingBar() {
        loadingAddEdit.setVisibility(View.INVISIBLE);
        loadingAddEdit.setClickable(false);
    }

    @Override
    public void notifyUserListActivity() {
        setResult(ViewCodes.ADD_OK);
    }

    @Override
    public void notifyUserDetailActivity() {
        setResult(ViewCodes.UPDATE_OK);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void onConfirmAddEdit(View view) {
        if (isPresenterAvailable) {
            isPresenterAvailable = false;
            Map<String, Object> user = new HashMap<>();
            user.put("username", inputUsername.getText().toString());
            user.put("password", inputPassword.getText().toString());
            presenter.onConfirmAddEditButtonClicked(user);
        }
    }
}
