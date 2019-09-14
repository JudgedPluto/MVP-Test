package org.firengine.mvp.presenter.user;

import org.firengine.mvp.contract.user.UserListActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Firebase;
import org.firengine.mvp.dependency.database.callback.ListCallback;
import org.firengine.mvp.model.user.UserModel;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class UserListActivityPresenter extends ListCallback implements UserListActivityContract.Presenter {
    private WeakReference<UserListActivityContract.View> view;

    private Firebase database;

    private boolean isRequested = true;

    public UserListActivityPresenter(UserListActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getFirebaseInstance(new UserModel());
        this.database.setCallback(this);
    }

    @Override
    public void onRecyclerViewRefreshed() {
        if (isRequested) {
            database.all();
            isRequested = false;
            view.get().showLoadingBar();
        }
    }

    @Override
    public void onAddButtonClicked() {
        view.get().startUserAddEditActivity();
    }

    @Override
    public void onListItemClicked(Map<String, Object> user) {
        view.get().startUserDetailActivity(user);
    }

    @Override
    public void sendUpdateRequest() {
        isRequested = true;
    }

    @Override
    public void callback(List<Map<String, Object>> list) {
        view.get().updateRecyclerView(list);
        view.get().hideLoadingBar();
    }
}
