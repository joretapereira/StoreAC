package org.academiadecodigo.codezillas.acstore.Store;

import org.academiadecodigo.codezillas.acstore.Drinks.Beer;
import org.academiadecodigo.codezillas.acstore.Drinks.Coffee;
import org.academiadecodigo.codezillas.acstore.Drinks.Drinkable;
import org.academiadecodigo.codezillas.acstore.Drinks.Water;

import java.security.PrivateKey;

public class Store implements Drinkable {
    private static final int MAX_ORDER = 10;
    private static final int MIN_STOCK = 24;
    private int beerStock = 1;
    private int waterStock = 120;
    private int coffeeStock = 120;

    public void consumedBeer(int askedNumber) {

        if (askedNumber > beerStock) {

            System.out.println("Sorry, we only have " + beerStock + " beers. \n");
            beerStock = beerStock + 120;
            return;
        }

        if (askedNumber > MAX_ORDER) {
            System.out.println("You can only order maximum " + MAX_ORDER + " beers at a time.");
            return;
        }

        beerStock = beerStock - askedNumber;
        System.out.println("Enjoy your " + askedNumber + " beers.");
        return;

    }

    public void consumedCoffee(int askedNumber) {

        if (askedNumber > coffeeStock) {

            System.out.println("Sorry, we only have " + coffeeStock + " coffees. \n");
            coffeeStock = coffeeStock + 120;
            return;

        }
        if (askedNumber > MAX_ORDER) {
            System.out.println("You can only order maximum " + MAX_ORDER + " coffees at a time.");
            return;
        }

        coffeeStock = coffeeStock - askedNumber;
        System.out.println("Enjoy your " + askedNumber + " coffee.");
            return;

    }

    public void consumedWater(int askedNumber) {

        if (askedNumber > waterStock) {

            System.out.println("Sorry, our stock of water is only " + waterStock + ". \n");
            waterStock = waterStock + 120;
            return;
        }
        if (askedNumber > MAX_ORDER) {
            System.out.println(" you can only order maximum " + MAX_ORDER + " waters at a time.");
            return;
        }

        waterStock = waterStock + askedNumber;

        System.out.println("Enjoy your " + askedNumber + " beer.");
        return;

    }

    public void bearStockLevel() {
        System.out.println("Bear stock: " + beerStock + ".");
    }

    public void waterStockLevel() {
        System.out.println("Water stock: " + waterStock + ".");
    }

    public void coffeeStockLevel() {
        System.out.println("Coffee stock: " + coffeeStock + ".");
    }

}
