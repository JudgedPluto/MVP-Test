package org.firengine.mvp.dependency.auth;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.firengine.mvp.dependency.Callback;

public class FirebaseAuthenticator implements Authenticator {
    private FirebaseAuth auth;

    public FirebaseAuthenticator(FirebaseAuth auth) {
        this.auth = auth;
    }

    @Override
    public void login(String email, String password, @NonNull final Callback<Void> callback) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess(null);
                    return;
                }
                callback.onFailure();
            }
        });
    }

    @Override
    public void logout(@NonNull Callback<Void> callback) {
        auth.signOut();
        callback.onSuccess(null);
    }

    @Override
    public void getCurrentUser(@NonNull Callback<String> callback) {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            callback.onSuccess(user.getUid());
            return;
        }
        callback.onFailure();
    }
}
