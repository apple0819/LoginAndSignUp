package com.example.loginandsignup.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ContextUtil {

//    메모장파일 처럼 데이터를 저장할 공간의 이름으로 쓸 변수.
    private static final String prefName = "myPref";

//    항목명도 자동완성 지원할수 있도록 미리 변수화.
    private static final String EMAIL = "EMAIL";

//    해당 항목의 값을 저장(setter) / 조회(getter) 하는 메쏘드 두개.

//    setter
    public static void setEmail(Context context, String email) {
//        메모장을 이용해서 값을 기록. => 메모장 파일부터 열어야 함.
//        메모장은 Context를 이용해서 열어야함. => Context변수도 파라미터로 필요함.

//        메모장을 열때 1) 어떤 메모장? prefName 변수에 담아둠 2) 어떤 모드? - Context.DODE_PRIVATE
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

//        열린 메모장에 항목(key)/값(value) 저장
        pref.edit().putString(EMAIL, email).apply();
    }

}
