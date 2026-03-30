package com.example.duanmau.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.duanmau.R;
import com.example.duanmau.dao.DanhMucDAO;
import com.example.duanmau.dao.SanPhamDAO;
import com.example.duanmau.model.DanhMuc;
import com.example.duanmau.model.SanPham;
import java.util.ArrayList;

public class EditSanPhamActivity extends AppCompatActivity {
    Spinner spDanhMuc;
    EditText edtTen, edtGia, edtSoLuong, edtMa;
    Button btnLuu, btnHuy;
    SanPhamDAO dao;
    DanhMucDAO dmDao;
    ArrayList<DanhMuc> listDM;
    int type, maSP, maDM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_san_pham);

        spDanhMuc = findViewById(R.id.spDanhMuc);
        edtMa = findViewById(R.id.edtMaDanhMuc);
        edtTen = findViewById(R.id.edtTenSanPham);
        edtGia = findViewById(R.id.edtGiaSanPham);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        btnLuu = findViewById(R.id.btnLuu);
        btnHuy = findViewById(R.id.btnHuy);

        dao = new SanPhamDAO(this);
        dmDao = new DanhMucDAO(this);
        
        loadSpinner();

        type = getIntent().getIntExtra("type", 0);
        if (type == 1) {
            maSP = getIntent().getIntExtra("maSanPham", 0);
            maDM = getIntent().getIntExtra("maDanhMuc", 0);
            edtMa.setText(String.valueOf(maSP));
            edtMa.setEnabled(false);
            edtTen.setText(getIntent().getStringExtra("tenSanPham"));
            edtGia.setText(String.valueOf(getIntent().getIntExtra("giaBan", 0)));
            edtSoLuong.setText(String.valueOf(getIntent().getIntExtra("soLuong", 0)));
            
            for (int i = 0; i < listDM.size(); i++) {
                if (listDM.get(i).getMaDanhMuc() == maDM) {
                    spDanhMuc.setSelection(i);
                    break;
                }
            }
        } else {
            findViewById(R.id.layoutMaSP).setVisibility(android.view.View.GONE);
        }

        btnHuy.setOnClickListener(v -> finish());
        btnLuu.setOnClickListener(v -> {
            if (validate()) {
                SanPham sp = new SanPham();
                sp.setTenSanPham(edtTen.getText().toString());
                sp.setGiaBan(Integer.parseInt(edtGia.getText().toString()));
                sp.setSoLuong(Integer.parseInt(edtSoLuong.getText().toString()));
                sp.setMaDanhMuc(((DanhMuc) spDanhMuc.getSelectedItem()).getMaDanhMuc());

                if (type == 0) {
                    if (dao.insert(sp) > 0) {
                        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    }
                } else {
                    sp.setMaSanPham(maSP);
                    if (dao.update(sp) > 0) {
                        Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            }
        });
    }

    void loadSpinner() {
        listDM = (ArrayList<DanhMuc>) dmDao.getAll();
        ArrayAdapter<DanhMuc> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listDM);
        spDanhMuc.setAdapter(adapter);
    }

    boolean validate() {
        if (edtTen.getText().toString().isEmpty() || edtGia.getText().toString().isEmpty() || edtSoLuong.getText().toString().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (spDanhMuc.getSelectedItem() == null) {
            Toast.makeText(this, "Vui lòng thêm danh mục trước", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
