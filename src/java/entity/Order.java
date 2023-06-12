/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class Order {
    private int order_id;
    private int user_id;
    private String order_date;
    private double total_price;
    private String address;
    private String phone_number;
    private String status;

    public Order() {
    }

    public Order(int order_id, int user_id, String order_date, double total_price, String address, String phone_number, String status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.order_date = order_date;
        this.total_price = total_price;
        this.address = address;
        this.phone_number = phone_number;
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", user_id=" + user_id + ", order_date=" + order_date + ", total_price=" + total_price + ", address=" + address + ", phone_number=" + phone_number + ", status=" + status + '}';
    }
    
}
