package co.org.ceindetec.derumba.modules.playlist;

import co.org.ceindetec.derumba.domain.FirebaseHelper;
import co.org.ceindetec.derumba.entities.PlaylistSong;
import co.org.ceindetec.derumba.lib.EventBus;
import co.org.ceindetec.derumba.lib.GreenRobotEventBus;
import co.org.ceindetec.derumba.modules.playlist.events.PlayListEvent;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by Ceindetec02 on 18/07/2016.
 */
public class PlayListRepositoryImpl implements PlayListRepository {

    private FirebaseHelper firebaseHelper;
    private ChildEventListener playlistEventListener;
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

    public String getCurrentUserName() {
        return firebaseHelper.getAuthUserName();
    }

    @Override
    public void changeConnectionStatus(boolean online) {

    }

    @Override
    public void datosDummy() {

        firebaseHelper.datosDummyEstablecimiento("EST0001", "El Rancho", 4.7109886, -74.072092, true);

        firebaseHelper.datosDummyPlaylist("PL011", "Merengue 01", "EST0001", true);

        firebaseHelper.datosDummyCancion("SNG011", "El Fin del Camino", "Mago de Oz", "Folk Metal", "EST0001", 3600, true);
        firebaseHelper.datosDummyCancion("SNG012", "La Magia de Tus quince años", "Rey Ruiz", "Salsa", "EST0001", 2400, true);
        firebaseHelper.datosDummyCancion("SNG013", "Siempre el mejor", "Pokémon", "Anime", "EST0001", 3600, true);

        firebaseHelper.datosDummyPlayListEstablecimiento("EST001PL011", "EST0001", "PL011");

        firebaseHelper.datosDummyPlayListCancion("PL011", "SNG011", "El Fin del Camino");
        firebaseHelper.datosDummyPlayListCancion("PL011", "SNG012", "La Magia de Tus quince años");
        firebaseHelper.datosDummyPlayListCancion("PL011", "SNG013", "Siempre el mejor");

    }

    @Override
    public void subscribeToPlayListEvents() {

        if (playlistEventListener == null) {
            playlistEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    handlePlayList(dataSnapshot, PlayListEvent.onSongPlaylistAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handlePlayList(dataSnapshot, PlayListEvent.onSongPlaylistChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handlePlayList(dataSnapshot, PlayListEvent.onSongPlaylistRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
        }

        //Cambiar Codigo Song
        firebaseHelper.getReferencePlaylist("PL011").addChildEventListener(playlistEventListener);
    }


    @Override
    public void unsubscribeToPlayListEvents() {
        if (playlistEventListener != null) {
            firebaseHelper.getReferencePlaylist("PL011").removeEventListener(playlistEventListener);
        }
    }

    @Override
    public void destroyListener() {
        playlistEventListener = null;
    }

    @Override
    public void loadPlayList(int idEstablecimiento) {

    }

    private void handlePlayList(DataSnapshot dataSnapshot, int type) {
        if (dataSnapshot.exists()) {
            PlaylistSong playlistSong = dataSnapshot.getValue(PlaylistSong.class);
            post(type, playlistSong);
        }
    }

    private void post(int type, PlaylistSong songPlaylist) {
        PlayListEvent playListEvent = new PlayListEvent();
        playListEvent.setEventType(type);
        playListEvent.setSongPlayList(songPlaylist);
        eventBus.post(playListEvent);
    }

}
