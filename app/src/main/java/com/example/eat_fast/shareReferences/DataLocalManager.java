package com.example.eat_fast.shareReferences;

import android.accounts.Account;
import android.content.Context;

import com.example.eat_fast.beens.User;
import com.google.gson.Gson;


public class DataLocalManager {

    private static final String REF_ACCOUNT = "REF_ACCOUNT";

    private static DataLocalManager instance;
    private MyShareReferences myShareReferences;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.myShareReferences = new MyShareReferences(context);
    }
    public static DataLocalManager getInstance(){
        if(instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setAccounts(User account){
        Gson gson = new Gson();
        String stringJSONAccount = gson.toJson(account);
        DataLocalManager.getInstance().myShareReferences.putStringValue(REF_ACCOUNT,stringJSONAccount);
    }
    public static void setAccount(String response){
        DataLocalManager.getInstance().myShareReferences.putStringValue(REF_ACCOUNT,response);
    }
    public static User getAccount(){
        Gson gson = new Gson();
        String stringJSONAccount = DataLocalManager.getInstance().myShareReferences.getStringValue(REF_ACCOUNT);
        User account = gson.fromJson(stringJSONAccount,User.class);
        return account;
    }

}
