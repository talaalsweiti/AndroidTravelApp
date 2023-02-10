package com.example.travelapp.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelapp.DataBaseHelper;
import com.example.travelapp.NavigationDrawerActivity;
import com.example.travelapp.R;
import com.example.travelapp.databinding.FragmentHomeBinding;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Context thisContext;
    HomeViewModel homeViewModel ;
    NavigationDrawerActivity navigationDrawerActivity;
    DataBaseHelper dataBaseHelper;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

         homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        thisContext = container.getContext();
        dataBaseHelper = new
                DataBaseHelper(thisContext, "TRAVEL_APP", null, 1);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("SetTextI18n")
    public void displayRandom() {
        TextView cityText = binding.city;
        TextView countryText = binding.country;
        TextView continentText = binding.continent;
        TextView costText = binding.cost;
        TextView descText = binding.desc;

        int count = 0;
        navigationDrawerActivity = (NavigationDrawerActivity) getActivity();
        Cursor displayRandom = navigationDrawerActivity.homeFragment();
        Cursor numberOfDestinations = navigationDrawerActivity.numberOfDestinations();

        while (numberOfDestinations.moveToNext())
            count +=1;

        // GET THE UPPER BOUND OF THE RANDOM INDEX OF THE SELECTED PREFERRED CONTINENT
        Random rand = new Random();
        int int_random = rand.nextInt(count);
        displayRandom.moveToPosition(int_random);
        cityText.setText(displayRandom.getString(0));
        countryText.setText(displayRandom.getString(1));
        continentText.setText(displayRandom.getString(2));
        costText.setText(displayRandom.getString(5) + "$");
        descText.setText( displayRandom.getString(7));

        ImageView imageView = binding.image;
        Picasso.get()
                .load(displayRandom.getString(6))
                .into(imageView);

        binding.location.setOnClickListener(view -> {
            Intent mapsIntent = new Intent();
            String location = "geo:" + displayRandom.getString(4) + "," + displayRandom.getString(3);
            mapsIntent.setAction(Intent.ACTION_VIEW);
            mapsIntent.setData(Uri.parse(location));
            startActivity(mapsIntent);
        });


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setLifecycleOwner(this);
        displayRandom();


        Cursor alreadyFav = dataBaseHelper.getFavorites(NavigationDrawerActivity.user.getEmail());

        boolean flg = false;
        while (alreadyFav.moveToNext()) {
            //if exists
            if (alreadyFav.getString(1).equals(binding.city.getText().toString())) {
                binding.addFav.setText("Remove from Favorites");
                flg=true;
                break;
            }
        }
        if(!flg){
            binding.addFav.setText("Add to Favorites");
        }
        binding.addFav.setOnClickListener(view1 -> {
            if(binding.addFav.getText().toString().compareTo("Remove from Favorites") == 0){
                dataBaseHelper.deleteFavorite(binding.city.getText().toString());
                Toast.makeText(thisContext, binding.city.getText().toString() + " DESTINATION REMOVED FROM FAVORITES SUCCESSFULLY",
                        Toast.LENGTH_SHORT).show();
                binding.addFav.setText("Add to Favorites");
            }else{
                dataBaseHelper.insertFavorite(NavigationDrawerActivity.user.getEmail(), binding.city.getText().toString(), binding.country.getText().toString());
                Toast.makeText(thisContext, binding.city.getText().toString() + " DESTINATION ADDED TO FAVORITES SUCCESSFULLY",
                        Toast.LENGTH_SHORT).show();
                binding.addFav.setText("Remove from Favorites");
            }
        });
    }


}