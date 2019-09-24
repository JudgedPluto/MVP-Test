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

    private Database database;

    private String paymentId;

    private Callback<Map<String, Object>> findCallback = new Callback<Map<String, Object>>() {
        @Override
        public void onSuccess(Map<String, Object> data) {
            paymentId = data.get("id").toString();
            view.get().updateFormElements(
                    data.get("place_name"),
                    data.get("student_name"),
                    data.get("landlord_name"),
                    data.get("payment_amount"),
                    data.get("payment_method"),
                    data.get("payment_description")
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

    public EditPaymentActivityPresenter(EditPaymentActivityContract.View view , Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new PaymentModel());
    }

    @Override
    public void onActivityCreated(String id) {
        database.find(id, findCallback);
    }

    @Override
    public void onEditButtonClicked(String paymentAmount, String paymentMethod, String paymentDescription) {
        Map<String, Object> payment = new HashMap<>();
        payment.put("payment_amount", paymentAmount);
        payment.put("payment_method", paymentMethod);
        payment.put("payment_description", paymentDescription);
        database.update(paymentId, payment, updateCallback);

    }
}