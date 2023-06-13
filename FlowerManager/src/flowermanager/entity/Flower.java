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
public class Flower {
     private Integer flowerId;
     private String name;
    private String status;
    private Double price;
    private Date date;
    private String image;

    public Flower(Integer flowerId, String name, String status, Double price , String image, Date date){
        this.flowerId = flowerId;
        this.name = name;
        this.status = status;
        this.price = price;
        this.date = date;
        this.image = image;
    }
    public Flower() {
    }
    public Integer getFlowerId(){
        return flowerId;
    }
    public String getName(){
        return name;
    }
    public String getStatus(){
        return status;
    }
    public Double getPrice(){
        return price;
    }
    public Date getDate(){
        return date;
    }
    public String getImage(){
        return image;
    }

    public void setFlowerId(Integer flowerId) {
        this.flowerId = flowerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
