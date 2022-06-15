package com.example.certamen2sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditCientifico extends AppCompatActivity {

    EditText txtRutCientifico, txtNombre, txtApellido;
    RadioGroup rgGenero;
    RadioButton rdMasculino, rdFemenino;
    Button btnConfirmarEdicion, btnBorrar;
    BDSandoval bd;
    ClaseCientifico cientifico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cientifico);
        txtRutCientifico = findViewById(R.id.txtRUTEdit);
        txtNombre = findViewById(R.id.txtNombreEdit);
        txtApellido = findViewById(R.id.txtApellidosEdit);
        rgGenero= findViewById(R.id.rdGeneroEdit);
        rdFemenino =findViewById(R.id.rdFemeninoEdit);
        rdMasculino =findViewById(R.id.rdMasculinoEdit);
        this.bd = new BDSandoval(this);
        if(getIntent().getExtras() != null) {
            this.cientifico = (ClaseCientifico) getIntent().getSerializableExtra("cientifico");
            txtRutCientifico.setText(cientifico.getRUTCientifico()+"");
            txtNombre.setText(cientifico.getNombreCientifico());
            txtApellido.setText(cientifico.getApellidosCientifico());
            if(cientifico.getGeneroCientifico().equals("Masculino"))
                rdMasculino.setChecked(true);
            else
            if(cientifico.getGeneroCientifico().equals("Femenino"))
                rdFemenino.setChecked(true);
        }
    }

    public void EditarCientifico(View v) {
        String rut = txtRutCientifico.getText().toString();
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String genero ="";
        if(rdMasculino.isChecked())
            genero="Masculino";
        if(rdFemenino.isChecked())
            genero="Femenino";
        String error="";
        if(rut.equals(""))
            error+="Debe ingresar rut\n";
        if(nombre.equals(""))
            error+="Debe ingresar nombre\n";
        if(apellido.equals(""))
            error+="Debe ingresar apellido\n";
        if(apellido.equals(""))
            error+="Debe selecionar genero\n";
        if(!error.equals(""))
            Mensaje(error);
        else
        {
            ClaseCientifico cientifico = new ClaseCientifico(this.cientifico.getIdCientifico(),Integer.parseInt(rut),nombre,apellido,genero);

            this.bd.editCientifico(cientifico);
            Mensaje("Editado el cientifico con RUT " + this.cientifico.getRUTCientifico());
            finish();

        }


    }
    public void Mensaje(String mensaje){
        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show();
    }

    public void BorrarCientifico(){
        String nombre = this.cientifico.getNombreCientifico();

        try {
            boolean check=this.bd.checkRecoleccionCientifico(nombre);
            if(!check)
            {
                this.bd.EliminarCientifico(nombre);
                Mensaje("Cientifico eliminado con el RUT " + this.cientifico.getNombreCientifico());
                finish();
            }
            else{
                Mensaje("Cientifico esta asociado a una recoleccion");
                finish();
            }

        }catch (Exception e)
        {
            Mensaje("No se pudo borrar");
        }
    }
}