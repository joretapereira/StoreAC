package org.academiadecodigo.codezillas.acstore.Client;

public class ClientLauncher {
    private static int clientNames = 0;

    public static void main(String[] args) {

        if(args.length < 2){
            System.out.println("Usage: <host> <port>");
            return;
        }

        try {
            clientNames++;
            Client client = new Client(args[0], Integer.valueOf(args[1]), "" + clientNames);
            client.start();



        } catch (NumberFormatException e) {
            System.err.println("Error port must be a valid number: " + args[1]);
        }
    }

    public void start() {
        while (true) {
            clientNames++;
        }
    }


}
