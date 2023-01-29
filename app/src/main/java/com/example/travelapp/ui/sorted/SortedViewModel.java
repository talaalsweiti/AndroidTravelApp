package com.example.travelapp.ui.sorted;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SortedViewModel extends ViewModel  {

    private MutableLiveData<Integer> selectedOption = new MutableLiveData<>();

    public MutableLiveData<Integer> getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(Integer selectedOption) {
        this.selectedOption.setValue(selectedOption);
    }
}
