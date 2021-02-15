package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Button btnActivarServidor;
    @FXML
    Button btnDesactivarServidor;
    @FXML
    TextArea txtServidor;


    ServerSocket servidor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}
