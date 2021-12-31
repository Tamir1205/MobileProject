package com.example.finalproject22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth = FirebaseAuth.getInstance();
        bottomNav=findViewById(R.id.bottomNav1);
        getSupportFragmentManager().beginTransaction().replace(R.id.Main_container,new Character_Fragment()).commit();
        bottomNav.setSelectedItemId(R.id.character_Fragment);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              Fragment fragmentc=null;
                switch (item.getItemId()){
                    case R.id.chr:
                    fragmentc= new Character_Fragment();
                    break;
                    case R.id.prf:
                        fragmentc=new Profile();
                        break;
                    case R.id.stgs:
                        fragmentc=new Settings();
                        break;
                    case R.id.lgtout:
                        mAuth.signOut();
                        startActivity(new Intent(MainActivity.this, Login_Activity.class));

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.Main_container,fragmentc).commit();
                return true;

            }
        });

        }



    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, Login_Activity.class));
        }
    }

}