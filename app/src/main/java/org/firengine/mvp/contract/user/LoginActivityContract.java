package org.firengine.mvp.contract.user;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

import java.util.Map;

public interface LoginActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityCreated();
        void onLoginButtonClicked(String email, String password);
        void onRegisterNavigateButtonClicked();
    }

    interface View extends BaseView {
        void startDashboardActivity();
        void startRegisterActivity();
    }
}
