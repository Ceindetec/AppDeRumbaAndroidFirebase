package co.org.ceindetec.derumba.entities;

import java.util.Map;

/**
 * Created by Ceindetec02 on 12/07/2016.
 */
public class Session {
    String email;
    String nick;
    private static Session sessionInstance;

    public Session() {
    }

    public static synchronized Session getInstance() {
        if (null == sessionInstance) {
            sessionInstance = new Session();
        }
        return sessionInstance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
