package org.firengine.mvp.presenter.payment;

import org.firengine.mvp.contract.payment.PaymentDeleteActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.payment.PaymentInfoModel;

import java.lang.ref.WeakReference;
import java.util.Map;

public class PaymentDeleteActivityPresenter implements PaymentDeleteActivityContract.Presenter {
    private WeakReference<PaymentDeleteActivityContract.View> view;

    private Database database;

    public PaymentDeleteActivityPresenter(PaymentDeleteActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new PaymentInfoModel());
    }


    @Override
    public void checkValue() {
        database.find("abc", new Callback<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> data) {
                view.get().updateView("Success");
            }

            @Override
            public void onFailure() {
                view.get().updateView("Failure");
            }
        });
    }
}
