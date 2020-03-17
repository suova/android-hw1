package com.akuznecova.numbers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements List.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            fragmentManager.beginTransaction().add(R.id.fragment_container, new List())
                    .commit();
        }
    }

    @Override
    public void onItemSelected(String number, int color) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (currentFragment instanceof List) {
            fragmentManager.beginTransaction().replace(R.id.fragment_container, Elem.newInstance(number, color))
            .addToBackStack(null)
                    .commit();
        }

    }
}
