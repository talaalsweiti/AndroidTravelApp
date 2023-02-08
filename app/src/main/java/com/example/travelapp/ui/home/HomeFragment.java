package com.example.travelapp.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelapp.NavigationDrawerActivity;
import com.example.travelapp.databinding.FragmentHomeBinding;

import java.util.Random;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Context thisContext;
    NavigationDrawerActivity navigationDrawerActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        thisContext = container.getContext();

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        displayRandom();
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
    }



}