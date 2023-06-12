/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;

/**
 *
 * @author admin
 */
public class ListOrderDetail {
    private List<OrderDetail> orderDetails;

    public ListOrderDetail() {
    }

    public ListOrderDetail(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
}
