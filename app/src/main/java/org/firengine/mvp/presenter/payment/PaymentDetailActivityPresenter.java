package org.firengine.mvp.presenter.payment;

import org.firengine.mvp.contract.payment.PaymentDetailActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.payment.PaymentModel;

import java.lang.ref.WeakReference;
import java.util.Map;

public class PaymentDetailActivityPresenter implements PaymentDetailActivityContract.Presenter {
    private WeakReference<PaymentDetailActivityContract.View> view;

    private Database paymentDatabase;
//    private Database userDatabase;
//    private Database placeDatabase;

    private String paymentId;

    private Callback<Map<String, Object>> findCallback = new Callback<Map<String, Object>>() {
        @Override
        public void onSuccess(Map<String, Object> data) {
            paymentId = data.get("id").toString();
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
        this.paymentDatabase = injector.getDatabaseInstance(new PaymentModel());
//        this.userDatabase = injector.getDatabaseInstance(new UserModel());
//        this.placeDatabase = injector.getDatabaseInstance(new PlaceModel());
    }

    @Override
    public void onActivityCreated(String id) {
        paymentDatabase.find(id, findCallback);
    }

    @Override
    public void onEditButtonClicked() {
        view.get().startPaymentEditActivity(paymentId);
    }
}
