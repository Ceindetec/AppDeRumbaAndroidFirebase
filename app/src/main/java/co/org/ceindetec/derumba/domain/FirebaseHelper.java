package co.org.ceindetec.derumba.domain;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import co.org.ceindetec.derumba.entities.Session;
import co.org.ceindetec.derumba.entities.User;

public class FirebaseHelper {

    private DatabaseReference dataReference;

    private final static String SEPARATOR = "___";
    private final static String SITE_PATH = "establecimiento";
    private final static String PLAYLIST_PATH = "playlist";
    public final static String SONG_PATH = "cancion";
    public final static String DATA_PATH = "data";
    public final static String USERS_PATH = "usuario";
    public final static String NICK_PATH = "nick";

    private static class SingletonHolder {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public FirebaseHelper() {
        this.dataReference = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDataReference() {
        return dataReference;
    }

    public String getAuthUserEmail() {
        FirebaseAuth authData = FirebaseAuth.getInstance();
        String email = null;
        FirebaseUser providerData = authData.getCurrentUser();
        if (providerData != null) {
            email = providerData.getEmail();
        }

        return email;
    }

    public DatabaseReference getUserReference(String email) {
        DatabaseReference userReference = null;
        if (email != null) {
            String emailKey = email.replace(".", "_");
            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return userReference;
    }

    public DatabaseReference getMyUserReference() {
        return getUserReference(getAuthUserEmail());
    }

    public void changeUserConnectionStatus(boolean online) {
        if (getMyUserReference() != null) {
            Map<String, Object> updates = new HashMap<>();
            updates.put("online", online);
            getMyUserReference().updateChildren(updates);
            notifyContactsOfConnectionChange(online);
        }
    }

    public void notifyContactsOfConnectionChange(boolean online) {
        //notifyContactsOfConnectionChange(online, false);
    }

    public void signOff() {
        FirebaseAuth.getInstance().signOut();
        // notifyContactsOfConnectionChange(User.OFFLINE, true);
    }
/*
    private void notifyContactsOfConnectionChange(final boolean online,final boolean signOff) {
        final String myEmail = getAuthUserEmail();
        getMyContactsReference().addListenerForSingleValueEvent(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        String email = child.getKey();
                        DatabaseReference reference = getOneContactReference(email, myEmail);
                        reference.setValue(online);
                    }
                    if(signOff){
                        FirebaseAuth.getInstance().signOut();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
        });
    }
    */
}
