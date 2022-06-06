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
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, codigoPlanta TEXT, nombrePlanta TEXT, nombreCientifico TEXT, imagenPlanta BLOB, uso TEXT )");

        db.execSQL("CREATE TABLE RECOLECCIONSANDOVAL" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT, codigoPlanta TEXT, rutCientifico TEXT, comentario TEXT, imagenLugar BLOB,latitud REAL,longitud REAL )");
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
}

