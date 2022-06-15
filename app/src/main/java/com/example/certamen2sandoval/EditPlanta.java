package com.example.certamen2sandoval;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class EditPlanta extends AppCompatActivity {

    Intent intent1;
    final static int cons=0;
    EditText txtCodigoPlanta,txtNombrePlanta, txtNombreCientifico, txtUso;
    Bitmap bmp1;
    ImageView ImagenPlanta;
    BDSandoval bd;
    ClasePlanta planta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_planta);
        txtCodigoPlanta = findViewById(R.id.txtCodigoPlantaEdit);
        txtNombrePlanta = findViewById(R.id.txtNombrePlantaEdit);
        txtNombreCientifico = findViewById(R.id.txtNombreCientificoEdit);
        txtUso = findViewById(R.id.txtUsoEdit);
        ImagenPlanta=(ImageView)findViewById(R.id.imageViewEditPlanta);
        this.bd = new BDSandoval(this);
        if(getIntent().getExtras() != null) {
            this.planta = (ClasePlanta) getIntent().getSerializableExtra("planta");
            txtCodigoPlanta.setText(planta.getCodigoPlanta()+"");
            txtNombrePlanta.setText(planta.getNombrePlanta());
            txtNombreCientifico.setText(planta.getNombreCientifico());
            Bitmap image = BitmapFactory.decodeByteArray(planta.getImagenPlanta(), 0, planta.getImagenPlanta().length);
            ImagenPlanta.setImageBitmap(image);
            txtUso.setText(planta.getUso());
            this.bmp1=image;
        }
    }

    public boolean TomarFoto(View v) {
        int id;
        id = v.getId();
        switch (id) {
            case R.id.btnCamara:
                intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent1, cons);
                break;
        }
        return false;
    }
    protected void onActivityResult(int requesCode, int resultCode, Intent data)
    {
        super.onActivityResult(requesCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK)
        {
            Bundle ext=data.getExtras();
            bmp1=(Bitmap)ext.get("data");
            ImagenPlanta.setImageBitmap(bmp1);

        }
    }
    public void EditarPlanta(View v){
        String codigo = txtCodigoPlanta.getText().toString();
        String nombre = txtNombrePlanta.getText().toString();
        String nombrecientifico = txtNombreCientifico.getText().toString();
        String uso = txtUso.getText().toString();
        byte [] byteArray = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byteArray = stream.toByteArray();

        ClasePlanta planta = new ClasePlanta(this.planta.getIdPlanta(),Integer.parseInt(codigo),nombre,nombrecientifico,byteArray,uso);
        this.bd.editPlanta(planta);
        finish();

    }

    public void BorrarPlanta(View view){
        int codigo = this.planta.getCodigoPlanta();
        int id = this.planta.getIdPlanta();
        try {
            boolean check=this.bd.checkRecoleccionPlanta(codigo+"");
            if(!check)
            {
                this.bd.EliminarPlanta(id);
                Mensaje("Planta eliminada con el codigo " + this.planta.getCodigoPlanta());
                finish();
            }
            else{
                Mensaje("La planta que desea eliminar esta asociada a una recoleccion");
                finish();
            }

        }catch (Exception e)
        {
            Mensaje("Error eliminando");
        }
    }


    public void Mensaje(String mensaje){
        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show();
    }
}