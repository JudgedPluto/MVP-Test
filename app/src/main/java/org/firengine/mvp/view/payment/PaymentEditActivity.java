package org.firengine.mvp.view.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.payment.PaymentEditActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.payment.PaymentEditActivityPresenter;

public class PaymentEditActivity extends AppCompatActivity implements PaymentEditActivityContract.View {
    private PaymentEditActivityContract.Presenter presenter;

    private TextView studentId;
    private TextView landlordId;
    private TextView placeId;
    private Spinner paymentType;
    private TextView paymentMethod;
    private TextView payemntAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_edit);

        presenter = new PaymentEditActivityPresenter(this, new Injector());

        studentId = findViewById(R.id.a_s_id);
        landlordId = findViewById(R.id.a_i_id);
        placeId = findViewById(R.id.a_p_id);
        paymentType = findViewById(R.id.payment_type);
        paymentMethod = findViewById(R.id.method_payment);
        payemntAmount = findViewById(R.id.amount_payment);

        Intent intent = getIntent();
        String id = intent.getStringExtra("payment_id");

        presenter.onActivityCreated(id);

    }

    @Override
    public void updateEditTexts(Object student_id, Object landlord_id, Object place_id, Object payment_type, Object payment_method, Object payment_amount) {
        studentId.setText(student_id.toString());
        landlordId.setText(landlord_id.toString());
        placeId.setText(place_id.toString());
        findIndex(paymentType, payment_type);
        paymentMethod.setText(payment_method.toString());
        payemntAmount.setText(payment_amount.toString());
    }

    public void onSave(View view) {
        presenter.onEditButtonClicked(
                "student_id",
                "landlord_id",
                "place_id",
                paymentType.getSelectedItem().toString(),
                paymentMethod.getText().toString(),
                payemntAmount.getText().toString()
        );
    }

    private void findIndex(Spinner spinner, Object data) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(data)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}
