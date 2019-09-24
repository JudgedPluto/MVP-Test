package org.firengine.mvp.view.place;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.place.EditPlaceActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.place.EditPlaceActivityPresenter;

public class EditPlaceActivity extends AppCompatActivity implements EditPlaceActivityContract.View {
    private EditPlaceActivityContract.Presenter presenter;

    private ImageView placePhoto;
    private EditText placeName;
    private TextView landlordName;
    private EditText placePrice;
    private RadioGroup placeAvailable;
    private RadioButton placeAvailableYes;
    private RadioButton placeAvailableNo;
    private EditText placeAddress;
    private EditText placePhone;
    private EditText placeFeatures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_place);

        presenter = new EditPlaceActivityPresenter(this, new Injector());

        placePhoto = findViewById(R.id.place_photo_edit);
        placeName = findViewById(R.id.place_name_edit);
        landlordName = findViewById(R.id.landlord_name_place_edit);
        placePrice = findViewById(R.id.place_price_edit);
        placeAvailable = findViewById(R.id.place_available_edit);
        placeAvailableYes = findViewById(R.id.place_available_yes_edit);
        placeAvailableNo = findViewById(R.id.place_available_no_edit);
        placeAddress = findViewById(R.id.place_address_edit);
        placePhone = findViewById(R.id.place_phone_edit);
        placeFeatures = findViewById(R.id.place_features_edit);

        Intent intent = getIntent();
        String id = intent.getStringExtra("place_id");

        presenter.onActivityCreated(id);
    }

    @Override
    public void updateFormElements(Object placeName, Object landlordName, Object placePrice, Object placeAvailable, Object placeAddress, Object placePhone, Object placeFeatures) {
        this.placeName.setText(placeName.toString());
        this.landlordName.setText(landlordName.toString());
        this.placePrice.setText(placePrice.toString());
        findCheckedItem(placeAvailable.toString());
        this.placeAddress.setText(placeAddress.toString());
        this.placePhone.setText(placePhone.toString());
        this.placeFeatures.setText(placeFeatures.toString());
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void onConfirmEdit(View view) {
        presenter.onEditButtonClicked(
                placeName.getText().toString(),
                placePrice.getText().toString(),
                getCheckedItem(placeAvailable),
                placeAddress.getText().toString(),
                placePhone.getText().toString(),
                placeFeatures.getText().toString()
        );
    }

    private void findCheckedItem(String value) {
        switch (value) {
            case "Available":
                placeAvailableYes.setChecked(true);
                break;
            case "Not Available":
                placeAvailableNo.setChecked(true);
                break;
        }
    }

    private String getCheckedItem(RadioGroup group) {
        switch (group.getCheckedRadioButtonId()) {
            case R.id.place_available_yes_edit:
                return "Available";
            case R.id.place_available_no_edit:
                return "Not Available";
        }
        return null;
    }
}
