package org.academiadecodigo.codezillas.acstore.Client;

public class ClientLauncher {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Usage: <host> <port>");
            return;
        }

        try {
            Client client = new Client(args[0], Integer.valueOf(args[1]));


        } catch (NumberFormatException e) {
            System.err.println("Error port must be a valid number: " + args[1]);
        }
    }


}
