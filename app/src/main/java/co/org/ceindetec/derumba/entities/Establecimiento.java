package co.org.ceindetec.derumba.entities;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ceindetec02 on 12/07/2016.
 */
public class Establecimiento {

    String codigo;
    String nombre;
    double latitud;
    double longitud;
    boolean estado;

    public Establecimiento() {
    }

    public Establecimiento(String codigo, String nombre, double latitud, double longitud, boolean estado) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
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

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
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
        result.put("latitud", Double.valueOf(latitud));
        result.put("longitud", Double.valueOf(longitud));
        result.put("estado", Boolean.valueOf(estado));

        return result;
    }

}
