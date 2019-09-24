package org.firengine.mvp.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.user.AddInfoActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.user.AddInfoActivityPresenter;
import org.firengine.mvp.view.constant.ViewCodes;

public class AddInfoActivity extends AppCompatActivity implements AddInfoActivityContract.View {
    private AddInfoActivityContract.Presenter presenter;

    private Spinner inputType;
    private EditText inputFirstName;
    private EditText inputLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        presenter = new AddInfoActivityPresenter(this, new Injector());

        inputType = findViewById(R.id.input_type_add_info);
        inputFirstName = findViewById(R.id.input_first_name_add_info);
        inputLastName = findViewById(R.id.input_last_name_add_info);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("user_id");

        presenter.onActivityCreated(userId);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackButtonClicked();
        super.onBackPressed();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void notifySuccess() {
        setResult(ViewCodes.DASHBOARD_OK);
    }

    @Override
    public void notifyCancel() {
        setResult(ViewCodes.DASHBOARD_CANCEL);
    }

    public void onAddInfo(View view) {
        presenter.onAddInfoButtonClicked(
                inputType.getSelectedItem().toString(),
                inputFirstName.getText().toString(),
                inputLastName.getText().toString()
        );
    }
}
