package org.firengine.mvp.view.review;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.review.ReviewAddActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.review.ReviewAddActivityPresenter;

public class ReviewAddActivity extends AppCompatActivity implements ReviewAddActivityContract.View {
    private ReviewAddActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_add);

        presenter = new ReviewAddActivityPresenter(this, new Injector());
    }
}
