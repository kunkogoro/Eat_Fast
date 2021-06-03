package com.example.eat_fast.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eat_fast.R;
import com.example.eat_fast.connection.ConnecttionConfigure;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginTabFragment extends Fragment {

    private EditText username;
    private EditText password;
    private TextView forgotpass;
    private AppCompatButton login;
    private float v = 0;
    private LoginController loginController;

   private String url = ConnecttionConfigure.URL+ConnecttionConfigure.LOGIN;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_login_fragment,container,false);

        getView(root);

        anim();

        event();

        return root;
    }
    void event(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                //login(user,pass);

                if (user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    loginController.login(user,pass);
                }
            }
        });
    }

    void login(String user,String pass){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "đăng nhập thành công", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR: " +error);
                Toast.makeText(getContext(), "đăng nhập thất bại "  + error , Toast.LENGTH_SHORT).show();

            }
        }){

            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();

                map.put("tai_khoan", user);
                map.put("mat_khau", pass);

                return map;
            }

        };


//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                if (response != null){
//                    Toast.makeText(getContext(), "đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                System.out.println("ERROR: "+error);
//                Toast.makeText(getContext(), "đăng nhập thất bại "  + error , Toast.LENGTH_SHORT).show();
//            }
//        }){
//
//            @Nullable
//        @org.jetbrains.annotations.Nullable
//        @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> map = new HashMap<>();
//
//                map.put("tai_khoan", user);
//                map.put("mat_khau", pass);
//
//                return map;
//            }
//        };

        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        requestQueue.add(stringRequest);
        }


  //  {


    private void anim() {
        username.setTranslationY(300);
        password.setTranslationY(300);
        forgotpass.setTranslationY(300);
        login.setTranslationY(300);

        username.setAlpha(v);
        password.setAlpha(v);
        forgotpass.setAlpha(v);
        login.setAlpha(v);

        username.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgotpass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();
    }

    private void getView(View root) {

        username = root.findViewById(R.id.user_name);
        password = root.findViewById(R.id.password);
        forgotpass = root.findViewById(R.id.quenmk);
        login = root.findViewById(R.id.appCompatButton);
        loginController = new LoginController(this);

    }


}
