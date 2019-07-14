package org.academiadecodigo.codezillas.acstore.Server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;

public class ServerToClientConnection implements Runnable {

    private Socket socket;
    private Server server;
    private String clientRequest;
    private String clientName;

    public ServerToClientConnection(Socket socket, Server server, String clientRequest) {
        this.socket = socket;
        this.server = server;
        this.clientRequest = clientRequest ;
    }

    @Override
    public void run() {


        try{
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Prompt prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream(), true));
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

            sendMsgToClient(output," !!! \n \n \n Welcome to AC amaaazing Store, Cadet_" + clientName + "!!!\n");


            sendMsgToClient(output,"Please make your request\n");


            while (!socket.isClosed()) {
                waitingForClientRequest(input);
               // sendMsgToClient(output, clientRequest);

            }
        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }

    private void waitingForClientRequest(BufferedReader input) throws IOException {
        //System.out.println("waiting your (client)request \n");
        clientRequest = input.readLine();
        //System.out.println((clientRequest));
        //System.out.println("client request  is  :" +clientRequest);
    }

    public void sendMsgToClient(PrintWriter output, String message){
        output.println(message + "\n");
    }

}
