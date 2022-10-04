package com.example.hesapmakinesi;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.hesapmakinesi.adapter.OzelSpinnerAdapter;
import com.example.hesapmakinesi.databinding.FragmentProgramlayiciBinding;
import com.example.hesapmakinesi.model.TabanCevirme;

import java.util.ArrayList;
import java.util.Objects;


public class ProgramlayiciFragment extends Fragment {
    private FragmentProgramlayiciBinding binding;
    private ArrayList<String> spinnerArray;
    private ArrayList<Button> butonlar = new ArrayList<>();ArrayList<Button> harfler= new ArrayList<>();
    private OzelSpinnerAdapter adapter;
    private String str = "0", binary = "", hexadecimal = "", octal = "";
    private long decimal = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProgramlayiciBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        setSpinnerArray();
        ButonRakamlar();
        ButonHarfler();
        Temizle();
        Sil();
        setSpinner();
        return view;
    }

    private void setSpinnerArray(){
        spinnerArray = new ArrayList<>();
        spinnerArray.add("Decimal (Onluk Sistem)");
        spinnerArray.add("Hexadecimal (Onaltılık Sistem)");
        spinnerArray.add("Octal (Sekizlik Sistem)");
        spinnerArray.add("Binary (İkilik Sistem)");
        adapter = new OzelSpinnerAdapter(requireContext(), spinnerArray);
        binding.spinner.setAdapter(adapter);
    }

    private void Temizle(){
        binding.buttonTemizleP.setOnClickListener(v->{
            str = "";
            binding.textViewEkranP.setText("0");
            binding.textViewoct.setText("0");
            binding.textViewhex.setText("0");
            binding.textViewdec.setText("0");
            binding.textViewbin.setText("0");
        });
    }

    private void Sil(){
        binding.imageButtonSilP.setOnClickListener(v->{
            if(str.length()!=0){
                str = str.substring(0, str.length()-1);
                binding.textViewEkranP.setText(str);
                if(!Objects.equals(str, ""))
                    Yazdirma();
                else{
                    binding.textViewEkranP.setText("0");
                    binding.textViewoct.setText("0");
                    binding.textViewhex.setText("0");
                    binding.textViewdec.setText("0");
                    binding.textViewbin.setText("0");
                }
            }
        });
    }

    private void setSpinner(){
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                str = "";
                binding.textViewEkranP.setText("0");
                binding.textViewbin.setText("0");
                binding.textViewdec.setText(String.valueOf("0"));
                binding.textViewhex.setText("0");
                binding.textViewoct.setText("0");
                if(spinnerArray.get(0).equals(spinnerArray.get(position))){
                    ButonRakamlarClick(0, 10);
                    for(int i=2; i < butonlar.size(); i++) {
                        butonlar.get(i).setTextColor(Color.BLACK);
                    }
                    for (int i = 0; i < harfler.size(); i++)
                        harfler.get(i).setTextColor(Color.rgb(207, 200, 200));
                }
                else if(spinnerArray.get(1).equals(spinnerArray.get(position))){
                    ButonRakamlarClick(0, 10);
                    ButonHarflerClick();
                    for (int i = 0; i < harfler.size(); i++)
                        harfler.get(i).setTextColor(Color.rgb(67, 160, 71));
                }
                else if(spinnerArray.get(2).equals(spinnerArray.get(position))) {
                    ButonRakamlarClick(0, 8);
                    for (int i = 2; i < butonlar.size(); i++){
                        if(i<8)
                            butonlar.get(i).setTextColor(Color.BLACK);
                        else
                            butonlar.get(i).setTextColor(Color.rgb(207, 200, 200));
                    }
                    for(int i = 0; i < harfler.size(); i++)
                        harfler.get(i).setTextColor(Color.rgb(207, 200, 200));

                }
                else{
                    ButonRakamlarClick(0,2);
                    for(int i=2; i < butonlar.size(); i++)
                        butonlar.get(i).setTextColor(Color.rgb(207, 200, 200));

                    for (int i = 0; i < harfler.size(); i++)
                        harfler.get(i).setTextColor(Color.rgb(207, 200, 200));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void Yazdirma(){
        if(spinnerArray.get(0).equals(spinnerArray.get(binding.spinner.getSelectedItemPosition()))){
            decimal = Long.parseLong(str);
            binary = new TabanCevirme().onlukToIkilik(decimal);
            octal = new TabanCevirme().onlukToSekizlik(decimal);
            hexadecimal = new TabanCevirme().onlukToOnaltilik(decimal);
        }

        else if(spinnerArray.get(1).equals(spinnerArray.get(binding.spinner.getSelectedItemPosition()))){
            hexadecimal = str;
            decimal = new TabanCevirme().onaltilikToOnluk(hexadecimal);
            binary = new TabanCevirme().onlukToIkilik(decimal);
            octal = new TabanCevirme().onlukToSekizlik(decimal);
        }

        else if(spinnerArray.get(2).equals(spinnerArray.get(binding.spinner.getSelectedItemPosition()))){
            octal = str;
            decimal = new TabanCevirme().sekizlikToOnluk(Long.parseLong(octal));
            binary = new TabanCevirme().onlukToIkilik(decimal);
            hexadecimal = new TabanCevirme().onlukToOnaltilik(decimal);
        }

        else{
            binary = str;
            decimal = new TabanCevirme().ikilikToOnluk(Long.parseLong(binary));
            octal = new TabanCevirme().onlukToSekizlik(decimal);
            hexadecimal = new TabanCevirme().onlukToOnaltilik(decimal);
        }
        binding.textViewbin.setText(binary);
        binding.textViewdec.setText(String.valueOf(decimal));
        binding.textViewhex.setText(hexadecimal);
        binding.textViewoct.setText(octal);
    }

    private void ButonHarfler(){
        harfler.add(binding.buttonA);
        harfler.add(binding.buttonB);
        harfler.add(binding.buttonC);
        harfler.add(binding.buttonD);
        harfler.add(binding.buttonE);
        harfler.add(binding.buttonF);
    }

    private void ButonRakamlar(){
        butonlar = new ArrayList<>();
        butonlar.add(binding.buttonPSifir);
        butonlar.add(binding.button1P);
        butonlar.add(binding.button2P);
        butonlar.add(binding.button3P);
        butonlar.add(binding.button4P);
        butonlar.add(binding.button5P);
        butonlar.add(binding.button6P);
        butonlar.add(binding.button7P);
        butonlar.add(binding.button8P);
        butonlar.add(binding.button9P);
    }

    private void ButonRakamlarClick(int bas, int bit){
        butonlar.get(0).setOnClickListener(v->{
            if(!str.equals("0")){
                binding.textViewEkranP.setText(str+"0");
                str = binding.textViewEkranP.getText().toString();
                Yazdirma();
            }
        });
        for(int i = bas+1; i < bit; i++){
            int finalI = i;
            butonlar.get(i).setOnClickListener(v->{
                if(str.equals("0")){
                    str = "";
                    binding.textViewEkranP.setText(str+ finalI);
                    str = binding.textViewEkranP.getText().toString();
                    Yazdirma();
                }
                else{
                    binding.textViewEkranP.setText(str+ finalI);
                    str = binding.textViewEkranP.getText().toString();
                    Yazdirma();
                }
            });
        }

    }

    private void ButonHarflerClick (){
        for(int i = 0; i < harfler.size(); i++){
            switch (i){
                case 0:
                    harfler.get(i).setOnClickListener(v->{
                        if(str.equals("0")){
                            str = "";
                            HarfYaz('A');
                            Yazdirma();
                        }
                        else{
                            HarfYaz('A');
                            Yazdirma();
                        }
                    });
                    break;
                case 1:
                    harfler.get(i).setOnClickListener(v->{
                        if(str.equals("0")){
                            str = "";
                            HarfYaz('B');
                            Yazdirma();
                        }
                        else{
                            HarfYaz('C');
                            Yazdirma();
                        }
                    });
                    break;
                case 2:
                    harfler.get(i).setOnClickListener(v->{
                        if(str.equals("0")){
                            str = "";
                            HarfYaz('C');
                            Yazdirma();
                        }
                        else{
                            HarfYaz('C');
                            Yazdirma();
                        }
                    });
                    break;
                case 3:
                    harfler.get(i).setOnClickListener(v->{
                        if(str.equals("0")){
                            str = "";
                            HarfYaz('D');
                            Yazdirma();
                        }
                        else{
                            HarfYaz('D');
                            Yazdirma();
                        }
                    });
                    break;
                case 4:
                    harfler.get(i).setOnClickListener(v->{
                        if(str.equals("0")){
                            str = "";
                            HarfYaz('E');
                            Yazdirma();
                        }
                        else{
                            HarfYaz('E');
                            Yazdirma();
                        }
                    });
                    break;
                case 5:
                    harfler.get(i).setOnClickListener(v->{
                        if(str.equals("0")){
                            str = "";
                            HarfYaz('F');
                            Yazdirma();
                        }
                        else{
                            HarfYaz('F');
                            Yazdirma();
                        }
                    });
                    break;
            }
        }
    }

    private void HarfYaz(char harf){
        binding.textViewEkranP.setText(str+ harf);
        str = binding.textViewEkranP.getText().toString();
    }

}