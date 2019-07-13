package org.academiadecodigo.codezillas.acstore.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerToClientConnection implements Runnable {

    private Socket socket;
    private Server server;
    private String clientName;

    public ServerToClientConnection(Socket socket, Server server, String clientName) {
        this.socket = socket;
        this.server = server;
        this.clientName = clientName;
    }

    @Override
    public void run() {

        try{
            BufferedReader input =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter output =
                     new PrintWriter(
                             socket.getOutputStream(), true);

            while (!socket.isClosed()) {
                waitingForClientRequest(input);
            }
        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }

    private void waitingForClientRequest(BufferedReader input) throws IOException {
        System.out.println("waiting your (client)request \n");
        String clientRequest = input.readLine();
        System.out.println("client request  is  :" +clientRequest);
    }
}
