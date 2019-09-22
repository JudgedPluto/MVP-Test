package org.firengine.mvp.view.place;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.firengine.mvp.R;

public class PlaceListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new PlaceListFragment()).commit();
    }
}
