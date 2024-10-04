package com.example.pro2.Model.bills;

import com.example.pro2.Model.users.user;
import jakarta.persistence.*;

import java.util.Date;


public class billA {

    private String phuongthucthanhtoan;
    private String note;
    private String status;
    private String address;
    private String totalPayment;

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPhuongthucthanhtoan() {
        return phuongthucthanhtoan;
    }

    public void setPhuongthucthanhtoan(String phuongthucthanhtoan) {
        this.phuongthucthanhtoan = phuongthucthanhtoan;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

