package org.firengine.mvp.contract.user;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

public interface UserInfoFragmentContract {
    interface Presenter extends BasePresenter {
        void onFragmentCreated(String userId);
    }

    interface View extends BaseView {
        void updateTextViews(String name, String type);
    }
}
