package com.example.certamen2sandoval;
//Diego Sandoval 20619.149-K
public class ClaseRecoleccion {
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCodigoPlanta() {
        return CodigoPlanta;
    }

    public void setCodigoPlanta(int codigoPlanta) {
        CodigoPlanta = codigoPlanta;
    }

    public String getRUTCientifico() {
        return RUTCientifico;
    }

    public void setRUTCientifico(String RUTCientifico) {
        this.RUTCientifico = RUTCientifico;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public byte[] getFotoLugar() {
        return FotoLugar;
    }

    public void setFotoLugar(byte[] fotoLugar) {
        FotoLugar = fotoLugar;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }

    public int getIdRecoleccion() {
        return IdRecoleccion;
    }

    public void setIdRecoleccion(int idRecoleccion) {
        IdRecoleccion = idRecoleccion;
    }

    public ClaseRecoleccion(int idRecoleccion,String fecha, int codigoPlanta, String RUTCientifico, String comentario, byte[] fotoLugar, double latitud, double longitud) {
        this.fecha = fecha;
        CodigoPlanta = codigoPlanta;
        this.RUTCientifico = RUTCientifico;
        Comentario = comentario;
        FotoLugar = fotoLugar;
        Latitud = latitud;
        Longitud = longitud;
        this.IdRecoleccion = idRecoleccion;
    }

    public ClaseRecoleccion() {
    }



    private int IdRecoleccion;

    private String fecha;
    private int CodigoPlanta;
    private String RUTCientifico;
    private String Comentario;
    private byte[] FotoLugar;
    private double Latitud;
    private double Longitud;
}
