package com.example.certamen2sandoval;
//Diego Sandoval 20619.149-K
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegistrarCientificos extends AppCompatActivity {

    EditText txtRUT, txtNombre, txtApellidos;
    RadioButton rdMasculino, rdFemenino;
    RadioGroup rdGenero;
    String genero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cientificos);
        txtRUT = findViewById(R.id.txtRUT);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidosEdit);
        rdMasculino =findViewById(R.id.rdMasculino);
        rdFemenino =findViewById(R.id.rdFemenino);
    }
    public void RegistrarCientificoBoton(View v){
        if (rdMasculino.isChecked() == true) {
            genero = "Masculino";
        }
        if (rdFemenino.isChecked() == true){
            genero = "Femenino";
        }
        ClaseCientifico cientifico = new ClaseCientifico(0,Integer.parseInt(txtRUT.getText().toString()),txtNombre.getText().toString(),txtApellidos.getText().toString(),genero);
        BDSandoval db = new BDSandoval(this);

        db.RegistrarCientifico(cientifico);


    }
}