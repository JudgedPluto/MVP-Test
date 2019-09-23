package org.firengine.mvp.view.place;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.firengine.mvp.contract.place.PlaceDetailActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.place.PlaceDetailActivityPresenter;

public class PlaceDetailActivity extends AppCompatActivity implements PlaceDetailActivityContract.View {
    private PlaceDetailActivityContract.Presenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new PlaceDetailActivityPresenter(this, new Injector());

        presenter.onActivityCreated();
    }

    @Override
    public void updateTextViews(Object b_name, Object b_address, Object b_specialities, Object b_rules, Object b_roomTypes, Object b_map, Object b_roomPrice, Object b_roomAvail, Object b_photos, Object b_phoneNum) {

    }
}
