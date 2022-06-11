package com.example.certamen2sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_side, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnSideCientifico:
                Intent intent = new Intent(MainActivity.this, MenuCientifico.class);
                startActivity(intent);
                return true;

            case R.id.btnSidePlanta:
                intent = new Intent(MainActivity.this, MenuPlantas.class);
                startActivity(intent);
                return true;
            case R.id.btnSideRecoleccion:
                intent = new Intent(MainActivity.this, MenuRecoleccion.class);
                startActivity(intent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


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