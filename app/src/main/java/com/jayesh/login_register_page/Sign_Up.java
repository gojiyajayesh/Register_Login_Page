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


public class Sign_Up extends AppCompatActivity {

    // Object Create
    MyDBHelper dbhelper = new MyDBHelper(this);
    // Variable Declaration
    private TextView go_login;
    private EditText signUp_email, signUp_password, signUp_username;
    private Button signUp_btn, signUp_facebook, signUp_google;
    private String user_email, user_password, user_name;

    // Override Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar obj = getSupportActionBar();
        if (obj != null) {
            obj.hide();
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        register();
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

    public void register() {
        // Variable Reference Creation

        go_login = (TextView) findViewById(R.id.go_login);
        signUp_email = (EditText) findViewById(R.id.register_email);
        signUp_password = (EditText) findViewById(R.id.register_password);
        signUp_username = (EditText) findViewById(R.id.user_name);
        signUp_btn = (Button) findViewById(R.id.btn_sign_up);
        signUp_facebook = (Button) findViewById(R.id.facebook_sign_up);
        signUp_google = (Button) findViewById(R.id.google_sign_up);

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Variable Initialization
                user_name = signUp_username.getText().toString();
                user_email = signUp_email.getText().toString();
                user_password = signUp_password.getText().toString();

                // Register Checked
                if (!(TextUtils.isEmpty(user_name) || TextUtils.isEmpty(user_email) || TextUtils.isEmpty(user_password))) {
                    if (!dbhelper.isUser(user_email)) {
                        if (dbhelper.register_user(user_name, user_email, user_password)) {
                            toastPrint("Registration Successful.\nplease log in");
                            changeActivity(Sign_Up.this, MainActivity.class);
                        } else {
                            toastPrint("Registration Failed.\nPlease try again");
                        }
                    } else {
                        toastPrint("This account already exists.\nplease log in");
                        changeActivity(Sign_Up.this, MainActivity.class);
                    }
                } else {
                    toastPrint("Please fill all fields!");
                }
            }
        });

        signUp_google.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });

        signUp_facebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });

        go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(Sign_Up.this, MainActivity.class);
            }
        });
    }
}

