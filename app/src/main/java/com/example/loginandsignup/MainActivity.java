package com.example.loginandsignup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.loginandsignup.databinding.ActivityMainBinding;
import com.example.loginandsignup.datas.User;
import com.example.loginandsignup.utils.ContextUtil;
import com.example.loginandsignup.utils.ServerUtil;

import org.json.JSONException;
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

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                얼럿으로 "정말 로그아웃 하시겠습니까?" 확인받자.
                final AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setMessage("정말 로그아웃 하시겠습니까?");
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        로그아웃 처리
//                        저장된 토큰을 => 날려버림.
                        ContextUtil.setUserToken(mContext,"");
//                        로그인 액티비티를 띄우자
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        startActivity(intent);

                        finish();
//                        로그인 액티비티를 띄우자.
                    }
                });
                alert.setNegativeButton("취소",null);
                alert.show();
            }
        });

        ServerUtil.getRequestMyInfo(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                Log.d("내정보", json.toString());

                try {
                    int code = json.getInt("code");

                    if (code == 200) {
                        JSONObject data = json.getJSONObject("data");
                        JSONObject user = data.getJSONObject("user");

                        final User myInfo = User.getUserFromJson(user);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.nameTxt.setText(myInfo.getName());
                                binding.memoTxt.setText(myInfo.getMemo());
                                binding.phoneTxt.setText(myInfo.getPhone());
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public void setValues() {

    }
}
