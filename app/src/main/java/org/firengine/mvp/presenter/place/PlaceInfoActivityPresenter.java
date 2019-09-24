package org.firengine.mvp.presenter.place;

import org.firengine.mvp.contract.place.PlaceInfoActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.place.PlaceModel;

import java.lang.ref.WeakReference;
import java.util.Map;

public class PlaceInfoActivityPresenter implements PlaceInfoActivityContract.Presenter {
    private WeakReference<PlaceInfoActivityContract.View> view;

    private Database database;

    private String userId;
    private String placeId;
    private String placeMap;

    private Callback<Map<String, Object>> findCallback = new Callback<Map<String, Object>>() {
        @Override
        public void onSuccess(Map<String, Object> data) {
            placeId = data.get("id").toString();
            placeMap = data.get("place_map").toString();
            view.get().updateElements(
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

    public PlaceInfoActivityPresenter(PlaceInfoActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new PlaceModel());
    }

    @Override
    public void onActivityCreated(String userId, String placeId) {
        this.userId = userId;
        database.find(placeId, findCallback);
    }

    @Override
    public void onEditButtonClicked() {
        view.get().startEditPlaceActivity(placeId);
    }

    @Override
    public void onDeleteButtonClicked() {
        view.get().startDeletePlaceActivity(placeId);
    }

    @Override
    public void onOrderButtonClicked() {
        if (userId != null) {
            view.get().startAddPaymentActivity(userId, placeId);
        } else {
            view.get().finishActivity();
        }
    }
}
