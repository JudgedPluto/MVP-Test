package org.firengine.mvp.presenter.review;

import org.firengine.mvp.contract.review.ReviewListFragmentContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.review.ReviewModel;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class ReviewListFragmentPresenter implements ReviewListFragmentContract.Presenter {
    private WeakReference<ReviewListFragmentContract.View> view;

    private Database database;
    private Callback<List<Map<String, Object>>> allCallback = new Callback<List<Map<String, Object>>>() {
        @Override
        public void onSuccess(List<Map<String, Object>> data) {
            view.get().updateAdapter(data);
        }
        @Override
        public void onFailure() {

        }
    };


    public ReviewListFragmentPresenter(ReviewListFragmentContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new ReviewModel());
    }

    @Override
    public void onActivityCreated() {
        database.all(allCallback);
    }
}
