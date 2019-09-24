package org.firengine.mvp.presenter.user;

import android.util.Log;

import org.firengine.mvp.contract.user.UserInfoFragmentContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.user.UserModel;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class UserInfoFragmentPresenter implements UserInfoFragmentContract.Presenter {
    private WeakReference<UserInfoFragmentContract.View> view;

    private Database database;

    private Callback<List<Map<String, Object>>> findCallback = new Callback<List<Map<String, Object>>>() {
        @Override
        public void onSuccess(List<Map<String, Object>> data) {
            view.get().updateTextViews(
                    data.get(0).get("user_display_name").toString(),
                    data.get(0).get("user_type").toString()
            );
        }

        @Override
        public void onFailure() {

        }
    };

    public UserInfoFragmentPresenter(UserInfoFragmentContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new UserModel());
    }

    @Override
    public void onFragmentCreated(String userId) {
        database.where("user_uid", userId, findCallback);
    }
}
