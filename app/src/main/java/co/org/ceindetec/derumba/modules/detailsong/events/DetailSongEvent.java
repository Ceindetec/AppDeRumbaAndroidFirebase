package co.org.ceindetec.derumba.modules.detailsong.events;

import co.org.ceindetec.derumba.entities.Song;

/**
 * Created by Ceindetec02 on 21/07/2016.
 */
public class DetailSongEvent {

    public final static int onGetInfoSongSuccess = 0;
    public final static int onGetInfoSongError = 1;

    public final static int onRankSongSuccess = 2;
    public final static int onRankSongError = 3;

    public final static int onUserLike = 4;
    public final static int onUserNoLike = 5;

    private Song infoSong;
    private int eventType;

    public Song getInfoSong() {
        return infoSong;
    }

    public void setInfoSong(Song infoSong) {
        this.infoSong = infoSong;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
