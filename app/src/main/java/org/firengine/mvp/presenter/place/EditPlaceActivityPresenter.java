package org.firengine.mvp.presenter.place;


import org.firengine.mvp.contract.place.EditPlaceActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.place.PlaceModel;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;


public class EditPlaceActivityPresenter implements EditPlaceActivityContract.Presenter {
    private WeakReference<EditPlaceActivityContract.View> view;

    private Database database;

    private String placeId;

    private Callback<Map<String, Object>> findCallback = new Callback<Map<String, Object>>() {
        @Override
        public void onSuccess(Map<String, Object> data) {
            placeId = data.get("id").toString();
            view.get().updateFormElements(
                    data.get("place_name"),
                    data.get("landlord_name"),
                    data.get("place_price"),
                    data.get("place_available"),
                    data.get("place_address"),
                    data.get("place_phone"),
                    data.get("place_features")
            );
        }

        @Override
        public void onFailure() {
            view.get().finishActivity();
        }
    };

    private Callback<Void> updateCallback = new Callback<Void>() {
        @Override
        public void onSuccess(Void data) {
            view.get().finishActivity();
        }

        @Override
        public void onFailure() {

        }
    };

    public EditPlaceActivityPresenter(EditPlaceActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new PlaceModel());
    }


    @Override
    public void onActivityCreated(String id) {
        database.find(id, findCallback);
    }

    @Override
    public void onEditButtonClicked(String placeName, String placePrice, String placeAvailable, String placeAddress, String placePhone, String placeFeatures) {
        Map<String, Object> placeInfo = new HashMap<>();
        placeInfo.put("place_name", placeName);
        placeInfo.put("place_price", placePrice);
        placeInfo.put("place_available", placeAvailable);
        placeInfo.put("place_address", placeAddress);
        placeInfo.put("place_phone", placePhone);
        placeInfo.put("place_features", placeFeatures);
        database.update(placeId, placeInfo, updateCallback);
    }
}
