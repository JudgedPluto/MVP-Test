package org.firengine.mvp.contract.payment;


import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface AddPaymentActivityContract {
    interface Presenter extends BasePresenter {
        void onAddButtonClicked(String studentId, String landlordId, String placeId, String paymentType, String paymentMethod, String paymentAmount);
    }

    interface View extends BaseView {}
}
