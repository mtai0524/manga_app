package com.example.bietdoidoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bietdoidoctruyen.dao.RegisterDAO;
import com.example.bietdoidoctruyen.database.DbHelper;
import com.example.bietdoidoctruyen.model.Register;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername, etPassword, etConfirm;
    Button btnSubmit;
    RegisterDAO registerDao;
    DbHelper helper;
    String username;
    String password;
    String comfirmPassword;
    Register register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        helper = new DbHelper(RegisterActivity.this);
        etUsername = findViewById(R.id.et_register_username);
        etPassword = findViewById(R.id.et_register_password);
        etConfirm = findViewById(R.id.et_register_confirm_password);
        btnSubmit = findViewById(R.id.btn_submit);
        registerDao = new RegisterDAO(RegisterActivity.this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                comfirmPassword = etConfirm.getText().toString();
                register = new Register(username, password);
                if (username.equals("") || password.equals("") || comfirmPassword.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(comfirmPassword)) {
                        boolean isCheckuser = registerDao.checkUserName(username);
                        if (!isCheckuser) { // trả về false nếu không tìm thấy username => tạo mới user
                            boolean isInsert = registerDao.insert(register);
                            if (isInsert) {
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành viên thành công", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                // do something....
                            } else {
                                Toast.makeText(RegisterActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Username đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Mật khẩu nhập lại không giống", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}