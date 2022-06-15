package com.example.certamen2sandoval;
//Diego Sandoval 20619.149-K
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListarRecoleccion extends AppCompatActivity {

    ListView lvListarRecoleccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_recoleccion);
        lvListarRecoleccion = findViewById(R.id.listRecoleccion);
        ListarRecoleccion();

    }

    BDSandoval objBD;

    public void  ListarRecoleccion() {
        objBD = new BDSandoval(this);


        ArrayList<ClaseRecoleccion> ListarRecolecciones = objBD.ListarRecoleccion();


        ArrayListviewRecoleccion adapter = new ArrayListviewRecoleccion(this, 0, ListarRecolecciones);
        lvListarRecoleccion.setAdapter(adapter);

    }
}