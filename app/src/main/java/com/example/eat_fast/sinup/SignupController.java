package com.example.eat_fast.sinup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.eat_fast.beens.User;
import com.example.eat_fast.menuHome.HomePageActivity;

public class SignupController {

    private SignupModel signupModel;
    private SignupTabFragment signupView;

    public SignupController(){
        signupModel = new SignupModel();
    }

    void register(String user,String pass,String repass,String email){

        if (user.isEmpty() || pass.isEmpty() || pass.isEmpty() || repass.isEmpty() ){
            signupView.showErrorEmpty();
            return;
        }else if (!CheckEmail.emailValidator(email)){
            signupView.showErrorEmail();
            return;
        } else if(!pass.equals(repass)){
            signupView.showErrorValida();
            return;
        }else {

           signupModel.setContext(signupView.getContext());

            Handler handler = new Handler(){

                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);

                    switch (msg.what){
                        case 0:
                            signupView.showErrorExistEmail();
                            break;
                        case 1:
                            signupView.showErrorExistUser();
                            break;
                        case 2:
                            signupView.showSuccess();
                            break;
                        case 3:
                            signupView.showFail();
                            break;
                    }

                }

            };

            signupModel.setHandler(handler);

            signupModel.register(user, pass, email);

        }
    }

    public void setSignupView(SignupTabFragment signupView) {
        this.signupView = signupView;
    }
}
