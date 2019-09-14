package org.firengine.mvp.contract.user;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

import java.util.Map;

public interface UserAddEditActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityStarted(Map<String, Object> user);
        void onConfirmAddEditButtonClicked(Map<String, Object> user);
    }

    interface View extends BaseView {
        void updateEditTexts(Map<String, Object> user);
        void showLoadingBar();
        void hideLoadingBar();
        void notifyUserListActivity();
        void notifyUserDetailActivity();
        void finishActivity();
    }
}
