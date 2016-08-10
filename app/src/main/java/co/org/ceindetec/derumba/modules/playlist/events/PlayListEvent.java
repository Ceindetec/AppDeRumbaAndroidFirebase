package co.org.ceindetec.derumba.modules.playlist.events;

import co.org.ceindetec.derumba.entities.PlaylistSong;

/**
 * Created by Ceindetec02 on 19/07/2016.
 */
public class PlayListEvent {

    public final static int onSongPlaylistAdded = 0;
    public final static int onSongPlaylistChanged = 1;
    public final static int onSongPlaylistRemoved = 2;

    private PlaylistSong songPlaylist;
    private int eventType;
    private String errorMessage;

    public PlaylistSong getSongPlayList() {
        return songPlaylist;
    }

    public void setSongPlayList(PlaylistSong songPlaylist) {
        this.songPlaylist = songPlaylist;
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
