/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flowermanager.entity;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Order {
    
    private Integer customerId;
    private Integer flowerId;
    private String name;
    private Integer quantity;
    private Double price;
    private Date date;
    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public Order(Integer customerId, Integer flowerId, String name, Integer quantity, Double price, Date date, Integer orderId) {
        this.customerId = customerId;
        this.flowerId = flowerId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getFlowerId() {
        return flowerId;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
}
