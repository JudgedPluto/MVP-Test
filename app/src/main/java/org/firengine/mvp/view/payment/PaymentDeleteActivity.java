package org.firengine.mvp.view.payment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.firengine.mvp.R;
import org.firengine.mvp.contract.payment.PaymentDeleteActivityContract;
import org.firengine.mvp.dependency.Injector;
import org.firengine.mvp.presenter.payment.PaymentDeleteActivityPresenter;

public class PaymentDeleteActivity extends AppCompatActivity implements PaymentDeleteActivityContract.View {
    private PaymentDeleteActivityContract.Presenter presenter;

    private TextView debugText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_delete);

        presenter = new PaymentDeleteActivityPresenter(this, new Injector());

        debugText = findViewById(R.id.debugText);

        presenter.checkValue();
    }

    @Override
    public void updateView(String message) {
        debugText.setText(message);
    }
}
