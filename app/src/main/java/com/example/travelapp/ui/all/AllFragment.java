package com.example.travelapp.ui.all;

import android.content.Context;
import android.database.Cursor;
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
        Cursor displayAll = navigationDrawerActivity.allFragment();
        displayAll(displayAll);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void displayAll(Cursor data) {
        TextView t1 = binding.t1;
        TextView t2 = binding.t2;
        TextView t3 = binding.t3;
        TextView t4 = binding.t4;
        TextView t11 = binding.t11;
        TextView t12 = binding.t12;
        TextView tt11 = binding.tt11;
        TextView tt12 = binding.tt12;
        TextView ttt11 = binding.ttt11;
        TextView ttt12 = binding.ttt12;
        TextView tttt11 = binding.tttt11;
        TextView tttt12 = binding.tttt12;
        TextView t21 = binding.t21;
        TextView t22 = binding.t22;
        TextView tt21 = binding.tt21;
        TextView tt22 = binding.tt22;
        TextView ttt21 = binding.ttt21;
        TextView ttt22 = binding.ttt22;
        TextView tttt21 = binding.tttt21;
        TextView tttt22 = binding.tttt22;

        while (data.moveToNext()) {
            if (t1.getText() == "") {
                t1.setText(data.getString(0));
                t11.setText(data.getString(1));
                t12.setText(data.getString(2));
            } else if (t2.getText() == "" && t1.getText().toString().compareTo(data.getString(0)) != 0) {
                t2.setText(data.getString(0));
                tt11.setText(data.getString(1));
                tt12.setText(data.getString(2));

            } else if (t3.getText() == "" && t1.getText().toString().compareTo(data.getString(0)) != 0 && t2.getText().toString().compareTo(data.getString(0)) != 0) {
                t3.setText(data.getString(0));
                ttt11.setText(data.getString(1));
                ttt12.setText(data.getString(2));
            } else if (t4.getText() == "" && t1.getText().toString().compareTo(data.getString(0)) != 0 && t2.getText().toString().compareTo(data.getString(0)) != 0 && t3.getText().toString().compareTo(data.getString(0)) != 0) {
                t4.setText(data.getString(0));
                tttt11.setText(data.getString(1));
                tttt12.setText(data.getString(2));
            } else if (t1.getText().toString().compareTo(data.getString(0)) == 0) {
                t21.setText(data.getString(1));
                t22.setText(data.getString(2));
            } else if (t2.getText().toString().compareTo(data.getString(0)) == 0) {
                tt21.setText(data.getString(1));
                tt22.setText(data.getString(2));
            }
            else if (t3.getText().toString().compareTo(data.getString(0)) == 0) {
                ttt21.setText(data.getString(1));
                ttt22.setText(data.getString(2));
            }
            else if (t4.getText().toString().compareTo(data.getString(0)) == 0) {
                tttt21.setText(data.getString(1));
                tttt22.setText(data.getString(2));
            }
        }

    }
}