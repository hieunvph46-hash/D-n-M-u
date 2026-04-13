package com.example.duanmau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.duanmau.R;
import com.example.duanmau.model.KhachHang;
import java.util.List;

public class TopKhachHangAdapter extends ArrayAdapter<KhachHang> {
    private Context context;
    private List<KhachHang> list;

    public TopKhachHangAdapter(@NonNull Context context, List<KhachHang> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_top_khach_hang, parent, false);
        }

        KhachHang item = list.get(position);
        if (item != null) {
            TextView tvTen = convertView.findViewById(R.id.tvCustomerName);
            TextView tvChiTieu = convertView.findViewById(R.id.tvTotalSpent);

            tvTen.setText(item.getHoTen());
            // We stored the total spend in the diaChi field for convenience in ThongKeDAO
            tvChiTieu.setText("Chi tiêu: " + item.getDiaChi() + " Đ");
        }

        return convertView;
    }
}
