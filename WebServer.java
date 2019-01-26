package com.aryzhkov.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class WebServer {
    private static final int PORT = 3000;
    private static final String HOME_PATH = "D:/JAVA/oopTest/resources/webapp";

    public static void main(String[] args) throws IOException {
        System.out.println("WebServer is working");
        try (ServerSocket serverSocket = new ServerSocket(PORT);
        ) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {

                    RequestHandler requestHandler = new RequestHandler(bufferedReader, bufferedWriter, HOME_PATH);
                    requestHandler.handle();

                } catch (SocketException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}