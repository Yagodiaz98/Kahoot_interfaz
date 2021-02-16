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
    Button btnOtraVez;
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
    @FXML
    Text txtNota;
    @FXML
    Text txtNotaFinal;

    ServerSocket servidor;

    int nPregunta;
    int nPreguntaTotal;
    int aciertos;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnOtraVez.setVisible(false);
        txtNota.setVisible(false);
        txtNotaFinal.setVisible(false);
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

        //btnEnviar.setVisible(false);
        //btnRecibirResultado.setVisible(false);
        //txtRespuesta.setVisible(false);

        //Recibimos el numero total de preguntas!!!!!!
        try {
            nPreguntaTotal = Integer.parseInt(cliente.getnPregunta());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        //Muestra la pregunta desde el principio!!!!!!
        try {
            txtCliente.setText(cliente.getPregunta());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void callBackComenzar(ActionEvent actionEvent){

    }

    public void callBackOtraVez(ActionEvent actionEvent) throws IOException {
        //initialize(location,resources);//COMOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
        //HiloServidor.run();
        btnOtraVez.setVisible(false);
        btnEnviar.setVisible(true);
        txtRespuesta.setVisible(true);
        txtNotaFinal.setVisible(false);
        txtNota.setVisible(false);
    }
    public void callBackEnviar(ActionEvent actionEvent) throws IOException {
        nPregunta = Integer.parseInt(cliente.getnPregunta());
        String respuesta = txtRespuesta.getText();
        cliente.enviarRespuesta(respuesta);
        btnOtraVez.setVisible(true);
        //btnEnviar.setVisible(false);
        //btnRecibirResultado.setVisible(false);
        //txtRespuesta.setVisible(false);
        txtCliente.setText(cliente.recibirResultado());


        //Puntuacion
        System.out.println("Entramos en puntuacion");
        String resultado = txtCliente.getText();
        if(resultado.equals("Has acertado")){
            System.out.println(resultado);
            aciertos = Integer.parseInt(txtAciertos.getText());
            aciertos++;
            txtAciertos.setText(String.valueOf(aciertos));
        }
        System.out.println("Entramos en partidas jugadas");
        txtNumeroPreguntas.setText(String.valueOf(Integer.parseInt(txtNumeroPreguntas.getText())+1));
        if(nPregunta == nPreguntaTotal){
            btnEnviar.setVisible(false);
            txtRespuesta.setVisible(false);
            //Sacamos la media
            System.out.println(aciertos);
            float nFinal =(float)aciertos/3*10;//BUGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
            System.out.println(nFinal);
            txtNotaFinal.setText(String.valueOf(nFinal));
            txtNota.setVisible(true);
            txtNotaFinal.setVisible(true);
            btnOtraVez.setVisible(false);
        }else{
            //Para que haga las preguntas automaticamente
            txtCliente.setText(cliente.getPregunta());
            //Volvemos a dejar en blanco el textField
            txtRespuesta.setText("");
        }
    }

    public void callBackRecibirResultado() throws IOException {
        txtCliente.setText(cliente.recibirResultado());
    }



}
