package org.firengine.mvp.view.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.payment.AddPaymentActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.payment.AddPaymentActivityPresenter;

public class AddPaymentActivity extends AppCompatActivity implements AddPaymentActivityContract.View {
    private AddPaymentActivityContract.Presenter presenter;

    private TextView placeName;
    private TextView studentName;
    private TextView landlordName;
    private EditText paymentAmount;
    private Spinner paymentMethod;
    private Spinner paymentDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        presenter = new AddPaymentActivityPresenter(this, new Injector());

        placeName = findViewById(R.id.place_name_payment_add);
        studentName = findViewById(R.id.student_name_payment_add);
        landlordName = findViewById(R.id.landlord_name_payment_add);
        paymentAmount = findViewById(R.id.payment_amount_add);
        paymentMethod = findViewById(R.id.payment_method_add);
        paymentDescription = findViewById(R.id.payment_description_add);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("user_id");
        String placeId = intent.getStringExtra("place_id");

        presenter.onActivityCreated(userId, placeId);
    }

    @Override
    public void updateTextViews(String placeName, String studentName, String landlordName) {
        this.placeName.setText(placeName);
        this.studentName.setText(studentName);
        this.landlordName.setText(landlordName);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void onConfirmAdd(View view) {
        presenter.onAddButtonClicked(
                paymentAmount.getText().toString(), paymentMethod.getSelectedItem().toString(), paymentDescription.getSelectedItem().toString()
        );
    }
}
