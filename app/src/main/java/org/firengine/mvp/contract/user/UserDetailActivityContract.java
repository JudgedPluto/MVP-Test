package org.firengine.mvp.contract.user;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

import java.util.Map;

public interface UserDetailActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityStarted(Map<String, Object> user);
        void onUserUpdated();
        void onUserDeleted();
        void onEditButtonClicked();
        void onDeleteButtonClicked();
    }
    interface View extends BaseView {
        void updateTextViews(Map<String, Object> user);
        void showLoadingBar();
        void hideLoadingBar();
        void startUserAddEditActivity(Map<String, Object> user);
        void startUserDeleteActivity(String userId);
        void notifyUserListActivity();
        void finishActivity();
    }
}
