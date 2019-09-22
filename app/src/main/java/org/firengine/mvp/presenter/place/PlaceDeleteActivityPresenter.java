package org.firengine.mvp.presenter.place;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import java.lang.ref.WeakReference;
import java.util.Map;

import org.firengine.mvp.contract.place.PlaceDeleteActivityContract;
import org.firengine.mvp.model.place.PlaceModel;

public class PlaceDeleteActivityPresenter implements PlaceDeleteActivityContract.Presenter {

    private WeakReference<PlaceDeleteActivityContract.View> view;

    private Database database;

    public PlaceDeleteActivityPresenter(PlaceDeleteActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new PlaceModel());
    }



    public void checkValue() {
        database.find("abc", new Callback<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> data) {
                view.get().updateView("Success");
            }

            @Override
            public void onFailure() {
                view.get().updateView("Failure");
            }
        });
    }
}
