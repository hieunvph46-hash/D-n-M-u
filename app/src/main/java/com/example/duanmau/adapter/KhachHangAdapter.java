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
import com.example.duanmau.model.KhachHang;
import com.example.duanmau.ui.QuanLyKhachHangActivity;
import java.util.List;

public class KhachHangAdapter extends ArrayAdapter<KhachHang> {
    private Context context;
    private QuanLyKhachHangActivity activity;
    private List<KhachHang> list;

    public KhachHangAdapter(@NonNull Context context, QuanLyKhachHangActivity activity, List<KhachHang> list) {
        super(context, 0, list);
        this.context = context;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_khach_hang, parent, false);
        }

        KhachHang item = list.get(position);
        if (item != null) {
            TextView tvMa = convertView.findViewById(R.id.tvMaKhachHang);
            TextView tvTen = convertView.findViewById(R.id.tvTenKhachHang);
            TextView tvSdt = convertView.findViewById(R.id.tvSoDienThoai);
            TextView tvDiaChi = convertView.findViewById(R.id.tvDiaChi);
            ImageView imgSua = convertView.findViewById(R.id.imgSuaKhachHang);
            ImageView imgXoa = convertView.findViewById(R.id.imgXoaKhachHang);
            
            // Hide email as it's not in our simple model
            convertView.findViewById(R.id.tvEmail).setVisibility(View.GONE);

            tvMa.setText("Mã: " + item.getMaKhachHang());
            tvTen.setText("Tên: " + item.getHoTen());
            tvSdt.setText("SĐT: " + item.getSoDienThoai());
            tvDiaChi.setText("Địa chỉ: " + item.getDiaChi());

            imgXoa.setOnClickListener(v -> activity.xoa(String.valueOf(item.getMaKhachHang())));
            imgSua.setOnClickListener(v -> activity.sua(item));
        }

        return convertView;
    }
}
