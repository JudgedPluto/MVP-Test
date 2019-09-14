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

import org.firengine.mvp.dependency.database.callback.Callback;
import org.firengine.mvp.model.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Firebase implements Database {
    private Model model;

    private FirebaseFirestore firestore;
    private Callback callback;

    public Firebase(FirebaseFirestore firestore, Model model) {
        this.firestore = firestore;
        this.model = model;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void all() {
        Task<QuerySnapshot> task = firestore.collection(model.getTableName())
                .orderBy("timestamp", Query.Direction.DESCENDING).get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    List<Map<String, Object>> data = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        Map<String, Object> columnData = new HashMap<>();
                        columnData.put("id", document.getId());
                        for (String column : model.getTableColumns()) {
                            columnData.put(column, document.getString(column));
                        }
                        data.add(columnData);
                    }
                    if (callback != null) {
                        callback.listCallback(data);
                    }
                }
            }
        });
    }

    @Override
    public void find(String id) {
        Task<DocumentSnapshot> task = firestore.collection(model.getTableName()).document(id).get();
        task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    DocumentSnapshot document = task.getResult();
                    Map<String, Object> data = new HashMap<>();
                    data.put("id", document.getId());
                    for (String column : model.getTableColumns()) {
                        data.put(column, document.getString(column));
                    }
                    if (callback != null) {
                        callback.dataCallback(data);
                    }
                }
            }
        });
    }

    @Override
    public void create(Map<String, Object> data) {
        data.put("timestamp", FieldValue.serverTimestamp());
        Task<DocumentReference> task = firestore.collection(model.getTableName()).add(data);
        task.addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    if (callback != null) {
                        callback.voidCallback();
                    }
                }
            }
        });
    }

    @Override
    public void update(String id, Map<String, Object> data) {
        data.put("timestamp", FieldValue.serverTimestamp());
        Task<Void> task = firestore.collection(model.getTableName()).document(id).update(data);
        task.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    if (callback != null) {
                        callback.voidCallback();
                    }
                }
            }
        });
    }

    @Override
    public void delete(String id) {
        Task<Void> task = firestore.collection(model.getTableName()).document(id).delete();
        task.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    if (callback != null) {
                        callback.voidCallback();
                    }
                }
            }
        });
    }
}
