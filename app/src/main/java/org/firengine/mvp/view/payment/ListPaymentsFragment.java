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
import org.firengine.mvp.contract.payment.ListPaymentsFragmentContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.payment.ListPaymentsFragmentPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ListPaymentsFragment extends Fragment implements ListPaymentsFragmentContract.View {
    private ListPaymentsFragmentContract.Presenter presenter;

    private RecyclerView paymentList;
    private PaymentListAdapter adapter;

    private String userId;

    public ListPaymentsFragment(String userId) {
        this.userId = userId;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_payments, container, false);
        paymentList = rootView.findViewById(R.id.payment_list);
        paymentList.setLayoutManager(new LinearLayoutManager(getContext()));
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListPaymentsFragmentPresenter(this, new Injector());

        presenter.onFragmentCreated(userId);
    }

    @Override
    public void setupAdapter(String userType) {
        adapter = new PaymentListAdapter(userType);
        adapter.setListener(new PaymentItemClickListener() {
            @Override
            public void onItemClick(String id) {
                presenter.onListItemClicked(id);
            }
        });
        paymentList.setAdapter(adapter);
    }

    @Override
    public void updateAdapter(List<Map<String, Object>> data) {
        adapter.updateList(data);
    }

    @Override
    public void startPaymentInfoActivity(String id) {
        Intent intent = new Intent(getContext(), PaymentInfoActivity.class);
        intent.putExtra("payment_id", id);
        startActivity(intent);
    }

    private static class PaymentListAdapter extends RecyclerView.Adapter<PaymentListViewHolder> {
        private List<Map<String, Object>> list = new ArrayList<>();

        private PaymentItemClickListener listener;

        private String viewType;

        PaymentListAdapter(String viewType) {
            this.viewType = viewType;
        }

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

            return PaymentListViewHolder.createViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(@NonNull PaymentListViewHolder holder, int position) {
            final String id = list.get(position).get("id").toString();
            holder.place.setText(list.get(position).get("place_name").toString());
            holder.amount.setText(list.get(position).get("payment_amount").toString());
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

        @Override
        public int getItemViewType(int position) {
            switch (viewType) {
                case "Landlord":
                    return 0;
                case "Student":
                    return 1;
            }
            return super.getItemViewType(position);
        }
    }

    private static class PaymentListViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView place;
        TextView amount;

        PaymentListViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            this.itemView = itemView;
            place = itemView.findViewById(R.id.payment_place_name);
            amount = itemView.findViewById(R.id.payment_amount);
            TextView placeField = itemView.findViewById(R.id.payment_place_name_field);
            TextView amountField = itemView.findViewById(R.id.payment_amount_field);
            switch (viewType) {
                case 0:
                    placeField.setText(R.string.payment_receipt);
                    amountField.setText(R.string.amount_received);
                    break;
                case 1:
                    placeField.setText(R.string.payment_invoice);
                    amountField.setText(R.string.amount_paid);
                    break;
            }
        }

        static PaymentListViewHolder createViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_payment_item, parent, false);
            return new PaymentListViewHolder(itemView, viewType);
        }
    }

    private interface PaymentItemClickListener {
        void onItemClick(String id);
    }
}
