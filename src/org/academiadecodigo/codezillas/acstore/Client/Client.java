package org.academiadecodigo.codezillas.acstore.Client;


import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.acstore.Server.Server;
import org.academiadecodigo.codezillas.acstore.Store.Store;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.codezillas.acstore.Store.Store;

public class Client implements Runnable {
    private ExecutorService pool;
    private Socket socket;
    private Prompt prompt;
    private Store store;


    public static final String EXIT = "/quit";

    public Client(String host, int port) {
        store = new Store();

        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {

        

        pool = Executors.newCachedThreadPool();

        pool.submit(this);

    }

    public void waitMessage(BufferedReader reader) throws IOException {
        String message = reader.readLine();

        if (message == null) {
            System.out.println("Connection closed from server!");

            pool.shutdownNow();
            System.exit(0);
        }

        System.out.println(message);
    }

    @Override
    public void run() {
        /*try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Prompt prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream(), true));
            StringInputScanner stringInputScanner = new StringInputScanner();
            stringInputScanner.setMessage("Dame o nome");
            prompt.getUserInput(stringInputScanner);
            clientName = prompt.getUserInput(stringInputScanner);
            sendMsgToClient(writer," !!! \n \n \n Welcome to AC amaaazing Store, Cadet_" + clientName + "!!!\n");


            sendMsgToClient(writer,"Please make your request\n");

            while (!socket.isClosed()) {
              //  String input = scanner.nextLine();

                if (input.equals(EXIT)) {
                    System.exit(0);
                }

                writer.println(input);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!socket.isClosed()) {
                waitMessage(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void menu() {

        try {
            prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] menuArr = {"Beer", "Coffee", "Water"};
        MenuInputScanner menuOptions = new MenuInputScanner(menuArr);

        // Integer beerNumber = prompt.getUserInput(anyInteger);

        int numOfOption = prompt.getUserInput(menuOptions);

        switch (numOfOption) {

            case 1:
                System.out.println("Here's your beer! Enjoy.");
                store.consumedBeer(howMany());
                break;

            case 2:
                System.out.println("Here's your coffee, enjoy!");
                store.consumedCoffee(howMany());
                break;

            case 3:
                System.out.println("Here's your healthy water, enjoy!");
                store.consumedWater(howMany());
                break;
        }

    }

    public int howMany() {

        IntegerInputScanner howManyDrinks = new IntegerInputScanner();
        howManyDrinks.setMessage("How many would you like to take?");

        int answer = prompt.getUserInput(howManyDrinks);

        return answer;
    }

}
