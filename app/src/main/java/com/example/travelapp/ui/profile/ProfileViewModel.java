package com.example.travelapp.ui.profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<Integer> selectedOption = new MutableLiveData<>();

    public ProfileViewModel() {

    }
    public MutableLiveData<Integer> getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(Integer selectedOption) {
        this.selectedOption.setValue(selectedOption);
    }
}