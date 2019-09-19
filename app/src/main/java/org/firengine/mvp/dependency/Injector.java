package org.firengine.mvp.dependency;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.firengine.mvp.dependency.auth.Authenticator;
import org.firengine.mvp.dependency.auth.AuthenticatorFirebase;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.dependency.database.DatabaseFirebase;
import org.firengine.mvp.model.Model;

public class Injector {
    public Database getDatabaseInstance(Model model) {
        return new DatabaseFirebase(FirebaseDatabase.getInstance(), model);
    }

    public Authenticator getAuthenticationInstance() {
        return new AuthenticatorFirebase(FirebaseAuth.getInstance());
    }
}
