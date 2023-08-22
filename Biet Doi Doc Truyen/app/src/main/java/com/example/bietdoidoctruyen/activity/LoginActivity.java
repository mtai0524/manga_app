package com.example.bietdoidoctruyen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bietdoidoctruyen.R;
import com.example.bietdoidoctruyen.dao.RegisterDAO;

public class LoginActivity extends AppCompatActivity {
    private ImageView tvLogo;
    private Button btnLogin;
    private Button btnRegister;
    private RegisterDAO registerDao;
    private EditText etUserName, etPassword;
    public static String username, password;

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        LoginActivity.userId = userId;
    }

    private static int userId;
    private void init() {
//        tvLogo = findViewById(R.id.tv_logo);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        registerDao = new RegisterDAO(LoginActivity.this);
        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        etUserName.setText("minh tai");
        etPassword.setText("admin123");
        showActivityRegister();
        verifyLoginInformation();
    }

    private void verifyLoginInformation() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = etUserName.getText().toString();
                password = etPassword.getText().toString();



                if (username.equals("") || password.equals(""))
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else {
                    boolean checkUserPass = registerDao.checkUserNamePassword(username, password);
                    if (checkUserPass == true) {
                        RegisterDAO registerDAO = new RegisterDAO(LoginActivity.this);
                        userId = registerDAO.getUserIdByUserName(username);


                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void showActivityRegister() {
        btnRegister.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}