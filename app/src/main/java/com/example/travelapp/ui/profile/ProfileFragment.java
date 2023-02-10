package com.example.travelapp.ui.profile;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelapp.DataBaseHelper;
import com.example.travelapp.NavigationDrawerActivity;
import com.example.travelapp.R;
import com.example.travelapp.User;
import com.example.travelapp.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    String[] continent = {"Asia", "Africa", "North America", "Europe"};
    User currentUser;
    ProfileViewModel profileViewModel;
    DataBaseHelper dataBaseHelper;
    Context thisContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        thisContext = container.getContext();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
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

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding.setLifecycleOwner(this);


        currentUser = NavigationDrawerActivity.user;
        dataBaseHelper = new
                DataBaseHelper(thisContext, "TRAVEL_APP", null, 1);
        ContentValues cv = new ContentValues();

        ArrayAdapter aa = new ArrayAdapter(thisContext, android.R.layout.simple_spinner_item, continent);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(aa);

        int spinnerPosition = aa.getPosition(currentUser.getDestination());
        binding.spinner.setSelection(spinnerPosition);
        final String[] des = {currentUser.getDestination()};

        binding.spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        profileViewModel.setSelectedOption(position);
                        des[0] = continent[position];

                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


        binding.userEmail.setText(currentUser.getEmail());
        binding.firstNameLayout.setHint(currentUser.getFirstName());
        binding.lastNameLayout.setHint(currentUser.getLastName());

        binding.confirm.setOnClickListener(view1 -> {
            if (!binding.firstNameEditText.getText().toString().isEmpty() && binding.firstNameEditText.getText().toString().length() <= 20 && binding.firstNameEditText.getText().toString().length() >= 3) {
                cv.put("FIRSTNAME", binding.firstNameEditText.getText().toString());
                currentUser.setFirstName(binding.firstNameEditText.getText().toString());
            }

            if (!binding.lastNameEditText.getText().toString().isEmpty() && binding.lastNameEditText.getText().toString().length() <= 20 && binding.lastNameEditText.getText().toString().length() >= 3) {
                cv.put("LASTNAME", binding.lastNameEditText.getText().toString());
                currentUser.setLastName(binding.lastNameEditText.getText().toString());
            }

            if (!binding.password.getText().toString().isEmpty() && (binding.password.getText().toString().length() > 15 || binding.password.getText().toString().length() < 8
                    || !binding.password.getText().toString().matches(".*\\d.*")
                    || !binding.password.getText().toString().matches(".*[a-z].*")
                    || !binding.password.getText().toString().matches(".*[A-Z].*"))) {
                binding.password.setError("PLEASE ENTER A VALID PASSWORD");
                binding.password.requestFocus();
                cv.put("PASSWORD", currentUser.getPassword());

            } else if (binding.password.getText().toString().compareTo(binding.confirmPassword.getText().toString()) != 0) {
                binding.password.setError("PASSWORD DOES NOT MATCH");
                binding.password.requestFocus();
                cv.put("PASSWORD", currentUser.getPassword());
            } else {
                cv.put("PASSWORD", binding.password.getText().toString());
                currentUser.setPassword(binding.password.getText().toString());
            }


            cv.put("DESTINATION",  des[0]);
            currentUser.setDestination( des[0]);

            dataBaseHelper.updateInformation(cv, currentUser.getEmail());
            binding.firstNameLayout.setHint(currentUser.getFirstName());
            binding.lastNameLayout.setHint(currentUser.getLastName());
            Toast.makeText(thisContext, "Changes Confirmed",
                    Toast.LENGTH_SHORT).show();


        });


    }

}