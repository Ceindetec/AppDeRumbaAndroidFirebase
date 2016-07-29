package co.org.ceindetec.derumba.modules.detailsong;

import co.org.ceindetec.derumba.lib.EventBus;
import co.org.ceindetec.derumba.lib.GreenRobotEventBus;

/**
 * Created by Ceindetec02 on 21/07/2016.
 */
public class DetailSongRepositoryImpl implements DetailSongRepository {

    private EventBus eventBus;
    //private FireBaseHelper fireBaseHelper;

    /**
     * Declaracion del constructor
     */
    public DetailSongRepositoryImpl() {
        this.eventBus = GreenRobotEventBus.getInstance();
        // this.fireBaseHelper = FireBaseHelper.getInstance();
    }

    /**
     * Metodo implementado de la interface DetailSongRepository
     *
     * @param idEstablecimiento
     * @param idCancion
     */
    @Override
    public void songRanked(int idEstablecimiento, int idCancion) {

    }
}
