package com.example.travelapp.ui.sorted;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelapp.Destination;
import com.example.travelapp.NavigationDrawerActivity;
import com.example.travelapp.databinding.FragmentSortedBinding;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SortedFragment extends Fragment {

    private FragmentSortedBinding binding;
    LinearLayout linearLayout;
    Context thisContext;
    List<Destination> destinations;
    NavigationDrawerActivity navigationDrawerActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SortedViewModel sortedViewModel =
                new ViewModelProvider(this).get(SortedViewModel.class);

        thisContext = container.getContext();

        binding = FragmentSortedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        linearLayout = binding.LinearLayout;
        navigationDrawerActivity =(NavigationDrawerActivity) getActivity();
        sortDestination();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void sortDestination(){
        linearLayout.removeAllViews();
        destinations = navigationDrawerActivity.destinations ;
        destinations.sort((o1, o2) -> Double.compare(o2.getCost(), o1.getCost()));
        for (int i = 0; i < destinations.size(); i++) {
            TextView textView = new TextView(thisContext);
            textView.setText(destinations.get(i).getCity() + " " + destinations.get(i).getCost());
            linearLayout.addView(textView);

        }

    }

}
