package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ClientController {
    public TextField txtText;
    public TextArea clientTextBox;
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
                    clientTextBox.appendText("\nServer :" + record.trim() + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void sendOnAction(ActionEvent actionEvent) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(txtText.getText());
        System.out.println("Client Writer :" + printWriter);
        clientTextBox.appendText("\n\n\t\t\t\t\t\t\t\t\t\t\t\tClient :" + txtText.getText());
        printWriter.flush();
    }

    public void cameraClient(MouseEvent mouseEvent) {
        JFileChooser jFileChooser = new JFileChooser();
        int response = jFileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
        }
    }
}
