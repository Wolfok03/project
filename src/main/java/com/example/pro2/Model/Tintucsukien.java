package com.example.pro2.Model;

import java.util.Date;

public class Tintucsukien {

    private  String Noidung;
    private  String Hinhanh;
    private Date Ngayhienthi;


    public String getNoidung() {
        return Noidung;
    }

    public String getHinhanh() {
        return Hinhanh;
    }

    public Date getNgayhienthi() {return Ngayhienthi;}

    public void setNoidung(String Noidung) {
        this.Noidung = Noidung;
    }

    public void setHinhanh(String Hinhanh) {
        this.Hinhanh = Hinhanh;
    }

    public void setNgayhienthi(Date Ngayhienthi) {this.Ngayhienthi = Ngayhienthi;}
}
