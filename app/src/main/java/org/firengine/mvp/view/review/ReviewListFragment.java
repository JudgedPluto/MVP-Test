package org.firengine.mvp.view.review;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.review.ReviewListFragmentContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.review.ReviewListFragmentPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReviewListFragment extends Fragment implements ReviewListFragmentContract.View {
    private ReviewListFragmentContract.Presenter presenter;

    private ReviewListAdapter adapter;
    private String filterColumn;
    private String filterValue;

    public ReviewListFragment(String filterColumn, String filterValue) {
        this.filterColumn = filterColumn;
        this.filterValue = filterValue;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review_list, container, false);
        RecyclerView reviewList = rootView.findViewById(R.id.review_list);
        reviewList.setLayoutManager(new LinearLayoutManager(getContext()));
        reviewList.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ReviewListFragmentPresenter(this, new Injector());

        adapter = new ReviewListAdapter();

        presenter.onActivityCreated();
    }

    @Override
    public void updateAdapter(List<Map<String, Object>> data) {
        adapter.updateList(data);
    }

    private static class ReviewListAdapter extends RecyclerView.Adapter<ReviewListViewHolder> {
        private List<Map<String, Object>> list = new ArrayList<>();

        void updateList(List<Map<String, Object>> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ReviewListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return ReviewListViewHolder.createViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ReviewListViewHolder holder, int position) {
            holder.placeField.setText(list.get(position).get("place_name").toString());
            holder.userField.setText(list.get(position).get("payment_amount").toString());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private static class ReviewListViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView placeField;
        TextView userField;

        ReviewListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            placeField = itemView.findViewById(R.id.review_place_field);
            userField = itemView.findViewById(R.id.review_user_field);
        }

        static ReviewListViewHolder createViewHolder(@NonNull ViewGroup parent) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_review_list,parent, false);
            return new ReviewListViewHolder(itemView);
        }
    }
    private interface ReviewClickListener {
        void onItemClick(String id);
    }
}


