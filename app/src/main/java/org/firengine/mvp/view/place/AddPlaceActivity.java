package org.firengine.mvp.view.place;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.place.AddPlaceActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.place.AddPlaceActivityPresenter;

public class AddPlaceActivity extends AppCompatActivity implements AddPlaceActivityContract.View {
    private AddPlaceActivityContract.Presenter presenter;

    private ImageView placePhoto;
    private EditText placeName;
    private TextView landlordName;
    private EditText placePrice;
    private RadioGroup placeAvailable;
    private EditText placeAddress;
    private EditText placePhone;
    private EditText placeFeatures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        presenter = new AddPlaceActivityPresenter(this, new Injector());

        placePhoto = findViewById(R.id.place_photo_add);
        placeName = findViewById(R.id.place_name_add);
        landlordName = findViewById(R.id.landlord_name_place_add);
        placePrice = findViewById(R.id.place_price_add);
        placeAvailable = findViewById(R.id.place_available_add);
        placeAddress = findViewById(R.id.place_address_add);
        placePhone = findViewById(R.id.place_phone_add);
        placeFeatures = findViewById(R.id.place_features_add);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("user_id");

        presenter.onActivityCreated(userId);
    }

    @Override
    public void updateTextView(String landlordName) {
        this.landlordName.setText(landlordName);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void onConfirmAdd(View view) {
        presenter.onAddButtonClicked(
                placeName.getText().toString(),
                placePrice.getText().toString(),
                getCheckedItem(placeAvailable),
                placeAddress.getText().toString(),
                placePhone.getText().toString(),
                placeFeatures.getText().toString()
        );
    }

    private String getCheckedItem(RadioGroup group) {
        switch (group.getCheckedRadioButtonId()) {
            case R.id.place_available_yes_add:
                return "Available";
            case R.id.place_available_no_add:
                return "Not Available";
        }
        return null;
    }
}
