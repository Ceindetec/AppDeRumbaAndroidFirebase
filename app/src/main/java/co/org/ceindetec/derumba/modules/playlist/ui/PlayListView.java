package co.org.ceindetec.derumba.modules.playlist.ui;

import co.org.ceindetec.derumba.entities.PlaylistSong;

/**
 * Created by Ceindetec02 on 18/07/2016.
 */
public interface PlayListView {

    void onSongAdded(PlaylistSong songPlaylist);

    void onSongChanged(PlaylistSong songPlaylist);

    void onSongRemoved(PlaylistSong songPlaylist);

    void showProgress();

    void hideProgress();

    void messageAdded();

}
