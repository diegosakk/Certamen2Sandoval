package com.example.certamen2sandoval;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ActivityCientifico(View view){
        Intent intent = new Intent(MainActivity.this, MenuCientifico.class);
        startActivity(intent);
    }
    public void ActivityPlantas (View view){
        Intent intent = new Intent(MainActivity.this, MenuPlantas.class);
        startActivity(intent);
    }
    public void ActivityRecoleccion (View view){
        Intent intent = new Intent(MainActivity.this, MenuRecoleccion.class);
        startActivity(intent);
    }

}