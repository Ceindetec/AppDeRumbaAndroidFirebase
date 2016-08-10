package co.org.ceindetec.derumba.modules.playlist;

/**
 * Created by avalo.
 */
public class PlayListSessionInteractorImpl implements PlayListSessionInteractor {
    PlayListRepository playListRepository;

    public PlayListSessionInteractorImpl() {
        playListRepository = new PlayListRepositoryImpl();
    }

    @Override
    public void signOff() {
        playListRepository.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return playListRepository.getCurrentUserEmail();
    }

    @Override
    public String getCurrentUserName() {
        return playListRepository.getCurrentUserName();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        playListRepository.changeConnectionStatus(online);
    }
}
