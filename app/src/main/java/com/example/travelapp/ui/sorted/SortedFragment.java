package com.example.travelapp.ui.sorted;

import android.content.Context;
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

import com.example.travelapp.Destination;
import com.example.travelapp.NavigationDrawerActivity;
import com.example.travelapp.R;
import com.example.travelapp.databinding.FragmentSortedBinding;

import java.util.Comparator;
import java.util.List;


public class SortedFragment extends Fragment {

    private FragmentSortedBinding binding;
    LinearLayout linearLayout;
    Context thisContext;
    List<Destination> destinations;
    NavigationDrawerActivity navigationDrawerActivity;

    SortedViewModel sortedViewModel ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         sortedViewModel =
                new ViewModelProvider(this).get(SortedViewModel.class);

        thisContext = container.getContext();
        navigationDrawerActivity =(NavigationDrawerActivity) getActivity();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sorted, container, false);

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

        sortedViewModel = new ViewModelProvider(this).get(SortedViewModel.class);

        binding.setLifecycleOwner(this);
        linearLayout = binding.LinearLayout;
        sortDestination("asc");
        binding.radiogroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.ascending:
                    sortedViewModel.setSelectedOption(1);
                    sortDestination("asc");
                    break;
                case R.id.descending:
                    sortedViewModel.setSelectedOption(2);
                    sortDestination("des");
                    break;
            }
        });
    }


    public void sortDestination(String sortMethod){
        destinations = navigationDrawerActivity.destinations ;
        linearLayout.removeAllViews();
        if(sortMethod=="asc"){
            destinations.sort(Comparator.comparingDouble(Destination::getCost));
        }else {

            destinations.sort((o1, o2) -> Double.compare(o2.getCost(), o1.getCost()));

        }
        for (int i = 0; i < destinations.size(); i++) {
            TextView textView = new TextView(thisContext);
            textView.setText(destinations.get(i).getCity() + " " + destinations.get(i).getCost());
            linearLayout.addView(textView);
        }
    }

}
