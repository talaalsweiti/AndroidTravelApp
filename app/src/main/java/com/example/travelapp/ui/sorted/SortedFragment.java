package com.example.travelapp.ui.sorted;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelapp.NavigationDrawerActivity;
import com.example.travelapp.R;
import com.example.travelapp.databinding.FragmentSortedBinding;


public class SortedFragment extends Fragment {

    private FragmentSortedBinding binding;
    LinearLayout linearLayout;
    Context thisContext;
    NavigationDrawerActivity navigationDrawerActivity;

    SortedViewModel sortedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sortedViewModel =
                new ViewModelProvider(this).get(SortedViewModel.class);

        thisContext = container.getContext();
        navigationDrawerActivity = (NavigationDrawerActivity) getActivity();
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
        binding.imageView.setVisibility(View.INVISIBLE);


        binding.radiogroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.ascending:
                    sortedViewModel.setSelectedOption(1);
                    sortDestination("asc");
                    binding.imageView.setVisibility(View.VISIBLE);
                    binding.imageView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.ascending));
                    break;
                case R.id.descending:
                    sortedViewModel.setSelectedOption(2);
                    sortDestination("des");
                    binding.imageView.setVisibility(View.VISIBLE);
                    binding.imageView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.descinding));
                    break;
            }
            binding.imageView.setVisibility(View.INVISIBLE);
        });
//        binding.imageView.setVisibility(View.INVISIBLE);
    }


    public void sortDestination(String sortMethod) {
        navigationDrawerActivity = (NavigationDrawerActivity) getActivity();
        Cursor sortedDes = navigationDrawerActivity.sortedFragment(sortMethod);

        linearLayout.removeAllViews();
        while (sortedDes.moveToNext()) {
            TextView textView = new TextView(thisContext);
            textView.setText(sortedDes.getString(0) + " " + sortedDes.getString(1));
            linearLayout.addView(textView);
        }
    }

}
