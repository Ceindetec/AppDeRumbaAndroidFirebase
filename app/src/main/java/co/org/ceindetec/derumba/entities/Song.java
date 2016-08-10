package co.org.ceindetec.derumba.entities;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ceindetec02 on 12/07/2016.
 */
public class Song {

    String codigo;
    String nombre;
    String interprete;
    String genero;
    String establecimiento;
    int duracion;
    boolean estado;

    public Song() {
    }

    public Song(String codigo, String nombre, String interprete, String genero, String establecimiento, int duracion, boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.interprete = interprete;
        this.genero = genero;
        this.establecimiento = establecimiento;
        this.duracion = duracion;
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

    public String getInterprete() {
        return interprete;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
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
        result.put("codigo", codigo);
        result.put("nombre", nombre);
        result.put("interprete", interprete);
        result.put("genero", genero);
        result.put("establecimiento", establecimiento);
        result.put("duracion", Integer.valueOf(duracion));
        result.put("estado", Boolean.valueOf(estado));

        return result;
    }

}
