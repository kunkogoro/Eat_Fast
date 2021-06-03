package com.example.eat_fast.login;

import android.content.Context;
import android.content.Intent;
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
import com.example.eat_fast.connection.ConnecttionConfigure;
import com.example.eat_fast.menuHome.HomePageActivity;

import java.util.HashMap;
import java.util.Map;

public class LoginModel {

   // private String urlCheckUser = ConnecttionConfigure.URL+ConnecttionConfigure.CHECK_USER;
    private String url = ConnecttionConfigure.URL+ConnecttionConfigure.LOGIN;
    private Context context;

    public LoginModel(Context context){
        this.context = context;
    }


    public void login(String username,String password){

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("NOT EXIST")){
                    System.out.println("RESPONSE EX" + response);

                    Toast.makeText(context, "Tài khoản không tồn tại.", Toast.LENGTH_SHORT).show();

                }else if (response.equals("ERROR PASS")){
                    System.out.println("RESPONSE NEX" + response);
                    Toast.makeText(context, "Mật khẩu không chính xác.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,HomePageActivity.class);

                    context.startActivity(intent);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Đăng nhập thất bại lỗi hệ thống.", Toast.LENGTH_SHORT).show();
                System.out.println("ERROR CHECK USER:" + error);
            }
        }){
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();

                map.put("tai_khoan", username);
                map.put("mat_khau", password);

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
//    public void checkPass(String username,String password){
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlCheckPass, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(context, "đăng nhập thành công", Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, "đăng nhập thất bại "  + error , Toast.LENGTH_SHORT).show();
//
//            }
//        }){
//
//            @Nullable
//            @org.jetbrains.annotations.Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> map = new HashMap<>();
//
//                map.put("tai_khoan", username);
//                map.put("mat_khau", password);
//
//                return map;
//            }
//
//        };
//        requestQueue.add(stringRequest);
//    }



}
