package co.org.ceindetec.derumba.modules.detailsong;

/**
 * Created by Ceindetec02 on 21/07/2016.
 */
public class DetailSongInteractorImpl implements DetailSongInteractor {


    DetailSongRepository detailSongRepository;

    public DetailSongInteractorImpl() {
        detailSongRepository = new DetailSongRepositoryImpl();
    }

    /**
     * @param codigoCancion
     */
    @Override
    public void getInfoSong(String codigoCancion) {
        detailSongRepository.getInfoSong(codigoCancion);
    }

    /**
     * @param codigoPlaylist
     * @param codigoCancion
     */
    @Override
    public void verifyLikeSong(String codigoPlaylist, String codigoCancion) {
        detailSongRepository.verifyLikeSong(codigoPlaylist, codigoCancion);
    }

    /**
     * @param codigoCacion
     */
    @Override
    public void rankSong(String codigoPlaylist, String codigoCacion) {
        detailSongRepository.rankSong(codigoPlaylist, codigoCacion);
    }
}
