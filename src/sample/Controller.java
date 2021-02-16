package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Cliente cliente;
    @FXML
    Button btnActivarServidor;
    @FXML
    Button btnDesactivarServidor;
    @FXML
    TextArea txtCliente;
    @FXML
    Button btnPregunta;
    @FXML
    Button btnEnviar;
    @FXML
    Button btnRecibirResultado;
    @FXML
    TextField txtRespuesta;
    @FXML
    Text txtAciertos;
    @FXML
    Text txtNumeroPreguntas;


    ServerSocket servidor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int puerto = 44444;
        Socket s = null;

        String nombre = "";
        nombre= JOptionPane.showInputDialog("Introduce tu nombre o nick:");
        nombre=(nombre=="")?"Yago":nombre;

        try {
            s = new Socket("localhost", puerto);
            cliente = new Cliente(s, nombre);
            new Thread(cliente).start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),
                    "<<MENSAJE DE ERROR:1>>", JOptionPane.ERROR_MESSAGE);
        }

        btnEnviar.setVisible(false);
        //btnRecibirResultado.setVisible(false);
        txtRespuesta.setVisible(false);
    }

    public void callBackPregunta(ActionEvent actionEvent) throws IOException {
        txtCliente.setText(cliente.getPregunta());
        btnPregunta.setVisible(false);
        btnEnviar.setVisible(true);
        txtRespuesta.setVisible(true);

    }
    public void callBackEnviar(ActionEvent actionEvent) throws IOException {
        String respuesta = txtRespuesta.getText();
        cliente.enviarRespuesta(respuesta);
        btnPregunta.setVisible(true);
        btnEnviar.setVisible(false);
        //btnRecibirResultado.setVisible(false);
        txtRespuesta.setVisible(false);
        txtCliente.setText(cliente.recibirResultado());

        //Puntuacion
        System.out.println("Entramos en puntuacion");
        String resultado = txtCliente.getText();
        int aciertos;
        if(resultado.equals("Has acertado")){
            System.out.println(resultado);
            aciertos = Integer.parseInt(txtAciertos.getText());
            aciertos++;
            txtAciertos.setText(String.valueOf(aciertos));
        }
        System.out.println("Entramos en partidas jugadas");
        txtNumeroPreguntas.setText(String.valueOf(Integer.parseInt(txtNumeroPreguntas.getText())+1));
    }

    public void callBackRecibirResultado() throws IOException {
        txtCliente.setText(cliente.recibirResultado());
    }



}
