package com.example.certamen2sandoval;
//Diego Sandoval 20619.149-K
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListarCientificos extends AppCompatActivity {

    ListView lvListarCientifico;
    private Object AdapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cientificos);
        lvListarCientifico = findViewById(R.id.listCientificos);
        ListarCientifico();

        lvListarCientifico.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(android.widget.AdapterView<?> adapter, View v, int position, long arg3)
            {
                ClaseCientifico value = (ClaseCientifico) adapter.getItemAtPosition(position);
                Intent goEdit = new Intent(getBaseContext(),EditCientifico.class);
                goEdit.putExtra("cientifico",value);
                startActivity(goEdit);
            }
        });

    }

    BDSandoval objBD;

    public void  ListarCientifico() {
        objBD = new BDSandoval(this);


        ArrayList<ClaseCientifico> ListarCientificos = objBD.ListarCientificos();


        ArrayListview adapter = new ArrayListview(this, 0, ListarCientificos);
        lvListarCientifico.setAdapter(adapter);

    }
}