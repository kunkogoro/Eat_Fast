package com.example.eat_fast.login;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eat_fast.beens.User;
import com.example.eat_fast.connection.ConnecttionConfigure;
import com.example.eat_fast.date.ConvertDate;
import com.example.eat_fast.encode.MD5;
import com.example.eat_fast.shareReferences.DataLocalManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LoginModel {

    private String url = ConnecttionConfigure.URL+ConnecttionConfigure.LOGIN;
    private Context context;
    private Handler handler;

    public LoginModel(){
    }

    public void login(String username,String password){

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("NOT EXIST")){
                    Message message = new Message();
                    message.what = LoginConfiguration.NOT_EXIST;
                    handler.sendMessage(message);
                }else if (response.equals("ERROR PASS")){
                    Message message = new Message();
                    message.what = LoginConfiguration.ERROR_PASS;
                    handler.sendMessage(message);
                }else {

                    try {

                        JSONObject object = new JSONObject(response);

                        String id = object.getString("id");
                        String loai = object.getString("loai");
                        String tai_khoan = object.getString("tai_khoan");
                        String email = object.getString("email");
                        String phone = object.getString("phone");
                        String ttkh = object.getString("ttkh");
                        String ttdg = object.getString("ttdg");
                        String name = object.getString("name");
                        String imgLink = object.getString("hinh");
                        String ngay_tao = object.getString("time");

                        User user = new User(id,loai,tai_khoan,email,phone, Integer.parseInt(ttkh) ,Integer.parseInt(ttdg),name,imgLink, ConvertDate.changeDate(ngay_tao));

                        DataLocalManager.setAccounts(user);

                        Message message = new Message();
                        message.what = LoginConfiguration.SUCCESS;
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user", (Serializable) user);
                        message.setData(bundle);
                        handler.sendMessage(message);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast.makeText(context, "Đăng nhập thất bại lỗi hệ thống.", Toast.LENGTH_SHORT).show();
                Message message = new Message();
                message.what = LoginConfiguration.ERROR_SYSTEM;
                handler.sendMessage(message);

            }
        }){
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();

                map.put("tai_khoan", username);
                map.put("mat_khau", MD5.md5(password));

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

    public void setContext(Context context) {
        this.context = context;
    }


    public void setHandler(Handler handler) {
        this.handler = handler;
    }

}
