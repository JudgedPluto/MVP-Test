package org.firengine.mvp.view.payment;

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
import org.firengine.mvp.contract.payment.PaymentListFragmentContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.payment.PaymentListFragmentPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PaymentListFragment extends Fragment implements PaymentListFragmentContract.View {
    private PaymentListFragmentContract.Presenter presenter;

    private PaymentListAdapter adapter;

    public PaymentListFragment() {}

    public static PaymentListFragment newInstance() {
        return new PaymentListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_payment_list, container, false);
        RecyclerView paymentList = rootView.findViewById(R.id.payment_list);
        paymentList.setLayoutManager(new LinearLayoutManager(getContext()));
        paymentList.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new PaymentListFragmentPresenter(this, new Injector());

        adapter = new PaymentListAdapter();

        presenter.onActivityCreated();
    }

    @Override
    public void updateAdapter(List<Map<String, Object>> data) {
        adapter.updateList(data);
    }

    private static class PaymentListAdapter extends RecyclerView.Adapter<PaymentListViewHolder> {

        private List<Map<String, Object>> list = new ArrayList<>();

        void updateList(List<Map<String, Object>> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public PaymentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return PaymentListViewHolder.createViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PaymentListViewHolder holder, int position) {
            holder.placeField.setText(list.get(position).get("place_name").toString());
            holder.amountField.setText(list.get(position).get("payment_amount").toString());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private static class PaymentListViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView placeField;
        TextView amountField;

        PaymentListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            placeField = itemView.findViewById(R.id.payment_place_field);
            amountField = itemView.findViewById(R.id.payment_amount_field);
        }

        static PaymentListViewHolder createViewHolder(@NonNull ViewGroup parent) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_payment_item, parent, false);
            return new PaymentListViewHolder(itemView);
        }
    }
}
