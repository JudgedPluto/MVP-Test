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

    private TextView placeName;
    private TextView studentName;
    private TextView landlordName;
    private TextView paymentAmount;
    private TextView paymentMethod;
    private TextView paymentDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        presenter = new PaymentInfoActivityPresenter(this, new Injector());

        placeName = findViewById(R.id.place_name_payment_info);
        studentName = findViewById(R.id.student_name_payment_info);
        landlordName = findViewById(R.id.landlord_name_payment_info);
        paymentAmount = findViewById(R.id.payment_amount_info);
        paymentMethod = findViewById(R.id.payment_method_info);
        paymentDescription = findViewById(R.id.payment_description_info);

        Intent intent = getIntent();
        String id = intent.getStringExtra("payment_id");

        presenter.onActivityCreated(id);
    }

    @Override
    public void updateTextViews(Object placeName, Object studentName, Object landlordName, Object paymentAmount, Object paymentMethod, Object paymentDescription) {
        this.placeName.setText(placeName.toString());
        this.studentName.setText(studentName.toString());
        this.landlordName.setText(landlordName.toString());
        this.paymentAmount.setText(paymentAmount.toString());
        this.paymentMethod.setText(paymentMethod.toString());
        this.paymentDescription.setText(paymentDescription.toString());
    }

    @Override
    public void startEditPaymentActivity(String id) {
        Intent intent = new Intent(this, EditPaymentActivity.class);
        intent.putExtra("payment_id", id);
        startActivity(intent);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void onEdit(View view) {
        presenter.onEditButtonClicked();
    }
}
