package co.org.ceindetec.derumba.modules.playlist.events;

import co.org.ceindetec.derumba.entities.Song;

/**
 * Created by Ceindetec02 on 19/07/2016.
 */
public class SongEvent {

    public final static int onSongAdded = 0;
    public final static int onSongChanged = 1;
    public final static int onSongRemoved = 2;

    private Song infoSong;
    private int eventType;
    private String errorMessage;

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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
