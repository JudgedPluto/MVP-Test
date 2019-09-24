package org.firengine.mvp.presenter.place;


import org.firengine.mvp.contract.place.AddPlaceActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.place.PlaceModel;
import org.firengine.mvp.model.user.UserModel;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddPlaceActivityPresenter implements AddPlaceActivityContract.Presenter {
    private WeakReference<AddPlaceActivityContract.View> view;

    private Database placeDatabase;
    private Database userDatabase;

    private String userId;

    private String userName;

    private boolean isUserFound = false;

    private Callback<Void> createCallBack = new Callback<Void>() {
        @Override
        public void onSuccess(Void data) {
            view.get().finishActivity();
        }

        @Override
        public void onFailure() {
        }
    };

    private Callback<List<Map<String, Object>>> landlordWhereCallback = new Callback<List<Map<String, Object>>>() {
        @Override
        public void onSuccess(List<Map<String, Object>> data) {
            userId = data.get(0).get("id").toString();
            userName = data.get(0).get("user_display_name").toString();
            isUserFound = true;
            updateActivity();
        }

        @Override
        public void onFailure() {
            view.get().finishActivity();
        }
    };

    public AddPlaceActivityPresenter(AddPlaceActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.placeDatabase = injector.getDatabaseInstance(new PlaceModel());
        this.userDatabase = injector.getDatabaseInstance(new UserModel());
    }

    @Override
    public void onActivityCreated(String userId) {
        userDatabase.where("user_uid", userId, landlordWhereCallback);
    }

    @Override
    public void onAddButtonClicked(String placeName, String placePrice, String placeAvailable, String placeAddress, String placePhone, String placeFeatures) {
        Map<String, Object> placeInfo = new HashMap<>();
        placeInfo.put("landlord_id", userId);
        placeInfo.put("landlord_name", userName);
        placeInfo.put("place_name", placeName);
        placeInfo.put("place_price", placePrice);
        placeInfo.put("place_available", placeAvailable);
        placeInfo.put("place_address", placeAddress);
        placeInfo.put("place_phone", placePhone);
        placeInfo.put("place_features", placeFeatures);
        placeInfo.put("place_map", "map");
        placeDatabase.create(placeInfo, createCallBack);
    }

    private void updateActivity() {
        if (isUserFound) {
            view.get().updateTextView(userName);
        }
    }
}
