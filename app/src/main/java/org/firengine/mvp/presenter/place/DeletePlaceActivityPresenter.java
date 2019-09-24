package org.firengine.mvp.presenter.place;

import org.firengine.mvp.contract.place.DeletePlaceActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.place.PlaceModel;

import java.lang.ref.WeakReference;
import java.util.Map;

public class DeletePlaceActivityPresenter implements DeletePlaceActivityContract.Presenter {
    private WeakReference<DeletePlaceActivityContract.View> view;

    private Database database;

    private String placeId;

    private Callback<Void> deleteCallback = new Callback<Void>() {
        @Override
        public void onSuccess(Void data) {
            view.get().finishActivity();
        }

        @Override
        public void onFailure() {

        }
    };
    private Callback<Map<String, Object>> findCallback = new Callback<Map<String, Object>>() {
        @Override
        public void onSuccess(Map<String, Object> data) {
            placeId = data.get("id").toString();
            view.get().updateTextView(data.get("place_name").toString());
        }

        @Override
        public void onFailure() {
            view.get().finishActivity();
        }
    };

    public DeletePlaceActivityPresenter(DeletePlaceActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new PlaceModel());
    }

    @Override
    public void onActivityCreated(String id) {
        database.find(id, findCallback);
    }

    @Override
    public void onDeleteButtonClicked() {
        database.delete(placeId, deleteCallback);
    }



//    public void checkValue() {
//        database.find("abc", new Callback<Map<String, Object>>() {
//            @Override
//            public void onSuccess(Map<String, Object> data) {
//                view.get().updateView("Success");
//            }
//
//            @Override
//            public void onFailure() {
//                view.get().updateView("Failure");
//            }
//        });
//    }
}
