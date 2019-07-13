package org.academiadecodigo.codezillas.acstore.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client implements Runnable {
    ExecutorService pool;
    Socket socket;
    public static final String EXIT = "/quit";

    public Client(String host, int port) {
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
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            while (!socket.isClosed()) {
                String input = scanner.nextLine();

                if (input.equals(EXIT)) {
                    System.exit(0);
                }

                writer.println(input);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!socket.isClosed()) {
                waitMessage(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
