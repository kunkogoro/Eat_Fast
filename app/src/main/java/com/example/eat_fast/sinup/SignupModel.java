package com.example.eat_fast.sinup;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eat_fast.beens.User;
import com.example.eat_fast.connection.ConnecttionConfigure;
import com.example.eat_fast.date.ConvertDate;
import com.example.eat_fast.encode.MD5;
import com.example.eat_fast.login.LoginConfiguration;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SignupModel {

    private String url = ConnecttionConfigure.URL+ConnecttionConfigure.SIGNUP;
    private Context context;
    private Handler handler;

    public SignupModel() {
    }

    void register(String user,String pass,String email){

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("EMAIL EXIST")){
                    Message message = new Message();
                    message.what = SignupConfiguration.EMAIL_EXIST;
                    handler.sendMessage(message);
                }else if (response.equals("USER EXIST")){
                    Message message = new Message();
                    message.what = SignupConfiguration.USER_EXIST;
                    handler.sendMessage(message);
                }else if (response.equals("SUCCESS")) {
                    Message message = new Message();
                    message.what = SignupConfiguration.SUCCESS;
                    handler.sendMessage(message);
                }else if (response.equals("FAIL")){
                    Message message = new Message();
                    message.what = SignupConfiguration.FAIL;
                    handler.sendMessage(message);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Message message = new Message();
                message.what = LoginConfiguration.ERROR_SYSTEM;
                handler.sendMessage(message);
            }
        }){
            // cái này claf chuyển 3 tham số đến database để đăng kí
            // ông xem mấy video t gửi trong nhóm lf hiểu à
            // là gửi dữ liệu thôi hả , chứ k phải là lưu tài khoản này lên sever hả
            // gửi dữ liệu lên trênseerver nó xử lí đăng kí
            // ông xem video á dễ lắm
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override  // cái lưu dữ liệu vào database là phương thức này hả ông
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();

                map.put("tai_khoan", user);
                map.put("mat_khau", MD5.md5(pass));
                map.put("email",email);

                return map;
            }

        };
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 10000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 10000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        requestQueue.add(stringRequest);

    }


    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int checkUser(String user, String pass,String repass,String email) {

        if (!CheckEmail.emailValidator(email)) {  // gọi classs checkEmail  để check email
            return 2;
        }
        else if (user.isEmpty() || pass.isEmpty()  || repass.isEmpty() ) {  //  chưa viết method CheckEmty ở đây nhưng logic là vậy
            return 1;

        } else if (!pass.equals(repass)) {  // hàm check Pass
            return 3;

        }
        return 0;
    }
}
