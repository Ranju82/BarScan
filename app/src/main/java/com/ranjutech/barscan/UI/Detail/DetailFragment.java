package com.ranjutech.barscan.UI.Detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ranjutech.barscan.R;
import com.ranjutech.barscan.databinding.FragmentDetailBinding;


public class DetailFragment extends Fragment {

    private DetailsViewModel detailsViewModel;
    private FragmentDetailBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        View view=binding.getRoot();
        Bundle bundle=this.getArguments();
        detailsViewModel =new ViewModelProvider(this,new DetailsViewModelFactory(bundle.getString("code"))).get(DetailsViewModel.class);

        binding.barCodeText.setText(bundle.getString("code"));

        detailsViewModel.getTitle().observe(getViewLifecycleOwner(),title->{
            binding.titleContent.setText(title);
        });
        detailsViewModel.getSubTitle().observe(getViewLifecycleOwner(),subTitle->{
            binding.subTitleContent.setText(subTitle);
        });
        detailsViewModel.getPrice().observe(getViewLifecycleOwner(),price->{
            binding.priceContent.setText(price);
        });
        detailsViewModel.getDetail().observe(getViewLifecycleOwner(),detail->{
            binding.detailsContent.setText(detail);
        });
        return view;
    }
}