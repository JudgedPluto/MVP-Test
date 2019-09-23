package org.firengine.mvp.view.payment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.payment.AddPaymentActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.payment.AddPaymentActivityPresenter;

public class AddPaymentActivity extends AppCompatActivity implements AddPaymentActivityContract.View {
    private AddPaymentActivityContract.Presenter presenter;

    private Spinner inputType;
    private EditText inputMethod;
    private EditText inputAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        presenter = new AddPaymentActivityPresenter(this, new Injector());

        inputType = findViewById(R.id.payment_type);
        inputMethod = findViewById(R.id.method_payment);
        inputAmount = findViewById(R.id.amount_payment);
    }

    public void onAdd(View view) {
        presenter.onAddButtonClicked(
            "student_id",
            "landlord_id",
            "place_id",
                inputType.getSelectedItem().toString(),
                inputMethod.getText().toString(),
                inputAmount.getText().toString()
        );
    }
}
