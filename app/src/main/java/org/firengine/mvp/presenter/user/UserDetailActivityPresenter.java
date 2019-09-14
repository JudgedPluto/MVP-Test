package org.firengine.mvp.presenter.user;

import org.firengine.mvp.contract.user.UserDetailActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Firebase;
import org.firengine.mvp.dependency.database.callback.DataCallback;
import org.firengine.mvp.model.user.UserModel;

import java.lang.ref.WeakReference;
import java.util.Map;

public class UserDetailActivityPresenter extends DataCallback implements UserDetailActivityContract.Presenter {
    private WeakReference<UserDetailActivityContract.View> view;

    private Firebase database;

    private Map<String, Object> user;

    public UserDetailActivityPresenter(UserDetailActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getFirebaseInstance(new UserModel());
        this.database.setCallback(this);
    }

    @Override
    public void onActivityStarted(Map<String, Object> user) {
        this.user = user;
        invalidateActivity();
    }

    @Override
    public void onUserUpdated() {
        view.get().notifyUserListActivity();
        database.find(user.get("id").toString());
        view.get().showLoadingBar();
    }

    @Override
    public void onUserDeleted() {
        view.get().notifyUserListActivity();
        view.get().finishActivity();
    }

    @Override
    public void onEditButtonClicked() {
        view.get().startUserAddEditActivity(user);
    }

    @Override
    public void onDeleteButtonClicked() {
        view.get().startUserDeleteActivity(user.get("id").toString());
    }

    private void invalidateActivity() {
        if (user != null) {
            view.get().updateTextViews(user);
        } else {
            view.get().finishActivity();
        }
    }

    @Override
    public void callback(Map<String, Object> data) {
        user = data;
        view.get().hideLoadingBar();
        invalidateActivity();
    }
}
