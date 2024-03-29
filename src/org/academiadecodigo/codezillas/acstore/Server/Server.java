package org.academiadecodigo.codezillas.acstore.Server;

import org.academiadecodigo.codezillas.acstore.Store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private ExecutorService service;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private static int clientName = 0;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        service = Executors.newCachedThreadPool();
    }

    public static void main(String[] args) {
        try {
            Server server = new Server(8080);
            Store store = new Store();

            server.Start();
            store.stockLevel();


        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

    public void Start() {


        while (true) {

            clientName++;
            waitConnection(clientName);

        }

    }

    public void waitConnection(int connections) {



        try {
            Socket clientSocket = serverSocket.accept();

            ServerToClientConnection connection =
                    new ServerToClientConnection(clientSocket, this, "" + clientName);
            service.submit(connection);
            System.out.println("Welcome Client_ " + clientName);

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }


}// end of the Class
