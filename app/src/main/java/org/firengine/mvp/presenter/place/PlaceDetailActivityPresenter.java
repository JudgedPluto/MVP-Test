package org.firengine.mvp.presenter.place;

import org.firengine.mvp.contract.place.PlaceDetailActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.place.PlaceModel;

import java.lang.ref.WeakReference;
import java.util.Map;

public class PlaceDetailActivityPresenter implements PlaceDetailActivityContract.Presenter {
    private WeakReference<PlaceDetailActivityContract.View> view;

    private Database database;

    private Callback<Map<String, Object>> findCallback = new Callback<Map<String, Object>>() {
        @Override
        public void onSuccess(Map<String, Object> data) {
            view.get().updateTextViews(
                    data.get("b_name"),
                    data.get("b_address"),
                    data.get("b_Specialities"),
                    data.get("b_rules"),
                    data.get("b_roomTypes"),
                    data.get("b_map"),
                    data.get("b_roomPrice"),
                    data.get("b_roomAvail"),
                    data.get("b_photos"),
                    data.get("b_phoneNum")
            );
        }

        @Override
        public void onFailure() {

        }
    };

    public PlaceDetailActivityPresenter(PlaceDetailActivityContract.View view , Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new PlaceModel());
    }

    @Override
    public void onActivityCreated() {

    }
}
