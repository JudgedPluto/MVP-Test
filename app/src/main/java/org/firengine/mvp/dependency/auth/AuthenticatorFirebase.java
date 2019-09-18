package org.firengine.mvp.dependency.auth;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.firengine.mvp.dependency.Callback;

public class AuthenticatorFirebase implements Authenticator {
    private FirebaseAuth auth;

    public AuthenticatorFirebase(FirebaseAuth auth) {
        this.auth = auth;
    }

    @Override
    public void register(String email, String password, @NonNull final Callback<Void> callback) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = authResult.getUser();
                if (user != null) {
                    callback.onSuccess(null);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure();
            }
        });
    }

    @Override
    public void login(String email, String password, @NonNull final Callback<Void> callback) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                callback.onSuccess(null);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
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
