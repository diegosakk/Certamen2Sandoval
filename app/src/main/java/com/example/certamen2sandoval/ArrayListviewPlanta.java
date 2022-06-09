package com.example.certamen2sandoval;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;
public class ArrayListviewPlanta extends ArrayAdapter <ClasePlanta> {
    View ListaPlanta;

    public ArrayListviewPlanta(@NonNull Context context, int resource, List<ClasePlanta> objects)
    { super(context, resource, objects); }

    @Override
    public View getView(int position, View covertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ListaPlanta = covertView;
        ListaPlanta = inflater.inflate(R.layout.plantalist, parent, false);

        TextView id = (TextView) ListaPlanta.findViewById(R.id.lblIdPlanta);
        TextView nombrePlanta = (TextView) ListaPlanta.findViewById(R.id.lblNombrePlanta);
        TextView nombreCientifico = (TextView) ListaPlanta.findViewById(R.id.lblNombreCientifico);
        TextView uso = (TextView) ListaPlanta.findViewById(R.id.lblUso);

        ClasePlanta planta = getItem(position);
        id.setText(planta.getIdPlanta() + "");
        nombrePlanta.setText(planta.getNombrePlanta() + "");
        nombreCientifico.setText(planta.getNombreCientifico() + "");
        uso.setText(planta.getUso());
        return ListaPlanta;
    }
}
