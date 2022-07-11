package com.example.certamen2sandoval;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
//Diego Sandoval 20619.149-K
public class BDSandoval extends SQLiteOpenHelper {
    public String Tablas;
    private static final int VERSION_BASEDATOS = 1;
    BDSandoval bd;



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
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT, CodigoPlanta TEXT, RUTCientifico TEXT, comentario TEXT, imagenLugar BLOB,latitud DOUBLE,longitud DOUBLE)");
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
                    cientifico = new ClaseCientifico(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4));
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
            valores.put("rut", cientificos.getRUTCientifico());
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
            valores.put("ImagenPlanta", planta.getImagenPlanta());
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

            if (c.moveToFirst()) {
                do {
                    planta = new ClasePlanta(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getBlob(4), c.getString(5));
                    plantas.add(planta);
                }
                while (c.moveToNext());
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

    public String RegistrarRecoleccion(ClaseRecoleccion recoleccion) {
        String insertar = "exito";
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("fecha", recoleccion.getFecha());
            valores.put("CodigoPlanta", recoleccion.getCodigoPlanta());
            valores.put("RUTCientifico", recoleccion.getRUTCientifico());
            valores.put("comentario", recoleccion.getComentario());
            valores.put("imagenLugar", recoleccion.getFotoLugar());
            valores.put("latitud", recoleccion.getLatitud());
            valores.put("longitud", recoleccion.getLongitud());

            try {
                db.insert("RECOLECCIONSANDOVAL", "", valores);
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
    public ArrayList<ClaseRecoleccion> ListarRecoleccion(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<ClaseRecoleccion> recolecciones = new ArrayList<ClaseRecoleccion>();
        ClaseRecoleccion recoleccion = new ClaseRecoleccion();
        try {
            Cursor c = db.rawQuery("SELECT * FROM RECOLECCIONSANDOVAL", null);

            if (c.moveToFirst()) {
                do {
                    recoleccion = new ClaseRecoleccion(c.getInt(0),c.getString(1), c.getInt(2), c.getString(3), c.getString(4), c.getBlob(5), c.getDouble(6),c.getDouble(7));
                    recolecciones.add(recoleccion);
                }
                while (c.moveToNext());
                this.close();
                c.close();

                return recolecciones;
            } else {
                this.close();
                c.close();

                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean editCientifico(ClaseCientifico cientifico){
        boolean update = true;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {

            String query = "UPDATE CientificoSandoval " +
                    "SET rut = '" + cientifico.getRUTCientifico() + "', nombre = '" + cientifico.getNombreCientifico() + "'," + "apellido = '" + cientifico.getApellidosCientifico() + "',  genero = '" + cientifico.getGeneroCientifico() + "' " + "WHERE id = " +  cientifico.getIdCientifico() + "";
            try {
                db.execSQL(query);
                db.close();

            } catch (Exception e) {
                db.close();
                update = false;

            }
        } else {
            onCreate(db);
        }
        return update;
    }

    public boolean EliminarCientifico(String nombre){
        boolean delete = true;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){
            String query = "DELETE FROM CientificoSandoval WHERE nombre = '" + nombre + "'";
            try {
                db.execSQL(query);
                db.close();
            } catch (Exception e) {
                db.close();
                delete = false;
            }
        }
        else {
            onCreate(db);
        }
        return delete;
    }

    public boolean checkRecoleccionCientifico(String nombre) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<ClaseCientifico> cientificos = new ArrayList<ClaseCientifico>();
        ClaseCientifico cientifico;
        try {
            Cursor c = db.rawQuery("SELECT * FROM RecolecciónSandoval WHERE nombreCientifico = '"+nombre+"'", null);

            if (c.moveToFirst()) {
                this.close();
                c.close();
                return true;
            } else {
                this.close();
                c.close();

                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    public boolean editPlanta(ClasePlanta planta){
        boolean update = true;
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            ContentValues valores = new ContentValues();
            valores.put("codigoPlanta", planta.getCodigoPlanta());
            valores.put("nombrePlanta", planta.getNombrePlanta());
            valores.put("nombreCientifico", planta.getNombreCientifico());
            valores.put("ImagenPlanta", planta.getImagenPlanta());
            valores.put("uso",  planta.getUso());
            try {
                bd.update("PlantaSandoval",valores,"id = "+planta.getIdPlanta(),null);
                bd.close();
            } catch (Exception e) {
                bd.close();
                update = false;
            }
        } else {
            onCreate(bd);
        }
        return update;
    }

    public boolean EliminarPlanta(int id){
        boolean delete = true;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){
            String query = "DELETE FROM PlantaSandoval WHERE id = " + id + "";
            try {
                db.execSQL(query);
                db.close();
            } catch (Exception e) {
                db.close();
                delete = false;
            }
        }
        else {
            onCreate(db);
        }
        return delete;
    }

    public boolean checkRecoleccionPlanta(String codigo) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<ClasePlanta> plantas = new ArrayList<ClasePlanta>();
        ClasePlanta planta;
        try {
            Cursor c = db.rawQuery("SELECT * FROM RecoleccionSandoval WHERE codigoPlanta = '"+codigo+"'", null);

            if (c.moveToFirst()) {
                this.close();
                c.close();
                return true;
            } else {
                this.close();
                c.close();

                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}

