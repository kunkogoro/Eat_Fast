package com.example.eat_fast.login;

import android.content.Intent;
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
import com.example.eat_fast.forgotpass.ChangePassword;
import com.example.eat_fast.forgotpass.EnterEmail;
import com.example.eat_fast.forgotpass.VerifyCode;
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

    public LoginTabFragment() {
    }

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

                loginController.login(user,pass);
            }
        });

        forgotpass.setOnClickListener(v->{
            startActivity(new Intent(getContext(), EnterEmail.class));
        });
    }

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
        loginController = new LoginController();
        loginController.setLoginView(this);

    }

    void showLoginSuccess(){
        Toast.makeText(getContext(), "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
    }
    void showLoginFail(){
        Toast.makeText(getContext(), "Sai mật khẩu, vui lòng nhập lại.", Toast.LENGTH_SHORT).show();
    }
    void showUserNotExitst(){
        Toast.makeText(getContext(), "Tài khoản không tồn tại.", Toast.LENGTH_SHORT).show();
    }
    void showErrorSystem(){
        Toast.makeText(getContext(), "Lỗi hệ thống, vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
    }
    void showErrorEmpty(){
        Toast.makeText(getContext(), "Tài khoản và mật khẩu không được bỏ trống.", Toast.LENGTH_SHORT).show();
    }


}
