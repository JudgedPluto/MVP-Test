package org.firengine.mvp.dependency;

import com.google.firebase.firestore.FirebaseFirestore;

import org.firengine.mvp.dependency.database.Firebase;
import org.firengine.mvp.model.Model;

public class Injector {
    public Firebase getFirebaseInstance(Model model) {
        return new Firebase(FirebaseFirestore.getInstance(), model);
    }
}
