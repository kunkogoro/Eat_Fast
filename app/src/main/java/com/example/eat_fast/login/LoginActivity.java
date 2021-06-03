package com.example.eat_fast.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.eat_fast.R;
import com.example.eat_fast.connection.ConnecttionConfigure;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;

public class LoginActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView google,facebook;

    private float v = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getView();
        addTab();
        init();
        anim();
    }

    private void anim() {
        google.setTranslationY(300);
        facebook.setTranslationY(300);
        tabLayout.setTranslationY(300);

        google.setAlpha(v);
        facebook.setAlpha(v);
        tabLayout.setAlpha(v);

        facebook.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
    }

    private void init() {
        final LoginAdapter  loginAdapter = new LoginAdapter(getSupportFragmentManager(),this,2);
         viewPager.setAdapter(loginAdapter);
         viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    void addTab(){
        tabLayout.addTab(tabLayout.newTab().setText("Đăng nhập"));
        tabLayout.addTab(tabLayout.newTab().setText("Đăng kí"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void getView() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_paper);
        google = findViewById(R.id.tab_google);
        facebook = findViewById(R.id.tab_facebook);


    }
}