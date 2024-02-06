package com.jayesh.login_register_page;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    // Class Object Create
    MyDBHelper dbhelper = new MyDBHelper(this);
    //    Variable Declaration
    private TextView go_register, go_forgot;
    private EditText login_email, login_password;
    private Button login_btn, login_facebook, login_google;
    private String user_email, user_password;

    // Override method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar obj = getSupportActionBar();
        if (obj != null) {
            obj.hide();
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        login();
    }
    public  String getEmail()
    {
        return this.user_email;
    }
    public void toastPrint(String text) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_custom_toast, (ViewGroup) findViewById(R.id.custom_toast_contenear));
        TextView textView = layout.findViewById(R.id.custom_toast_text);
        textView.setText(text);
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void changeActivity(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        startActivity(intent);
    }

    public void login() {

        // Variable Reference Creation

        go_register = (TextView) findViewById(R.id.go_register);
        go_forgot = (TextView) findViewById(R.id.go_forgot);
        login_email = (EditText) findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);
        login_btn = (Button) findViewById(R.id.login_btn);
        login_facebook = (Button) findViewById(R.id.login_facebook);
        login_google = (Button) findViewById(R.id.login_google);

        // Button Click Method
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Variable Initialization
                user_email = login_email.getText().toString();
                user_password = login_password.getText().toString();

                // Login Checked
                if (!(TextUtils.isEmpty(user_email) || TextUtils.isEmpty(user_password))) {
                        if (dbhelper.isUserPassword(user_email, user_password)) {
                            toastPrint("Login Successful!");
                            changeActivity(MainActivity.this, WelcomeActivity.class);
                        } else {
                            toastPrint("Login Failed!");
                        }
                } else {
                    toastPrint("please fill all fields!");
                }
            }
        });

        login_facebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });

        login_google.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });

        go_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(MainActivity.this, Sign_Up.class);
            }
        });

        go_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(MainActivity.this, Sign_Up.class);
            }
        });
    }
}