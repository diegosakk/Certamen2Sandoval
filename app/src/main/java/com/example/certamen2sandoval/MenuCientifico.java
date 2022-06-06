package com.example.certamen2sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuCientifico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cientifico);
    }
    public void ActivityRegistrarCientificos(View view){
        Intent intent = new Intent(MenuCientifico.this, RegistrarCientificos.class);
        startActivity(intent);
    }
    public void ActivityListarCientificos(View view){
        Intent intent = new Intent(MenuCientifico.this, ListarCientificos.class);
        startActivity(intent);
    }
}