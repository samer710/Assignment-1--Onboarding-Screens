package com.example2.onboardingscreens;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import android.view.View;

import com.example2.onboardingscreens.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Fragment2.OnFragment2InteractionListener ,Fragment3.OnCheckChangedListener  {

    private ActivityMainBinding binding;
    private int currentFragment = 1;
    private String Name, Age, Gender, Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment1 fragment1 = Fragment1.newInstance(null, null);
        Fragment2 fragment2 = Fragment2.newInstance(null, null);




        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment1)
                .commit();

        binding.buttonContinue.setOnClickListener(view -> {
            if (currentFragment == 1) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null)
                        .commit();
                currentFragment = 2;
            } else {
                if (!TextUtils.isEmpty(Fragment2.fragment2Binding.editTextName.getText().toString())) {

                    Name= Fragment2.fragment2Binding.editTextName.getText().toString();

                    Toast.makeText( getApplicationContext(),Name, Toast.LENGTH_SHORT).show();  //اظهار toast
                } else {
                    Toast.makeText(
                                    getApplicationContext(),
                                    "Enter name!",
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });



    }


    @Override
    public void onContinueClicked(String name, String age, String geder, String email) {
      Name =name;
      Age=age;
      Gender=geder;
      Email=email;

        Fragment3 fragment3 = Fragment3.newInstance(Name);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .addToBackStack(null)
                .commit();

        // Fragment3 له زره الخاص


    }

    @Override
    public void onCheckChanged(boolean isChecked) {



        Fragment current = getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        if (current instanceof Fragment3) {
            ((Fragment3) current).updateButton(isChecked);
        }

    }
}