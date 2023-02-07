package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.travelapp.destinationFragments.DescriptionFragment;
import com.example.travelapp.destinationFragments.ImageFragment;
import com.example.travelapp.destinationFragments.LocationFragment;

public class DestinationActivity extends AppCompatActivity {
    public static String city;

    final DescriptionFragment descriptionFragment = new DescriptionFragment();
    final ImageFragment imageFragment = new ImageFragment();
    final LocationFragment locationFragment = new LocationFragment();
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

        Cursor information = dataBaseHelper.selectOneDestination(city);

        while(information.moveToNext()) {
            desc = information.getString(0);
            imgLink = information.getString(1);
            longitude=information.getDouble(2);
            latitude=information.getDouble(3);

        }
        description.setOnClickListener(view -> {
            displayDescriptionFragment();
        });

        image.setOnClickListener(view -> {
            displayImageFragment();
        });

        location.setOnClickListener(view->{
            displayLocationFragment();
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
        if (locationFragment.isAdded()) { ft.remove(locationFragment); }
        // Commit changes
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
        if (locationFragment.isAdded()) { ft.remove(locationFragment); }
        ft.commit();
    }

    protected void displayLocationFragment() {
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        if (locationFragment.isAdded()) { // if the fragment is already in container
//            ft.remove(locationFragment);
//        } else { // fragment needs to be added to frame container
//            LocationFragment.longitude = longitude;
//            LocationFragment.latitude = latitude;
//            LocationFragment.city=city;
//            ft.add(R.id.root_layout, locationFragment, "location");
//        }
//        if (descriptionFragment.isAdded()) { ft.remove(descriptionFragment); }
//        if (imageFragment.isAdded()) { ft.remove(imageFragment); }
//        ft.commit();

        Intent mapsIntent =new Intent();
        String location = "geo:" + String.valueOf(latitude) +","+ String.valueOf(longitude);
        mapsIntent.setAction(Intent.ACTION_VIEW);
        mapsIntent.setData(Uri.parse(location));
        startActivity(mapsIntent);
    }

}