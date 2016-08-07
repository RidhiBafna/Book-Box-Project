package com.example.ridzi.helpteens1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Signup extends AppCompatActivity {

    Button submit;
    EditText name, email, phone, address, city, password, confirm;
    DBHelper dbHelper;
    TextView tv;
    String Gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        submit = (Button) findViewById(R.id.submit);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone_no);
        address = (EditText) findViewById(R.id.home_address);
        city = (EditText) findViewById(R.id.city);
        password = (EditText) findViewById(R.id.password);
        confirm = (EditText) findViewById(R.id.confirm);


        dbHelper = new DBHelper(getApplicationContext());

        final String EMAIL_PATTERN = "^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty())
                    Toast.makeText(Signup.this, "Please enter name.", Toast.LENGTH_SHORT).show();
                else if (email.getText().toString().isEmpty())
                    Toast.makeText(Signup.this, "Please enter email address.", Toast.LENGTH_SHORT).show();
                else if (phone.getText().toString().isEmpty())
                    Toast.makeText(Signup.this, "Please enter phone number.", Toast.LENGTH_SHORT).show();
                else if (!email.getText().toString().matches(EMAIL_PATTERN))
                    Toast.makeText(Signup.this, "Enter valid Email Address...", Toast.LENGTH_SHORT).show();
                else if (address.getText().toString().isEmpty())
                    Toast.makeText(Signup.this, "Please enter address.", Toast.LENGTH_SHORT).show();
                else if (city.getText().toString().isEmpty())
                    Toast.makeText(Signup.this, "Please enter city.", Toast.LENGTH_SHORT).show();
                else if (password.getText().toString().isEmpty())
                    Toast.makeText(Signup.this, "Please enter password.", Toast.LENGTH_SHORT).show();
                else if (confirm.getText().toString().isEmpty())
                    Toast.makeText(Signup.this, "Please enter password.", Toast.LENGTH_SHORT).show();
                else if (!password.getText().toString().equals(confirm.getText().toString())) {
                    Toast.makeText(Signup.this, "Password does not match.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Signup.this, "Account Created", Toast.LENGTH_SHORT).show();
                    BookBoxModel book = new BookBoxModel();
                    book.name = name.getText().toString();
                    book.phone = phone.getText().toString();
                    book.city = city.getText().toString();
                    book.address = address.getText().toString();
                    book.password = password.getText().toString();
                    book.email = email.getText().toString();
                    book.gender = Gender;
                    dbHelper.addDetails(book);

                    Intent intent = new Intent(Signup.this, NavigationDrawer.class);
                    startActivity(intent);

                }
            }
        });


    }


    public void RadioButtonClicked(View view) {

//This variable will store whether the user was male or female

// Check that the button is  now checked?
        boolean checked = ((RadioButton) view).isChecked();

// Check which radio button was clicked
        switch (view.getId()) {
            case R.id.female:
                if (checked)
                    Gender = "female";
                break;
            case R.id.male:
                if (checked)
                    Gender = "male";
                break;
        }
    }
}
