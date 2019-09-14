package org.firengine.mvp.dependency.database.callback;

import java.util.List;
import java.util.Map;

public abstract class ListCallback implements Callback {
    @Override
    public void dataCallback(Map<String, Object> data) {

    }

    @Override
    public void listCallback(List<Map<String, Object>> list) {
        this.callback(list);
    }

    @Override
    public void voidCallback() {

    }

    public abstract void callback(List<Map<String, Object>> list);
}
