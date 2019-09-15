package org.firengine.mvp.dependency.database;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.firengine.mvp.dependency.Callback;
import org.firengine.mvp.model.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseDatabase implements Database {
    private Model model;

    private FirebaseFirestore firestore;

    public FirebaseDatabase(FirebaseFirestore firestore, Model model) {
        this.firestore = firestore;
        this.model = model;
    }

    @Override
    public void all(@NonNull final Callback<List<Map<String, Object>>> callback) {
        Task<QuerySnapshot> task = firestore.collection(model.getTableName())
                .orderBy("timestamp", Query.Direction.DESCENDING).get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        Map<String, Object> columnData = new HashMap<>();
                        columnData.put("id", document.getId());
                        for (String column : model.getTableColumns()) {
                            columnData.put(column, document.getString(column));
                        }
                        list.add(columnData);
                    }
                    callback.onSuccess(list);
                    return;
                }
                callback.onFailure();
            }
        });
    }

    @Override
    public void find(String id, @NonNull final Callback<Map<String, Object>> callback) {
        Task<DocumentSnapshot> task = firestore.collection(model.getTableName()).document(id).get();
        task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    Map<String, Object> map = new HashMap<>();
                    DocumentSnapshot document = task.getResult();
                    map.put("id", document.getId());
                    for (String column : model.getTableColumns()) {
                        map.put(column, document.getString(column));
                    }
                    callback.onSuccess(map);
                    return;
                }
                callback.onFailure();
            }
        });
    }

    @Override
    public void findWhere(String column, String value, @NonNull final Callback<List<Map<String, Object>>> callback) {
        Task<QuerySnapshot> task = firestore.collection(model.getTableName()).whereEqualTo(column, value).get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        Map<String, Object> columnData = new HashMap<>();
                        columnData.put("id", document.getId());
                        for (String column : model.getTableColumns()) {
                            columnData.put(column, document.getString(column));
                        }
                        list.add(columnData);
                    }
                    callback.onSuccess(list);
                    return;
                }
                callback.onFailure();
            }
        });
    }

    @Override
    public void create(Map<String, Object> data, @NonNull final Callback<Void> callback) {
        data.put("timestamp", FieldValue.serverTimestamp());
        Task<DocumentReference> task = firestore.collection(model.getTableName()).add(data);
        task.addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess(null);
                    return;
                }
                callback.onFailure();
            }
        });
    }

    @Override
    public void update(String id, Map<String, Object> data, @NonNull final Callback<Void> callback) {
        data.put("timestamp", FieldValue.serverTimestamp());
        Task<Void> task = firestore.collection(model.getTableName()).document(id).update(data);
        task.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess(null);
                    return;
                }
                callback.onFailure();
            }
        });
    }

    @Override
    public void delete(String id, @NonNull final Callback<Void> callback) {
        Task<Void> task = firestore.collection(model.getTableName()).document(id).delete();
        task.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess(null);
                    return;
                }
                callback.onFailure();
            }
        });
    }
}
