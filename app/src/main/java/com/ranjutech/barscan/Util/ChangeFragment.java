package com.ranjutech.barscan.Util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.ranjutech.barscan.R;

public class ChangeFragment {
    public static void changeMainFragmentWithBack(FragmentActivity fragmentActivity, Fragment fragment){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.navHostFragment,fragment)
                .addToBackStack(null)
                .commit();
    }
}
