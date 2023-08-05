package com.example.bietdoidoctruyen;

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
        tvLogo = findViewById(R.id.tv_logo);
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
        showMyDialog();
        verifyLoginInformation();
    }

    private void showMyDialog() {
        tvLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnOnPopupLogo(Gravity.CENTER);
            }
        });
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

    private void turnOnPopupLogo(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_popup);
        TextView tvContent = dialog.findViewById(R.id.tvContent);

        String storyText = "<h1>Tiêu đề truyện</h1>"
                + "<p>&emsp;Trong một buổi sáng nắng đẹp, những tia nắng ấm áp len lỏi qua những cánh cửa sổ nhỏ vào trong căn phòng. Tiếng chim hót líu lo vang lên từ ngoài hiên, tạo nên bầu không khí thật tươi mát và thư giãn"
                + "&emsp;Nàng công chúa đứng bên cửa sổ, nhìn ra ngoài cảnh vật tươi đẹp của vương quốc. Trong đôi mắt nàng tỏa sáng ánh sáng, hạnh phúc đong đầy trong từng nụ cười. Cuộc sống của nàng đầy hứa hẹn và niềm vui vẫn đang chờ đón nàng ở phía trước..</p>"
                + "<p>&emsp;Quyền lực và trí tuệ của nàng sẽ được thử thách, nhưng nàng không còn sợ hãi. Trái tim nàng đập mạnh hơn bao giờ hết, bởi nàng biết rằng cô công chúa nhỏ bé kia đã trưởng thành thành một người phụ nữ mạnh mẽ, sẵn sàng đối đầu với bất cứ thử thách nào..</p>";

// Chuyển đoạn văn bản có thẻ HTML thành chuỗi hiển thị được trong TextView
        Spanned spannedText = Html.fromHtml(storyText);

// Gán chuỗi đã chuyển đổi vào TextView




        tvContent.setText(spannedText);
        Window window = dialog.getWindow();
        if (window == null) return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttr = window.getAttributes();
        windowAttr.gravity = gravity;
        window.setAttributes(windowAttr);
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        }
        dialog.show();
    }


}