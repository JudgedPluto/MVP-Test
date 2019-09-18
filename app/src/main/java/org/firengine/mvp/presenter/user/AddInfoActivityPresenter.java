package org.firengine.mvp.presenter.user;

import org.firengine.mvp.contract.user.AddInfoActivityContract;
import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.model.user.UserModel;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class AddInfoActivityPresenter implements AddInfoActivityContract.Presenter {
    private WeakReference<AddInfoActivityContract.View> view;

    private Database database;

    private String userId;

    private Callback<Void> createCallback = new Callback<Void>() {
        @Override
        public void onSuccess(Void data) {
            view.get().notifySuccess();
            view.get().finishActivity();
        }

        @Override
        public void onFailure() {}
    };

    public AddInfoActivityPresenter(AddInfoActivityContract.View view, Injector injector) {
        this.view = new WeakReference<>(view);
        this.database = injector.getDatabaseInstance(new UserModel());
    }

    @Override
    public void onActivityCreated(String userId) {
        this.userId = userId;
    }

    @Override
    public void onAddInfoButtonClicked(String type, String firstName, String lastName) {
        Map<String, Object> data = new HashMap<>();
        data.put("user_uid", userId);
        data.put("user_type", type);
        data.put("user_first_name", firstName);
        data.put("user_last_name", lastName);
        database.create(data, createCallback);
    }

    @Override
    public void onBackButtonClicked() {
        view.get().notifyCancel();
        view.get().finishActivity();
    }
}
