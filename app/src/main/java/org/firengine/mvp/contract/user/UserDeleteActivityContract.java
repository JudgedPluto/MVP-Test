package org.firengine.mvp.contract.user;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface UserDeleteActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityStarted(String id);
        void onConfirmDeleteButtonClicked();
    }

    interface View extends BaseView {
        void showLoadingBar();
        void hideLoadingBar();
        void finishActivity();
    }
}
