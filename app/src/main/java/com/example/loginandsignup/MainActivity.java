package com.example.loginandsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.example.loginandsignup.databinding.ActivityMainBinding;
import com.example.loginandsignup.utils.ServerUtil;

import org.json.JSONObject;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setValues();
        setupEvents();
    }

    @Override
    public void setupEvents() {

//        저장된 토큰을 확인해서
//        => 그 토큰으로 내 정보를 서버에서 다시 불러올것 - 조회(GET)

        ServerUtil.getRequestMyInfo(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                Log.d("내정보", json.toString());
            }
        });


    }

    @Override
    public void setValues() {

    }
}
