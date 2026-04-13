package com.example.duanmau.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.dao.NhanVienDAO;
import com.example.duanmau.model.NhanVien;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText edtUsername, edtPassword;
    Button btnLogin, btnSignup;
    CheckBox chkRemember;
    NhanVienDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        chkRemember = findViewById(R.id.chkRemember);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        dao = new NhanVienDAO(this);

        // Đọc user, pass từ SharedPreferences
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        edtUsername.setText(pref.getString("USERNAME", ""));
        edtPassword.setText(pref.getString("PASSWORD", ""));
        chkRemember.setChecked(pref.getBoolean("REMEMBER", false));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public void checkLogin() {
        String strUser = edtUsername.getText().toString();
        String strPass = edtPassword.getText().toString();

        if (strUser.isEmpty() || strPass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        } else {
            if (dao.checkLogin(strUser, strPass) > 0) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                
                // Lưu vai trò vào SharedPreferences
                NhanVien nv = dao.getID(strUser);
                SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("ROLE", nv.getVaiTro());
                edit.apply();

                rememberUser(strUser, strPass, chkRemember.isChecked());
                
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                i.putExtra("user", strUser);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            edit.remove("USERNAME");
            edit.remove("PASSWORD");
            edit.putBoolean("REMEMBER", status);
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        edit.apply();
    }
}
