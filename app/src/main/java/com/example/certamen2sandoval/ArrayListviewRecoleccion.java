package com.example.certamen2sandoval;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;
//Diego Sandoval 20619.149-K
public class ArrayListviewRecoleccion extends ArrayAdapter<ClaseRecoleccion> {
    View ListaRecoleccion;

    public ArrayListviewRecoleccion(@NonNull Context context, int resource, List<ClaseRecoleccion> objects)
    { super(context, resource, objects); }

    @Override
    public View getView(int position, View covertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ListaRecoleccion = covertView;
        ListaRecoleccion = inflater.inflate(R.layout.recoleccionlist, parent, false);

        TextView fecha = (TextView) ListaRecoleccion.findViewById(R.id.lblFecha);
        TextView CodigoPlanta = (TextView) ListaRecoleccion.findViewById(R.id.lblCodigoPlanta);
        TextView RUTCientifico = (TextView) ListaRecoleccion.findViewById(R.id.lblRUTCientifico);
        TextView Comentario = (TextView) ListaRecoleccion.findViewById(R.id.lblComentario);
        ImageView imagenRecoleccion = (ImageView) ListaRecoleccion.findViewById(R.id.imagenRecoleccion);

        ClaseRecoleccion recoleccion = getItem(position);
        fecha.setText(recoleccion.getFecha() + "");
        CodigoPlanta.setText(recoleccion.getCodigoPlanta() + "");
        RUTCientifico.setText(recoleccion.getRUTCientifico()+"");
        Comentario.setText(recoleccion.getComentario());
        Bitmap image = BitmapFactory.decodeByteArray(recoleccion.getFotoLugar(), 0, recoleccion.getFotoLugar().length);
        imagenRecoleccion.setImageBitmap(image);
        return ListaRecoleccion;
    }
}
