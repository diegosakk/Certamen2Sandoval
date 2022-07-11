package com.example.certamen2sandoval;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityRemoto extends AppCompatActivity {
    BDRemota bdRemota = new BDRemota();
    BDSandoval bdLocal = new BDSandoval(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remoto);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void ObtenerPlantasRemota(View view){
        ArrayList<ClasePlanta> plantas = new ArrayList<>();
        plantas= bdRemota.recibirdatosPlanta();
        if(plantas.size()>0){
            for (int i =0;i<plantas.size();i++){
                bdLocal.RegistrarPlanta(plantas.get(i));
            }
            Toast.makeText(this, "agregado con exito", Toast.LENGTH_SHORT).show();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void ObtenerCientificosRemota(View view){
        ArrayList<ClaseCientifico> cientificos = new ArrayList<>();
        cientificos= bdRemota.recibirdatosCientifico();
        if(cientificos.size()>0){
            for (int i =0;i<cientificos.size();i++){
                bdLocal.RegistrarCientifico(cientificos.get(i));
            }
            Toast.makeText(this, "agregado con exito", Toast.LENGTH_SHORT).show();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void EnviarRecolecciones(View view){
        ArrayList<ClaseRecoleccion> recolecciones = new ArrayList<>();
        recolecciones= bdLocal.ListarRecoleccion();
        if(recolecciones.size()>0){
            for (int i =0;i<recolecciones.size();i++){
                bdRemota.PostRecoleccion(recolecciones.get(i));
            }
            Toast.makeText(this, "agregado con exito", Toast.LENGTH_SHORT).show();
        }
    }
}