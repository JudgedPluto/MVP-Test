package org.firengine.mvp.presenter.user;

import org.firengine.mvp.contract.user.RegisterActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.auth.Authenticator;

import java.lang.ref.WeakReference;

public class RegisterActivityPresenter implements RegisterActivityContract.Presenter {
    private WeakReference<RegisterActivityContract.View> view;

    private Authenticator authenticator;

    private Callback<Void> registerCallback = new Callback<Void>() {
        @Override
        public void onSuccess(Void data) {
            view.get().startDashboardActivity();
        }

        @Override
        public void onFailure() {

        }
    };

    public RegisterActivityPresenter(RegisterActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.authenticator = injector.getAuthenticationInstance();
    }

    @Override
    public void onRegisterCreateButton(String email, String password, String confirmPassword) {
        authenticator.register(email, password, registerCallback);
    }
}
