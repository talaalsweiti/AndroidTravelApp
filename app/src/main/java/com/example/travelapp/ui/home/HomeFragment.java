package com.example.travelapp.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelapp.ConnectionAsyncTask;
import com.example.travelapp.Destination;
import com.example.travelapp.LoginActivity;
import com.example.travelapp.NavigationDrawerActivity;
import com.example.travelapp.R;
import com.example.travelapp.databinding.FragmentHomeBinding;

import java.util.List;
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

            System.err.println(displayRandom.getString(0) +
                    displayRandom.getString(1) + displayRandom.getString(2) + displayRandom.getString(3) + displayRandom.getString(4) + displayRandom.getString(5) + displayRandom.getString(6) + displayRandom.getString(7));
    }

}