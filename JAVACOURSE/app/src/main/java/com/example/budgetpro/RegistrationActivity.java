package com.example.budgetpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    EditText et_email,et_password,et_name,et_mobile;
    Button buttonRegister;
    MyDatabaseHelper myDatabaseHelper;
    String email,password,name,mobile;
    boolean isNameValid, isEmailValid, isPhoneValid, isPasswordValid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);
        buttonRegister=findViewById(R.id.button_register);
        et_mobile=findViewById(R.id.et_mobile);
        et_name=findViewById(R.id.et_name);

        myDatabaseHelper= new MyDatabaseHelper(this);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=et_email.getText().toString();
                password=et_password.getText().toString();
                name=et_name.getText().toString();
                mobile=et_mobile.getText().toString();

                SetValidation_Register();

            }
        });
    }
    public void SetValidation_Register() {
        // Kiểm tra fields name
        if (et_name.getText().toString().isEmpty()) {
            et_name.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
        }

        // Kiểm tra fields email
        if (et_email.getText().toString().isEmpty()) {
            et_email.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
            et_email.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }

        // Kiểm tra fields số điện thoại
        if (et_mobile.getText().toString().isEmpty()) {
            et_mobile.setError(getResources().getString(R.string.phone_error));
            isPhoneValid = false;
        } else  {
            isPhoneValid = true;
        }

        // Kiểm tra fields password
        if (et_password.getText().toString().isEmpty()) {
            et_password.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (et_password.getText().length() < 6) {
            et_password.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }

        if (isNameValid && isEmailValid && isPhoneValid && isPasswordValid) {
            Toast.makeText(getApplicationContext(), "Đăng kí thành công", Toast.LENGTH_LONG).show();
            boolean status = myDatabaseHelper.addUser(name,email,mobile,password);
            if (status) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                i.putExtra("email",et_email.getText().toString());
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Bạn vẫn chưa đăng kí ?!", Toast.LENGTH_LONG).show();
            }
        }else
        {
            Toast.makeText(getApplicationContext(), "Bạn vẫn chưa đăng kí ?!", Toast.LENGTH_LONG).show();
        }

    }
}