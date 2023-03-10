package com.example.momeninersia;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.momeninersia.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final ArrayList<Inertia> list = new ArrayList<>();
    private boolean isLinear = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.tv_app));
        }

        if (getIntent() != null){
            isLinear = getIntent().getBooleanExtra("isLinear",true);
        }
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvInertia.setHasFixedSize(true);

        list.addAll(getListInertia());
        showRecyclerList();
    }

    public ArrayList<Inertia> getListInertia() {
        String[] dataTitle = getResources().getStringArray(R.array.array_title);
        String[] dataFormula = getResources().getStringArray(R.array.array_formula);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.array_image);

        ArrayList<Inertia> listInertia = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++) {
            Inertia inertia = new Inertia();
            inertia.setTitle(dataTitle[i]);
            inertia.setFormula(dataFormula[i]);
            inertia.setAvatar(dataPhoto.getResourceId(i, -1));
            listInertia.add(inertia);
        }
        System.out.println(listInertia);
        return listInertia;
    }

    private void showRecyclerList() {
        if (isLinear){
            binding.rvInertia.setLayoutManager(new LinearLayoutManager(this));
        }else{
            binding.rvInertia.setLayoutManager(new GridLayoutManager(this, 2));
        }

        ListInertiaAdapter listInertiaAdapter = new ListInertiaAdapter(list);
        binding.rvInertia.setAdapter(listInertiaAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        if (isLinear){
            MenuItem item = menu.findItem(R.id.menu_linear);
            item.setVisible(false);
        }else{
            MenuItem item = menu.findItem(R.id.menu_grid);
            item.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_grid:
                isLinear = false;
                Intent intentGrid = new Intent(MainActivity.this,MainActivity.class);
                intentGrid.putExtra("isLinear",isLinear);
                startActivity(intentGrid);
                finish();
                return true;
            case R.id.menu_linear:
                isLinear = true;
                Intent intentLinear = new Intent(MainActivity.this,MainActivity.class);
                intentLinear.putExtra("isLinear",isLinear);
                startActivity(intentLinear);
                finish();
                return true;
            case R.id.menu_language:
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}