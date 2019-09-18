package org.firengine.mvp.contract.user;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface AddInfoActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityCreated(String userId);
        void onAddInfoButtonClicked(String type, String firstName, String lastName);
        void onBackButtonClicked();
    }

    interface View extends BaseView {
        void finishActivity();
        void notifySuccess();
        void notifyCancel();
    }
}
