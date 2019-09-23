package org.firengine.mvp.view.payment;

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
import org.firengine.mvp.contract.payment.PaymentListFragmentContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.payment.PaymentListFragmentPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PaymentListFragment extends Fragment implements PaymentListFragmentContract.View {
    private PaymentListFragmentContract.Presenter presenter;

    private PaymentListAdapter adapter;

    private String filterColumn;
    private String filterValue;

    public PaymentListFragment(String filterColumn, String filterValue) {
        this.filterColumn = filterColumn;
        this.filterValue = filterValue;
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
        adapter.setListener(new PaymentItemClickListener() {
            @Override
            public void onItemClick(String id) {
                presenter.onListItemClicked(id);
            }
        });

        presenter.onActivityCreated(filterColumn, filterValue);
    }

    @Override
    public void updateAdapter(List<Map<String, Object>> data) {
        adapter.updateList(data);
    }

    @Override
    public void startPaymentDetailActivity(String id) {
        Intent intent = new Intent(getContext(), PaymentDetailActivity.class);
        intent.putExtra("payment_id", id);
        startActivity(intent);
    }

    private static class PaymentListAdapter extends RecyclerView.Adapter<PaymentListViewHolder> {
        private List<Map<String, Object>> list = new ArrayList<>();

        private PaymentItemClickListener listener;

        void setListener(PaymentItemClickListener listener) {
            this.listener = listener;
        }

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
            final String id = list.get(position).get("id").toString();
            holder.placeField.setText(list.get(position).get("id").toString());
            holder.amountField.setText(list.get(position).get("payment_amount").toString());
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

    private static class PaymentListViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView placeField;
        TextView amountField;

        PaymentListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            placeField = itemView.findViewById(R.id.payment_id_field);
            amountField = itemView.findViewById(R.id.payment_amount_field);
        }

        static PaymentListViewHolder createViewHolder(@NonNull ViewGroup parent) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_payment_item, parent, false);
            return new PaymentListViewHolder(itemView);
        }
    }

    private interface PaymentItemClickListener {
        void onItemClick(String id);
    }
}
