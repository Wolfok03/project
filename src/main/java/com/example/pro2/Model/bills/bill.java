package com.example.pro2.Model.bills;

import com.example.pro2.Model.users.user;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "bill")
public class bill {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private Date createAt;
    private String phuongthucthanhtoan;
    private String note;
    private String status;
    private String address;
    private Double totalPayment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
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

    public com.example.pro2.Model.users.user getUser() {
        return user;
    }

    public void setUser(com.example.pro2.Model.users.user user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

