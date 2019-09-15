package org.firengine.mvp.presenter.user;

import org.firengine.mvp.contract.user.LoginActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.auth.Authenticator;

import java.lang.ref.WeakReference;

public class LoginActivityPresenter implements LoginActivityContract.Presenter {
    private WeakReference<LoginActivityContract.View> view;

    private Authenticator authenticator;

    private Callback<Void> loginCallback = new Callback<Void>() {
        @Override
        public void onSuccess(Void data) {
            authenticator.getCurrentUser(getCurrentUserCallback);
        }

        @Override
        public void onFailure() {
            view.get().hideLoadingBar();
        }
    };

    private Callback<String> getCurrentUserCallback = new Callback<String>() {
        @Override
        public void onSuccess(String data) {
            view.get().startDashboardActivity();
            view.get().hideLoadingBar();
        }

        @Override
        public void onFailure() {
            view.get().hideLoadingBar();
        }
    };

    public LoginActivityPresenter(LoginActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.authenticator = injector.getAuthenticationInstance();
    }

    @Override
    public void onActivityCreated() {
        authenticator.getCurrentUser(getCurrentUserCallback);
    }

    @Override
    public void onLoginButtonClicked(String email, String password) {
        authenticator.login(email, password, loginCallback);
        view.get().showLoadingBar();
    }
}
