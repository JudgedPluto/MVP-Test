package org.firengine.mvp.dependency.database.callback;

import java.util.List;
import java.util.Map;

public abstract class VoidCallback implements Callback {
    @Override
    public void dataCallback(Map<String, Object> data) {

    }

    @Override
    public void listCallback(List<Map<String, Object>> list) {

    }

    @Override
    public void voidCallback() {
        this.callback();
    }

    public abstract void callback();
}
