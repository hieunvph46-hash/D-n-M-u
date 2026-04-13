package com.example.duanmau.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.dao.NhanVienDAO;
import com.example.duanmau.model.NhanVien;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {
    TextInputEditText edtUsername, edtFullname, edtPassword;
    Spinner spRole;
    Button btnSignup, btnBack;
    NhanVienDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtUsername = findViewById(R.id.edtSignupUsername);
        edtFullname = findViewById(R.id.edtSignupFullname);
        edtPassword = findViewById(R.id.edtSignupPassword);
        spRole = findViewById(R.id.spSignupRole);
        btnSignup = findViewById(R.id.btnConfirmSignup);
        btnBack = findViewById(R.id.btnBackToLogin);

        dao = new NhanVienDAO(this);

        String[] roles = {"Admin", "Nhân viên"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roles);
        spRole.setAdapter(adapter);

        btnBack.setOnClickListener(v -> finish());

        btnSignup.setOnClickListener(v -> {
            String user = edtUsername.getText().toString().trim();
            String name = edtFullname.getText().toString().trim();
            String pass = edtPassword.getText().toString().trim();
            String role = spRole.getSelectedItem().toString();

            if (user.isEmpty() || name.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dao.getID(user) != null) {
                Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                return;
            }

            NhanVien nv = new NhanVien(user, name, pass, role);
            if (dao.insert(nv) > 0) {
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
