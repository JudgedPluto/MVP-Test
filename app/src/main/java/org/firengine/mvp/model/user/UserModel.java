package org.firengine.mvp.model.user;

import org.firengine.mvp.model.Model;

public class UserModel extends Model {
    public UserModel() {
        tableName = "User";
        tableColumns = new String[] {
                "user_uid",
                "user_phone",
                "user_type"
        };
    }
}
