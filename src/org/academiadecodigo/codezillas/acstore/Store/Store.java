package org.academiadecodigo.codezillas.acstore.Store;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.codezillas.acstore.Drinks.Drinkable;

import java.net.Socket;

public class Store implements Drinkable {
    private static final int MAX_ORDER = 10;
    private static final int MIN_STOCK = 24;
    private int beerStock = 240;
    private int waterStock = 240;
    private int coffeeStock = 240;
    private Prompt prompt;
    private Socket socket;


    public String consumedBeer(int answer) {

        if (answer > beerStock) {
            return "Sorry, we only have " + beerStock + " beers. \n";
        }

        if (answer > MAX_ORDER) {
            return "You can only order maximum " + MAX_ORDER + " beers at a time.";
        } else {
            beerStock = beerStock - answer;
            return "Enjoy your " + answer + " beers!";
            //The actual Stock is  " + beerStock;


        }

    }

    public String consumedCoffee(int askedNumber) {

        if (askedNumber > coffeeStock) {
            return "Sorry, we only have " + coffeeStock + " coffees. \n";

        }
        if (askedNumber > MAX_ORDER) {
            return "You can only order maximum " + MAX_ORDER + " coffees at a time.";
        }

        coffeeStock = coffeeStock - askedNumber;
        return "Enjoy your " + askedNumber + " coffees!";
                //The Actual stock of Coffee is  " + coffeeStock;
    }

    public String consumedWater(int askedNumber) {

        if (askedNumber > waterStock) {

            return "Sorry, our stock of water is only " + waterStock + ". \n";
        }
        if (askedNumber > MAX_ORDER) {

            return "You can only order maximum " + MAX_ORDER + " waters at a time.";
        }

        waterStock = waterStock - askedNumber;

        return "Enjoy your " + askedNumber + " waters.";
        //The Actual stock of Water is  " + waterStock;

    }

    public void bearStockLevel() {
        System.out.println("Beer stock: " + beerStock + ".");
    }

    public void waterStockLevel() {
        System.out.println("Water stock: " + waterStock + ".");
    }

    public void coffeeStockLevel() {
        System.out.println("Coffee stock: " + coffeeStock + ".");
    }

    public void stockLevel() {

        System.out.println("Beer stock: " + beerStock + ".");
        System.out.println("Coffee stock: " + coffeeStock + ".");
        System.out.println("Water stock: " + waterStock + ".");

    }
}
