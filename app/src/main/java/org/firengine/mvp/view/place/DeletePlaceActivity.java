package org.firengine.mvp.view.place;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.place.DeletePlaceActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.place.DeletePlaceActivityPresenter;

public class DeletePlaceActivity extends AppCompatActivity implements DeletePlaceActivityContract.View {
    private DeletePlaceActivityContract.Presenter presenter;

    private TextView placeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_place);

        presenter = new DeletePlaceActivityPresenter(this, new Injector());

        placeName = findViewById(R.id.place_name_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("place_id");
        presenter.onActivityCreated(id);
    }

    @Override
    public void updateTextView(String placeName) {
        this.placeName.setText(placeName);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void onConfirmDelete(View view) {
        presenter.onDeleteButtonClicked();
    }
}
