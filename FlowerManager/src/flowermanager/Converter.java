package flowermanager;


import flowermanager.entity.Order;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class Converter {
    public static String OrderToCsvString(Order order){
        return ""+"," + order.getName() + "," + order.getQuantity()+ " Flower(s)" +
                "," + "$ "+ order.getPrice()+","+ order.getDate();
    }
}
