package org.firengine.mvp.model.payment;

import org.firengine.mvp.model.Model;

public class PaymentListModel extends Model{

    public PaymentListModel() {
        tableName = "Payment_List";
        tableColumns = new String[] {
                "payment_info_id",
                "landlord_id",
                "place_name",
                "payment_amount"
        };
    }
}
