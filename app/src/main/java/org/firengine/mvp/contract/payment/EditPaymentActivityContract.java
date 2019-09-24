package org.firengine.mvp.contract.payment;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface EditPaymentActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityCreated(String s);
        void onEditButtonClicked(String paymentAmount, String paymentMethod, String paymentDescription);
    }

    interface View extends BaseView {
        void updateFormElements(Object placeName, Object studentName, Object landlordName, Object paymentAmount, Object paymentMethod, Object paymentDescription);
        void finishActivity();
    }
}
