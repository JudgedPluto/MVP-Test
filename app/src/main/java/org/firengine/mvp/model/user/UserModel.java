package org.firengine.mvp.model.user;

import org.firengine.mvp.model.Model;

public class UserModel extends Model {
    public UserModel() {
        tableName = "User";
        tableColumns = new String[] {
                "username",
                "password"
        };
    }
}
