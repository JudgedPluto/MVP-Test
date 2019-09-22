package org.firengine.mvp.model.place;

import org.firengine.mvp.model.Model;

public class PlaceModel extends Model {
    public PlaceModel(){
        tableName = "Place";
        tableColumns = new String[] {

                "b_name",
                "b_address",
                "b_Specialities",
                "b_rules",
                "b_roomTypes",
                "b_map",
                "b_roomPrice",
                "b_roomAvail",
                "b_photos",
                "b_phoneNum"
        };
    }



}

