package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogingFormController {
    public TextField txtUsername;
    public AnchorPane logingForm;

    static String username;

    public void LogingButton(ActionEvent actionEvent) throws IOException {
        username = txtUsername.getText();
        if (username.equals("")) {
            new Alert(Alert.AlertType.WARNING, "Please check your Username").showAndWait();

        } else {
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            stage.close();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/client.fxml"))));
            stage1.setTitle(username);
            stage1.centerOnScreen();
            stage1.show();
        }
    }
}