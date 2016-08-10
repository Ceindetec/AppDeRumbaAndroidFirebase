package co.org.ceindetec.derumba.entities;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ceindetec02 on 19/07/2016.
 */
public class Playlist {

    String codigo;
    String nombre;
    String establecimiento;
    boolean estado;

    public Playlist() {
    }

    public Playlist(String codigo, String nombre, String establecimiento, boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.establecimiento = establecimiento;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("codigo", codigo);
        result.put("establecimiento", establecimiento);
        result.put("estado", Boolean.valueOf(estado));

        return result;
    }
}
