//package com.example.pro2.Model.Product;
//
//
//import com.example.pro2.Model.Product.colors.color;
//import com.example.pro2.Model.brands.brand;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "productLaptop")
//public class productLaptop {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int Product_id;
//    private String CPU;
//    private String BoNho;
//    private String ScreenSize;
//    private String Graphics;
//    private String Battery;
//    private String Weight;
//
//    @ManyToOne()
//    @JoinColumn(name = "Color_id")
//    private color Color;
//    @OneToOne()
//    @JoinColumn(name = "id")
//    private Product product;
//
//    public int getId() {
//        return Product_id;
//    }
//
//    public void setId(int product_id) {
//        this.Product_id = product_id;
//    }
//
//    public String getCPU() {
//        return CPU;
//    }
//
//    public void setCPU(String CPU) {
//        this.CPU = CPU;
//    }
//
//    public String getBoNho() {
//        return BoNho;
//    }
//
//    public void setBoNho(String boNho) {
//        BoNho = boNho;
//    }
//
//    public String getScreenSize() {
//        return ScreenSize;
//    }
//
//    public void setScreenSize(String screenSize) {
//        ScreenSize = screenSize;
//    }
//
//    public String getGraphics() {
//        return Graphics;
//    }
//
//    public void setGraphics(String graphics) {
//        Graphics = graphics;
//    }
//
//    public String getBattery() {
//        return Battery;
//    }
//
//    public void setBattery(String battery) {
//        Battery = battery;
//    }
//
//    public String getWeight() {
//        return Weight;
//    }
//
//    public void setWeight(String weight) {
//        Weight = weight;
//    }
//
//    public color getColor() {
//        return Color;
//    }
//
//    public void setColor(color color) {
//        Color = color;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//}
