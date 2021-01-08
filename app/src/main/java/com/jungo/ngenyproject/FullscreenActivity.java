package com.jungo.ngenyproject;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class FullscreenActivity extends AppCompatActivity {
    //    my variable or attribute
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        fragmentTransaction.add(R.id.main_container, new SplashFragment());
        fragmentTransaction.commit();

      new Handler().postDelayed(new Runnable() {
        @Override
           public void run() {
            FragmentTransaction f = getSupportFragmentManager().beginTransaction();
            f.replace(R.id.main_container, new LoginFragment());
            f.commit();
           }
        }, 3000);
   }

}