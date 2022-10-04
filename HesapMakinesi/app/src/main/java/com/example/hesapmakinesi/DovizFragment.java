package com.example.hesapmakinesi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.hesapmakinesi.adapter.DovizSpinnerAdapter;
import com.example.hesapmakinesi.dao.APIDao;
import com.example.hesapmakinesi.databinding.FragmentDovizBinding;
import com.example.hesapmakinesi.model.ExchangeData;
import com.example.hesapmakinesi.model.ExchangeResponse;
import com.example.hesapmakinesi.model.Responses;
import com.example.hesapmakinesi.model.Results;
import com.example.hesapmakinesi.retrofit.APIUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DovizFragment extends Fragment {
    private FragmentDovizBinding binding;
    private ArrayList<Button> butonlar;
    private ArrayList<Results> datas = new ArrayList<>();
    private APIDao apiDao;
    private String str = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDovizBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        apiDao = APIUtils.getDoviz();
        str = binding.textViewBaseRate.getText().toString();
        if(str.equals("0")){
            binding.textViewOtherRate.setText("0");
        }
        ButonRakamlar();
        VerileriGetir();
        ButonlarClick();
        Spinnerlar(binding.spinnerBase);
        Spinnerlar(binding.spinnerOther);
        return v;
    }
    private void Spinnerlar(Spinner spinner){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                str = "0";
                binding.textViewBaseRate.setText("0");
                binding.textViewOtherRate.setText("0");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void ButonRakamlar(){
        butonlar = new ArrayList<>();
        butonlar.add(binding.buttonDovizSifir);
        butonlar.add(binding.buttonDoviz1);
        butonlar.add(binding.buttonDoviz2);
        butonlar.add(binding.buttonDoviz3);
        butonlar.add(binding.buttonDoviz4);
        butonlar.add(binding.buttonDoviz5);
        butonlar.add(binding.buttonDoviz6);
        butonlar.add(binding.buttonDoviz7);
        butonlar.add(binding.buttonDoviz8);
        butonlar.add(binding.buttonDoviz9);
    }
    private void VerileriGetir(){
        apiDao.getCode().enqueue(new Callback<Responses>() {
            @Override
            public void onResponse(Call<Responses> call, Response<Responses> response) {
                datas = response.body().getResults();
                DovizSpinnerAdapter adapter = new DovizSpinnerAdapter(requireContext(), datas);
                binding.spinnerBase.setAdapter(adapter);
                binding.spinnerOther.setAdapter(adapter);
                binding.spinnerOther.setSelection(51);
                binding.spinnerBase.setSelection(datas.size()-1);
            }

            @Override
            public void onFailure(Call<Responses> call, Throwable t) {

            }
        });
    }

    private void Hesapla(){
        String base = datas.get(binding.spinnerBase.getSelectedItemPosition()).getCode();
        String to = datas.get(binding.spinnerOther.getSelectedItemPosition()).getCode();
        if(base.equals(to))
            binding.textViewOtherRate.setText(str);
        else{
            apiDao.getExchange(str, to, base).enqueue(new Callback<ExchangeResponse>() {
                @Override
                public void onResponse(Call<ExchangeResponse> call, Response<ExchangeResponse> response) {
                    ArrayList<ExchangeData> data = response.body().getResult().getDatas();
                    String guncel = response.body().getResult().getLastUpdate();
                    binding.textViewOtherRate.setText(data.get(0).getCalculatedstr());
                    binding.textViewGuncellik.setText(guncel);
                    binding.textViewGuncellik.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure(Call<ExchangeResponse> call, Throwable t) {

                }
            });
        }
    }

    private void ButonlarClick(){
        binding.buttonDovizTemizle.setOnClickListener(view -> {
            str = "0";
            binding.textViewBaseRate.setText("0");
            binding.textViewOtherRate.setText("0");
        });
        binding.imageButtonDovizSil.setOnClickListener(view -> {
            if(str.length() != 0){
                str = str.substring(0, str.length()-1);
                binding.textViewBaseRate.setText(str);
                str = binding.textViewBaseRate.getText().toString();
                Hesapla();
            }
        });
        butonlar.get(0).setOnClickListener(view -> {
            if(!str.equals("0")){
                binding.textViewBaseRate.setText(str + 0);
                str = binding.textViewBaseRate.getText().toString();
                Hesapla();
            }
        });
        for(int i = 0; i < butonlar.size(); i++){
            int finalI = i;
            butonlar.get(i).setOnClickListener(view -> {
                if(str.equals("0")){
                    str = "";
                    binding.textViewBaseRate.setText(str + finalI);
                    str = binding.textViewBaseRate.getText().toString();
                    Hesapla();
                }
                else{
                    str = binding.textViewBaseRate.getText().toString();
                    binding.textViewBaseRate.setText(str + finalI);
                    str = binding.textViewBaseRate.getText().toString();
                    Hesapla();
                }
            });
        }
    }
}