package com.example.eat_fast.sinup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.eat_fast.R;


public class SignupTabFragment extends Fragment {

    private EditText username,password,email,repass;
    private AppCompatButton sinup;
    private SignupController signupController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_singup_fragment,container,false);

        getView(root);

        eventCheckErro();

        return root;
    }

    void eventCheckErro(){

        sinup.setOnClickListener(v -> {
            String user = username.getText().toString().trim();
            String email = this.email.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String repass = this.repass.getText().toString().trim();

            signupController.register(user,pass,repass,email);
        });

    }

    private void getView(View root) {

        email = root.findViewById(R.id.email);
        repass = root.findViewById(R.id.repassword);
        username = root.findViewById(R.id.user_name);
        password = root.findViewById(R.id.password);
        sinup = root.findViewById(R.id.appCompatButton);
        signupController = new SignupController();
        signupController.setSignupView(this);

    }
    void showErrorEmpty(){
        Toast.makeText(getContext(), "Vui lòng nhập đầu đủ thông tin", Toast.LENGTH_SHORT).show();
    }
    void showErrorValida(){
        Toast.makeText(getContext(), "Xác nhận mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
    }
    void showErrorEmail(){
        Toast.makeText(getContext(), "Email không hợp lệ", Toast.LENGTH_SHORT).show();
    }
    void showErrorExistEmail(){
        Toast.makeText(getContext(), "Email đã đăng kí cho mụt tài khoản khác", Toast.LENGTH_SHORT).show();
    }
    void showErrorExistUser(){
        Toast.makeText(getContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
    }
    void showSuccess(){
        Toast.makeText(getContext(), "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
    }
    void showFail(){
        Toast.makeText(getContext(), "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
    }

}
