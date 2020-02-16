package org.firengine.mvp.presenter.review;

import org.firengine.mvp.contract.review.ReviewAddActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.review.ReviewModel;

import java.lang.ref.WeakReference;

public class ReviewAddActivityPresenter implements ReviewAddActivityContract.Presenter {
    //private ReviewAddActivityContract.View view;
    private WeakReference< ReviewAddActivityContract.View> view;

    private Database database;

    public ReviewAddActivityPresenter(ReviewAddActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view) ;
        this.database = injector.getDatabaseInstance(new ReviewModel());
    }
}
