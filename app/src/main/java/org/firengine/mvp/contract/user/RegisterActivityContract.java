package org.firengine.mvp.contract.user;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface RegisterActivityContract {
    interface Presenter extends BasePresenter {
        void onRegisterCreateButton(String email, String password, String confirmPassword);
    }

    interface View extends BaseView {
        void startDashboardActivity();
    }
}
