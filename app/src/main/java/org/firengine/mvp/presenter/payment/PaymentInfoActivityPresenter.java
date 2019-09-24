package org.firengine.mvp.presenter.payment;

import org.firengine.mvp.contract.payment.PaymentInfoActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.payment.PaymentModel;

import java.lang.ref.WeakReference;
import java.util.Map;

public class PaymentInfoActivityPresenter implements PaymentInfoActivityContract.Presenter {
    private WeakReference<PaymentInfoActivityContract.View> view;

    private Database paymentDatabase;

    private String paymentId;

    private Callback<Map<String, Object>> findCallback = new Callback<Map<String, Object>>() {
        @Override
        public void onSuccess(Map<String, Object> data) {
            paymentId = data.get("id").toString();
            view.get().updateTextViews(
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

    public PaymentInfoActivityPresenter(PaymentInfoActivityContract.View view , Injector injector) {
        this.view = new WeakReference<>(view);
        this.paymentDatabase = injector.getDatabaseInstance(new PaymentModel());
    }

    @Override
    public void onActivityCreated(String id) {
        paymentDatabase.find(id, findCallback);
    }

    @Override
    public void onEditButtonClicked() {
        view.get().startEditPaymentActivity(paymentId);
    }
}
