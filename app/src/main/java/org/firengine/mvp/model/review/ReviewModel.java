package org.firengine.mvp.model.review;

import org.firengine.mvp.model.Model;

public class ReviewModel extends Model {
    public ReviewModel() {
        tableName = "Review";
        tableColumns = new String[] {
                "user_id",
                "place_id",
                "rating",
                "message"
        };
    }
}
