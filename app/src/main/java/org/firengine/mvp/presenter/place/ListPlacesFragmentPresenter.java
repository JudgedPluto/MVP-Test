package org.firengine.mvp.presenter.place;

import org.firengine.mvp.contract.place.ListPlacesFragmentContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.place.PlaceModel;
import org.firengine.mvp.model.user.UserModel;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class ListPlacesFragmentPresenter implements ListPlacesFragmentContract.Presenter {
    private WeakReference<ListPlacesFragmentContract.View> view;

    private Database placeDatabase;
    private Database userDatabase;

    private Callback<List<Map<String, Object>>> whereAllCallback = new Callback<List<Map<String, Object>>>() {
        @Override
        public void onSuccess(List<Map<String, Object>> data) {
            view.get().updateAdapter(data);
        }

        @Override
        public void onFailure() {

        }
    };

    private Callback<List<Map<String, Object>>> userWhereCallback = new Callback<List<Map<String, Object>>>() {
        @Override
        public void onSuccess(List<Map<String, Object>> data) {
            String userId = data.get(0).get("id").toString();
            String userType = data.get(0).get("user_type").toString();
            view.get().setupAdapter();
            switch (userType) {
                case "Landlord":
                    placeDatabase.where("landlord_id", userId, whereAllCallback);
                    break;
                case "Student":
                    placeDatabase.all(whereAllCallback);
                    break;
            }
        }

        @Override
        public void onFailure() {

        }
    };

    public ListPlacesFragmentPresenter(ListPlacesFragmentContract.View view , Injector injector) {
        this.view = new WeakReference<>(view);
        this.placeDatabase = injector.getDatabaseInstance(new PlaceModel());
        this.userDatabase = injector.getDatabaseInstance(new UserModel());
    }

    @Override
    public void onFragmentCreated(String userId) {
        userDatabase.where("user_uid", userId, userWhereCallback);
    }

    @Override
    public void onListItemClicked(String id) {
        view.get().startPlaceInfoActivity(id);
    }
}
