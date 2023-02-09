package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.travelapp.destinationFragments.DescriptionFragment;
import com.example.travelapp.destinationFragments.ImageFragment;

public class DestinationActivity extends AppCompatActivity {
    public static String city;
    public static String country;
    User currentUser;
    final DescriptionFragment descriptionFragment = new DescriptionFragment();
    final ImageFragment imageFragment = new ImageFragment();
    String desc , imgLink;
    double longitude,latitude;
    DataBaseHelper dataBaseHelper = new
            DataBaseHelper(DestinationActivity.this, "TRAVEL_APP", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        Button description = findViewById(R.id.description);
        Button image = findViewById(R.id.image);
        Button location = findViewById(R.id.location);
        TextView cityText = findViewById(R.id.city);
        cityText.setText(city);
        currentUser = NavigationDrawerActivity.user;
        Cursor selectedCity = dataBaseHelper.selectOneDestination(city);


        while(selectedCity.moveToNext()) {
            country = selectedCity.getString(1);
            desc = selectedCity.getString(7);
            imgLink = selectedCity.getString(6);
            longitude=selectedCity.getDouble(3);
            latitude=selectedCity.getDouble(4);

        }


        TextView fav = findViewById(R.id.addFav);

        fav.setOnClickListener(view->{
            dataBaseHelper.insertFavorite(currentUser.getEmail(),city,country);
        });

        description.setOnClickListener(view -> {
            displayDescriptionFragment();
        });

        image.setOnClickListener(view -> {
            displayImageFragment();
        });

        location.setOnClickListener(view->{
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (descriptionFragment.isAdded()) { ft.remove(descriptionFragment); }
            if (imageFragment.isAdded()) { ft.remove(imageFragment); }
            displayLocationFragment();
            ft.commit();
        });
    }
    // Replace the switch method
    protected void displayDescriptionFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (descriptionFragment.isAdded()) { // if the fragment is already in container
            ft.remove(descriptionFragment);
        } else { // fragment needs to be added to frame container
            DescriptionFragment.description = desc;
            ft.add(R.id.root_layout, descriptionFragment, "description");
        }
        if (imageFragment.isAdded()) { ft.remove(imageFragment); }

        ft.commit();
    }

    // Replace the switch method
    protected void displayImageFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (imageFragment.isAdded()) { // if the fragment is already in container
            ft.remove(imageFragment);
        } else { // fragment needs to be added to frame container
            ImageFragment.link = imgLink;
            ft.add(R.id.root_layout, imageFragment, "image");
        }
        if (descriptionFragment.isAdded()) { ft.remove(descriptionFragment); }

        ft.commit();
    }

    protected void displayLocationFragment() {
        Intent mapsIntent =new Intent();
        String location = "geo:" + latitude +","+ longitude;
        mapsIntent.setAction(Intent.ACTION_VIEW);
        mapsIntent.setData(Uri.parse(location));
        startActivity(mapsIntent);
    }

}