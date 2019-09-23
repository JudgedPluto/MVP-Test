package org.firengine.mvp.presenter.payment;

import org.firengine.mvp.contract.payment.EditPaymentActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.payment.PaymentModel;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class EditPaymentActivityPresenter implements EditPaymentActivityContract.Presenter {
    private WeakReference<EditPaymentActivityContract.View> view;

    private Database paymentDatabase;
//    private Database userDatabase;
//    private Database placeDatabase;

    private String paymentId;

    private Callback<Map<String, Object>> findCallback = new Callback<Map<String, Object>>() {
        @Override
        public void onSuccess(Map<String, Object> data) {
            view.get().updateEditTexts(
                    data.get("student_id"),
                    data.get("landlord_id"),
                    data.get("place_id"),
                    data.get("payment_type"),
                    data.get("payment_method"),
                    data.get("payment_amount")
            );
        }

        @Override
        public void onFailure() {

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


    public EditPaymentActivityPresenter(EditPaymentActivityContract.View view , Injector injector) {
        this.view = new WeakReference<EditPaymentActivityContract.View>(view);
        this.paymentDatabase = injector.getDatabaseInstance(new PaymentModel());
//        this.userDatabase = injector.getDatabaseInstance(new UserModel());
//        this.placeDatabase = injector.getDatabaseInstance(new PlaceModel());
    }


    @Override
    public void onActivityCreated(String id) {
        paymentId = id;
        paymentDatabase.find(id, findCallback);
    }

    @Override
    public void onEditButtonClicked(String student_id, String landlord_id, String place_id, String toString, String toString1, String toString2) {
        Map<String, Object> payment = new HashMap<>();
//        payment.put("student_id", student_id);
//        payment.put("landlord_id", landlord_id);
//        payment.put("place_id", place_id);
        payment.put("payment_type", toString);
        payment.put("payment_method", toString1);
        payment.put("payment_amount", toString2);
        paymentDatabase.update(paymentId, payment, updateCallback);

    }
}