package org.firengine.mvp.contract.user;

import org.firengine.mvp.presenter.BasePresenter;
import org.firengine.mvp.view.BaseView;

import java.util.List;
import java.util.Map;

public interface UserListActivityContract {
    interface Presenter extends BasePresenter {
        void onRecyclerViewRefreshed();
        void onAddButtonClicked();
        void onListItemClicked(Map<String, Object> user);
        void sendUpdateRequest();
    }

    interface View extends BaseView {
        void updateRecyclerView(List<Map<String, Object>> list);
        void showLoadingBar();
        void hideLoadingBar();
        void startUserAddEditActivity();
        void startUserDetailActivity(Map<String, Object> user);
    }
}
