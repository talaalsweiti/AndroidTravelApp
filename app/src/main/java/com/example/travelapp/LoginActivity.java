package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DataBaseHelper dataBaseHelper =new
                DataBaseHelper(LoginActivity.this,"TRAVEL_APP",null,1);

        Button btnLogIn = findViewById(R.id.btnLogIn);
        final EditText emailEditText =
                (EditText)findViewById(R.id.edtSignInEmail);



        EditText passwordEditText = findViewById(R.id.edtSignInPassword);

        btnLogIn.setOnClickListener(
                view -> {
//                    Log.i("emaillllll",  emailEditText.getText().toString());
                    if(emailEditText.getText().toString().equals("")) {
                        emailEditText.setError("PLEASE ENTER A VALID EMAIL ADDRESS");
                        emailEditText.requestFocus();
                    }else {
                        Cursor searchUser = dataBaseHelper.searchUser(emailEditText.getText().toString());
                        if (searchUser.moveToFirst()){

//                            if (searchUser.getString(3).matches(passwordEditText.getText().toString())) {
//                                passwordEditText.setError("WRONG PASSWORD");
//                                passwordEditText.requestFocus();
//                            } else {
//                                Intent intent = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
//                                LoginActivity.this.startActivity(intent);
//                                finish();
//                            }
                        } else {
                            emailEditText.setError("PLEASE ENTER A VALID EMAIL ADDRESS");
                            emailEditText.requestFocus();

                        }
                        searchUser.close();
                    }

                    dataBaseHelper.close();
                }
        );

    }
}