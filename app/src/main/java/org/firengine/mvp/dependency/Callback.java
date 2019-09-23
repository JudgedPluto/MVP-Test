package org.firengine.mvp.dependency;

public interface Callback<T> {
    void onSuccess(T data);
    void onFailure();
}
