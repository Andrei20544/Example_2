package com.example.example_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment,new ServiceFragment());
        fragmentTransaction.commit();
        bottomNavigation=findViewById(R.id.bottomNav);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.serviceFragment:
                        fragment=new ServiceFragment();
                        break;
                    case R.id.newsFragment:
                        fragment=new NewsFragment();
                        break;
                    case  R.id.profileFragment:
                        fragment=new ProfileFragment();
                        break;
                }
                fragmentTransaction.replace(R.id.fragment,fragment);
                fragmentTransaction.commit();
                return true;
            }
        });
    }
}