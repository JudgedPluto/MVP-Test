package org.firengine.mvp.presenter.user;

import org.firengine.mvp.contract.user.RegisterActivityContract;
import org.firengine.mvp.dependency.Injector;

import java.lang.ref.WeakReference;

public class RegisterActivityPresenter implements RegisterActivityContract.Presenter {
    private WeakReference<RegisterActivityContract.View> view;

    public RegisterActivityPresenter(RegisterActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
    }
}
