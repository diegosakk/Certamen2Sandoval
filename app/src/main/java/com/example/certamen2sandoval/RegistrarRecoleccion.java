package com.example.certamen2sandoval;
//Diego Sandoval 20619.149-K
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    int codigoPlanta, latitud,longitud;
    Location location;
    LocationManager locationManager;
    TextView txtLongitud, txtLatitud;
    Button btnGps;
    String RUTCientifico;

    BDSandoval conn;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_recoleccion);

        txtFecha = findViewById(R.id.txtFecha);
        txtComentario = findViewById(R.id.txtComentario);
        img=(ImageView)findViewById(R.id.imageView);

        txtLongitud = (TextView) findViewById(R.id.txtLongitud);
        txtLatitud = (TextView) findViewById(R.id.txtLatitud);
        btnGps = (Button) findViewById(R.id.btnGPS);
        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerGps();
            }
        });

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

    public void obtenerGps() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))    // chequea si está activo gps
        {
            alerte_Gps();
        }

        if (ContextCompat.checkSelfPermission(RegistrarRecoleccion.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(RegistrarRecoleccion.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RegistrarRecoleccion.this, new String[]
                    {
                            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        };

        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                txtLongitud.setText(String.valueOf(location.getLongitude()));
                txtLatitud.setText(String.valueOf(location.getLatitude()));
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                Toast.makeText(RegistrarRecoleccion.this,"GPS DESACTIVADO \n ACTIVELO",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

        });

    }

    public void alerte_Gps(){
        new AlertDialog.Builder(RegistrarRecoleccion.this)
                .setTitle("Activar GPS")
                .setMessage("¿El GPS esta desactivado  ¿Desea Activarlo??")

                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));/*Abre App para Activa el GPS*/
                                //dialog.cancel();
                            }
                        })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                }).show();

    }

    public void RegistrarRecoleccion (View v){

        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte [] byteArray = stream.toByteArray();

        codigoPlanta = Integer.parseInt(spinIDPlanta.getSelectedItem().toString());



        ClaseRecoleccion recoleccion = new ClaseRecoleccion(0,txtFecha.getText().toString(),codigoPlanta,RUTCientifico,txtComentario.getText().toString(),byteArray, Double.parseDouble(txtLatitud.getText().toString()),Double.parseDouble(txtLongitud.getText().toString()));
        BDSandoval db = new BDSandoval(this);

        db.RegistrarRecoleccion(recoleccion);
    }

}