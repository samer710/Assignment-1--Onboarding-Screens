package com.example2.onboardingscreens;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example2.onboardingscreens.databinding.Fragment2Binding;



public class Fragment2 extends Fragment {

    public interface OnFragment2InteractionListener {
        void onContinueClicked(String name, String age, String gender, String email);
    }

    private OnFragment2InteractionListener mListener;
    private String Name, Age, Gender, Email;
    private Button btnContinue;
    public static Fragment2Binding fragment2Binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment2() {
        // Required empty public constructor
    }


    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment2InteractionListener) {
            mListener = (OnFragment2InteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment2InteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragment2Binding = Fragment2Binding.inflate(inflater, container, false);
        return fragment2Binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().findViewById(R.id.button_continue).setVisibility(View.VISIBLE);
        View continueButton = getActivity().findViewById(R.id.button_continue);

        continueButton.setOnClickListener(v -> {

            Name = fragment2Binding.editTextName.getText().toString();
            Age = fragment2Binding.editTextAge.getText().toString();
            Gender = fragment2Binding.editTextGender.getText().toString();
            Email = fragment2Binding.editTextEmail.getText().toString();

            if (Name.isEmpty()) {
                fragment2Binding.editTextName.setError("Please enter your name");
                return;
            }
            mListener.onContinueClicked(Name, Age, Gender, Email);


        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragment2Binding = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}