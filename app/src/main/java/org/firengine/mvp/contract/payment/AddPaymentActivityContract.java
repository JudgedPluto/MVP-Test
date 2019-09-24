package org.firengine.mvp.contract.payment;


import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface AddPaymentActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityCreated(String userId, String placeId);
        void onAddButtonClicked(String paymentAmount, String paymentMethod, String paymentDescription);
    }

    interface View extends BaseView {
        void updateTextViews(String placeName, String studentName, String landlordName);
        void finishActivity();
    }
}
