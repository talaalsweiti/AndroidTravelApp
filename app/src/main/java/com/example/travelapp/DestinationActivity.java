package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.travelapp.destinationFragments.DescriptionFragment;
import com.example.travelapp.destinationFragments.ImageFragment;
import com.example.travelapp.destinationFragments.LocationFragment;

public class DestinationActivity extends AppCompatActivity {
    public static String city;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    final DescriptionFragment descriptionFragment = new DescriptionFragment();
    final ImageFragment imageFragment = new ImageFragment();
    final LocationFragment locationFragment = new LocationFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        LinearLayout linearLayout = findViewById(R.id.root_layout);
        Button description = findViewById(R.id.description);
        Button image = findViewById(R.id.image);
        Button location = findViewById(R.id.location);
        TextView cityText = findViewById(R.id.city);
        cityText.setText(city);

    }

}