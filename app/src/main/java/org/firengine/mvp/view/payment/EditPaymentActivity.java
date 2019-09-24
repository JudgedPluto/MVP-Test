package org.firengine.mvp.view.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.payment.EditPaymentActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.payment.EditPaymentActivityPresenter;

public class EditPaymentActivity extends AppCompatActivity implements EditPaymentActivityContract.View {
    private EditPaymentActivityContract.Presenter presenter;

    private TextView placeName;
    private TextView studentName;
    private TextView landlordName;
    private EditText paymentAmount;
    private Spinner paymentMethod;
    private Spinner paymentDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_payment);

        presenter = new EditPaymentActivityPresenter(this, new Injector());

        placeName = findViewById(R.id.place_name_payment_edit);
        studentName = findViewById(R.id.student_name_payment_edit);
        landlordName = findViewById(R.id.landlord_name_payment_edit);
        paymentAmount = findViewById(R.id.payment_amount_edit);
        paymentMethod = findViewById(R.id.payment_method_edit);
        paymentDescription = findViewById(R.id.payment_description_edit);

        Intent intent = getIntent();
        String id = intent.getStringExtra("payment_id");

        presenter.onActivityCreated(id);
    }

    @Override
    public void updateFormElements(Object placeName, Object studentName, Object landlordName, Object paymentAmount, Object paymentMethod, Object paymentDescription) {
        this.placeName.setText(placeName.toString());
        this.studentName.setText(studentName.toString());
        this.landlordName.setText(landlordName.toString());
        this.paymentAmount.setText(paymentAmount.toString());
        findIndex(this.paymentMethod, paymentMethod);
        findIndex(this.paymentDescription, paymentDescription);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void onConfirmEdit(View view) {
        presenter.onEditButtonClicked(
                paymentAmount.getText().toString(),
                paymentMethod.getSelectedItem().toString(),
                paymentDescription.getSelectedItem().toString()
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
