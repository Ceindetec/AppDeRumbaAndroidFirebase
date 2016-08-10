package co.org.ceindetec.derumba.modules.playlist;

/**
 * Created by Ceindetec02 on 18/07/2016.
 */
public class PlayListInteractorImpl implements PlayListInteractor {


    //Instanciamiento del PlayListRepository
    private PlayListRepository playListRepository;

    /**
     * Constructor de la clase
     */
    public PlayListInteractorImpl() {

        //Inicializacion del repositorio
        playListRepository = new PlayListRepositoryImpl();
    }

    @Override
    public void datosDummy() {
        playListRepository.datosDummy();
    }

    @Override
    public void subscribe() {
        playListRepository.subscribeToPlayListEvents();
    }

    @Override
    public void unsubscribe() {
        playListRepository.unsubscribeToPlayListEvents();
    }

    @Override
    public void destroyListener() {
        playListRepository.destroyListener();
    }

    @Override
    public void loadPlayList(int idEstablecimiento) {

    }
}
