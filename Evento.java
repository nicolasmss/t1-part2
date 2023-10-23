import java.util.Arrays;

public class Evento {
    String tipo; // p12 e saida ou chegada
    double tempo;
    double [] temposX;
    int tamX;
    double demora;

    public Evento(double tempo, double tempoX, int posX, String tipo, int tamX, double demora) {
        this.tempo = tempo;
        this.tipo = tipo;
        temposX = new double[tamX+1];
        for (int i = 0; i < temposX.length; i++) {
            temposX[i] = 0;
        }
        temposX[posX] += tempoX;
        this.demora = demora;
        
    }

    @Override
    public String toString() {
        return "\nEvento [tipo=" + tipo + ", demora=" + demora + ", tempo=" + tempo + ", temposX=" + Arrays.toString(temposX) + "]";
    }
}
