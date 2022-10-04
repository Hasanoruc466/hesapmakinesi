package com.example.hesapmakinesi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hesapmakinesi.R;

import java.util.ArrayList;

public class OzelSpinnerAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> liste;

    public OzelSpinnerAdapter( Context context, ArrayList<String> liste) {
        super(context, 0, liste);
        this.context = context;
        this.liste = liste;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(context).inflate(R.layout.spinner_layout, parent, false);
        TextView textView = convertView.findViewById(R.id.textViewSpinner);
        textView.setText(liste.get(position));
        return convertView;
    }
}
