package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    static final int MAXIMO = 2;//MAXIMO DE CONEXIONES PERMITIDAS

    public static void main(String args[]) throws IOException {
        int PUERTO = 44444;

        ServerSocket servidor = new ServerSocket(PUERTO);
        System.out.println("Servidor iniciado...");

        Socket tabla[] = new Socket[MAXIMO];//para controlar las conexiones
        ComunHilos comun = new ComunHilos(MAXIMO, 0, 0, tabla);

        while (comun.getConexiones() < MAXIMO) { //Esto se ejecuta cada vez que entra un cliente
            Socket socket = new Socket(); //Creo socket para recibir al cliente
            socket = servidor.accept();// esperando cliente
            System.out.println("El servidor ha aceptado al cliente");

            //comun.addTabla(socket, comun.getConexiones());
            //comun.setActuales(comun.getActuales() + 1);
            comun.setConexiones(comun.getConexiones() + 1); //No es importante

            System.out.println("Voy a crear HiloServidor");
            HiloServidor hilo = new HiloServidor(socket, comun); //Importante: Creo un hiloservidor para atender
            //al cliente que acaba de llegar y le paso el socket para comunicarse con ese cliente
            //También le paso común con los datos que comparten todos los hilosservidor
            hilo.start();
        }
        servidor.close();
    }
}
