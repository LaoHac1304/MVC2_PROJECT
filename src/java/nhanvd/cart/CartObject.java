/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanvd.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import nhanvd.registration.ProductDAO;
import nhanvd.registration.ProductDTO;

/**
 *
 * @author simnh
 */
public class CartObject  implements Serializable{
    private Map<String, Integer> items;
    
    public Map<String, Integer> getItems(){
        return items;
    }
    
    public void addBookCart (String book){
        if (this.items == null){
            this.items = new HashMap<>();
        }// items are not existed
        //items has existed
        int quantity = 1;
        if (this.items.containsKey(book)){
            quantity = this.items.get(book) +1;
        }
        //update quantity of book
        this.items.put(book,quantity);
    }
    
    public void removeBookFromCart(String book){
        if (this.items == null){
            return;
        }
        //items have existed
        if (this.items.containsKey(book)){
            this.items.remove(book);
            if (this.items.isEmpty()){
                this.items = null;
            }
        }//item existed in items
    }
    
    public int getQuantityByBookName(String nameBook){
        return this.items.get(nameBook);
    }
}
