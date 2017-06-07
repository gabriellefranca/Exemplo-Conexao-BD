package com.example.aluno.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gabi2.login.R;

public class UserProfile extends Activity {
    private TextView textView;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        textView = (TextView) findViewById(R.id.textViewUsername);

        Intent intent = getIntent();

        String user = intent.getStringExtra(MainActivity.KEY_USERNAME);
        textView.setText("Bem-vindo " + user);
    }
}