package com.example.duanmau;

import com.example.duanmau.model.HoaDonChiTiet;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    public static List<HoaDonChiTiet> cartList = new ArrayList<>();

    public static void add(HoaDonChiTiet item) {
        for (HoaDonChiTiet it : cartList) {
            if (it.getMaSanPham() == item.getMaSanPham()) {
                it.setSoLuong(it.getSoLuong() + 1);
                return;
            }
        }
        cartList.add(item);
    }

    public static int getTotal() {
        int total = 0;
        for (HoaDonChiTiet it : cartList) {
            total += it.getSoLuong() * it.getDonGia();
        }
        return total;
    }
}
