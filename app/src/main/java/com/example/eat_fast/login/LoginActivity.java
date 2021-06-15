package com.example.eat_fast.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.eat_fast.R;
import com.example.eat_fast.beens.User;
import com.example.eat_fast.connection.ConnecttionConfigure;
import com.example.eat_fast.menuHome.HomePageActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;

public class LoginActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView facebook;
    private GoogleSignInClient mGoogleSignInClient;
    private float v = 0;
    private static int RC_SIGN_IN = 1;
    private SignInButton signInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getView();
        addTab();
        init();
        event();
        anim();

    }

    void event(){

        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }

            }
        });

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);


            User user = new User();

            Intent intent =  new Intent(LoginActivity.this, HomePageActivity.class);

            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                System.out.println("User URI: " + personPhoto);

                user.setEmail(personEmail);
               user.setName(personName);
              user.setHinh(String.valueOf(personPhoto));

            }

             intent.putExtra("user",user);
            startActivity(intent);
            finish();

            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("LOGIN ", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void anim() {
       // google.setTranslationY(300);
        facebook.setTranslationY(300);
        tabLayout.setTranslationY(300);

      //  google.setAlpha(v);
        facebook.setAlpha(v);
        tabLayout.setAlpha(v);

        facebook.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
       // google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
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
        //google = findViewById(R.id.tab_google);
        facebook = findViewById(R.id.tab_facebook);
        signInButton = findViewById(R.id.sign_in_button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }
}