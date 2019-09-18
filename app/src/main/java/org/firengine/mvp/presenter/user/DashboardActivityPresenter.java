package org.firengine.mvp.presenter.user;

import org.firengine.mvp.contract.user.DashboardActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.auth.Authenticator;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.user.UserModel;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class DashboardActivityPresenter implements DashboardActivityContract.Presenter {
    private WeakReference<DashboardActivityContract.View> view;

    private Authenticator authenticator;
    private Database database;

    private String currentUserId;

    private Callback<List<Map<String, Object>>> findWhereCallback = new Callback<List<Map<String, Object>>>() {
        @Override
        public void onSuccess(List<Map<String, Object>> data) {
            if (data.isEmpty()) {
                view.get().startAddInfoActivity(currentUserId);
            } else {
                Map<String, Object> user = data.get(0);
                view.get().updateTextView(user.get("user_first_name") + " " + user.get("user_last_name"));
            }
        }

        @Override
        public void onFailure() {

        }
    };

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
            currentUserId = data;
            database.where("user_uid", currentUserId, findWhereCallback);
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
        this.database = injector.getDatabaseInstance(new UserModel());
    }

    @Override
    public void onActivityCreated() {
        authenticator.getCurrentUser(getCurrentUserCallback);
    }

    @Override
    public void onAddInfoActivitySuccess() {
        database.where("user_uid", currentUserId, findWhereCallback);
    }

    @Override
    public void onAddInfoActivityFailure() {
        view.get().finishActivity();
    }

    @Override
    public void onLogoutButtonClicked() {
        authenticator.logout(logoutCallback);
    }
}
