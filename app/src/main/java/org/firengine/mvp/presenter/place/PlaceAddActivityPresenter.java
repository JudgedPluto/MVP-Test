package org.firengine.mvp.presenter.place;


import org.firengine.mvp.contract.place.PlaceAddActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.place.PlaceModel;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class PlaceAddActivityPresenter implements PlaceAddActivityContract.Presenter {
    private WeakReference<PlaceAddActivityContract.View> view;

    private Database database;

    private Callback<Void> createCallBack = new Callback<Void>() {
        @Override
        public void onSuccess(Void data) {
            view.get().showMessage();
        }

        @Override
        public void onFailure() {
            view.get().showMessage();
        }
    };

    public PlaceAddActivityPresenter(PlaceAddActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new PlaceModel());
    }



    @Override
    public void onAddButtonClicked(String toString, String toString1, String toString2, String toString3, String toString4, String toString5, String toString6, String toString7, String toString8, String toString9) {
        Map<String, Object> place = new HashMap<>();

        place.put("b_name", toString);
        place.put("b_address", toString1);
        place.put("b_Specialities", toString2);
        place.put("b_rules", toString3);
        place.put("b_roomTypes", toString4);
        place.put("b_map", toString5);
        place.put("b_roomPrice", toString6);
        place.put("b_roomAvail", toString7);
        place.put("b_photos", toString8);
        place.put("b_phoneNum", toString9);
        database.create(place, createCallBack);
    }


}
