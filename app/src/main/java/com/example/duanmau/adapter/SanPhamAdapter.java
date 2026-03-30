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
import com.example.duanmau.model.SanPham;
import com.example.duanmau.ui.QuanLySanPhamActivity;
import java.util.List;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    private Context context;
    private QuanLySanPhamActivity activity;
    private List<SanPham> list;

    public SanPhamAdapter(@NonNull Context context, QuanLySanPhamActivity activity, List<SanPham> list) {
        super(context, 0, list);
        this.context = context;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_san_pham, parent, false);
        }

        SanPham item = list.get(position);
        if (item != null) {
            TextView tvTen = convertView.findViewById(R.id.tvTenSanPham);
            TextView tvGia = convertView.findViewById(R.id.tvGiaSanPham);
            TextView tvSoLuong = convertView.findViewById(R.id.tvSoLuongTonKho);
            ImageView imgSua = convertView.findViewById(R.id.imgSuaDanhMuc);
            ImageView imgXoa = convertView.findViewById(R.id.imgXoaDanhMuc);
            ImageView imgGioHang = convertView.findViewById(R.id.imgGioHang);

            tvTen.setText(item.getTenSanPham());
            tvGia.setText("Giá: " + item.getGiaBan() + " VNĐ");
            tvSoLuong.setText("Tồn kho: " + item.getSoLuong());

            imgXoa.setOnClickListener(v -> activity.xoa(String.valueOf(item.getMaSanPham())));
            imgSua.setOnClickListener(v -> activity.sua(item));
            imgGioHang.setOnClickListener(v -> activity.themVaoGioHang(item));
        }

        return convertView;
    }
}
