package org.firengine.mvp.contract.payment;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface EditPaymentActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityCreated(String s);
        void onEditButtonClicked(String student_id, String landlord_id, String place_id, String toString, String toString1, String toString2);
    }

    interface View extends BaseView {
        void updateEditTexts(Object student_id, Object landlord_id, Object place_id, Object payment_type, Object payment_method, Object payment_amount);
        void finishActivity();
    }
}
