package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] country = { "India", "USA", "China", "Japan", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        Button addCustomerButton = findViewById(R.id.btnSignUp);

        final EditText emailEditText =
                findViewById(R.id.edtSignUpEmail);
        final EditText firstNameEditText =
                findViewById(R.id.edtSignUpFirstName);
        final EditText lastNameEditText =
                findViewById(R.id.edtSignUpLastName);
        final EditText PassEditText =
                findViewById(R.id.edtSignUpPassword);

        final EditText confirmPassEditText =
                findViewById(R.id.edtSignUpConfirmPassword);


        addCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean flg = true;
                User newUser =new User();
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                        "[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                        "A-Z]{2,7}$";
                Pattern pat = Pattern.compile(emailRegex);

                if(emailEditText.getText().toString().isEmpty() || ! pat.matcher(emailEditText.getText().toString()).matches()) {
                    emailEditText.setError("PLEASE ENTER A VALID EMAIL ADDRESS");
                    emailEditText.requestFocus();
                    flg=false;
                }
                else {
                    newUser.setEmail(emailEditText.getText().toString());
                    flg = true;
                }

                if(firstNameEditText.getText().toString().isEmpty() || PassEditText.getText().toString().length() > 20 || PassEditText.getText().toString().length() < 3) {
                    firstNameEditText.setError("PLEASE ENTER A VALID FIRST NAME");
                    firstNameEditText.requestFocus();
                    flg=false;
                }
                else {
                    newUser.setFirstName(firstNameEditText.getText().toString());
                    flg= true;
                }

                if(lastNameEditText.getText().toString().isEmpty() || PassEditText.getText().toString().length() > 20 || PassEditText.getText().toString().length() < 3 ) {
                    lastNameEditText.setError("PLEASE ENTER A VALID LAST NAME");
                    lastNameEditText.requestFocus();
                    flg=false;
                }
                else {
                    newUser.setLastName(lastNameEditText.getText().toString());
                    flg=true;
                }

                if(PassEditText.getText().toString().isEmpty() || PassEditText.getText().toString().length() > 15 || PassEditText.getText().toString().length() < 8
                || ! PassEditText.getText().toString().matches(".*\\d.*")
                || ! PassEditText.getText().toString().matches(".*[a-z].*")
                        || ! PassEditText.getText().toString().matches(".*[A-Z].*")   ) {
                    PassEditText.setError("PLEASE ENTER A VALID PASSWORD");
                    PassEditText.requestFocus();
                    flg=false;
                }
                else if (PassEditText.getText().toString().compareTo(confirmPassEditText.getText().toString()) != 0)
                {
                    PassEditText.setError("PASSWORD DOES NOT MATCH");
                    PassEditText.requestFocus();
                    flg=false;
                }
                else {
                    newUser.setPassword(PassEditText.getText().toString());
                    flg=true;
                }
                newUser.setDestination(spin.getSelectedItem().toString());


                DataBaseHelper dataBaseHelper =new
                        DataBaseHelper(SignupActivity.this,"TRAVEL_APP",null,1);
                if(flg)
                    dataBaseHelper.insertUser(newUser);



                Cursor allUsersCursor = dataBaseHelper.getAllUsers();
                LinearLayout ll = findViewById(R.id.LinearLayout);
                ll.removeAllViews();
                while (allUsersCursor.moveToNext()) {
                    TextView textView11 = new TextView(SignupActivity.this);
                    textView11.setText(
                            "Email= " + allUsersCursor.getString(0)
                                    + "\nFirst Name= " + allUsersCursor.getString(1)
                                    + "\nLast Name= " + allUsersCursor.getString(2)
                                    + "\nPassword= " + allUsersCursor.getString(3)
                                    + "\nDest= " + allUsersCursor.getString(4)

                                    + "\n\n"
                    );
                    ll.addView(textView11);
                }

//                Intent intent=new Intent(SignupActivity.this,NavigationDrawerActivity.class);
//                SignupActivity.this.startActivity(intent);
//                finish();


            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }



}