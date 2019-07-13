package org.academiadecodigo.codezillas.acstore.Client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.codezillas.acstore.Server.Server;

public class Client {

    private Prompt prompt;
    private Server server;

    public Client() {
        this.server = server;
    }

    public void menu() {

        prompt = new Prompt(System.in, System.out);

        String[] menuArr = {"Beer", "Coffee", "Water"};
        MenuInputScanner menuOptions = new MenuInputScanner(menuArr);

        int numOfOption = prompt.getUserInput(menuOptions);

        int askedBeerNumber = prompt.getUserInput(server.consumedBeer);

        int askedCoffeeNumber = prompt.getUserInput(server.consumedCoffee);

        int askedWaterNumber = prompt.getUserInput(server.consumedWater);


        switch (numOfOption) {

            case 1:
                System.out.println("Here's your beer! Enjoy.");
                server.consumedBeer(prompt.getUserInput(askedBeerNumber));
                break;

            case 2:
                System.out.println("Here's your coffee, enjoy!");
                server.consuemdCoffee(prompt.getUserInput(askedCoffeeNumber));
                break;

            case 3:
                System.out.println("Here's your healthy water, enjoy!");
                server.consumedWater(prompt.getUserInput(askedWaterNumber));
                break;
        }

    }

}
