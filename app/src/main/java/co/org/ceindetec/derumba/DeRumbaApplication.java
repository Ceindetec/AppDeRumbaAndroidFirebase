package co.org.ceindetec.derumba;

import android.app.Application;

import com.facebook.FacebookSdk;

import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ceindetec02 on 18/07/2016.
 */
public class DeRumbaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
        setupFacebook();

    }

    private void setupFirebase() {

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }

    private void setupFacebook() {

        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);

    }

}