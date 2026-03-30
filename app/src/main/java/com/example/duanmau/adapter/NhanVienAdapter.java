package com.example.duanmau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.duanmau.R;
import com.example.duanmau.model.NhanVien;
import com.example.duanmau.ui.QuanLyNhanVienActivity;
import java.util.List;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    private Context context;
    private QuanLyNhanVienActivity activity;
    private List<NhanVien> list;

    public NhanVienAdapter(@NonNull Context context, QuanLyNhanVienActivity activity, List<NhanVien> list) {
        super(context, 0, list);
        this.context = context;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_nhan_vien, parent, false);
        }

        NhanVien item = list.get(position);
        if (item != null) {
            TextView tvMa = convertView.findViewById(R.id.tvMaNhanVien);
            TextView tvTen = convertView.findViewById(R.id.tvTenNhanVien);
            TextView tvVaiTro = convertView.findViewById(R.id.tvChucVu);
            ImageView imgSua = convertView.findViewById(R.id.imgSuaNhanVien);
            ImageView imgXoa = convertView.findViewById(R.id.imgXoaNhanVien);

            // Hidden unused fields from layout in this simplified model
            convertView.findViewById(R.id.tvDiaChi).setVisibility(View.GONE);
            convertView.findViewById(R.id.tvLuong).setVisibility(View.GONE);

            tvMa.setText("Mã: " + item.getMaNhanVien());
            tvTen.setText("Tên: " + item.getHoTen());
            tvVaiTro.setText("Vai trò: " + item.getVaiTro());

            imgXoa.setOnClickListener(v -> activity.xoa(item.getMaNhanVien()));
            imgSua.setOnClickListener(v -> activity.sua(item));
        }

        return convertView;
    }
}
