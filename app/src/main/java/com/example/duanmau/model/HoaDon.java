package com.example.duanmau.model;

public class HoaDon {
    private int maHoaDon;
    private String maNhanVien;
    private int maKhachHang;
    private String ngayMua;
    private int tongTien;

    public HoaDon() {}

    public HoaDon(int maHoaDon, String maNhanVien, int maKhachHang, String ngayMua, int tongTien) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
        this.ngayMua = ngayMua;
        this.tongTien = tongTien;
    }

    public int getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(int maHoaDon) { this.maHoaDon = maHoaDon; }
    public String getMaNhanVien() { return maNhanVien; }
    public void setMaNhanVien(String maNhanVien) { this.maNhanVien = maNhanVien; }
    public int getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(int maKhachHang) { this.maKhachHang = maKhachHang; }
    public String getNgayMua() { return ngayMua; }
    public void setNgayMua(String ngayMua) { this.ngayMua = ngayMua; }
    public int getTongTien() { return tongTien; }
    public void setTongTien(int tongTien) { this.tongTien = tongTien; }
}
