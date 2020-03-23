package com.example.loginandsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.loginandsignup.databinding.ActivitySignUpBinding;

public class SignUpActivity extends BaseActivity {

    ActivitySignUpBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

    }
}
