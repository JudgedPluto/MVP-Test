package org.firengine.mvp.dependency.auth;

import androidx.annotation.NonNull;

import org.firengine.mvp.dependency.Callback;

public interface Authenticator {
    void register(String username, String password, @NonNull Callback<Void> callback);

    void login(String username, String password, @NonNull Callback<Void> callback);

    void logout(@NonNull Callback<Void> callback);

    void getCurrentUser(@NonNull Callback<String> callback);
}
