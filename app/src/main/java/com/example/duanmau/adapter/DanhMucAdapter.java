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
import com.example.duanmau.model.DanhMuc;
import com.example.duanmau.ui.QuanLyDanhMucActivity;
import java.util.List;

public class DanhMucAdapter extends ArrayAdapter<DanhMuc> {
    private Context context;
    private QuanLyDanhMucActivity activity;
    private List<DanhMuc> list;

    public DanhMucAdapter(@NonNull Context context, QuanLyDanhMucActivity activity, List<DanhMuc> list) {
        super(context, 0, list);
        this.context = context;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_danh_muc, parent, false);
        }

        DanhMuc item = list.get(position);
        if (item != null) {
            TextView tvMa = convertView.findViewById(R.id.tvTenSanPham); // ID from layout
            TextView tvTen = convertView.findViewById(R.id.tvGiaSanPham); // ID from layout
            ImageView imgSua = convertView.findViewById(R.id.imgSuaDanhMuc);
            ImageView imgXoa = convertView.findViewById(R.id.imgXoaDanhMuc);

            tvMa.setText("Mã: " + item.getMaDanhMuc());
            tvTen.setText("Tên: " + item.getTenDanhMuc());

            imgXoa.setOnClickListener(v -> activity.xoa(String.valueOf(item.getMaDanhMuc())));
            imgSua.setOnClickListener(v -> activity.sua(item));
        }

        return convertView;
    }
}
