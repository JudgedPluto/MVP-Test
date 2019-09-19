package org.firengine.mvp.contract.payment;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface PaymentDeleteActivityContract {
    interface Presenter extends BasePresenter {
        void checkValue();
    }

    interface View extends BaseView {
        void updateView(String message);
    }
}