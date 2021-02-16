package sample;

import java.io.*;
import java.net.Socket;

public class HiloServidor extends Thread{
    Socket socket;
    ComunHilos comun;
    DataInputStream entrada;
    DataOutputStream salida;
    /*ObjectOutputStream salidaObjetos;
    ObjectInputStream entradaObjetos;*/
    String nombreCliente;

    //Existe un HiloServidor para cada cliente, encargado de comunicarse con él
    public HiloServidor(Socket socket, ComunHilos comun){
        this.socket=socket;
        this.comun=comun;
        try {
            entrada=new DataInputStream(socket.getInputStream());
            salida=new DataOutputStream(socket.getOutputStream());
            /*entradaObjetos=new ObjectInputStream(socket.getInputStream());
            salidaObjetos=new ObjectOutputStream(socket.getOutputStream());*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){ //Se ejecuta uno por hiloservidor y se comunica con 1 cliente
        //Recibir el nombre del cliente
        System.out.println("Antes del nombre del cliente");
        try {
            nombreCliente = entrada.readUTF();
            System.out.println("Nombre cliente:" + nombreCliente);
        }catch(IOException io){
            io.printStackTrace();
        }

        for(int i = 0; i<3;i++){
            /*//Enviamos numero pregunta
            try {
                salida.writeUTF(String.valueOf(i));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }*/

            //Enviar pregunta al cliente
            Pregunta aux=comun.getPreguntaActual(i);
            try {
                //salidaObjetos.writeObject(aux);
                salida.writeUTF(aux.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Recibir respuesta del cliente
            String respuesta="";
            try {
                respuesta=entrada.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Comprobar si la respuesta es correcta
            try {
                if(comun.getPreguntaActual(i).correcta==Integer.valueOf(respuesta)) {
                    salida.writeUTF("Has acertado");
                }else{
                    salida.writeUTF("Has fallado");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}