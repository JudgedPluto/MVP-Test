package org.firengine.mvp.view.place;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.firengine.mvp.contract.place.PlaceDeleteActivityContract;

public class PlaceDeleteActivity extends AppCompatActivity implements PlaceDeleteActivityContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void updateView(String message) {

    }
}
