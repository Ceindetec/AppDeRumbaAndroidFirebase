package co.org.ceindetec.derumba.entities;

import java.util.Map;

/**
 * Created by Ceindetec02 on 19/07/2016.
 */
public class PlayList {

    int idEstablecimientoPlayList;
    int posicionCancionPlayList;
    String nombreCancionPlayList;
    int puntosCancionPlayList;
    //Map<String, Boolean> votosCancionPlayList;

    public PlayList() {
    }

    public PlayList(int idEstablecimientoPlayList,
                    int posicionCancionPlayList,
                    String nombreCancionPlayList,
                    int puntosCancionPlayList) {
        this.idEstablecimientoPlayList = idEstablecimientoPlayList;
        this.posicionCancionPlayList = posicionCancionPlayList;
        this.nombreCancionPlayList = nombreCancionPlayList;
        this.puntosCancionPlayList = puntosCancionPlayList;
    }
    /*
    public PlayList(int idEstablecimientoPlayList,
                    String nombreCancionPlayList,
                    int puntosCancionPlayList,
                    Map<String, Boolean> votosCancionPlayList) {
        this.idEstablecimientoPlayList = idEstablecimientoPlayList;
        this.nombreCancionPlayList = nombreCancionPlayList;
        this.puntosCancionPlayList = puntosCancionPlayList;
        //this.votosCancionPlayList = votosCancionPlayList;
    }
*/
    public int getIdEstablecimientoPlayList() {
        return idEstablecimientoPlayList;
    }

    public void setIdEstablecimientoPlayList(int idEstablecimientoPlayList) {
        this.idEstablecimientoPlayList = idEstablecimientoPlayList;
    }

    public int getPosicionCancionPlayList() {
        return posicionCancionPlayList;
    }

    public void setPosicionCancionPlayList(int posicionCancionPlayList) {
        this.posicionCancionPlayList = posicionCancionPlayList;
    }

    public String getNombreCancionPlayList() {
        return nombreCancionPlayList;
    }

    public void setNombreCancionPlayList(String nombreCancionPlayList) {
        this.nombreCancionPlayList = nombreCancionPlayList;
    }

    public int getPuntosCancionPlayList() {
        return puntosCancionPlayList;
    }

    public void setPuntosCancionPlayList(int puntosCancionPlayList) {
        this.puntosCancionPlayList = puntosCancionPlayList;
    }
/*
    public Map<String, Boolean> getVotosCancionPlayList() {
        return votosCancionPlayList;
    }

    public void setVotosCancionPlayList(Map<String, Boolean> votosCancionPlayList) {
        this.votosCancionPlayList = votosCancionPlayList;
    }
    */
}
