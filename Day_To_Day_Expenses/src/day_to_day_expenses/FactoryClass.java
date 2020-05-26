/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day_to_day_expenses;

/**
 *
 * @author gaura
 */
public class FactoryClass {
    public FunctionalityProviderInterface getInstance(String type){
        if(type.equals("popup")){
            return new ViewPopup();
        }
        else{
            return null;
        }
    }
}
