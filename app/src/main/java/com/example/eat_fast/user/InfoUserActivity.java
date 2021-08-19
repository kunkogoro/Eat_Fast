package com.example.eat_fast.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.eat_fast.R;
import com.example.eat_fast.beens.User;
import com.example.eat_fast.menuHome.HomePageActivity;
import com.example.eat_fast.shareReferences.DataLocalManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class InfoUserActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener  {

    private ImageView imageView;
    private TextView tvName,tvTk,tvEmail,tvPhone,tvTT;
    private AppCompatButton btndx;
    private GoogleApiClient signInClient;
    private GoogleSignInOptions gso;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_info_user);

        getView();

        init();

        envent();

    }
    void envent(){

        btndx.setOnClickListener(v -> {

            if (user.getTai_khoan() == null)
            Auth.GoogleSignInApi.signOut(signInClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull @NotNull Status status) {
                    if (status.isSuccess()){
                        startActivity(new Intent(InfoUserActivity.this, HomePageActivity.class));
                        finish();
                    }

                }
            });
           DataLocalManager.setAccounts(null);
            startActivity(new Intent(this, HomePageActivity.class));
            finish();

        });

    }
    void init(){

        user = DataLocalManager.getAccount();


        tvName.setText(user.getName());
        tvEmail.setText("Email: " + user.getEmail());
        if (user.getPhone() == null){
            tvPhone.setText("Số điện thoại: "+ "chưa cập nhật");
        }else{
            tvPhone.setText("Số điện thoại: "+ user.getPhone());
        }


        if (user.getTai_khoan() == null){
            tvTk.setText("Tài khoản: "+user.getEmail());
        }else{
            tvTk.setText("Tài khoản: "+user.getTai_khoan());
        }


        if (user.getTtdg() ==1 || user.getTai_khoan() == null){
            tvTT.setText("Trạng thái đánh giá: Hoạt động");
        }else {
            tvTT.setText("Trạng thái đánh giá: Cấm chat");
        }

        if (user.getHinh() != null){
            ImageRequest imageRequest = new ImageRequest(user.getHinh(), response -> {

                imageView.setImageBitmap(response);

            },0,0, ImageView.ScaleType.FIT_CENTER, null, error -> {
                Toast.makeText(InfoUserActivity.this, "Lỗi tải hình!", Toast.LENGTH_SHORT).show();
            });
            Volley.newRequestQueue(InfoUserActivity.this).add(imageRequest);
        }



    }
    void getView(){

        imageView = findViewById(R.id.hinh);
        tvName = findViewById(R.id.name);
        tvEmail = findViewById(R.id.email);
        tvPhone = findViewById(R.id.phone);
        tvTk = findViewById(R.id.tai_khoan);
        tvTT = findViewById(R.id.ttdg);
        btndx = findViewById(R.id.dangxuat);

         gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        signInClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

    }

    @Override
    public void onConnectionFailed(@NonNull @NotNull ConnectionResult connectionResult) {

    }
}