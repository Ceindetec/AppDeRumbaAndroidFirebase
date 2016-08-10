package co.org.ceindetec.derumba.entities;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ceindetec02 on 19/07/2016.
 */
public class PlaylistSong {

    String nombreCancion;
    String codigoCancion;
    String codigoPlaylist;
    public Map<String, Boolean> likes = new HashMap<>();

    public PlaylistSong() {
    }

    public PlaylistSong(String codigoPlaylist, String nombreCancion, String codigoCancion) {
        this.nombreCancion = nombreCancion;
        this.codigoCancion = codigoCancion;
        this.codigoPlaylist = codigoPlaylist;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public String getCodigoCancion() {
        return codigoCancion;
    }

    public void setCodigoCancion(String codigoCancion) {
        this.codigoCancion = codigoCancion;
    }

    public String getCodigoPlaylist() {
        return codigoPlaylist;
    }

    public void setCodigoPlaylist(String codigoPlaylist) {
        this.codigoPlaylist = codigoPlaylist;
    }

    @Exclude
    public int CountLikes(){
        return likes.size();
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombreCancion", nombreCancion);
        result.put("codigoCancion", codigoCancion);
        result.put("codigoPlaylist", codigoPlaylist);

        return result;
    }
}
