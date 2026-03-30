package com.example.duanmau.model;

public class HoaDonChiTiet {
    private int maHDCT;
    private int maHoaDon;
    private int maSanPham;
    private int soLuong;
    private int donGia;
    
    // Auxiliary fields for display
    private String tenSanPham;

    public HoaDonChiTiet() {}

    public HoaDonChiTiet(int maHDCT, int maHoaDon, int maSanPham, int soLuong, int donGia) {
        this.maHDCT = maHDCT;
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getMaHDCT() { return maHDCT; }
    public void setMaHDCT(int maHDCT) { this.maHDCT = maHDCT; }
    public int getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(int maHoaDon) { this.maHoaDon = maHoaDon; }
    public int getMaSanPham() { return maSanPham; }
    public void setMaSanPham(int maSanPham) { this.maSanPham = maSanPham; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public int getDonGia() { return donGia; }
    public void setDonGia(int donGia) { this.donGia = donGia; }
    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }
}
