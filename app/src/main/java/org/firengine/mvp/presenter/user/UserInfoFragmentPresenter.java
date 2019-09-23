package org.firengine.mvp.presenter.user;

import org.firengine.mvp.contract.user.UserInfoFragmentContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.view.user.UserInfoFragment;

import java.lang.ref.WeakReference;

public class UserInfoFragmentPresenter implements UserInfoFragmentContract.Presenter {
    private WeakReference<UserInfoFragmentContract.View> view;

    public UserInfoFragmentPresenter(UserInfoFragmentContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void onFragmentCreated(String name, String type) {
        view.get().updateTextViews(name, type);
    }
}
