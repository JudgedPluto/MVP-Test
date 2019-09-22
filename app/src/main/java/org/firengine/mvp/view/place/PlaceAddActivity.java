package org.firengine.mvp.view.place;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.place.PlaceAddActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.place.PlaceAddActivityPresenter;

public class PlaceAddActivity extends AppCompatActivity implements PlaceAddActivityContract.View {
    private PlaceAddActivityContract.Presenter presenter;

    private EditText b_name;
    private EditText b_address;
    private EditText b_Specilities;
    private EditText b_rules;
    private EditText b_roomType;
    private ImageView b_map;
    private EditText b_roomPrice;
    private EditText b_roomAvail;
    private ImageView b_photos;
    private EditText b_phone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_add);

        presenter = new PlaceAddActivityPresenter(this, new Injector());

        b_name = findViewById(R.id.b_name);
        b_address = findViewById(R.id.b_address);
        b_Specilities = findViewById(R.id.b_Specialities);
        b_rules = findViewById(R.id.b_rules);
        b_roomType = findViewById(R.id.b_roomTypes);
        b_map = findViewById(R.id.b_map);
        b_roomPrice = findViewById(R.id.b_roomPrice);
        b_roomAvail = findViewById(R.id.b_roomAvail);
        b_photos = findViewById(R.id.b_photos);
        b_phone = findViewById(R.id.b_phoneNum);
    }

    @Override
    public void showMessage() {
        Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
    }

    // onclick method
    public void onAdd(View view) {
        presenter.onAddButtonClicked(
                b_name.getText().toString(),
                b_address.getText().toString(),
                b_Specilities.getText().toString(),
                b_rules.getText().toString(),
                b_roomType.getText().toString(),
                b_map.getImageMatrix().toString(),
                b_roomPrice.getText().toString(),
                b_roomAvail.getText().toString(),
                b_photos.getImageMatrix().toString(),
                b_phone.getText().toString()


        );
    }
}
