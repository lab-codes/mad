package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signin extends AppCompatActivity {
    EditText username,password;
    Button signinbtn;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        signinbtn=(Button) findViewById(R.id.signin);
        Bundle bundle=getIntent().getExtras();
        String uname= bundle.getString("username");
        String pwd=bundle.getString("password");
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(uname.equals(user)&&pwd.equals(pass))
                {
                    Toast.makeText(signin.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }else
                {count++;
                    if(count>=3)
                    {
                        signinbtn.setEnabled(false);
                    }else{
                        Toast.makeText(signin.this,"Invalid username or password",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}
