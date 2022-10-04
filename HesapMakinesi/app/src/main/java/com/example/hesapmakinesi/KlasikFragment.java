package com.example.hesapmakinesi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.hesapmakinesi.databinding.FragmentKlasikBinding;

import java.util.ArrayList;


public class KlasikFragment extends Fragment {
    private FragmentKlasikBinding binding;
    private ArrayList<Button> butonlar;
    private double sonuc = 0, eski=0;
    private double sonSayi;
    private String str = "0", ekran = "0"; char c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentKlasikBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ButonRakamlar();
        CiftSifir();
        Sifir();
        Butonlar();
        Virgul();
        ekran = binding.textViewEkran.getText().toString();
        Toplama();
        Cikarma();
        Carpma();
        Bolme();
        ModAlma();
        Temizle();
        Sil();
        Esittir();
        return view;
    }
    private void ButonRakamlar(){
        butonlar = new ArrayList<>();
        butonlar.add(binding.button1);
        butonlar.add(binding.button2);
        butonlar.add(binding.button3);
        butonlar.add(binding.button4);
        butonlar.add(binding.button5);
        butonlar.add(binding.button6);
        butonlar.add(binding.button7);
        butonlar.add(binding.button8);
        butonlar.add(binding.button9);
    }

    private void CiftSifir(){
        binding.buttonCiftSifir.setOnClickListener(v -> {
            if(!str.equals("0")){
                binding.textViewSonuc.setText(str+"00");
                str = binding.textViewSonuc.getText().toString();
                if(binding.textViewEkran.getText().toString().equals("0")){
                    sonuc = Double.valueOf(binding.textViewSonuc.getText().toString());
                }else{
                    sonSayi = Double.valueOf(binding.textViewSonuc.getText().toString());
                }
            }
        });
    }

    private void Sifir(){
        binding.buttonSifir.setOnClickListener(v -> {
            if(!str.equals("0")){
                binding.textViewSonuc.setText(str+"0");
                str = binding.textViewSonuc.getText().toString();
                if(binding.textViewEkran.getText().toString().equals("0")){
                    sonuc = Double.valueOf(binding.textViewSonuc.getText().toString());
                }else{
                    sonSayi = Double.valueOf(binding.textViewSonuc.getText().toString());
                }
            }
        });
    }

    private void Butonlar(){
        for(int i = 0; i<butonlar.size(); i++){
            int finalI = i+1;
            butonlar.get(i).setOnClickListener(v->{
                if(str.equals("0")){
                    str = "";
                    binding.textViewSonuc.setText(str+finalI);
                    str = binding.textViewSonuc.getText().toString();
                }
                else if(!str.equals("0")){
                    binding.textViewSonuc.setText(str+finalI);
                    str = binding.textViewSonuc.getText().toString();
                }
                if(binding.textViewEkran.getText().toString().equals("0")){
                    sonuc = Double.valueOf(binding.textViewSonuc.getText().toString());
                }else{
                    sonSayi = Double.valueOf(binding.textViewSonuc.getText().toString());
                }
            });
        }
    }

    private void Toplama(){
        binding.buttonArti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = ekran.charAt(ekran.length()-1);
                if(c=='-' || c=='x' || c=='/'){
                    binding.textViewEkran.setText(sonuc+"+");
                    binding.textViewSonuc.setText(String.valueOf(sonuc));
                }
                else{
                    if(sonSayi != 0){
                        sonuc += sonSayi;
                        if(intMiDoubleMi(sonuc)){
                            int deger = (int) sonuc;
                            binding.textViewEkran.setText(deger+"+");
                            binding.textViewSonuc.setText(String.valueOf(deger));
                        }else{
                            binding.textViewEkran.setText(sonuc+"+");
                            binding.textViewSonuc.setText(String.valueOf(sonuc));
                        }
                    }
                    else{
                        if(intMiDoubleMi(sonuc)){
                            int deger = (int) sonuc;
                            binding.textViewEkran.setText(deger+"+");
                            binding.textViewSonuc.setText(String.valueOf(deger));
                        }else{
                            binding.textViewEkran.setText(sonuc+"+");
                            binding.textViewSonuc.setText(String.valueOf(sonuc));
                        }
                    }
                }
                binding.textViewEkran.setVisibility(View.VISIBLE);
                sonSayi = sonuc;
                str="";
            }
        });
    }

    private void Cikarma(){
        binding.buttoneksi.setOnClickListener(v->{
            c = ekran.charAt(ekran.length()-1);
            if(c=='+' || c=='x' || c=='/'){
                binding.textViewEkran.setText(sonuc+"-");
                binding.textViewSonuc.setText(String.valueOf(sonuc));
            }
            else{
                if(sonSayi != 0){
                    sonuc -= sonSayi;
                    if(intMiDoubleMi(sonuc)){
                        int deger = (int) sonuc;
                        binding.textViewEkran.setText(deger+"-");
                        binding.textViewSonuc.setText(String.valueOf(deger));
                    }else{
                        binding.textViewEkran.setText(sonuc+"-");
                        binding.textViewSonuc.setText(String.valueOf(sonuc));
                    }
                }
                else{
                    if(intMiDoubleMi(sonuc)){
                        int deger = (int) sonuc;
                        binding.textViewEkran.setText(deger+"-");
                        binding.textViewSonuc.setText(String.valueOf(deger));
                    }else{
                        binding.textViewEkran.setText(sonuc+"-");
                        binding.textViewSonuc.setText(String.valueOf(sonuc));
                    }
                }
            }
            binding.textViewEkran.setVisibility(View.VISIBLE);
            sonSayi = sonuc;
            str="";
        });
    }

    private void Carpma(){
        binding.buttoncarpi.setOnClickListener(v->{
            c = ekran.charAt(ekran.length()-1);
            if(c=='+' || c=='-' || c=='/'){
                binding.textViewEkran.setText(sonuc+"x");
                binding.textViewSonuc.setText(String.valueOf(sonuc));
            }
            else{
                if(sonSayi != 0){
                    sonuc *= sonSayi;
                    if(intMiDoubleMi(sonuc)){
                        int deger = (int) sonuc;
                        binding.textViewEkran.setText(deger+"x");
                        binding.textViewSonuc.setText(String.valueOf(deger));
                    }else{
                        binding.textViewEkran.setText(sonuc+"x");
                        binding.textViewSonuc.setText(String.valueOf(sonuc));
                    }
                }
                else{
                    if(intMiDoubleMi(sonuc)){
                        int deger = (int) sonuc;
                        binding.textViewEkran.setText(deger+"x");
                        binding.textViewSonuc.setText(String.valueOf(deger));
                    }else{
                        binding.textViewEkran.setText(sonuc+"x");
                        binding.textViewSonuc.setText(String.valueOf(sonuc));
                    }
                }
            }
            binding.textViewEkran.setVisibility(View.VISIBLE);
            sonSayi = sonuc;
            str="";
        });
    }

    private void Bolme(){
        binding.buttonBol.setOnClickListener(v->{
            c = ekran.charAt(ekran.length()-1);
            if(c=='+' || c=='x' || c=='-'){
                binding.textViewEkran.setText(sonuc+"/");
                binding.textViewSonuc.setText(String.valueOf(sonuc));
            }
            else{
                if(sonSayi != 0){
                    sonuc /= sonSayi;
                    if(intMiDoubleMi(sonuc)){
                        int deger = (int) sonuc;
                        binding.textViewEkran.setText(deger+"/");
                        binding.textViewSonuc.setText(String.valueOf(deger));
                    }else{
                        binding.textViewEkran.setText(sonuc+"/");
                        binding.textViewSonuc.setText(String.valueOf(sonuc));
                    }
                }
                else{
                    if(intMiDoubleMi(sonuc)){
                        int deger = (int) sonuc;
                        binding.textViewEkran.setText(deger+"/");
                        binding.textViewSonuc.setText(String.valueOf(deger));
                    }else{
                        binding.textViewEkran.setText(sonuc+"/");
                        binding.textViewSonuc.setText(String.valueOf(sonuc));
                    }
                }
            }
            binding.textViewEkran.setVisibility(View.VISIBLE);
            sonSayi = sonuc;
            str="";
        });
    }

    private void ModAlma(){
       binding.buttonYuzde.setOnClickListener(v->{
           if(sonuc!=0){
               sonuc = sonuc / 100;
               if(intMiDoubleMi(sonuc)){
                   int deger = (int) sonuc;
                   binding.textViewSonuc.setText(String.valueOf(deger));
                   binding.textViewEkran.setText(String.valueOf(deger));
               }else{
                   binding.textViewSonuc.setText(String.valueOf(sonuc));
                   binding.textViewEkran.setText(String.valueOf(sonuc));
               }
           }
           else{
               sonSayi = sonSayi/100;
               if(intMiDoubleMi(sonSayi)){
                   int deger = (int) sonSayi;
                   binding.textViewSonuc.setText(String.valueOf(deger));
                   binding.textViewEkran.setText(String.valueOf(deger));
               }else{
                   binding.textViewSonuc.setText(String.valueOf(sonSayi));
                   binding.textViewEkran.setText(String.valueOf(sonSayi));
               }
           }
           binding.textViewEkran.setVisibility(View.VISIBLE);
           str = "";
           sonSayi = 0;
       });
    }

    private boolean intMiDoubleMi(double deger){
        int sayi = (int)deger;
        double sayi1 = (double) sayi;
        if(sayi1-deger==0)
            return true;
        else
            return false;

    }

    private void EsittirIslem(String c){
        if(intMiDoubleMi(sonuc)){
            int deger = (int) sonuc;
            if(intMiDoubleMi(eski)){
                if(intMiDoubleMi(sonSayi)){
                    int deger2 = (int) sonSayi;
                    binding.textViewEkran.setText((int)eski+c+deger2+"="+ deger);
                    binding.textViewSonuc.setText(String.valueOf(deger));
                }
                else{
                    binding.textViewEkran.setText((int)eski+c+sonSayi+"="+ deger);
                    binding.textViewSonuc.setText(String.valueOf(deger));
                }
            }else{
                if(intMiDoubleMi(sonSayi)){
                    int deger2 = (int) sonSayi;
                    binding.textViewEkran.setText(eski+c+deger2+"="+ deger);
                    binding.textViewSonuc.setText(String.valueOf(deger));
                }
                else{
                    binding.textViewEkran.setText((eski+c+sonSayi+"="+ deger));
                    binding.textViewSonuc.setText(String.valueOf(deger));
                }
            }
        }else{
            if(intMiDoubleMi(eski)){
                if(intMiDoubleMi(sonSayi)){
                    int deger2 = (int) sonSayi;
                    binding.textViewEkran.setText((int)eski+c+deger2+"="+ sonuc);
                    binding.textViewSonuc.setText(String.valueOf(sonuc));
                }
                else{
                    binding.textViewEkran.setText((int)eski+c+sonSayi+"="+ sonuc);
                    binding.textViewSonuc.setText(String.valueOf(sonuc));
                }
            }else{
                if(intMiDoubleMi(sonSayi)){
                    int deger2 = (int) sonSayi;
                    binding.textViewEkran.setText(eski+c+deger2+"="+ sonuc);
                    binding.textViewSonuc.setText(String.valueOf(sonuc));
                }
                else{
                    binding.textViewEkran.setText((eski+c+sonSayi+"="+ sonuc));
                    binding.textViewSonuc.setText(String.valueOf(sonuc));
                }
            }
        }
    }

    private void Temizle(){
        binding.buttonTemizle.setOnClickListener(v->{
            str="";
            sonSayi = 0;
            sonuc=0;
            binding.textViewSonuc.setText("0");
            binding.textViewEkran.setText("0");
            binding.textViewEkran.setVisibility(View.INVISIBLE);
        });
    }

    private void Virgul(){
        binding.buttonVirgul.setOnClickListener(v->{
            binding.textViewSonuc.setText(str+".");
            str = binding.textViewSonuc.getText().toString();
            if(binding.textViewEkran.getText().toString().equals("0")){
                sonuc = Double.valueOf(binding.textViewSonuc.getText().toString());
            }else{
                sonSayi = Double.valueOf(binding.textViewSonuc.getText().toString());
            }
        });
    }

    private void Esittir(){
        binding.buttonEsittir.setOnClickListener(v->{
            ekran = binding.textViewEkran.getText().toString();
            c = ekran.charAt(ekran.length()-1);
            eski = sonuc;
            switch (c){
                case '+':
                    sonuc+=sonSayi;
                    EsittirIslem("+");
                    break;
                case '-':
                    sonuc-=sonSayi;
                    EsittirIslem("-");
                    break;
                case 'x':
                    sonuc*=sonSayi;
                    EsittirIslem("x");
                    break;
                case '/':
                    sonuc/=sonSayi;
                    EsittirIslem("/");
                    break;
                default:
                    binding.textViewEkran.setText(String.valueOf(sonuc));
                    binding.textViewEkran.setText(String.valueOf(sonuc));
            }
            binding.textViewEkran.setVisibility(View.VISIBLE);
            sonSayi=0;
            str="";
        });
    }

    private void Sil(){
        binding.imageButtonSil.setOnClickListener(v->{
            if(str.length()!=0){
                str = str.substring(0, str.length()-1);
                binding.textViewSonuc.setText(str);
            }
        });
    }
}