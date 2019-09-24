package org.firengine.mvp.contract.payment;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

import java.util.List;
import java.util.Map;

public interface ListPaymentsFragmentContract {
    interface Presenter extends BasePresenter {
        void onFragmentCreated(String userId);
        void onListItemClicked(String id);
    }

    interface View extends BaseView {
        void setupAdapter(String userType);
        void updateAdapter(List<Map<String, Object>> data);
        void startPaymentInfoActivity(String id);
    }
}