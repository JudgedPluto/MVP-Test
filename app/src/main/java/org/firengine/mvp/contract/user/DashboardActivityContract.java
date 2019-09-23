package org.firengine.mvp.contract.user;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

import java.util.Map;

public interface DashboardActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityCreated();
        void onAddInfoActivitySuccess();
        void onAddInfoActivityFailure();
        void onLogoutButtonClicked();
    }

    interface View extends BaseView {
        void updateNavigationBar(Map<String, Object> user);
        void startLoginActivity();
        void startAddInfoActivity(String userId);
        void finishActivity();
    }
}
