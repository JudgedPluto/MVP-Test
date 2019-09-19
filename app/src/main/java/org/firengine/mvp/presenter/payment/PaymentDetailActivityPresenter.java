package org.firengine.mvp.presenter.payment;

import org.firengine.mvp.contract.payment.PaymentDetailActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.payment.PaymentInfoModel;

import java.lang.ref.WeakReference;
import java.util.Map;

public class PaymentDetailActivityPresenter implements PaymentDetailActivityContract.Presenter {
    private WeakReference<PaymentDetailActivityContract.View> view;

    private Database database;

    private Callback<Map<String, Object>> findCallback = new Callback<Map<String, Object>>() {
        @Override
        public void onSuccess(Map<String, Object> data) {
            view.get().updateTextViews(
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

    public PaymentDetailActivityPresenter(PaymentDetailActivityContract.View view , Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new PaymentInfoModel());
    }

    @Override
    public void onActivityCreated(String id) {
        database.find(id, findCallback);
    }
}
