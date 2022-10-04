package com.example.hesapmakinesi;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.example.hesapmakinesi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navHostFragment);
        NavigationUI.setupWithNavController(binding.navView, navHostFragment.getNavController());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar
                ,0,0);
        toggle.getDrawerArrowDrawable().setColor(Color.rgb(192, 202, 51));
        binding.drawer.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.inflateHeaderView(R.layout.drawer_baslik_layout);
    }

    @Override
    public void onBackPressed() {
        if(binding.drawer.isDrawerOpen(GravityCompat.START))
            binding.drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}