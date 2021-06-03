package com.example.eat_fast.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.eat_fast.R;


public class SignupTabFragment extends Fragment {

    private EditText username;
    private EditText password;
    private AppCompatButton login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_singup_fragment,container,false);

        getView(root);

        return root;
    }
    private void getView(View root) {

        username = root.findViewById(R.id.user_name);
        password = root.findViewById(R.id.password);
        login = root.findViewById(R.id.appCompatButton);

    }
}
