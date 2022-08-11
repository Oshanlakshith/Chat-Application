package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {
    public TextArea serverB;
    public TextField txtServer;
    Socket accept = null;
    BufferedReader bufferedReader;

    public void initialize() {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(5000);
                System.out.println("Server Started..!");
                accept = serverSocket.accept();
                System.out.println("Client Connected..!");

                InputStreamReader inputStreamReader = new InputStreamReader(accept.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                String record = bufferedReader.readLine();


                while (!record.equals("Exit")) {
                    record = bufferedReader.readLine();
                    serverB.appendText("\nClient :" + record.trim() + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void btnServerSend(ActionEvent actionEvent) throws IOException {
        PrintWriter printWriter = new PrintWriter(accept.getOutputStream());
        printWriter.println(txtServer.getText());
        serverB.appendText("\n\n\t\t\t\t\t\t\t\t\t\t\t\tServer :" + txtServer.getText());
        printWriter.flush();
    }
}
