package org.firengine.mvp.dependency;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.firengine.mvp.dependency.auth.Authenticator;
import org.firengine.mvp.dependency.auth.FirebaseAuthenticator;
import org.firengine.mvp.dependency.database.Database;
import org.firengine.mvp.dependency.database.FirebaseDatabase;
import org.firengine.mvp.model.Model;

public class Injector {
    public Database getDatabaseInstance(Model model) {
        return new FirebaseDatabase(FirebaseFirestore.getInstance(), model);
    }

    public Authenticator getAuthenticationInstance() {
        return new FirebaseAuthenticator(FirebaseAuth.getInstance());
    }
}
