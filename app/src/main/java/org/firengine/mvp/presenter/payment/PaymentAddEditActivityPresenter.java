package org.firengine.mvp.presenter.payment;

import android.util.Log;

import org.firengine.mvp.contract.payment.PaymentAddEditActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.payment.PaymentInfoModel;
import org.firengine.mvp.model.payment.PaymentListModel;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class PaymentAddEditActivityPresenter implements PaymentAddEditActivityContract.Presenter {
//    private PaymentAddEditActivityContract.View view;
    private WeakReference<PaymentAddEditActivityContract.View> view;

    private Database database;

    private Callback<Void> createCallBack = new Callback<Void>() {
        @Override
        public void onSuccess(Void data) {

        }

        @Override
        public void onFailure() {

        }
    };

    public PaymentAddEditActivityPresenter(PaymentAddEditActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new PaymentInfoModel());
    }

    @Override
    public void onAddButtonClicked(String studentId, String landlordId, String placeId, String paymentType, String paymentMethod, String paymentAmount) {
        Map<String, Object> paymentInfo = new HashMap<>();
        paymentInfo.put("student_id", studentId);
        paymentInfo.put("landlord_id", landlordId);
        paymentInfo.put("place_id", placeId);
        paymentInfo.put("payment_type", paymentType);
        paymentInfo.put("payment_method", paymentMethod);
        paymentInfo.put("payment_amount", paymentAmount);
        database.create(paymentInfo, createCallBack);
    }
}
