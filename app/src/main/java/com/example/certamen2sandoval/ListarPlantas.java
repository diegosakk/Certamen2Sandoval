package com.example.certamen2sandoval;
//Diego Sandoval 20619.149-K
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        lvListarPlanta.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                ClasePlanta value = (ClasePlanta) adapter.getItemAtPosition(position);
                Intent prueba = new Intent(getBaseContext(),EditPlanta.class);
                prueba.putExtra("planta",value);
                startActivity(prueba);
            }
        });
    }


    BDSandoval objBD;

    public void  ListarPlantas() {
        objBD = new BDSandoval(this);


        ArrayList<ClasePlanta> ListarPlantas = objBD.ListarPlantas();


        ArrayListviewPlanta adapter = new ArrayListviewPlanta(this, 0, ListarPlantas);
        lvListarPlanta.setAdapter(adapter);

    }
}