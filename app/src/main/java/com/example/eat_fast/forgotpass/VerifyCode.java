package com.example.eat_fast.forgotpass;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.eat_fast.R;

public class VerifyCode extends AppCompatActivity {
    AppCompatButton buttonSubmitCode;
    EditText digitCode1, digitCode2, digitCode3, digitCode4;
    String code, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        anhxa();
        autoNextEdit();

        Intent intent = this.getIntent();
        code = intent.getStringExtra("code");
        id = intent.getStringExtra("id");

        event();

    }

    public void event() {
        buttonSubmitCode.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, ChangePassword.class);
            intent1.putExtra("id", id);
            startActivity(intent1);
//            if(isRightCode()){
//                startActivity(intent1);
//            }else{
//                Toast.makeText(this, "Mã OTP không đúng", Toast.LENGTH_SHORT).show();
//            }

        });
    }

    public boolean isRightCode() {
        String inputCode = digitCode1.getText().toString() + digitCode2.getText().toString() + digitCode3.getText().toString() + digitCode4.getText().toString();
        return inputCode.equals(code);
    }

    public void autoNextEdit() {
        digitCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (digitCode1.length() > 0) {
                    digitCode2.requestFocus();
                }
            }
        });

        digitCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (digitCode1.length() > 0) {
                    digitCode3.requestFocus();
                }
            }
        });

        digitCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (digitCode1.length() > 0) {
                    digitCode4.requestFocus();
                }
            }
        });
    }

    public void anhxa() {
        buttonSubmitCode = findViewById(R.id.buttonSubmitcode);
        digitCode1 = findViewById(R.id.editTextDigitCode1);
        digitCode2 = findViewById(R.id.editTextDigitCode2);
        digitCode3 = findViewById(R.id.editTextDigitCode3);
        digitCode4 = findViewById(R.id.editTextDigitCode4);
    }
}