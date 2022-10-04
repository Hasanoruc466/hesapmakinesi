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
import com.example.hesapmakinesi.model.Results;

import java.util.ArrayList;

public class DovizSpinnerAdapter extends ArrayAdapter<Results> {
    private Context context;
    private ArrayList<Results> liste;
    public DovizSpinnerAdapter(Context context, ArrayList<Results> liste){
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
        textView.setText(liste.get(position).toString());
        return convertView;
    }
}
