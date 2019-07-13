package org.academiadecodigo.codezillas.acstore.Server;

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

    public Server( int port) throws IOException {
        serverSocket = new ServerSocket(port);
        service = Executors.newCachedThreadPool();
    }

    public static void main(String[] args) {
        try {
            Server server = new Server(8080);
            server.Start();
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }

    }

    public void Start() {
        int connections = 0;

        while (true) {

            waitConnection(connections);
            connections++;

        }

    }

    public void waitConnection(int connections){
        try {
            Socket clientSocket = serverSocket.accept();

            ServerToClientConnection connection =
                    new ServerToClientConnection(clientSocket, this, "Cadet_" +connections);
            service.submit(connection);
            System.out.println("Welcome to AC Amazing store!!!");

        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }

    }



}// fim da Classe
