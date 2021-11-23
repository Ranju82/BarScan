package com.ranjutech.barscan.UI.Detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailsViewModelFactory implements ViewModelProvider.Factory {

    private String bcode;
    public DetailsViewModelFactory(String bcode){
        this.bcode=bcode;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailsViewModel(bcode);
    }
}
