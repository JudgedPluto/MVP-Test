package org.firengine.mvp.contract.user;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

import java.util.Map;

public interface DashboardActivityContract {
    interface Presenter extends BasePresenter {
        void onActivityCreated();
        void onAddInfoActivitySuccess();
        void onAddInfoActivityFailure();
        void onItemPlacesClicked();
        void onItemReviewsClicked();
        void onItemPaymentsClicked();
        void onItemUserClicked();
        void onLogoutButtonClicked();
        void onAddButtonClicked();
    }

    interface View extends BaseView {
        void updateNavigationBar(Map<String, Object> user);
        void startLoginActivity();
        void startAddPlaceActivity(String id);
        void startAddInfoActivity(String userId);
        void startListPlacesFragment(String userId);
        void startListReviewsFragment(String userId);
        void startListPaymentsFragment(String userId);
        void startUserInfoFragment(String userId);
        void finishActivity();
    }
}
