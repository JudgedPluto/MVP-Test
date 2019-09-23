package org.firengine.mvp.view.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.payment.PaymentInfoActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.payment.PaymentInfoActivityPresenter;

public class PaymentInfoActivity extends AppCompatActivity implements PaymentInfoActivityContract.View {
    private PaymentInfoActivityContract.Presenter presenter;

    private TextView studentId;
    private TextView landlordId;
    private TextView placeId;
    private TextView paymentType;
    private TextView paymentMethod;
    private TextView payemntAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        presenter = new PaymentInfoActivityPresenter(this, new Injector());

        studentId = findViewById(R.id.student_name_payment_detail);
        landlordId = findViewById(R.id.landlord_name_payment_detail);
        placeId = findViewById(R.id.place_name_payment_detail);
        paymentType = findViewById(R.id.type_payment_detail);
        paymentMethod = findViewById(R.id.method_payment_detail);
        payemntAmount = findViewById(R.id.amount_payment_detail);

        Intent intent = getIntent();
        String id = intent.getStringExtra("payment_id");

        presenter.onActivityCreated(id);
    }

    @Override
    public void updateTextViews(Object student_id, Object landlord_id, Object place_id, Object payment_type, Object payment_method, Object payment_amount) {
        studentId.setText(student_id.toString());
        landlordId.setText(landlord_id.toString());
        placeId.setText(place_id.toString());
        paymentType.setText(payment_type.toString());
        paymentMethod.setText(payment_method.toString());
        payemntAmount.setText(payment_amount.toString());
    }

    @Override
    public void startPaymentEditActivity(String id) {
        Intent intent = new Intent(this, EditPaymentActivity.class);
        intent.putExtra("payment_id", id);
        startActivity(intent);
    }

    public void onEdit(View view) {
        presenter.onEditButtonClicked();
    }
}
