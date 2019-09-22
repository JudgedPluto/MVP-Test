package org.firengine.mvp.dependency.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.model.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseFirebase implements Database {
    private Model model;

    private DatabaseReference database;

    public DatabaseFirebase(FirebaseDatabase database, Model model) {
        this.database = database.getReference();
        this.model = model;
    }

    @Override
    public void all(@NonNull final Callback<List<Map<String, Object>>> callback) {
        database.child(model.getTableName())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<Map<String, Object>> list = new ArrayList<>();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Map<String, Object> columnData = new HashMap<>();
                            columnData.put("id", data.getKey());
                            for (String column : model.getTableColumns()) {
                                columnData.put(column, data.child(column).getValue());
                            }
                            list.add(columnData);
                        }
                        callback.onSuccess(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        callback.onFailure();
                    }
        });
    }

    @Override
    public void find(String id, @NonNull final Callback<Map<String, Object>> callback) {
        database.child(model.getTableName())
                .child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", dataSnapshot.getKey());
                        for (String column : model.getTableColumns()) {
                            map.put(column, dataSnapshot.child(column).getValue());
                        }
                        callback.onSuccess(map);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        callback.onFailure();
                    }
        });
    }

    @Override
    public void where(String column, String value, @NonNull final Callback<List<Map<String, Object>>> callback) {
        database.child(model.getTableName())
                .orderByChild(column).equalTo(value).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.d("onDataChange", "onDataChange");
                        List<Map<String, Object>> list = new ArrayList<>();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Map<String, Object> columnData = new HashMap<>();
                            columnData.put("id", data.getKey());
                            for (String column : model.getTableColumns()) {
                                columnData.put(column, data.child(column).getValue());
                            }
                            list.add(columnData);
                        }
                        callback.onSuccess(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        callback.onFailure();
                    }
        });
    }

    @Override
    public void create(Map<String, Object> data, @NonNull final Callback<Void> callback) {
        database.child(model.getTableName())
                .push().setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callback.onSuccess(null);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure();
            }
        });
    }

    @Override
    public void update(String id, Map<String, Object> data, @NonNull final Callback<Void> callback) {
        database.child(model.getTableName())
                .child(id).updateChildren(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callback.onSuccess(null);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void delete(String id, @NonNull final Callback<Void> callback) {
        database.child(model.getTableName())
                .child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callback.onSuccess(null);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure();
            }
        });
    }
}
