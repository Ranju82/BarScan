package com.ranjutech.barscan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.ranjutech.barscan.UI.Home.HomeFragment;
import com.ranjutech.barscan.Util.ChangeFragment;
import com.ranjutech.barscan.Util.Constant;
import com.ranjutech.barscan.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ChangeFragment.changeMainFragmentWithBack(this,new HomeFragment());
    }
}