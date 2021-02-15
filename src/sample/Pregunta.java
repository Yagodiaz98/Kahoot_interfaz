package sample;

import java.io.Serializable;

public class Pregunta implements Serializable { //Imprescindible implementar el interface Serializable
    String enunciado;
    String r1, r2, r3;
    int correcta;

    public Pregunta() {
    }

    public Pregunta(String enunciado, String r1, String r2, String r3, int correcta) {
        this.enunciado = enunciado;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.correcta = correcta;
    }

    public String toString(){
        return enunciado + "\n1) " + r1 + "\n2) " + r2 + "\n3)" + r3;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        this.correcta = correcta;
    }
}