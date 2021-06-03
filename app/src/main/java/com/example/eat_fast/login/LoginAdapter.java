package com.example.eat_fast.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    private int totalTabs;

    public LoginAdapter(@NonNull FragmentManager fm,Context context,int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    public Fragment getItem(int position) {

        switch (position){
            case 0:
                LoginTabFragment loginTabFragment = new LoginTabFragment();
                System.out.println("so1");
                return loginTabFragment;
            case 1:
                SignupTabFragment signupTabFragment = new SignupTabFragment();
                System.out.println("so2");
                return signupTabFragment;
        }

        return  null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
