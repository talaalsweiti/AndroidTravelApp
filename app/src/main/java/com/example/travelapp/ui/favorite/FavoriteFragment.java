package com.example.travelapp.ui.favorite;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelapp.DataBaseHelper;
import com.example.travelapp.DestinationActivity;
import com.example.travelapp.NavigationDrawerActivity;
import com.example.travelapp.R;
import com.example.travelapp.User;
import com.example.travelapp.databinding.FragmentFavoriteBinding;


public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    FavoriteViewModel favoriteViewModel ;
    User currentUser;
    DataBaseHelper dataBaseHelper;
    Context thisContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoriteViewModel =
                new ViewModelProvider(this).get(FavoriteViewModel.class);
        currentUser = NavigationDrawerActivity.user;
        thisContext =container.getContext();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite,container, false);
        dataBaseHelper= new
           DataBaseHelper(thisContext, "TRAVEL_APP", null, 1);


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        binding.setLifecycleOwner(this);
        Cursor favs = dataBaseHelper.getFavorites(currentUser.getEmail());


        while(favs.moveToNext()) {
            LinearLayout linearLayout = new LinearLayout(thisContext);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            TextView city = new TextView(thisContext);
            city.setText(favs.getString(0));
            city.setPadding(5,5,5,5);
            TextView country = new TextView(thisContext);
            country.setText(favs.getString(1));

            binding.favRoot.addView(linearLayout);

        }



    }
}