package com.example.cosmeticsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

public class MainActivity extends AppCompatActivity {
    private static  final String PREFER_NAME="pref";
    private static  final String KEY_EMAIL="email_key";
    private static  final String KEY_PASSWORD="pass_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null) {
            getFragmentManager().beginTransaction().replace(R.id.frame, MainFragment.newInstance()).commit();
        }
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        getFragmentManager().beginTransaction().replace(R.id.frame, MainFragment.newInstance()).addToBackStack(null).commitAllowingStateLoss();
                        break;
                    case R.id.saved:
                        getFragmentManager().beginTransaction().replace(R.id.frame, FavouritesFragment.newInstance()).addToBackStack(null).commitAllowingStateLoss();
                    break;
                    case R.id.logout:
                        SharedPreferences preferences=getSharedPreferences(PREFER_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString(KEY_EMAIL,"");
                        editor.putString(KEY_PASSWORD, "");
                        editor.apply();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                return true;
            }
        });

    }
}
