package com.example.budgetpro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText et_email,et_password;
    Button buttonLogin;
    MyDatabaseHelper myDatabaseHelper;
    String email,password;
    TextView tv_register;
    boolean isEmailValid, isPasswordValid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);
        buttonLogin=findViewById(R.id.button_login);
        tv_register=findViewById(R.id.tv_register);

        Intent intent=getIntent();
        Bundle bd=intent.getExtras();
        if(bd!=null)
        {
            String getname=(String)bd.get("email");
            et_email.setText(getname);
        }
        myDatabaseHelper= new MyDatabaseHelper(this);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=et_email.getText().toString();
                password=et_password.getText().toString();

                SetValidation_Login();
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(i);

            }
        });
    }
    public void SetValidation_Login() {
        // Check for a valid email address.
        if (et_email.getText().toString().isEmpty()) {
            et_email.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
            et_email.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }

        // Check for a valid password.
        if (et_password.getText().toString().isEmpty()) {
            et_password.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (et_password.getText().length() < 6) {
            et_password.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }

        if (isEmailValid && isPasswordValid) {

            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            int status =Integer.parseInt( myDatabaseHelper.getLoginData(email, password));
            if (status>0) {
                final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this,"Thông Báo","Loading...");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                },4000);

            } else {
                Toast.makeText(getApplicationContext(), "Bạn đã đăng kí tài khoản chưa ?!", Toast.LENGTH_SHORT).show();
            }
        }
        else {

            Toast.makeText(getApplicationContext(), "Bạn đã đăng kí tài khoản chưa ?!", Toast.LENGTH_SHORT).show();
        }
    }
}