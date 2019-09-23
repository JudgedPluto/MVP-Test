package org.firengine.mvp.presenter.payment;

import org.firengine.mvp.contract.payment.PaymentListFragmentContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.payment.PaymentModel;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class PaymentListFragmentPresenter implements PaymentListFragmentContract.Presenter {
    private WeakReference<PaymentListFragmentContract.View> view;

    private Database database;
    private Callback<List<Map<String, Object>>> whereCallback = new Callback<List<Map<String, Object>>>() {
        @Override
        public void onSuccess(List<Map<String, Object>> data) {
            view.get().updateAdapter(data);
        }

        @Override
        public void onFailure() {

        }
    };

    public PaymentListFragmentPresenter(PaymentListFragmentContract.View view , Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new PaymentModel());
    }

    @Override
    public void onActivityCreated(String column, String value) {
        database.where(column, value, whereCallback);
    }

    @Override
    public void onListItemClicked(String id) {
        view.get().startPaymentDetailActivity(id);
    }
}
