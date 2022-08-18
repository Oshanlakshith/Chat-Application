package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client2Controller extends ClientController {
    public TextArea client2TextBox;
    public TextField txt2Text;
    Socket socket = null;
    BufferedReader bufferedReader;

    public void initialize() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 5000);
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                String record = bufferedReader.readLine();

                while (!record.equals("Exit")) {
                    record = bufferedReader.readLine();
                    client2TextBox.appendText("\nServer :" + record.trim() + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void sendOnAction2(ActionEvent actionEvent) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(txt2Text.getText());
        client2TextBox.appendText("\n\n\t\t\t\t\t\t\t\t\t\t\t\tServer :" + txt2Text.getText());
        printWriter.flush();
    }
}
