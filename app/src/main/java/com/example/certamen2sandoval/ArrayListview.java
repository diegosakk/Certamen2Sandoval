package com.example.certamen2sandoval;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.certamen2sandoval.R;

import java.util.ArrayList;
import java.util.List;
public class ArrayListview extends ArrayAdapter <ClaseCientifico> {
    View ListaCientifico;

    public ArrayListview(@NonNull Context context, int resource, List<ClaseCientifico> objects)
    { super(context, resource, objects); }

    @Override
    public View getView(int position, View covertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ListaCientifico = covertView;
        ListaCientifico = inflater.inflate(R.layout.cientificolist, parent, false);

        TextView id = (TextView) ListaCientifico.findViewById(R.id.lblID);
        TextView nombres = (TextView) ListaCientifico.findViewById(R.id.lblNombres);
        TextView rut = (TextView) ListaCientifico.findViewById(R.id.lblRUT);

        ClaseCientifico cientifico = getItem(position);
        id.setText(cientifico.getIdCientifico() + "");
        nombres.setText(cientifico.getNombreCientifico() + " " + cientifico.getApellidosCientifico());
        rut.setText(cientifico.getRUTCientifico()+"");
        return ListaCientifico;
    }

}
