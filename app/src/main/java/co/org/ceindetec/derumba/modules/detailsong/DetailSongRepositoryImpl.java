package co.org.ceindetec.derumba.modules.detailsong;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import co.org.ceindetec.derumba.domain.FirebaseHelper;
import co.org.ceindetec.derumba.entities.PlaylistSong;
import co.org.ceindetec.derumba.entities.Song;
import co.org.ceindetec.derumba.lib.EventBus;
import co.org.ceindetec.derumba.lib.GreenRobotEventBus;
import co.org.ceindetec.derumba.modules.detailsong.events.DetailSongEvent;

/**
 * Created by Ceindetec02 on 21/07/2016.
 */
public class DetailSongRepositoryImpl implements DetailSongRepository {

    private final FirebaseAuth firebaseAuth;
    private FirebaseHelper firebaseHelper;
    private EventBus eventBus;

    /**
     * Declaracion del constructor
     */
    public DetailSongRepositoryImpl() {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.firebaseHelper = FirebaseHelper.getInstance();
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    /**
     * Metodo implementado de la interface DetailSongRepository para la obtencion de la informacion de la cancion
     *
     * @param codeSong
     */
    @Override
    public void getInfoSong(String codeSong) {
        firebaseHelper.getReferenceSong(codeSong).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Song infoSong = dataSnapshot.getValue(Song.class);
                if (infoSong == null) {
                    post(null, DetailSongEvent.onGetInfoSongError);
                } else {
                    post(infoSong, DetailSongEvent.onGetInfoSongSuccess);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * @param codigoPlaylist
     * @param codigoCancion
     */
    @Override
    public void verifyLikeSong(String codigoPlaylist, String codigoCancion) {
        firebaseHelper.getReferencePlaylistSong(codigoPlaylist, codigoCancion).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PlaylistSong playlistSong = dataSnapshot.getValue(PlaylistSong.class);

                String uid = firebaseAuth.getCurrentUser().getUid();

                if (playlistSong.likes.containsKey(uid)) {
                    post(DetailSongEvent.onUserLike);
                } else {
                    post(DetailSongEvent.onUserNoLike);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                post(null, DetailSongEvent.onGetInfoSongError);
            }
        });

    }

    /**
     * @param codigoCancion
     */
    @Override
    public void rankSong(String codigoPlaylist, String codigoCancion) {
        firebaseHelper.getReferencePlaylistSong(codigoPlaylist, codigoCancion).runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                PlaylistSong playlistSong = mutableData.getValue(PlaylistSong.class);

                String uid = firebaseAuth.getCurrentUser().getUid();

                if (playlistSong == null) {
                    return Transaction.success(mutableData);
                }

                if (playlistSong.likes.containsKey(uid)) {
                    playlistSong.likes.remove(uid);
                } else {
                    playlistSong.likes.put(uid, true);
                }

                // Set value and report transaction success
                mutableData.setValue(playlistSong);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                if (databaseError == null) {
                    post(DetailSongEvent.onRankSongSuccess);
                } else {
                    post(DetailSongEvent.onRankSongError);
                }
            }

        });
    }

    /**
     * @param type
     */
    private void post(int type) {
        DetailSongEvent detailSongEvent = new DetailSongEvent();
        detailSongEvent.setEventType(type);
        eventBus.post(detailSongEvent);
    }

    /**
     * @param infoSong
     * @param type
     */
    private void post(Song infoSong, int type) {
        DetailSongEvent detailSongEvent = new DetailSongEvent();
        detailSongEvent.setEventType(type);
        detailSongEvent.setInfoSong(infoSong);
        eventBus.post(detailSongEvent);
    }

}
