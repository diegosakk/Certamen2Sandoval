package com.example.certamen2sandoval;
//Diego Sandoval 20619.149-K
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPlantas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_plantas);
    }
    public void ActivityRegistrarPlantas(View view){
        Intent intent = new Intent(MenuPlantas.this, RegistrarPlantas.class);
        startActivity(intent);
    }
    public void ActivityListarPlantas(View view){
        Intent intent = new Intent(MenuPlantas.this, ListarPlantas.class);
        startActivity(intent);
    }
}