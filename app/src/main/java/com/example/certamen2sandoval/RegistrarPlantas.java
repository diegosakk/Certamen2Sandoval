package com.example.certamen2sandoval;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class RegistrarPlantas extends AppCompatActivity {

    Intent intent1;
    final static int cons=0;
    EditText txtCodigoPlanta,txtNombrePlanta, txtNombreCientifico, txtUso;
    Bitmap bmp1;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_plantas);
        txtCodigoPlanta = findViewById(R.id.txtCodigoPlanta);
        txtNombrePlanta = findViewById(R.id.txtNombre);
        txtNombreCientifico = findViewById(R.id.txtNombreCientifico);
        txtUso = findViewById(R.id.txtUso);
        img=(ImageView)findViewById(R.id.imageView);
    }

    public boolean TomarFoto(View v) {
        int id;
        id = v.getId();
        switch (id) {
            case R.id.btnCamara:
                intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);/*lanza actividad y espera resultado*/
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
            img.setImageBitmap(bmp1);

        }
    }

    public void RegistrarPlanta (View v){
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte [] byteArray = stream.toByteArray();

        ClasePlanta planta = new ClasePlanta(0,Integer.parseInt(txtCodigoPlanta.getText().toString()),txtNombrePlanta.getText().toString(),txtNombreCientifico.getText().toString(),byteArray,txtUso.getText().toString());
        BDSandoval db = new BDSandoval(this);

        db.RegistrarPlanta(planta);
    }
}