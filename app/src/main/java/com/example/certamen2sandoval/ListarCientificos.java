package com.example.certamen2sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListarCientificos extends AppCompatActivity {

    ListView lvListarCientifico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cientificos);
        lvListarCientifico = findViewById(R.id.listCientificos);
        ListarCientifico();
    }
    BDSandoval objBD;

    public void  ListarCientifico() {
        objBD = new BDSandoval(this);


        ArrayList<ClaseCientifico> ListarCientificos = objBD.ListarCientificos();


            ArrayListview adapter = new ArrayListview(this, 0, ListarCientificos);
            lvListarCientifico.setAdapter(adapter);

    }
}