package org.firengine.mvp.presenter.payment;

import org.firengine.mvp.contract.payment.ListPaymentsFragmentContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.payment.PaymentModel;
import org.firengine.mvp.model.user.UserModel;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class ListPaymentsFragmentPresenter implements ListPaymentsFragmentContract.Presenter {
    private WeakReference<ListPaymentsFragmentContract.View> view;

    private Database paymentDatabase;
    private Database userDatabase;

    private Callback<List<Map<String, Object>>> whereCallback = new Callback<List<Map<String, Object>>>() {
        @Override
        public void onSuccess(List<Map<String, Object>> data) {
            view.get().updateAdapter(data);
        }

        @Override
        public void onFailure() {

        }
    };

    private Callback<List<Map<String, Object>>> userWhereCallback = new Callback<List<Map<String, Object>>>() {
        @Override
        public void onSuccess(List<Map<String, Object>> data) {
            String userId = data.get(0).get("id").toString();
            String userType = data.get(0).get("user_type").toString();
            view.get().setupAdapter(userType);
            switch (userType) {
                case "Landlord":
                    paymentDatabase.where("landlord_id", userId, whereCallback);
                    break;
                case "Student":
                    paymentDatabase.where("student_id", userId, whereCallback);
                    break;
            }
        }

        @Override
        public void onFailure() {

        }
    };

    public ListPaymentsFragmentPresenter(ListPaymentsFragmentContract.View view , Injector injector) {
        this.view = new WeakReference<>(view);
        this.paymentDatabase = injector.getDatabaseInstance(new PaymentModel());
        this.userDatabase = injector.getDatabaseInstance(new UserModel());
    }

    @Override
    public void onFragmentCreated(String userId) {
        userDatabase.where("user_uid", userId, userWhereCallback);
    }

    @Override
    public void onListItemClicked(String id) {
        view.get().startPaymentInfoActivity(id);
    }
}