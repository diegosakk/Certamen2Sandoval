package com.example.certamen2sandoval;

import java.sql.Blob;
//Diego Sandoval 20619.149-K
public class ClasePlanta {
    private int IdPlanta;

    public int getIdPlanta() {
        return IdPlanta;
    }

    public void setIdPlanta(int idPlanta) {
        IdPlanta = idPlanta;
    }

    public int getCodigoPlanta() {
        return CodigoPlanta;
    }

    public void setCodigoPlanta(int codigoPlanta) {
        CodigoPlanta = codigoPlanta;
    }

    public String getNombrePlanta() {
        return NombrePlanta;
    }

    public void setNombrePlanta(String nombrePlanta) {
        NombrePlanta = nombrePlanta;
    }

    public String getNombreCientifico() {
        return NombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        NombreCientifico = nombreCientifico;
    }

    public byte[] getImagenPlanta() {
        return ImagenPlanta;
    }

    public void setImagenPlanta(byte[] imagenPlanta) {
        ImagenPlanta = imagenPlanta;
    }

    public String getUso() {
        return Uso;
    }

    public void setUso(String uso) {
        Uso = uso;
    }

    private int CodigoPlanta;
    private String NombrePlanta;
    private String NombreCientifico;
    private byte[] ImagenPlanta;
    private String Uso;

    public ClasePlanta(int idPlanta, int codigoPlanta, String nombrePlanta, String nombreCientifico, byte[] imagenPlanta, String uso) {
        IdPlanta = idPlanta;
        CodigoPlanta = codigoPlanta;
        NombrePlanta = nombrePlanta;
        NombreCientifico = nombreCientifico;
        ImagenPlanta = imagenPlanta;
        Uso = uso;
    }

    public ClasePlanta() {
    }
}
