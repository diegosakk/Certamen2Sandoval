package com.example.certamen2sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListarPlantas extends AppCompatActivity {

    ListView lvListarPlanta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_plantas);
        lvListarPlanta = findViewById(R.id.listPlantas);
        ListarPlantas();
    }

    BDSandoval objBD;

    public void  ListarPlantas() {
        objBD = new BDSandoval(this);


        ArrayList<ClasePlanta> ListarPlantas = objBD.ListarPlantas();


        ArrayListviewPlanta adapter = new ArrayListviewPlanta(this, 0, ListarPlantas);
        lvListarPlanta.setAdapter(adapter);

    }
}