package org.firengine.mvp.model.payment;

import org.firengine.mvp.model.Model;

public class PaymentModel extends Model {
    public PaymentModel() {
        tableName = "Payment";
        tableColumns = new String[] {
                "student_id",
                "landlord_id",
                "place_id",
                "payment_type",
                "payment_method",
                "payment_amount"
        };
    }
}
