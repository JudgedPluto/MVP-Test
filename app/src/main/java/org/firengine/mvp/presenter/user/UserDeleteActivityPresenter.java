package org.firengine.mvp.presenter.user;

import org.firengine.mvp.contract.user.UserDeleteActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Firebase;
import org.firengine.mvp.dependency.database.callback.VoidCallback;
import org.firengine.mvp.model.user.UserModel;

import java.lang.ref.WeakReference;

public class UserDeleteActivityPresenter extends VoidCallback implements UserDeleteActivityContract.Presenter {
    private WeakReference<UserDeleteActivityContract.View> view;

    private Firebase database;

    private String id;

    public UserDeleteActivityPresenter(UserDeleteActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getFirebaseInstance(new UserModel());
        this.database.setCallback(this);
    }

    @Override
    public void onActivityStarted(String id) {
        this.id = id;
    }

    @Override
    public void onConfirmDeleteButtonClicked() {
        if (!id.isEmpty()) {
            database.delete(id);
            view.get().showLoadingBar();
        }
    }

    @Override
    public void callback() {
        view.get().hideLoadingBar();
        view.get().finishActivity();
    }
}
