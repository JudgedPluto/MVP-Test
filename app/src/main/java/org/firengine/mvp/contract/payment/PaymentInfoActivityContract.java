package org.firengine.mvp.contract.payment;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface PaymentInfoActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityCreated(String id);
        void onEditButtonClicked();
    }

    interface View extends BaseView{
        void updateTextViews(Object placeName, Object studentName, Object landlordName, Object paymentAmount, Object paymentMethod, Object paymentDescription);
        void startEditPaymentActivity(String id);
        void finishActivity();
    }

}
