package com.example.ridzi.helpteens1;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText emailAddress, password;
    Button login;
    TextView signup, forgot;

    private static int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        // getSupportActionBar().setTitle("Login");
        emailAddress = (EditText) findViewById(R.id.email_address);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        signup = (TextView) findViewById(R.id.signup);
        forgot = (TextView) findViewById(R.id.forgot_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailAddress.getText().toString();
                String passw = password.getText().toString();

                DBHelper db = new DBHelper(getApplicationContext());

                String stored_password = db.getRegister(email);

                if (email.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Enter userName", Toast.LENGTH_SHORT).show();
                    return;
                } else if (passw.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!passw.equals(stored_password) && !email.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Enter valid Username & Password", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(getBaseContext(), "Successfully logged in...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, NavigationDrawer.class);
                    intent.putExtra("data",email);
                    startActivity(intent);
                }
            }
        });

        //Signup activity
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        openDialog();

                    }
                });


            }

            //Dialog Box for forgot password
            private void openDialog() {

                final Dialog dialog = new Dialog(Login.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialogue);
                final EditText email_forgot = (EditText) dialog.findViewById(R.id.txt_name);

                Button cancelBtn = (Button) dialog.findViewById(R.id.btn_cancel);
                Button ok = (Button) dialog.findViewById(R.id.btn_ok);
                final DBHelper db = new DBHelper(getApplicationContext());
                final TextView password_show = (TextView) dialog.findViewById(R.id.password);

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        password_show.setText(db.getRegister(email_forgot.getText().toString()));
                    }
                });
                dialog.show();
            }


 /*   //Dialog Box for forgot password
    private void openDialog() {

        final Dialog dialog = new Dialog(Login.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue);
        Button cancelBtn = (Button) dialog.findViewById(R.id.btn_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        DBHelper db = new DBHelper(getApplicationContext());
        final String email = emailAddress.getText().toString();
        final String stored_email = db.getEmail(email);
        final String stored_password = db.getRegister(email);
        Button ok = (Button) dialog.findViewById(R.id.btn_ok);

        final EditText email_forgot = (EditText) dialog.findViewById(R.id.txt_name);
        final TextView password_show = (TextView) dialog.findViewById(R.id.password);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.equals(stored_email))
                    password_show.setText(stored_password);
                else
                    Toast.makeText(getBaseContext(), "Enter Valid Email Address...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

*/
}
