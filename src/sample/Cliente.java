package sample;

import javafx.fxml.FXML;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


public class Cliente implements Runnable{
    //private static final long serialVersionUID = 1L;
    Socket socket = null;
    DataInputStream entrada;
    DataOutputStream salida;
    ObjectInputStream entradaObjetos;
    String nombre;
    String pregunta;


    public Cliente(Socket socket, String nombre){

        this.socket=socket;
        this.nombre=nombre;

        try {
            //Abro
            salida=new DataOutputStream(socket.getOutputStream());
            //entradaObjetos=new ObjectInputStream(socket.getInputStream());
            entrada=new DataInputStream(socket.getInputStream());

            System.out.println("Antes de enviar nombre " + nombre + " al cliente");
            //Envío el nombre al servidor
            salida.writeUTF(nombre);

            //Recibo la pregunta del servidor
            //Pregunta pregunta= (Pregunta) entradaObjetos.readObject();
            //String pregunta=entrada.readUTF();

            //Mostrar pregunta en pantalla y esperar la respuesta por teclado
            //String respuesta=JOptionPane.showInputDialog(pregunta.toString());
            //String respuesta= JOptionPane.showInputDialog(pregunta);

            //Envío la respuesta al servidor
            //salida.writeUTF(respuesta);

            //Recibo del servidor si he acertado
            //String resultado=entrada.readUTF();
            //JOptionPane.showMessageDialog(null,  resultado);

        } catch (IOException e) {
            e.printStackTrace();
        } /*catch(ClassNotFoundException ce){
            ce.printStackTrace();
        }*/
    }

    /*public static void main(String args[]) {  //Orden de ejecución: Main, constructor, run
        int puerto = 44444;
        Socket s = null;

        String nombre = "";
        nombre=JOptionPane.showInputDialog("Introduce tu nombre o nick:");
        nombre=(nombre=="")?"Yago":nombre;

        try {
            s = new Socket("localhost", puerto);
            Cliente cliente = new Cliente(s, nombre);
            new Thread(cliente).start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),
                    "<<MENSAJE DE ERROR:1>>", JOptionPane.ERROR_MESSAGE);
        }

    }*/

    public void run(){
        /*//De momento no hace nada en el futuro, haremos que se juegue más de una palabra
        //String texto = "";
        boolean repetir=true;
        while (repetir) {
            try {
                pregunta = entrada.readUTF();
                System.out.println(pregunta);
            } catch (IOException e) {
                System.out.println("No se puede conectar con el servidor");
                repetir = false;
            }
        } // while

        try {
            socket.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public String getPregunta() throws IOException {
        pregunta=entrada.readUTF();
        return pregunta;
    }

    public String getnPregunta() throws IOException {
        String nPregunta = entrada.readUTF();
        return nPregunta;
    }

    public void enviarRespuesta(String respuesta) throws IOException {
        salida.writeUTF(respuesta);
    }

    public String recibirResultado() throws IOException {
        String resultado=entrada.readUTF();

        return resultado;
    }
}

