package com.example.eat_fast.forgotpass;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eat_fast.R;
import com.example.eat_fast.login.LoginActivity;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends AppCompatActivity {

    AppCompatButton buttonChangePass;
    EditText editTextNewPass, editTextReNewPass;
    CheckBox checkBox;
    String urlUpdatePass = "https://eatfastandroid.000webhostapp.com/updatepassword.php";
    String pass, repass;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        anhxa();
        Intent intent = this.getIntent();
        id = intent.getStringExtra("id");

        event();

    }

    public void updatePassword() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpdatePass,
                response -> {

                },
                error -> {

                }) {
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("pass", pass);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public boolean isEqualPass() {
        return pass.equalsIgnoreCase(repass);
    }

    public void event(){
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                editTextNewPass.setTransformationMethod(null);
                editTextReNewPass.setTransformationMethod(null);
            }else{
                editTextNewPass.setTransformationMethod(new PasswordTransformationMethod());
                editTextReNewPass.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        buttonChangePass.setOnClickListener(v -> {
            pass = editTextNewPass.getText().toString();
            repass = editTextReNewPass.getText().toString();
            if (pass.isEmpty() || repass.isEmpty()) {
                Toast.makeText(this, "Y??u c???u nh???p m???t kh???u", Toast.LENGTH_SHORT).show();
            } else {
                if (pass.length() < 8) {
                    Toast.makeText(this, "M???t kh???u ph???i nhi???u h??n 8 k?? t???", Toast.LENGTH_SHORT).show();
                }else{
                    if(isEqualPass()){
                        updatePassword();
                        startActivity(new Intent(this, LoginActivity.class));
                        Toast.makeText(this, "?????i m???t kh???u th??nh c??ng", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "M???t kh???u kh??ng tr??ng nhau", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void anhxa() {
        buttonChangePass = findViewById(R.id.buttonSubmitPassword);
        editTextNewPass = findViewById(R.id.editTextNewPassword);
        editTextReNewPass = findViewById(R.id.editTextReNewPassword);
        checkBox = findViewById(R.id.checkboxPass);
    }
}