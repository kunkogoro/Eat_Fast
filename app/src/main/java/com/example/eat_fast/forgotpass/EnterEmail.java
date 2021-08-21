package com.example.eat_fast.forgotpass;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eat_fast.R;
import com.example.eat_fast.beens.User;
import com.example.eat_fast.date.ConvertDate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

public class EnterEmail extends AppCompatActivity {

    AppCompatButton buttonSend;
    EditText editTextEmail;
    String urlGetUser = "https://eatfastandroid.000webhostapp.com/getdata.php";
    String urlSentEmail = "https://eatfastandroid.000webhostapp.com/phpmailer/index.php";
    List<User> listUser;
    int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_email);

        listUser = new ArrayList<>();
        buttonSend = findViewById(R.id.buttonSendEmail);
        editTextEmail = findViewById(R.id.editTextEmail);

        getUser();

        buttonSend.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            if (email.isEmpty()) {
                Toast.makeText(this, "Yêu cầu nhập email", Toast.LENGTH_SHORT).show();
            } else {
                if (!isValid(email)) {
                    Toast.makeText(this, "Email không đúng", Toast.LENGTH_SHORT).show();
                } else {
                    if (isExistEmail(email)) {
                        sentEmail(email);
                        Intent intent = new Intent(this, VerifyCode.class);
                        intent.putExtra("code", String.valueOf(code));
                        intent.putExtra("id", String.valueOf(getId(email)));
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Email không tồn tại trong hệ thống", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void getUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlGetUser, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
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

                            User user = new User(id, loai, tai_khoan, email, phone, Integer.parseInt(ttkh), Integer.parseInt(ttdg), name, imgLink, ConvertDate.changeDate(ngay_tao));

                            listUser.add(user);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> {

                });
        requestQueue.add(jsonArrayRequest);
    }

    public boolean isExistEmail(String email) {
        for (int i = 0; i < listUser.size(); i++) {
            String e = listUser.get(i).getEmail();
            if (e.equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public String getId(String email) {
        for (int i = 0; i < listUser.size(); i++) {
            String e = listUser.get(i).getEmail();
            if (e.equalsIgnoreCase(email)) {
                return listUser.get(i).getId();
            }
        }
        return null;
    }

    public void sentEmail(String email) {
        Random random = new Random();
        code = random.nextInt(8999) + 1000;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlSentEmail,
                response -> {

                }, error -> {

        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("code", String.valueOf(code));

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public static boolean isValid(String email) {
        String emailFormate = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\."
                + "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        Pattern p = Pattern.compile(emailFormate);
        if (email == null) {
            return false;
        }
        return p.matcher(email).matches();
    }
}