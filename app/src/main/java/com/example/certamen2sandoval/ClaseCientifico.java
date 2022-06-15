package com.example.certamen2sandoval;

import java.io.Serializable;

//Diego Sandoval 20619.149-K
public class ClaseCientifico implements Serializable {
    public int getIdCientifico() {
        return IdCientifico;
    }

    public void setIdCientifico(int idCientifico) {
        IdCientifico = idCientifico;
    }

    private int IdCientifico;

    public String getNombreCientifico() {
        return NombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        NombreCientifico = nombreCientifico;
    }

    public String getApellidosCientifico() {
        return ApellidosCientifico;
    }

    public void setApellidosCientifico(String apellidosCientifico) {
        ApellidosCientifico = apellidosCientifico;
    }

    private String NombreCientifico;

    private String ApellidosCientifico;

    public String getGeneroCientifico() {
        return GeneroCientifico;
    }

    public void setGeneroCientifico(String generoCientifico) {
        GeneroCientifico = generoCientifico;
    }

    private String GeneroCientifico;

    public int getRUTCientifico() {
        return RUTCientifico;
    }

    public void setRUTCientifico(int RUTCientifico) {
        this.RUTCientifico = RUTCientifico;
    }

    private int RUTCientifico;

    public ClaseCientifico(int idCientifico,int RUTCientifico, String nombreCientifico, String apellidosCientifico, String generoCientifico) {
        IdCientifico = idCientifico;
        NombreCientifico = nombreCientifico;
        ApellidosCientifico = apellidosCientifico;
        GeneroCientifico = generoCientifico;
        this.RUTCientifico = RUTCientifico;
    }

    public ClaseCientifico() {
    }
}
