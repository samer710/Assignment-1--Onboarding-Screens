package com.example2.onboardingscreens;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example2.onboardingscreens.databinding.Fragment3Binding;

public class Fragment3 extends Fragment {

    public interface OnCheckChangedListener {
        void onCheckChanged(boolean isChecked);
    }

    private static final String NAME = "name";
    private OnCheckChangedListener mListener;
    private String mParam1;
    private Fragment3Binding binding;
    private String name;

    public Fragment3() {
        // Required empty public constructor
    }

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnCheckChangedListener) {
            mListener = (OnCheckChangedListener) context;
        } else {
            throw new RuntimeException(context
                    + " must implement OnCheckChangedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(NAME);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= Fragment3Binding.inflate(inflater, container, false);
        if (getArguments().containsKey(NAME)){
            name=getArguments().getString(NAME);
        }


        binding.checkBoxConfirm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (mListener != null) {
                mListener.onCheckChanged(isChecked);
            }
        });

        return binding.getRoot();

    }


    public void updateButton(boolean enabled) {
        if (binding == null) return;
        binding.buttonContinue3.setEnabled(enabled);
        binding.buttonContinue3.setText(enabled ? "Finish" : "Continue");
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textViewName.setText("Name :"+name);


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}