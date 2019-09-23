package org.firengine.mvp.contract.user;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface SplashActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityCreated();
    }

    interface View extends BaseView {
        void startDashboardActivity(String userId);
        void startLoginActivity();
    }
}
