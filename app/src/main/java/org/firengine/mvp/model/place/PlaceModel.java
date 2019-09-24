package org.firengine.mvp.model.place;

import org.firengine.mvp.model.Model;

public class PlaceModel extends Model {
    public PlaceModel(){
        tableName = "Place";
        tableColumns = new String[] {
                "landlord_id",
                "landlord_name",
                "place_photo",
                "place_name",
                "place_price",
                "place_available",
                "place_address",
                "place_phone",
                "place_features",
                "place_map"
        };
    }



}

