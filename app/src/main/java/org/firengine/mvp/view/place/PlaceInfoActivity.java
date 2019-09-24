package org.firengine.mvp.view.place;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.place.PlaceInfoActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.place.PlaceInfoActivityPresenter;
import org.firengine.mvp.view.payment.AddPaymentActivity;

public class PlaceInfoActivity extends AppCompatActivity implements PlaceInfoActivityContract.View {
    private PlaceInfoActivityContract.Presenter presenter;

    private ImageView placePhoto;
    private TextView placeName;
    private TextView landlordName;
    private TextView placePrice;
    private TextView placeAvailable;
    private TextView placeAddress;
    private TextView placePhone;
    private TextView placeFeatures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);

        presenter = new PlaceInfoActivityPresenter(this, new Injector());

        placePhoto = findViewById(R.id.place_photo_info);
        placeName = findViewById(R.id.place_name_info);
        landlordName = findViewById(R.id.landlord_name_place_info);
        placePrice = findViewById(R.id.place_price_info);
        placeAvailable = findViewById(R.id.place_available_info);
        placeAddress = findViewById(R.id.place_address_info);
        placePhone = findViewById(R.id.place_phone_info);
        placeFeatures = findViewById(R.id.place_features_info);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("user_id");
        String placeId = intent.getStringExtra("place_id");

        presenter.onActivityCreated(userId, placeId);
    }

    @Override
    public void updateElements(Object placeName, Object landlordName, Object placePrice, Object placeAvailable, Object placeAddress, Object placePhone, Object placeFeatures) {
        this.placeName.setText(placeName.toString());
        this.landlordName.setText(landlordName.toString());
        this.placePrice.setText(placePrice.toString());
        this.placeAvailable.setText(placeAvailable.toString());
        this.placeAddress.setText(placeAddress.toString());
        this.placePhone.setText(placePhone.toString());
        this.placeFeatures.setText(placeFeatures.toString());
    }

    @Override
    public void startEditPlaceActivity(String id) {
        Intent intent = new Intent(this, EditPlaceActivity.class);
        intent.putExtra("place_id", id);
        startActivity(intent);
    }

    @Override
    public void startDeletePlaceActivity(String id) {
        Intent intent = new Intent(this, DeletePlaceActivity.class);
        intent.putExtra("place_id", id);
        startActivity(intent);
    }

    @Override
    public void startAddPaymentActivity(String userId, String placeId) {
        Intent intent = new Intent(this, AddPaymentActivity.class);
        intent.putExtra("user_id", userId);
        intent.putExtra("place_id", placeId);
        startActivity(intent);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void onEdit(View view) {
        presenter.onEditButtonClicked();
    }

    public void onDelete(View view) {
        presenter.onDeleteButtonClicked();
    }

    public void onOrder(View view) {
        presenter.onOrderButtonClicked();
    }
}
