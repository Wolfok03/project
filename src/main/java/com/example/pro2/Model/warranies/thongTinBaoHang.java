package com.example.pro2.Model.warranies;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "thongTinBaoHang")
public class thongTinBaoHang {
    @Id
    private int maThongTinBaoHang;
    private int ProductId ;
    private int CustomerName;
    private int Seririal;
    private Date PurchaseDate;
    private Date WarrantyExpirationDate;
    private Date FaultDescription;
    private Date ReceivedDate;
    private Date ReturnDate;
    private String WarrantyStatus;



}
