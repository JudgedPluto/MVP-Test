package org.firengine.mvp.dependency.database;

import java.util.Map;

public interface Database {
    void all();

    void find(String id);

    void create(Map<String, Object> data);

    void update(String id, Map<String, Object> data);

    void delete(String id);
}
