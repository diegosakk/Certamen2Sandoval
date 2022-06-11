package com.example.certamen2sandoval;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RegistrarRecoleccion extends AppCompatActivity {

    Intent intent1;
    final static int cons=0;
    Spinner spinIDPlanta, spinCientifico;
    ArrayList<ClaseCientifico> listaCientificos;
    EditText txtFecha,txtComentario;
    Bitmap bmp1;
    ImageView img;
    int codigoPlanta, RUTCientifico, latitud,longitud;

    BDSandoval conn;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_recoleccion);

        txtFecha = findViewById(R.id.txtFecha);
        txtComentario = findViewById(R.id.txtComentario);
        img=(ImageView)findViewById(R.id.imageView);

        //SPINNER PLANTA

        BDSandoval bd = new BDSandoval(this);
        spinIDPlanta = (Spinner) findViewById(R.id.spinIDPlanta) ;
        ArrayList<ClasePlanta> listaPlantas= bd.ListarPlantas();
        ArrayList<String> listaIDPlantas = new ArrayList<String>();

        listaPlantas.forEach(ClasePlantas -> listaIDPlantas.add(ClasePlantas.getIdPlanta()+""));

        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.spinner,listaIDPlantas);
        spinIDPlanta.setAdapter(adapter);
        spinIDPlanta.setSelection(0);

        //SPINNER CIENTIFICO
        spinCientifico = (Spinner) findViewById(R.id.spinCientifico) ;
        ArrayList<ClaseCientifico> listaCientificos= bd.ListarCientificos();
        ArrayList<String> listaCientificosRUT = new ArrayList<String>();

        listaCientificos.forEach(ClaseCientifico -> listaCientificosRUT.add(ClaseCientifico.getRUTCientifico()+""));

        ArrayAdapter adapter2 = new ArrayAdapter(this,R.layout.spinner,listaCientificosRUT);
        spinCientifico.setAdapter(adapter2);
        spinCientifico.setSelection(0);


    }
    public boolean TomarFotoRecoleccion(View v) {
        int id;
        id = v.getId();
        switch (id) {
            case R.id.btnCamara2:
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
            img.setImageBitmap(bmp1);

        }
    }



    public void RegistrarRecoleccion (View v){

        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte [] byteArray = stream.toByteArray();

        codigoPlanta = Integer.parseInt(spinIDPlanta.getSelectedItem().toString());
        RUTCientifico = Integer.parseInt(spinCientifico.getSelectedItem().toString()) ;

        latitud = 0;
        longitud=0;

        BDSandoval db = new BDSandoval(this);

        ClaseRecoleccion recoleccion = new ClaseRecoleccion(txtFecha.getText().toString(),codigoPlanta,RUTCientifico,txtComentario.getText().toString(),byteArray,latitud,longitud);
        db.RegistrarRecoleccion(recoleccion);
    }

}