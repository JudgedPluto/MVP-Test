package org.firengine.mvp.view.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.payment.PaymentDetailActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.payment.PaymentDetailActivityPresenter;

public class PaymentDetailActivity extends AppCompatActivity implements PaymentDetailActivityContract.View {
    private PaymentDetailActivityContract.Presenter presenter;

    private TextView studentId;
    private TextView landlordId;
    private TextView placeId;
    private TextView paymentType;
    private TextView paymentMethod;
    private TextView payemntAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);

        presenter = new PaymentDetailActivityPresenter(this, new Injector());

        studentId = findViewById(R.id.d_student_id);
        landlordId = findViewById(R.id.d_landlord_id);
        placeId = findViewById(R.id.d_place_id);
        paymentType = findViewById(R.id.d_payment_type);
        paymentMethod = findViewById(R.id.d_payment_method);
        payemntAmount = findViewById(R.id.d_payment_amount);

        presenter.onActivityCreated("-Lp38NeFtDNmQcQg84Z8");
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

    public void editPayement(View view){
        Intent intent_1 = new Intent(this, PaymentAddEditActivity.class);
        startActivity(intent_1);
    }

    public void deletePayment(View view){
        Intent intent_2 = new Intent(this, PaymentDeleteActivity.class);
        startActivity(intent_2);
    }
}
