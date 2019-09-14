package org.firengine.mvp.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.user.UserListActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.user.UserListActivityPresenter;
import org.firengine.mvp.view.constants.ViewCodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserListActivity extends AppCompatActivity implements UserListActivityContract.View {
    private UserListActivityContract.Presenter presenter;

    private FrameLayout loadingList;
    private UserAdapter userListAdapter;

    private boolean isPresenterAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        presenter = new UserListActivityPresenter(this, new Injector());

        loadingList = findViewById(R.id.loading_list);
        RecyclerView userList = findViewById(R.id.user_list);

        userListAdapter = new UserAdapter();

        userListAdapter.setUserItemClickListener(new UserItemClickListener() {
            @Override
            public void onItemClick(Map<String, Object> user) {
                if (isPresenterAvailable) {
                    isPresenterAvailable = false;
                    presenter.onListItemClicked(user);
                }
            }
        });

        userList.setLayoutManager(new LinearLayoutManager(this));
        userList.setAdapter(userListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPresenterAvailable = true;
        presenter.onRecyclerViewRefreshed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == ViewCodes.ADD_CODE && resultCode == ViewCodes.ADD_OK) ||
                (requestCode == ViewCodes.MODIFIED_CODE && resultCode == ViewCodes.MODIFIED_OK)) {
            presenter.sendUpdateRequest();
        }
    }

    @Override
    public void updateRecyclerView(List<Map<String, Object>> list) {
        userListAdapter.update(list);
    }

    @Override
    public void showLoadingBar() {
        loadingList.setVisibility(View.VISIBLE);
        loadingList.setClickable(true);
    }

    @Override
    public void hideLoadingBar() {
        loadingList.setVisibility(View.INVISIBLE);
        loadingList.setClickable(false);
    }

    @Override
    public void startUserAddEditActivity() {
        Intent intent = new Intent(this, UserAddEditActivity.class);
        startActivityForResult(intent, ViewCodes.ADD_CODE);
    }

    @Override
    public void startUserDetailActivity(Map<String, Object> user) {
        Intent intent = new Intent(this, UserDetailActivity.class);
        HashMap<String, Object> extra = new HashMap<>(user);
        intent.putExtra("reference_user", extra);
        startActivityForResult(intent, ViewCodes.MODIFIED_CODE);
    }

    public void onAdd(View view) {
        if (isPresenterAvailable) {
            isPresenterAvailable = false;
            presenter.onAddButtonClicked();
        }
    }

    static class UserAdapter extends RecyclerView.Adapter<UserListHolder> {
        private List<Map<String, Object>> userList = new ArrayList<>();

        private UserItemClickListener itemClickListener;

        void update(List<Map<String, Object>> userList) {
            this.userList = userList;
            notifyDataSetChanged();
        }

        void setUserItemClickListener(UserItemClickListener listener) {
            this.itemClickListener = listener;
        }

        @NonNull
        @Override
        public UserListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return UserListHolder.createViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull UserListHolder holder, int position) {
            final Map<String, Object> user = userList.get(position);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(user);
                }
            });
            holder.username.setText(user.get("username").toString());
            holder.password.setText(user.get("password").toString());
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }
    }

    static class UserListHolder extends RecyclerView.ViewHolder {
        View view;
        TextView username;
        TextView password;

        private UserListHolder(View itemView) {
            super(itemView);
            view = itemView;
            username = itemView.findViewById(R.id.username);
            password = itemView.findViewById(R.id.password);
        }

        static UserListHolder createViewHolder(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.holder_user, parent, false);
            return new UserListHolder(itemView);
        }
    }

    interface UserItemClickListener {
        void onItemClick(Map<String, Object> user);
    }
}
