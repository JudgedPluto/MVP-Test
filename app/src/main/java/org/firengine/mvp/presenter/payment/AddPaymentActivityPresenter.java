package org.firengine.mvp.presenter.payment;

import org.firengine.mvp.contract.payment.AddPaymentActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.payment.PaymentModel;
import org.firengine.mvp.model.place.PlaceModel;
import org.firengine.mvp.model.user.UserModel;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddPaymentActivityPresenter implements AddPaymentActivityContract.Presenter {
    private WeakReference<AddPaymentActivityContract.View> view;

    private Database paymentDatabase;
    private Database userDatabase;
    private Database placeDatabase;

    private String userId;
    private String placeId;
    private String landlordId;

    private String userName;
    private String placeName;
    private String landlordName;

    private boolean isUserFound = false;
    private boolean isPlaceFound = false;
    private boolean isLandlordFound = false;

    private Callback<Void> createCallBack = new Callback<Void>() {
        @Override
        public void onSuccess(Void data) {
            view.get().finishActivity();
        }

        @Override
        public void onFailure() {

        }
    };

    private Callback<List<Map<String, Object>>> studentWhereCallback = new Callback<List<Map<String, Object>>>() {
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

    private Callback<Map<String, Object>> placeFindCallback = new Callback<Map<String, Object>>() {
        @Override
        public void onSuccess(Map<String, Object> data) {
            placeId = data.get("id").toString();
            placeName = data.get("place_name").toString();
            isPlaceFound = true;
            updateActivity();
            userDatabase.find(data.get("landlord_id").toString(), landlordFindCallback);
        }

        @Override
        public void onFailure() {
            view.get().finishActivity();
        }
    };

    private Callback<Map<String, Object>> landlordFindCallback = new Callback<Map<String, Object>>() {
        @Override
        public void onSuccess(Map<String, Object> data) {
            landlordId = data.get("id").toString();
            landlordName = data.get("user_display_name").toString();
            isLandlordFound = true;
            updateActivity();
        }

        @Override
        public void onFailure() {
            view.get().finishActivity();
        }
    };

    public AddPaymentActivityPresenter(AddPaymentActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.paymentDatabase = injector.getDatabaseInstance(new PaymentModel());
        this.userDatabase = injector.getDatabaseInstance(new UserModel());
        this.placeDatabase = injector.getDatabaseInstance(new PlaceModel());
    }

    @Override
    public void onActivityCreated(String userId, String placeId) {
        userDatabase.where("user_uid", userId, studentWhereCallback);
        placeDatabase.find(placeId, placeFindCallback);
    }

    @Override
    public void onAddButtonClicked(String paymentAmount, String paymentMethod, String paymentDescription) {
        Map<String, Object> paymentInfo = new HashMap<>();
        paymentInfo.put("place_id", placeId);
        paymentInfo.put("place_name", placeName);
        paymentInfo.put("student_id", userId);
        paymentInfo.put("student_name", userName);
        paymentInfo.put("landlord_id", landlordId);
        paymentInfo.put("landlord_name", landlordName);
        paymentInfo.put("payment_amount", paymentAmount);
        paymentInfo.put("payment_method", paymentMethod);
        paymentInfo.put("payment_description", paymentDescription);
        paymentDatabase.create(paymentInfo, createCallBack);
    }

    private void updateActivity() {
        if (isUserFound && isPlaceFound && isLandlordFound) {
            view.get().updateTextViews(placeName, userName, landlordName);
        }
    }
}
