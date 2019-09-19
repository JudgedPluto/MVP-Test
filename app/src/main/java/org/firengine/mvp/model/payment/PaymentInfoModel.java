package org.firengine.mvp.model.payment;

import org.firengine.mvp.model.Model;

public class PaymentInfoModel extends Model {
    public PaymentInfoModel() {
        tableName = "Payment_Info";
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
