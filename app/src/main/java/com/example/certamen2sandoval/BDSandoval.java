package com.example.certamen2sandoval;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDSandoval extends SQLiteOpenHelper {
    public String Tablas;
    private static final int VERSION_BASEDATOS = 1;

    private static final String NOMBRE_BASEDATOS = "PERSONAS.sqlite";

    private static final String IMAGENES = "CREATE TABLE IMAGENES (COD INT PRIMARY KEY, DETALLE TEXT, IMAGEN BLOB)";

    // constructor
    public BDSandoval(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(IMAGENES);   // SE CREA LA TABLA. Esta instrucción se repite por cada tabla que se crea
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // genere codigo correspondiente
    }

    //---------------------------------------METODO PARA GRABAR UN REGISTRO----------------------------
    public boolean Inserta_Imagen(int cod, String detalle, byte[] img) {
        boolean sw1 = true;
        SQLiteDatabase db = getWritableDatabase(); //ABRE BASE DE DATOS EN MODO ESCRITURA
        if (db != null) {//SI LA BASE EXISTE

            ContentValues valores = new ContentValues();
            valores.put("COD", cod);
            valores.put("DETALLE", detalle);
            valores.put("IMAGEN", img);

            try {
                db.insert("IMAGENES", "img", valores);
                db.close();
            } catch (Exception e) {
                db.close();
                sw1 = false;
            }
        } else {
            onCreate(db);
        }
        return sw1;
//---------------------------------------METODO PARA CONSULTAR UN REGISTRO------------------------

    }
}

