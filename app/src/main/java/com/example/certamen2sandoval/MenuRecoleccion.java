package com.example.certamen2sandoval;
//Diego Sandoval 20619.149-K
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuRecoleccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_recoleccion);
    }
    public void ActivityRegistrarRecoleccion(View view){
        Intent intent = new Intent(MenuRecoleccion.this, RegistrarRecoleccion.class);
        startActivity(intent);
    }
    public void ActivityListarRecoleccion(View view){
        Intent intent = new Intent(MenuRecoleccion.this, ListarRecoleccion.class);
        startActivity(intent);
    }
    public void ActivityListarRecoleccionEspecifico(View view){
        Intent intent = new Intent(MenuRecoleccion.this, ListarRecoleccionPorCientifico.class);
        startActivity(intent);
    }

}
