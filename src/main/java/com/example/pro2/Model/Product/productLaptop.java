package com.example.pro2.Model.Product;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table (name = "productLaptop")
public class productLaptop {
  private int ProductId;
  private String CPU;
  private String BoNho;
  private String ScreenSize;
  private String Graphics;
  private String Battery;
  private String Weight;
  private int Color_id;

}
