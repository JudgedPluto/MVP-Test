package org.firengine.mvp.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.user.UserDeleteActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.user.UserDeleteActivityPresenter;
import org.firengine.mvp.view.constants.ViewCodes;

public class UserDeleteActivity extends AppCompatActivity implements UserDeleteActivityContract.View {
    private UserDeleteActivityContract.Presenter presenter;

    private FrameLayout loadingDelete;

    private boolean isPresenterAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_delete);

        presenter = new UserDeleteActivityPresenter(this, new Injector());

        loadingDelete = findViewById(R.id.loading_delete);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("reference_user_id");

        presenter.onActivityStarted(userId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPresenterAvailable = true;
    }

    @Override
    public void showLoadingBar() {
        loadingDelete.setVisibility(View.VISIBLE);
        loadingDelete.setClickable(true);
    }

    @Override
    public void hideLoadingBar() {
        loadingDelete.setVisibility(View.INVISIBLE);
        loadingDelete.setClickable(false);
    }

    @Override
    public void finishActivity() {
        setResult(ViewCodes.DELETE_OK);
        finish();
    }

    public void onConfirmDelete(View view) {
        if (isPresenterAvailable) {
            isPresenterAvailable = false;
            presenter.onConfirmDeleteButtonClicked();
        }
    }
}
