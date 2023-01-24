package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;

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

        sharedPrefManager = SharedPrefManager.getInstance(this);
        emailEditText.setText(sharedPrefManager.readString("Email","noValue"));

        btnLogIn.setOnClickListener(
                view -> {
                    if(emailEditText.getText().toString().equals("")) {
                        emailEditText.setError("PLEASE ENTER A VALID EMAIL ADDRESS");
                        emailEditText.requestFocus();
                    }else {
                        Cursor searchUser = dataBaseHelper.searchUser(emailEditText.getText().toString());
                        if (searchUser.moveToFirst()){
                            CheckBox RememberMe = (CheckBox) findViewById(R.id.checkBox);
                            if (!searchUser.getString(3).matches(passwordEditText.getText().toString())) {
                                passwordEditText.setError("WRONG PASSWORD");
                                passwordEditText.requestFocus();
                            } else {
                                //when successful log in
                                if(RememberMe.isChecked()){
                                    sharedPrefManager.writeString("Email",emailEditText.getText().toString());
                                    Toast.makeText(LoginActivity.this, "Email Address Remembered",
                                            Toast.LENGTH_SHORT).show();
                                }
                                Intent intent = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
                                LoginActivity.this.startActivity(intent);
                                finish();
                            }

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
    /**
     * Called when the user touches the button
     */
    public void signUp(View view) {
        // Do something in response to button click
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        LoginActivity.this.startActivity(intent);
    }

}