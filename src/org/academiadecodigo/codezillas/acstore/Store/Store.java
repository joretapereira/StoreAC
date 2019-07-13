package org.academiadecodigo.codezillas.acstore.Store;

import org.academiadecodigo.codezillas.acstore.Drinks.Drinkable;

import java.util.LinkedList;
import java.util.List;

public class Store implements Drinkable {
    private LinkedList<String> productList;

    public void fulfillProductList(){
        Store AC_Store = new Store();
        AC_Store.productList.add("beer");


    }

    
}
