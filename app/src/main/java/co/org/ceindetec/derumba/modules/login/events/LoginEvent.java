package co.org.ceindetec.derumba.modules.login.events;

/**
 * Created by avalo.
 */
public class LoginEvent {
    public static final int onSignInSuccess = 0;
    public static final int onSignUpUSuccess= 1;
    public static final int onSignInUserDeRumbaError = 2;
    public static final int onSignUpUserDeRumbaError = 3;
    public static final int onSignInFacebookError = 4;
    public static final int onFailedToRecoverSession = 5;

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
