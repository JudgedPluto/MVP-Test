package org.firengine.mvp.model.payment;

import org.firengine.mvp.model.Model;

public class PaymentModel extends Model {
    public PaymentModel() {
        tableName = "Payment";
        tableColumns = new String[] {
                "place_id",
                "place_name",
                "student_id",
                "student_name",
                "landlord_id",
                "landlord_name",
                "payment_amount",
                "payment_method",
                "payment_description"
        };
    }
}
