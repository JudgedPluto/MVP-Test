package org.firengine.mvp.contract.payment;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

import java.util.List;
import java.util.Map;

public interface ListPaymentsFragmentContract {
    interface Presenter extends BasePresenter {
        void onFragmentCreated(String column, String value);
        void onListItemClicked(String id);
    }

    interface View extends BaseView {
        void updateAdapter(List<Map<String, Object>> data);
        void startPaymentDetailActivity(String id);
    }
}
