package co.org.ceindetec.derumba.entities;

import java.util.Map;

/**
 * Created by Ceindetec02 on 12/07/2016.
 */
public class User {
    String email;
    String nick;
    boolean online;
    public final static boolean ONLINE = true;
    public final static boolean OFFLINE = false;

    public User() {
    }

    public User(String email, String nick, boolean online) {
        this.email = email;
        this.nick = nick;
        this.online = online;
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

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public boolean equals(Object object) {
        boolean equal = false;

        if (object instanceof User) {
            User user = (User) object;
            equal = this.email.equals(user.getEmail());
        }

        return equal;
    }
}
