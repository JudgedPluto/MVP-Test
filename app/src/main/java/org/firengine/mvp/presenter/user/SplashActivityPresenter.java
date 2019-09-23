package org.firengine.mvp.presenter.user;

import org.firengine.mvp.contract.user.SplashActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.auth.Authenticator;

import java.lang.ref.WeakReference;

public class SplashActivityPresenter implements SplashActivityContract.Presenter {
    private WeakReference<SplashActivityContract.View> view;

    private Authenticator authenticator;

    private Callback<String> getCurrentUserCallback = new Callback<String>() {
        @Override
        public void onSuccess(String data) {
            view.get().startDashboardActivity(data);
        }

        @Override
        public void onFailure() {
            view.get().startLoginActivity();
        }
    };

    public SplashActivityPresenter(SplashActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.authenticator = injector.getAuthenticationInstance();
    }

    @Override
    public void onActivityCreated() {
        authenticator.getCurrentUser(getCurrentUserCallback);
    }
}
