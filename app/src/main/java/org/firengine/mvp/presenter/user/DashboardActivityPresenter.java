package org.firengine.mvp.presenter.user;

import org.firengine.mvp.contract.user.DashboardActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.auth.Authenticator;

import java.lang.ref.WeakReference;

public class DashboardActivityPresenter implements DashboardActivityContract.Presenter {
    private WeakReference<DashboardActivityContract.View> view;

    private Authenticator authenticator;

    private Callback<Void> logoutCallback = new Callback<Void>() {
        @Override
        public void onSuccess(Void data) {
            view.get().startLoginActivity();
            view.get().finishActivity();
        }

        @Override
        public void onFailure() {}
    };

    private Callback<String> getCurrentUserCallback = new Callback<String>() {
        @Override
        public void onSuccess(String data) {
            view.get().updateTextView(data);
        }

        @Override
        public void onFailure() {
            view.get().startLoginActivity();
            view.get().finishActivity();
        }
    };

    public DashboardActivityPresenter(DashboardActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.authenticator = injector.getAuthenticationInstance();
    }

    @Override
    public void onActivityCreated() {
        authenticator.getCurrentUser(getCurrentUserCallback);
    }

    @Override
    public void onLogoutButtonClicked() {
        authenticator.logout(logoutCallback);
    }
}
