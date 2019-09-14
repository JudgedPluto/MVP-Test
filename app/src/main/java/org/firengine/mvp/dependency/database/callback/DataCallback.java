package org.firengine.mvp.dependency.database.callback;

import java.util.List;
import java.util.Map;

public abstract class DataCallback implements Callback {
    @Override
    public void dataCallback(Map<String, Object> data) {
        this.callback(data);
    }

    @Override
    public void listCallback(List<Map<String, Object>> list) {

    }

    @Override
    public void voidCallback() {

    }

    public abstract void callback(Map<String, Object> data);
}
