package org.firengine.mvp.presenter.user;

import org.firengine.mvp.contract.user.UserAddEditActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Firebase;
import org.firengine.mvp.dependency.database.callback.VoidCallback;
import org.firengine.mvp.model.user.UserModel;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class UserAddEditActivityPresenter extends VoidCallback implements UserAddEditActivityContract.Presenter {
    private WeakReference<UserAddEditActivityContract.View> view;

    private Firebase database;

    private Map<String, Object> user;

    private boolean isUpdating;

    public UserAddEditActivityPresenter(UserAddEditActivityContract.View view, Injector injector) {
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
    public void onConfirmAddEditButtonClicked(Map<String, Object> user) {
        String newUsername = user.get("username").toString();
        String newPassword = user.get("password").toString();
        if (newUsername.isEmpty() && newPassword.isEmpty()) {
            return;
        }
        Map<String, Object> data = new HashMap<>();
        data.put("username", newUsername);
        data.put("password", newPassword);
        if (this.user != null) {
            String currentUsername = this.user.get("username").toString();
            String currentPassword = this.user.get("password").toString();
            if (currentUsername.equals(newUsername) && currentPassword.equals(newPassword)) {
                return;
            }
            isUpdating = true;
            database.update(this.user.get("id").toString(), data);
        } else {
            isUpdating = false;
            database.create(data);
        }
        view.get().showLoadingBar();
    }

    private void invalidateActivity() {
        if (user != null) {
            view.get().updateEditTexts(user);
        }
    }

    @Override
    public void callback() {
        view.get().hideLoadingBar();
        if (isUpdating) {
            view.get().notifyUserDetailActivity();
        } else {
            view.get().notifyUserListActivity();
        }
        view.get().finishActivity();
    }
}
