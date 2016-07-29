package co.org.ceindetec.derumba.modules.playlist.events;

/**
 * Created by Ceindetec02 on 19/07/2016.
 */
public class PlayListEvent {

    public final static int onLoadPlayListSuccess = 0;
    public final static int onLoadPlayListFailed = 1;
    public final static int onRankPlusSuccess = 2;
    public final static int onRankPlusFailed = 3;
    public final static int onRankMinusSuccess = 4;
    public final static int onRankMinusFailed = 5;

    private int eventType;
    private String errorMessage;

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
