package com.example.eat_fast.login;

public class LoginController {

    private LoginModel loginModel;
    private LoginTabFragment loginView;

    public LoginController (LoginTabFragment loginTabFragment){
        this.loginView = loginTabFragment;
        this.loginModel = new LoginModel(loginView.getContext());
    }

    void login(String username,String password){
     loginModel.login(username,password);
    }

}
