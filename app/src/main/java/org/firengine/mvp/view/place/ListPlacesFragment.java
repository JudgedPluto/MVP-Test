package org.firengine.mvp.view.place;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.place.ListPlacesFragmentContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.place.ListPlacesFragmentPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListPlacesFragment extends Fragment implements ListPlacesFragmentContract.View {
    private ListPlacesFragmentContract.Presenter presenter;

    private RecyclerView placeList;
    private PlaceListAdapter adapter;

    private String userId;

    public ListPlacesFragment(String userId) {
        this.userId = userId;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_places, container, false);
        placeList = rootView.findViewById(R.id.place_list);
        placeList.setLayoutManager(new LinearLayoutManager(getContext()));
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListPlacesFragmentPresenter(this, new Injector());

        presenter.onFragmentCreated(userId);
    }

    @Override
    public void setupAdapter() {
        adapter = new PlaceListAdapter();
        adapter.setListener(new PlaceItemClickListener() {
            @Override
            public void onItemClick(String id) {
                presenter.onListItemClicked(id);
            }
        });
        placeList.setAdapter(adapter);
    }

    @Override
    public void updateAdapter(List<Map<String, Object>> data) {
        adapter.updateList(data);
    }

    @Override
    public void startPlaceInfoActivity(String id) {
        Intent intent = new Intent(getContext(), PlaceInfoActivity.class);
        intent.putExtra("user_id", userId);
        intent.putExtra("place_id", id);
        startActivity(intent);
    }

    private static class PlaceListAdapter extends RecyclerView.Adapter<PlaceListViewHolder> {
        private List<Map<String, Object>> list = new ArrayList<>();

        private PlaceItemClickListener listener;

        void setListener(PlaceItemClickListener listener) {
            this.listener = listener;
        }

        void updateList(List<Map<String, Object>> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public PlaceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return PlaceListViewHolder.createViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PlaceListViewHolder holder, int position) {
            final String id = list.get(position).get("id").toString();
            holder.placeField.setText(list.get(position).get("place_name").toString());
            holder.priceField.setText(list.get(position).get("place_price").toString());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(id);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private static class PlaceListViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView placeField;
        TextView priceField;

        PlaceListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            placeField = itemView.findViewById(R.id.place_name);
            priceField = itemView.findViewById(R.id.place_price);
        }

        static PlaceListViewHolder createViewHolder(@NonNull ViewGroup parent) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_place_item, parent, false);
            return new PlaceListViewHolder(itemView);
        }
    }

    private interface PlaceItemClickListener {
        void onItemClick(String id);
    }
}