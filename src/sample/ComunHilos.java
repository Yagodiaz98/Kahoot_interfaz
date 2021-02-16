package sample;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ComunHilos {
    int conexiones; //Conexiones ocupadas en el array
    int actuales;   //Conexiones actuales
    int maximo;     //Máximo de conexiones permitidas
    Socket tabla[] = new Socket[maximo];// Sockets de clientes conectados
    ArrayList<Pregunta> listaPreguntas=new ArrayList<Pregunta>();
    Pregunta preguntaActual;

    //CREAMOS EL CONSTRUCTOR DE COMUN HILOS
    public ComunHilos(int maximo, int actuales, int conexiones, Socket[] tabla) {
        this.maximo = maximo;
        this.actuales = actuales;
        this.conexiones = conexiones;
        this.tabla = tabla;

        listaPreguntas.add(new Pregunta("¿2+2?", "5", "3", "4", 3));
        listaPreguntas.add(new Pregunta("¿5+2?", "7", "3", "4", 1));
        listaPreguntas.add(new Pregunta("¿4+2?", "5", "6", "2", 2));

        //Fijo las letras para jugar
        setPreguntaActual();
    }

    //OBTENEMOS UNA PREGUNTA ALEATORIA Y LA PREPARAMOS PARA ENVIARLA A HILOSERVIDOR
    public void setPreguntaActual(){
        /*int n= new Random().nextInt(listaPreguntas.size());
        preguntaActual=listaPreguntas.get(n);*/
        preguntaActual=listaPreguntas.get(0);
    }

    public Pregunta getPreguntaActual(int n){
        preguntaActual=listaPreguntas.get(n);
        return preguntaActual;
    }

    public int getConexiones() { return conexiones;	}
    public synchronized void setConexiones(int conexiones) {
        this.conexiones = conexiones;
    }

}