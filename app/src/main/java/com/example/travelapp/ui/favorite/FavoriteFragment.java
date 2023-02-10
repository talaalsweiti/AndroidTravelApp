package com.example.travelapp.ui.favorite;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelapp.DataBaseHelper;
import com.example.travelapp.DestinationActivity;
import com.example.travelapp.NavigationDrawerActivity;
import com.example.travelapp.R;
import com.example.travelapp.User;
import com.example.travelapp.databinding.FragmentFavoriteBinding;
import com.example.travelapp.ui.all.AllFragment;


public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    FavoriteViewModel favoriteViewModel;
    User currentUser;
    DataBaseHelper dataBaseHelper;
    Context thisContext;
    Intent destinationIntent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoriteViewModel =
                new ViewModelProvider(this).get(FavoriteViewModel.class);
        currentUser = NavigationDrawerActivity.user;
        thisContext = container.getContext();
        destinationIntent = new Intent(thisContext, DestinationActivity.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false);

        return binding.getRoot();
    }

    public void displayFaves() {
        dataBaseHelper = new
                DataBaseHelper(thisContext, "TRAVEL_APP", null, 1);

        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        binding.setLifecycleOwner(this);

        TextView city1 = binding.city1;
        TextView city2 = binding.city2;
        TextView city3 = binding.city3;
        TextView city4 = binding.city4;
        TextView city5 = binding.city5;
        TextView city6 = binding.city6;
        TextView city7 = binding.city7;
        TextView city8 = binding.city8;
        TextView country1 = binding.country1;
        TextView country2 = binding.country2;
        TextView country3 = binding.country3;
        TextView country4 = binding.country4;
        TextView country5 = binding.country5;
        TextView country6 = binding.country6;
        TextView country7 = binding.country7;
        TextView country8 = binding.country8;

        Cursor faves = dataBaseHelper.getFavorites(currentUser.getEmail());

        while (faves.moveToNext()) {
            if (city1.getText() == "") {
                city1.setText(faves.getString(1));
                country1.setText(faves.getString(2));
                city1.setOnClickListener(view1 -> {
                    DestinationActivity.city = (String) city1.getText();
                    FavoriteFragment.this.startActivity(destinationIntent);
                });
            } else if (city2.getText() == "") {
                city2.setText(faves.getString(1));
                country2.setText(faves.getString(2));
                city2.setOnClickListener(view1 -> {
                    DestinationActivity.city = (String) city2.getText();
                    FavoriteFragment.this.startActivity(destinationIntent);
                    getActivity().finish();

                });
            } else if (city3.getText() == "") {
                city3.setText(faves.getString(1));
                country3.setText(faves.getString(2));
                city3.setOnClickListener(view1 -> {
                    DestinationActivity.city = (String) city3.getText();
                    FavoriteFragment.this.startActivity(destinationIntent);
                    getActivity().finish();

                });
            } else if (city4.getText() == "") {
                city4.setText(faves.getString(1));
                country4.setText(faves.getString(2));
                city4.setOnClickListener(view1 -> {
                    DestinationActivity.city = (String) city4.getText();
                    FavoriteFragment.this.startActivity(destinationIntent);
                    getActivity().finish();

                });
            } else if (city5.getText() == "") {
                city5.setText(faves.getString(1));
                country5.setText(faves.getString(2));
                city5.setOnClickListener(view1 -> {
                    DestinationActivity.city = (String) city5.getText();
                    FavoriteFragment.this.startActivity(destinationIntent);
                    getActivity().finish();

                });
            } else if (city6.getText() == "") {
                city6.setText(faves.getString(1));
                country6.setText(faves.getString(2));
                city6.setOnClickListener(view1 -> {
                    DestinationActivity.city = (String) city6.getText();
                    FavoriteFragment.this.startActivity(destinationIntent);
                    getActivity().finish();

                });
            } else if (city7.getText() == "") {
                city7.setText(faves.getString(1));
                country7.setText(faves.getString(2));
                city7.setOnClickListener(view1 -> {
                    DestinationActivity.city = (String) city7.getText();
                    FavoriteFragment.this.startActivity(destinationIntent);
                    getActivity().finish();

                });
            } else if (city8.getText() == "") {
                city8.setText(faves.getString(1));
                country8.setText(faves.getString(2));
                city8.setOnClickListener(view1 -> {
                    DestinationActivity.city = (String) city8.getText();
                    FavoriteFragment.this.startActivity(destinationIntent);
                    getActivity().finish();

                });
            }

        }
        dataBaseHelper.close();
        faves.close();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        displayFaves();
    }

    @Override
    public void onResume() {
        super.onResume();
        // REFRESH THE DISPLAYED CONTENT
        TextView city1 = binding.city1;
        TextView city2 = binding.city2;
        TextView city3 = binding.city3;
        TextView city4 = binding.city4;
        TextView city5 = binding.city5;
        TextView city6 = binding.city6;
        TextView city7 = binding.city7;
        TextView city8 = binding.city8;
        TextView country1 = binding.country1;
        TextView country2 = binding.country2;
        TextView country3 = binding.country3;
        TextView country4 = binding.country4;
        TextView country5 = binding.country5;
        TextView country6 = binding.country6;
        TextView country7 = binding.country7;
        TextView country8 = binding.country8;
        city1.setText("");
        city2.setText("");
        city3.setText("");
        city4.setText("");
        city5.setText("");
        city6.setText("");
        city7.setText("");
        city8.setText("");
        country1.setText("");
        country2.setText("");
        country3.setText("");
        country4.setText("");
        country5.setText("");
        country6.setText("");
        country7.setText("");
        country8.setText("");
        displayFaves();
    }



}