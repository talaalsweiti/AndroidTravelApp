package com.example.travelapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.travelapp.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    String[] continent = {"Asia", "Africa", "North America", "Europe"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button confirm = binding.confirm;

//        binding.spinner.setOnItemClickListener((adapterView, view, i, l) -> {
//
//        });
        ArrayAdapter aa = new ArrayAdapter(container.getContext(), android.R.layout.simple_spinner_item, continent);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(aa);

        confirm.setOnClickListener(view->{
            if (binding.firstNameEditText.getText().toString().isEmpty() || binding.firstNameEditText.getText().toString().length() > 20 || binding.firstNameEditText.getText().toString().length() < 3) {
                binding.firstNameEditText.setError("PLEASE ENTER A VALID FIRST NAME");
                binding.firstNameEditText.requestFocus();

            } else {
//                currentUser.setFirstName(firstNameEditText.getText().toString());

            }

            if (binding.lastNameEditText.getText().toString().isEmpty() || binding.lastNameEditText.getText().toString().length() > 20 || binding.lastNameEditText.getText().toString().length() < 3) {
                binding.lastNameEditText.setError("PLEASE ENTER A VALID LAST NAME");
                binding.lastNameEditText.requestFocus();

            } else {
//                newUser.setLastName(binding.lastNameEditText.getText().toString());

            }

            if (binding.password.getText().toString().isEmpty() || binding.password.getText().toString().length() > 15 || binding.password.getText().toString().length() < 8
                    || !binding.password.getText().toString().matches(".*\\d.*")
                    || !binding.password.getText().toString().matches(".*[a-z].*")
                    || !binding.password.getText().toString().matches(".*[A-Z].*")) {
                binding.password.setError("PLEASE ENTER A VALID PASSWORD");
                binding.password.requestFocus();

            } else if (binding.password.getText().toString().compareTo(binding.confirmPassword.getText().toString()) != 0) {
                binding.password.setError("PASSWORD DOES NOT MATCH");
                binding.password.requestFocus();
            } else {
//                newUser.setPassword(binding.password.getText().toString());
            }
//            newUser.setDestination(spin.getSelectedItem().toString());

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}