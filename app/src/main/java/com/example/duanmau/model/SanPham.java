package com.example.duanmau.model;

public class SanPham {
    private int maSanPham;
    private String tenSanPham;
    private int giaBan;
    private int soLuong;
    private String ngayNhap;
    private int maDanhMuc;

    public SanPham() {}

    public SanPham(int maSanPham, String tenSanPham, int giaBan, int soLuong, String ngayNhap, int maDanhMuc) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.ngayNhap = ngayNhap;
        this.maDanhMuc = maDanhMuc;
    }

    public int getMaSanPham() { return maSanPham; }
    public void setMaSanPham(int maSanPham) { this.maSanPham = maSanPham; }
    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }
    public int getGiaBan() { return giaBan; }
    public void setGiaBan(int giaBan) { this.giaBan = giaBan; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public String getNgayNhap() { return ngayNhap; }
    public void setNgayNhap(String ngayNhap) { this.ngayNhap = ngayNhap; }
    public int getMaDanhMuc() { return maDanhMuc; }
    public void setMaDanhMuc(int maDanhMuc) { this.maDanhMuc = maDanhMuc; }
}
