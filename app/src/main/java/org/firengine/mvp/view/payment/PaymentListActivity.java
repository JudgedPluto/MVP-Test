package org.firengine.mvp.view.payment;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.firengine.mvp.R;
import org.firengine.mvp.dependency.Injector;

public class PaymentListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_list);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new PaymentListFragment("student_id", "student_id_2")).commit();
    }
}
