package com.example.certamen2sandoval;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BDSandoval extends SQLiteOpenHelper {
    public String Tablas;
    private static final int VERSION_BASEDATOS = 1;



    // constructor
    public BDSandoval(Context context) {
        super(context, "BDSandoval", null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE CIENTIFICOSANDOVAL" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, rut TEXT unique,nombre TEXT , apellido TEXT, genero TEXT)");

        db.execSQL("CREATE TABLE PLANTASANDOVAL" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, CodigoPlanta TEXT, NombrePlanta TEXT, NombreCientifico TEXT, ImagenPlanta BLOB, Uso TEXT )");

        db.execSQL("CREATE TABLE RECOLECCIONSANDOVAL" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT, CodigoPlanta TEXT, RUTCientifico TEXT, comentario TEXT, imagenLugar BLOB,latitud REAL,longitud REAL )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // genere codigo correspondiente
    }

    public ArrayList<ClaseCientifico> ListarCientificos(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<ClaseCientifico> cientificos = new ArrayList<ClaseCientifico>();
        ClaseCientifico cientifico = new ClaseCientifico();
        try {
            Cursor c = db.rawQuery("SELECT * FROM CIENTIFICOSANDOVAL", null);

            if (c.getCount() > 0) {
                while (c.moveToNext()){
                    cientifico = new ClaseCientifico(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3), c.getString(4));
                    cientificos.add(cientifico);
                }
                this.close();
                c.close();

                return cientificos;
            } else {
                this.close();
                c.close();

                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public String RegistrarCientifico(ClaseCientifico cientificos) {
        String insertar = "exito";
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("rut", cientificos.getRUTCientifico()+"");
            valores.put("nombre", cientificos.getNombreCientifico());
            valores.put("apellido", cientificos.getApellidosCientifico());
            valores.put("genero", cientificos.getGeneroCientifico());

            try {
                db.insert("CIENTIFICOSANDOVAL", "", valores);
                db.close();
            } catch (Exception e) {
                db.close();
                insertar = "error";
            }
        } else {
            onCreate(db);
        }
        return insertar;
    }

    public String RegistrarPlanta(ClasePlanta planta) {
        String insertar = "exito";
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("CodigoPlanta", planta.getCodigoPlanta()+"");
            valores.put("NombrePlanta", planta.getNombrePlanta());
            valores.put("NombreCientifico", planta.getNombreCientifico());
            //valores.put("ImagenPlanta", planta.getImagenPlanta());
            valores.put("Uso", planta.getUso());

            try {
                db.insert("PLANTASANDOVAL", "", valores);
                db.close();
            } catch (Exception e) {
                db.close();
                insertar = "error";
            }
        } else {
            onCreate(db);
        }
        return insertar;
    }
    public ArrayList<ClasePlanta> ListarPlantas(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<ClasePlanta> plantas = new ArrayList<ClasePlanta>();
        ClasePlanta planta = new ClasePlanta();
        try {
            Cursor c = db.rawQuery("SELECT * FROM PLANTASANDOVAL", null);

            if (c.getCount() > 0) {
                while (c.moveToNext()){
                    //planta = new ClasePlanta(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3), c.getBlob(4), c.getString(5));
                    plantas.add(planta);
                }
                this.close();
                c.close();

                return plantas;
            } else {
                this.close();
                c.close();

                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}

