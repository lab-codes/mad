package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button signupbtn;
    String regularExpr="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!])[A-Za-z\\d@$!]{8,}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        signupbtn=(Button) findViewById(R.id.signup);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=username.getText().toString();
                String pwd=password.getText().toString();
                if(validatePwd(pwd))
                {
                    Bundle bundle=new Bundle();
                    bundle.putString("username",uname);
                    bundle.putString("password",pwd);
                    Intent intent=new Intent(MainActivity.this,signin.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"Invalid entry",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public boolean validatePwd(String pwd)
    {
        Pattern pattern=Pattern.compile(regularExpr);
        Matcher matcher= pattern.matcher(pwd);
        return matcher.matches();
    }
}
