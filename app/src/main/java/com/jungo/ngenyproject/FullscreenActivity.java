package com.jungo.ngenyproject;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class FullscreenActivity extends AppCompatActivity {
    //    my variable or attribute
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        navController = Navigation.findNavController(this, R.id.fragment_nav_host);
        NavigationUI.setupActionBarWithNavController(this,navController);

//        fragmentTransaction.add(R.id.main_container, new SplashFragment());
//        fragmentTransaction.commit();

//      new Handler().postDelayed(new Runnable() {
//        @Override
//           public void run() {
////            FragmentTransaction f = getSupportFragmentManager().beginTransaction();
////            f.replace(R.id.main_container, new LoginFragment());
////            f.commit();
//           }
//        }, 3000);
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.map_nav:
//                navController.navigate(R.id.action_homeFragment_to_mapActivityFragment);
                Intent intent =  new Intent(FullscreenActivity.this, PositionActivity.class);
                startActivity(intent);
                return true;
            case  R.id.deconected_nav:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public boolean onSupportNavigateUp(){
        return navController.navigateUp();
   }

}