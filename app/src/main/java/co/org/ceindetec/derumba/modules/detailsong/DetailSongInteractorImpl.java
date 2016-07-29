package co.org.ceindetec.derumba.modules.detailsong;

/**
 * Created by Ceindetec02 on 21/07/2016.
 */
public class DetailSongInteractorImpl implements DetailSongInteractor {


    DetailSongRepository detailSongRepository;

    public DetailSongInteractorImpl() {
        detailSongRepository = new DetailSongRepositoryImpl();
    }

    @Override
    public void songRanked(int idEstablecimiento, int idCancion) {

    }
}
