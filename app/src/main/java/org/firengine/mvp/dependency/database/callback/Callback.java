package org.firengine.mvp.dependency.database.callback;

import java.util.List;
import java.util.Map;

public interface Callback {
    void dataCallback(Map<String, Object> data);

    void listCallback(List<Map<String, Object>> list);

    void voidCallback();
}
