package com.example.loginandsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.example.loginandsignup.databinding.ActivityMainBinding;
import com.example.loginandsignup.utils.ContextUtil;
import com.example.loginandsignup.utils.ServerUtil;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

//        체크박스에 체크가 될때 (변화가 있을때) 마다
//        체크 여부를 저장.
        binding.idCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                ContextUtil을 이용해서, 체크 여부 저장.
                ContextUtil.setIdCheck(mContext, isChecked);
            }
        });

//        로그인 버튼을 누르면 => 아이디 저장이 체크되어 있다면 => 입력되어있는 이메일 저장 =>
//        그렇지 않다면 => 이메일을 빈칸 ""으로 저장
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                체크박스에 체크가 되어있나?
                if (binding.idCheckBox.isChecked()) {

//                    체크가 되어있는 상황
                    String inputEmail = binding.emailEdt.getText().toString();
                    ContextUtil.setEmail(mContext, inputEmail);
                }
                else {
//                    체크가 안된 상황
                    ContextUtil.setEmail(mContext, "");
                }

                String inputEmail = binding.emailEdt.getText().toString();
                String inputPw = binding.pwEdt.getText().toString();
                ServerUtil.postRequestLogin(mContext, inputEmail, inputPw, null);
            }
        });
    }

    @Override
    public void setValues() {

//        이 화면을 키면, 저장된 이메일 값을 emailEdt에 입력.
        binding.emailEdt.setText(ContextUtil.getEmail(mContext));

        binding.idCheckBox.setChecked(ContextUtil.isIdCheck(mContext));
    }
}
