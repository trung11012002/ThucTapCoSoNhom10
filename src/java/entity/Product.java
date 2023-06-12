/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class Product {
    /*
       ID varchar(10)  primary key,
       [name] [nvarchar](max) NULL,
	[quantity] [int] NULL,
	[price] [money] NULL,
	[releaseDate] [date] NULL,
	[describe] [nvarchar](max) NULL,
	[image] [nvarchar](max) NULL,
       [cid] [int] references Categories(ID),
    */
    private int product_id;
    private String name;
    private int quantity;
    private double price;
    private String describe,image;
    public Product() {
    }

    public Product(int product_id, String name, int quantity, double price, String describe, String image) {
        this.product_id = product_id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.describe = describe;
        this.image = image;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
    