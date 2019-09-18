package org.firengine.mvp.dependency.database;

import androidx.annotation.NonNull;

import org.firengine.mvp.dependency.Callback;

import java.util.List;
import java.util.Map;

public interface Database {
    void all(@NonNull Callback<List<Map<String, Object>>> callback);

    void find(String id, @NonNull Callback<Map<String, Object>> callback);

    void where(String column, String value, @NonNull Callback<List<Map<String, Object>>> callback);

    void create(Map<String, Object> data, @NonNull Callback<Void> callback);

    void update(String id, Map<String, Object> data,@NonNull Callback<Void> callback);

    void delete(String id, @NonNull Callback<Void> callback);
}
