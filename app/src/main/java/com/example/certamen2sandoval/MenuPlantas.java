package com.example.certamen2sandoval;

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
}