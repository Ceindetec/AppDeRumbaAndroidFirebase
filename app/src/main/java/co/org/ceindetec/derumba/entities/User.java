package co.org.ceindetec.derumba.entities;

/**
 * Created by Ceindetec02 on 12/07/2016.
 */
public class User {
    String email;
    String username;

    public User() {
    }

    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
