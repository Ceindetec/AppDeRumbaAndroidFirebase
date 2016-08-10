package co.org.ceindetec.derumba.domain;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import co.org.ceindetec.derumba.entities.Establecimiento;
import co.org.ceindetec.derumba.entities.Playlist;
import co.org.ceindetec.derumba.entities.PlaylistSong;
import co.org.ceindetec.derumba.entities.PlaylistEstablecimiento;
import co.org.ceindetec.derumba.entities.Song;
import co.org.ceindetec.derumba.entities.User;

public class FirebaseHelper {

    //Inicializacion de la referencia de datos DataReference
    private DatabaseReference dataReference;

    private final static String SITE_PATH = "establecimiento";
    private final static String PLAYLIST_PATH = "playlist";
    private final static String SONG_PATH = "cancion";
    private final static String LIKE_PATH = "likes";
    private final static String USERS_PATH = "usuario";

    /**
     * Creacion del Singleton para el FirebaseHelper
     */
    private static class SingletonHolder {

        private static final FirebaseHelper INSTANCE = new FirebaseHelper();

    }

    /**
     * Metodo para obtener la instancia del Singleton
     *
     * @return
     */
    public static FirebaseHelper getInstance() {

        return SingletonHolder.INSTANCE;

    }

    /**
     * Constructor de la clase FireBaseHelper
     */
    public FirebaseHelper() {

        this.dataReference = FirebaseDatabase.getInstance().getReference();

    }

    /**
     * Metodo getter para la obtencion de la referencia de datos
     *
     * @return
     */
    public DatabaseReference getDataReference() {

        return dataReference;

    }

    /**
     * Metodo de la clase que retorna el Email del usuario autenticado
     *
     * @return
     */
    public String getAuthUserEmail() {
        FirebaseAuth authData = FirebaseAuth.getInstance();
        String email = null;
        FirebaseUser providerData = authData.getCurrentUser();
        if (providerData != null) {
            email = providerData.getEmail();
        }
        return email;
    }


    /**
     * Metodo de la clase que retorna el Email del usuario autenticado
     *
     * @return
     */
    public String getAuthUserName() {
        FirebaseAuth authData = FirebaseAuth.getInstance();
        String userName = null;
        FirebaseUser providerData = authData.getCurrentUser();
        if (providerData != null) {
            userName = providerData.getDisplayName();
        }
        return userName;
    }

    /**
     * Metodo de la clase que retorna una referencia de usuario
     *
     * @param email
     * @return
     */
    public DatabaseReference getUserReference(String email) {
        DatabaseReference userReference = null;
        if (email != null) {
            String emailKey = email.replace(".", "_");
            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return userReference;
    }

    /**
     * Metodo de la clase que retorna la referencia de usuario del usuario autenticado
     *
     * @return
     */
    public DatabaseReference getMyUserReference() {

        return getUserReference(getAuthUserEmail());

    }

    /**
     * @param currentUser
     * @param uid
     */
    public void setNewUser(User currentUser, String uid) {
        dataReference.child(USERS_PATH).child(uid).setValue(currentUser);
    }

    /**
     * @param codigoPlaylist
     * @return
     */
    public DatabaseReference getReferencePlaylist(String codigoPlaylist) {
        DatabaseReference playlistReference = null;
        if (codigoPlaylist != null) {
            playlistReference = dataReference.getRoot().child(PLAYLIST_PATH + "-" + SONG_PATH).child(codigoPlaylist);
        }
        return playlistReference;
    }

    /**
     * @param codigoCancion
     * @return
     */
    public DatabaseReference getReferenceSong(String codigoCancion) {
        DatabaseReference songReference = null;
        if (codigoCancion != null) {
            songReference = dataReference.getRoot().child(SONG_PATH).child(codigoCancion);
        }
        return songReference;
    }


    /**
     * @param codigoCancion
     * @return
     */
    public DatabaseReference getReferencePlaylistSong(String codigoPlaylist, String codigoCancion) {
        DatabaseReference playlistSongReference = null;
        if (codigoCancion != null && codigoPlaylist != null) {
            playlistSongReference = dataReference.getRoot().child(PLAYLIST_PATH + "-" + SONG_PATH).child(codigoPlaylist).child(codigoCancion);
        }
        return playlistSongReference;
    }


    /**
     *
     */
    public void signOff() {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();

    }

    /**
     * @param codigo
     * @param nombre
     * @param latitud
     * @param longitud
     * @param estado
     */
    public void datosDummyEstablecimiento(String codigo, String nombre, double latitud, double longitud, boolean estado) {
        //String key = dataReference.child(SITE_PATH).push().getKey();
        Establecimiento establecimiento = new Establecimiento(codigo, nombre, latitud, longitud, estado);
        Map<String, Object> establecimientoValues = establecimiento.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + SITE_PATH + "/" + codigo, establecimientoValues);

        dataReference.updateChildren(childUpdates);
    }

    public void datosDummyPlaylist(String codigo, String nombre, String establecimiento, boolean estado) {
        //String key = dataReference.child(PLAYLIST_PATH).push().getKey();
        Playlist playlist = new Playlist(codigo, nombre, establecimiento, estado);
        Map<String, Object> playlistValues = playlist.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + PLAYLIST_PATH + "/" + codigo, playlistValues);

        dataReference.updateChildren(childUpdates);
    }

    public void datosDummyCancion(String codigo, String nombre, String interprete, String genero, String establecimiento, int duracion, boolean estado) {
        //String key = dataReference.child(PLAYLIST_PATH).push().getKey();
        Song song = new Song(codigo, nombre, interprete, genero, establecimiento, duracion, estado);
        Map<String, Object> cancionValues = song.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + SONG_PATH + "/" + codigo, cancionValues);

        dataReference.updateChildren(childUpdates);
    }

    public void datosDummyPlayListEstablecimiento(String codigo, String codigoEstablecimiento, String codigoPlaylist) {
        //String key = dataReference.child(PLAYLIST_PATH).push().getKey();
        PlaylistEstablecimiento playlistEstablecimiento = new PlaylistEstablecimiento(codigo, codigoEstablecimiento, codigoPlaylist);
        Map<String, Object> playlistEstablecimientoValues = playlistEstablecimiento.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + PLAYLIST_PATH + "-" + SITE_PATH + "/" + codigo, playlistEstablecimientoValues);

        dataReference.updateChildren(childUpdates);
    }

    public void datosDummyPlayListCancion(String codigoPlaylist, String codigoCancion, String nombreCancion) {

        PlaylistSong playlistSong = new PlaylistSong(codigoPlaylist, nombreCancion, codigoCancion);
        Map<String, Object> playlistCancionValues = playlistSong.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + PLAYLIST_PATH + "-" + SONG_PATH + "/" + codigoPlaylist + "/" + codigoCancion, playlistCancionValues);

        dataReference.updateChildren(childUpdates);
        datosDummyLikes(codigoPlaylist, codigoCancion);
    }

    public void datosDummyLikes(String codigoPlaylist, String codigoCancion) {

        Map<String, Object> likesValues = new HashMap<>();
        likesValues.put("popopopoepoepoe", true);
        likesValues.put("sdsadksdnjkashd", true);
        likesValues.put("sdadsavsdsdsf", true);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + PLAYLIST_PATH + "-" + SONG_PATH + "/" + codigoPlaylist + "/" + codigoCancion + "/" + LIKE_PATH, likesValues);

        dataReference.updateChildren(childUpdates);
    }

}
