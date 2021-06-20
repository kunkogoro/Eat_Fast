package com.example.eat_fast.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.eat_fast.beens.User;
import com.example.eat_fast.menuHome.HomePageActivity;
import com.example.eat_fast.menuHome.HomePageFragment;

import java.io.Serializable;

public class LoginController {

    private LoginModel loginModel;
    private LoginTabFragment loginView;

    public LoginController() {
        this.loginModel = new LoginModel();
    }

    void login(String username, String password) {

        if (username.isEmpty() || password.isEmpty()) {
            loginView.showErrorEmpty();
            return;
        } else {
            loginModel.setContext(loginView.getContext());
            Handler handler = new Handler() {

                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);

                    switch (msg.what) {
                        case 0:
                            loginView.showUserNotExitst();
                            break;
                        case 1:
                            loginView.showLoginFail();
                            break;
                        case 2:
                            loginView.showLoginSuccess();
;
                            Bundle bundle = msg.getData();

                            User user = (User) bundle.getSerializable("user");

                            System.out.println("OK: " + user.toString());

                            Intent intent = new Intent(loginView.getContext(), HomePageActivity.class);

                            intent.putExtra("user", user);

                            loginView.getContext().startActivity(intent);
                            break;
                        case 3:
                            loginView.showErrorSystem();
                            break;
                    }

                }

            };

            loginModel.setHandler(handler);

            loginModel.login(username, password);

        }
    }

    public void setLoginView(LoginTabFragment loginView) {
        this.loginView = loginView;
    }
}
