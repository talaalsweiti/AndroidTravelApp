package com.example.travelapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.travelapp.databinding.ActivityNavigationDrawerBinding;
import com.google.android.material.navigation.NavigationView;


public class NavigationDrawerActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationDrawerBinding binding;
    public String Continent;
    DataBaseHelper dataBaseHelper = new
            DataBaseHelper(NavigationDrawerActivity.this, "TRAVEL_APP", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // GET THE USER'S PREFERRED CONTINENT
        Intent intent = getIntent();
        Continent = intent.getStringExtra("message_key");

        binding = ActivityNavigationDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavigationDrawer.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(menuItem -> {
            Intent intent2 = new Intent(NavigationDrawerActivity.this, LoginActivity.class);
            NavigationDrawerActivity.this.startActivity(intent2);
            return true;
        });


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_all, R.id.nav_favorite, R.id.nav_sorted, R.id.nav_profile)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer_acvtivity);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer_acvtivity, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer_acvtivity);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public Cursor allFragment() {
        return dataBaseHelper.selectDestinations();
    }

    public Cursor sortedFragment(String sortMethod) {
        return dataBaseHelper.sortDestinations(sortMethod);
    }

    public Cursor homeFragment() {
        return dataBaseHelper.getRandomDestination(Continent);
    }

    public Cursor numberOfDestinations() {
        return dataBaseHelper.numberOfDestinations(Continent);
    }
    /**
     * Called when the user touches the button
     */
    public void addFav(View view) {
        // Do something in response to button click
        Toast.makeText(NavigationDrawerActivity.this, "DESTINATION ADDED TO FAVORITES SUCCESSFULLY",Toast.LENGTH_SHORT).show();

    }

}