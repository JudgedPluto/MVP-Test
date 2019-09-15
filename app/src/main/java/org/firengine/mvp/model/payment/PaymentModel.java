package org.firengine.mvp.model.payment;

import org.firengine.mvp.model.Model;

public class PaymentModel extends Model{

    public PaymentModel() {
        tableName = "Payment";
        tableColumns = new String[] {
                "place_id",
                "user_ id",
                "amount",
                "payment_type",
                "payment_method"
        };
    }
}
