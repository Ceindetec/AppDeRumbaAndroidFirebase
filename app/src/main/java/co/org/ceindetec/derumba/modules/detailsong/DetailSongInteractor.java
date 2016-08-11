package co.org.ceindetec.derumba.modules.detailsong;

/**
 * Created by Ceindetec02 on 21/07/2016.
 */
public interface DetailSongInteractor {

    void getInfoSong(String codigoCancion);

    void verifyLikeSong(String codigoPlaylist, String codigoCancion);

    void rankSong(String codigoPlaylist, String codigoCacion);

}
