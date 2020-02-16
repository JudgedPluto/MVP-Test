package org.firengine.mvp.view.review;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.firengine.mvp.R;
import org.firengine.mvp.dependency.Injector;

public class ReviewListActivity extends AppCompatActivity implements ReviewListActivityContract.View {
    private ReviewListActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list3);
        presenter = new ReviewListActivityPresenter( this, new Injector());

    }
}
