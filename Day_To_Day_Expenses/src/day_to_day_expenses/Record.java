/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gaura
 */
package day_to_day_expenses;

import java.util.Date;

public class Record {
    String item_name;
    double price;
    Date date;

    public Record(String item_name, double price, Date date) {
        this.item_name = item_name;
        this.price = price;
        this.date = date;
    }
    
}
