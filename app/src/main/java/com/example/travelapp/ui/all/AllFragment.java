package com.example.travelapp.ui.all;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelapp.Destination;
import com.example.travelapp.NavigationDrawerActivity;
import com.example.travelapp.R;
import com.example.travelapp.databinding.FragmentAllBinding;

import org.w3c.dom.Text;

import java.util.List;


public class AllFragment extends Fragment {

    private FragmentAllBinding binding;
    NavigationDrawerActivity navigationDrawerActivity;
    ScrollView sv;
    Context thisContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AllViewModel allViewModel =
                new ViewModelProvider(this).get(AllViewModel.class);
        thisContext = container.getContext();

        binding = FragmentAllBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        navigationDrawerActivity = (NavigationDrawerActivity) getActivity();
        System.err.println(navigationDrawerActivity.Continent);
        sv = binding.scrollView;
        // allViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        displayAll();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void displayAll() {

        List<Destination> destinations;
        destinations = navigationDrawerActivity.destinations;
        String continent = navigationDrawerActivity.Continent;
        TextView t1 = binding.t1;
        TextView t2 = binding.t2;
        TextView t3 = binding.t3;
        TextView t4 = binding.t4;
        TextView t11 = binding.t11;
        TextView t12 = binding.t12;
        TextView t21 = binding.t21;
        TextView t22 = binding.t22;

        for (int i = 0; i < destinations.size(); i++) {

            if (t1.getText() == "" || destinations.get(i).getContinent().compareTo(t1.getText().toString()) == 0) {
                if (t11.getText() == "") {
                    t11.setText(destinations.get(i).getCountry());
                    t12.setText(destinations.get(i).getCity());
                } else {
                    if (t21.getText() == "") {
                        t21.setText(destinations.get(i).getCountry());
                        t22.setText(destinations.get(i).getCity());
                    }
                }
            }
            if (t1.getText() == "")
                t1.setText(destinations.get(i).getContinent());
            else if (t2.getText() == "" && t1.getText().toString().compareTo(destinations.get(i).getContinent()) != 0) {
                t2.setText(destinations.get(i).getContinent());
            } else if (t3.getText() == "" && t1.getText().toString().compareTo(destinations.get(i).getContinent()) != 0 && t2.getText().toString().compareTo(destinations.get(i).getContinent()) != 0) {
                t3.setText(destinations.get(i).getContinent());
            } else if (t4.getText() == "" && t1.getText().toString().compareTo(destinations.get(i).getContinent()) != 0 && t2.getText().toString().compareTo(destinations.get(i).getContinent()) != 0 && t3.getText().toString().compareTo(destinations.get(i).getContinent()) != 0) {
                t4.setText(destinations.get(i).getContinent());
            }
        }

    }
}