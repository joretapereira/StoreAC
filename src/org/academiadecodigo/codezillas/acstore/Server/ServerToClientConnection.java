package org.academiadecodigo.codezillas.acstore.Server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.codezillas.acstore.Store.Store;

import java.io.*;
import java.net.Socket;

public class ServerToClientConnection implements Runnable {

    private Socket socket;
    private Server server;
    private String clientRequest;
    private String clientName;

    //////
    private Store store;
    private int numOfOption;
    private Prompt prompt;
    private int answer;

    //////

    public ServerToClientConnection(Socket socket, Server server, String clientRequest) {
        this.socket = socket;
        this.server = server;
        this.clientRequest = clientRequest;
    }

    @Override
    public void run() {


        try {

            prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream(), true));
            StringInputScanner stringInputScanner = new StringInputScanner();
            stringInputScanner.setMessage("Enter your username: ");

            clientName = prompt.getUserInput(stringInputScanner);
            BufferedReader input =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter output =
                    new PrintWriter(
                            socket.getOutputStream(), true);

            sendMsgToClient(output, " !!! \n \n \n Welcome to AC amaaazing Store, Cadet_" + clientName + "!!!\n");


            sendMsgToClient(output, "Please make your request\n");

            menu();


            while (!socket.isClosed()) {
                waitingForClientRequest(input);
                // sendMsgToClient(output, clientRequest);

            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    private void waitingForClientRequest(BufferedReader input) throws IOException {
        clientRequest = input.readLine();
    }

    public void sendMsgToClient(PrintWriter output, String message) {
        output.println(message + "\n");
    }

    public void menu() {

        store = new Store();

        while (true) {

            String[] menuArr = {"Beer", "Coffee", "Water", "Exit"};

            MenuInputScanner menuOptions = new MenuInputScanner(menuArr);

            menuOptions.setMessage("What would you like to have?");

            numOfOption = prompt.getUserInput(menuOptions);


            switch (numOfOption) {

                case 1:
                    //System.out.println("customer wants " + menuArr[0]);
                    String beer = store.consumedBeer(howMany());
                    printResponse(beer);
                    break;

                case 2:
                    //System.out.println("Here's your coffee, enjoy!");
                    String coffee = store.consumedCoffee(howMany());
                    printResponse(coffee);
                    break;

                case 3:
                    //System.out.println("Here's your healthy water, enjoy!");
                    String water = store.consumedWater(howMany());
                    printResponse(water);
                    break;

                case 4:
                   System.exit(0);
            }
        }

    }

    public int howMany() {

        IntegerInputScanner howManyDrinks = new IntegerInputScanner();
        howManyDrinks.setMessage("How many would you like to take? \n");

        answer = prompt.getUserInput(howManyDrinks);
        System.out.println(answer);
        return answer;
    }


    public void printResponse(String string) {
        PrintStream print = null;
        try {
            print = new PrintStream(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        print.println(string);

    }
}
