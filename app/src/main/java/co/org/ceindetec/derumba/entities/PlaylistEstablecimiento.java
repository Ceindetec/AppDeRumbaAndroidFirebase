package co.org.ceindetec.derumba.entities;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ceindetec02 on 19/07/2016.
 */
public class PlaylistEstablecimiento {

    String codigo;
    String codigoEstablecimiento;
    String codigoPlayList;

    public PlaylistEstablecimiento() {
    }

    public PlaylistEstablecimiento(String codigo, String codigoEstablecimiento, String codigoPlayList) {
        this.codigo = codigo;
        this.codigoEstablecimiento = codigoEstablecimiento;
        this.codigoPlayList = codigoPlayList;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("codigo", codigo);
        result.put("codigoEstablecimiento", codigoEstablecimiento);
        result.put("codigoPlayList", codigoPlayList);

        return result;
    }
}
