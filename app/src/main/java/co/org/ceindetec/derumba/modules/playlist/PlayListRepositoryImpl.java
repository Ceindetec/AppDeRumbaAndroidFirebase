package co.org.ceindetec.derumba.modules.playlist;

import co.org.ceindetec.derumba.domain.FirebaseHelper;
import co.org.ceindetec.derumba.entities.User;
import co.org.ceindetec.derumba.lib.EventBus;
import co.org.ceindetec.derumba.lib.GreenRobotEventBus;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Ceindetec02 on 18/07/2016.
 */
public class PlayListRepositoryImpl implements PlayListRepository {

    private FirebaseHelper firebaseHelper;
    private ChildEventListener childEventListener;
    private EventBus eventBus;

    public PlayListRepositoryImpl() {
        firebaseHelper = FirebaseHelper.getInstance();
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void signOff() {
        firebaseHelper.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return firebaseHelper.getAuthUserEmail();
    }

    @Override
    public void changeConnectionStatus(boolean online) {

    }

    @Override
    public void subscribeToPlayListEvents() {
        /*
        if(contactEventListener == null){
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContactAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContactChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot, ContactListEvent.onContactRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
        }
        helper.getMyContactsReference().addChildEventListener(contactEventListener);
         */
    }

    @Override
    public void unsubscribeToPlayListEvents() {
        if (childEventListener != null) {
            // firebaseHelper.getMyContactsReference().removeEventListener(childEventListener);
        }
    }

    @Override
    public void destroyListener() {
        childEventListener = null;
    }

    @Override
    public void loadPlayList(int idEstablecimiento) {

    }
}
