package com.jayesh.login_register_page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
private TextView welcome_name;
MainActivity name=new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
    }
    public void name()
    {
        welcome_name=(TextView) findViewById(R.id.name);
        welcome_name.setText(name.getEmail());
    }
}