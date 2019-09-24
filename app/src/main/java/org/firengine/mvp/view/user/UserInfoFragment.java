package org.firengine.mvp.view.user;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.user.UserInfoFragmentContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.user.UserInfoFragmentPresenter;

public class UserInfoFragment extends Fragment implements UserInfoFragmentContract.View {
    private UserInfoFragmentContract.Presenter presenter;

    private TextView displayName;
    private TextView displayType;

    private String userId;

    public UserInfoFragment(String userId) {
        this.userId = userId;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_info, container, false);
        displayName = rootView.findViewById(R.id.display_name_user_info);
        displayType = rootView.findViewById(R.id.display_type_user_info);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new UserInfoFragmentPresenter(this, new Injector());

        presenter.onFragmentCreated(userId);
    }

    @Override
    public void updateTextViews(String name, String type) {
        displayName.setText(name);
        displayType.setText(type);
    }
}
