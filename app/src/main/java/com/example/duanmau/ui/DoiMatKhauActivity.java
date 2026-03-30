package com.example.duanmau.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.dao.NhanVienDAO;
import com.example.duanmau.model.NhanVien;
import com.google.android.material.textfield.TextInputEditText;

public class DoiMatKhauActivity extends AppCompatActivity {
    TextInputEditText edtOldPass, edtNewPass, edtConfirmPass;
    Button btnSave, btnCancel;
    NhanVienDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        edtOldPass = findViewById(R.id.edtOldPassword);
        edtNewPass = findViewById(R.id.edtNewPassword);
        edtConfirmPass = findViewById(R.id.edtConfirmPassword);
        btnSave = findViewById(R.id.btnLuu);
        btnCancel = findViewById(R.id.btnHuy);

        dao = new NhanVienDAO(this);

        btnCancel.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String user = pref.getString("USERNAME", "");
            
            if (validate() > 0) {
                NhanVien nv = dao.getID(user);
                nv.setMatKhau(edtNewPass.getText().toString());
                if (dao.update(nv) > 0) {
                    Toast.makeText(this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public int validate() {
        int check = 1;
        if (edtOldPass.getText().toString().isEmpty() || edtNewPass.getText().toString().isEmpty() || edtConfirmPass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD", "");
            String pass = edtNewPass.getText().toString();
            String rePass = edtConfirmPass.getText().toString();
            if (!passOld.equals(edtOldPass.getText().toString())) {
                Toast.makeText(this, "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)) {
                Toast.makeText(this, "Mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}
