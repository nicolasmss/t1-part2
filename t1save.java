import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class t1save {
    // FILA1 g/g/2/3
    // intervalo de tempo de chegada dos clientes
    static double itChegada0 = 1;
    static double itChegada1 = 3;
    // intervalo de tempo de atendimento
    static double itAtendimento0 = 2;
    static double itAtendimento1 = 4;
    // numero de servidores
    static int numServ = 2;
    // capacidade da fila
    static int capFila = 3;
    // primeira entrada
    static double chegada = 3;

    // FILA2 g/g/1/5
    // intervalo de tempo de chegada dos clientes (nao usado)
    // static double f2itChegada0 = 1;
    // static double f2itChegada1 = 3;
    // intervalo de tempo de atendimento
    static double f2itAtendimento0 = 1;
    static double f2itAtendimento1 = 3;
    // numero de servidores
    static int f2numServ = 1;
    // capacidade da fila
    static int f2capFila = 5;
    // primeira entrada (nao usado)
    // static double f2chegada = 3;

    // semente
    static long x0 = 15893;
    // static long x0 = 31598;
    // static long x0 = 68252;
    // static long x0 = 6341;
    // static long x0 = 12345;

    // fila 1
    static List<Evento> saidas = new ArrayList<>();
    static List<Evento> eventos = new ArrayList<>();

    // fila 2
    static List<Evento> saidas2 = new ArrayList<>();
    static List<Evento> eventos2 = new ArrayList<>();

    public static void main(String[] args) {
        CongruenteLinear cl = new CongruenteLinear(x0);
        int numAleatorios = 100000;
        cl.gerarX(numAleatorios);
        if (capFila == 0) {
            capFila = numAleatorios;
        }

        int fila = 0;
        int serv = 0;
        int fila2 = 0;
        int serv2 = 0;

        double tempo = 0;
        double novotempo = chegada;
        tempo += novotempo;
        eventos.add(new Evento(chegada, chegada, 0, "chegada", capFila, chegada));
        fila++;

        // calcula saida do primeiro
        double tempoS = (itAtendimento1 - itAtendimento0) * cl.resultados.get(0) + itAtendimento0;
        saidas.add(new Evento(tempo + tempoS, tempoS, 0, "saida", capFila, tempoS));
        serv++;

        for (int i = 1; i < cl.resultados.size(); i++) {
            if (fila == 0 && serv == 1) {
                System.out.println(i);
            }
            if (i >= 99999) {
                System.out.println("oi");
                System.out.println(cl.resultados.size());
                System.out.println(eventos.size());
            }
            Collections.sort(saidas, (obj1, obj2) -> Double.compare(obj1.tempo, obj2.tempo));
            double tempoI = (itChegada1 - itChegada0) * cl.resultados.get(i) + itChegada0;

            if (true) {

            }

            while (serv >= 1) { // checar saida1 é menor tempo
                double teste = saidas.get(0).tempo;
                if (tempoI + tempo > teste && !(fila >= serv + 1 && serv < numServ)) { // && !(fila >= serv + 1 && serv
                                                                                       // < numServ)
                    for (int j = 0; j < saidas.get(0).temposX.length; j++) {
                        saidas.get(0).temposX[j] = eventos.get(eventos.size() - 1).temposX[j];
                        if (fila == j) {
                            saidas.get(0).temposX[j] += saidas.get(0).tempo - eventos.get(eventos.size() - 1).tempo;
                        }
                    }
                    eventos.add(saidas.get(0));
                    tempo = saidas.get(0).tempo;

                    eventos2.add(saidas.get(0)); // adiciona uma chegada2
                    if (fila2 < f2capFila) {
                        fila2++;
                    }

                    saidas.remove(0);
                    serv--;
                    fila--;
                } else {
                    break;
                }
            }

            if (serv < numServ && fila > 0 && fila >= serv + 1) { // adiciona saida1 em saidas
                tempoS = (itAtendimento1 - itAtendimento0) * cl.resultados.get(i) + itAtendimento0;
                saidas.add(new Evento(tempo + tempoS, tempoS, 0, "saida", capFila, tempoS));
                serv++;

            } else if (fila < capFila) { // adiciona uma chegada1
                tempoI = (itChegada1 - itChegada0) * cl.resultados.get(i) + itChegada0;

                tempo = tempoI + tempo;
                Evento evento = new Evento(tempo, tempoI, fila, "chegada", capFila, tempoI);

                for (int j = 0; j < evento.temposX.length; j++) {
                    evento.temposX[j] = eventos.get(eventos.size() - 1).temposX[j];
                    if (fila == j) {
                        evento.temposX[j] += tempoI;
                    }
                }

                eventos.add(evento);
                fila++;
            } else { // fila cheia chegada1 sai fora
                tempoI = (itChegada1 - itChegada0) * cl.resultados.get(i) + itChegada0;
                tempo += tempoI;
                Evento evento = new Evento(tempo, tempoI, fila, "chegada", capFila, tempoI);

                for (int j = 0; j < evento.temposX.length; j++) {
                    evento.temposX[j] = eventos.get(eventos.size() - 1).temposX[j];
                    if (fila == j) {
                        evento.temposX[j] += tempoI;
                    }
                }
                eventos.add(evento);
            }
        }

        System.out.println(eventos.get(eventos.size() - 1));

        double[] vetor = eventos.get(eventos.size() - 1).temposX;
        double tempoF = eventos.get(eventos.size() - 1).tempo;

        System.out.println("TOTAL=" + tempoF);
        System.out.println("tam " + vetor.length + "/// seed: " + x0);
        for (int i = 0; i < vetor.length; i++) {
            System.out.println(vetor[i]);
        }
        System.out.println("tam " + vetor.length + "/// seed: " + x0);
        for (int i = 0; i < vetor.length; i++) {
            System.out.println(vetor[i] * 100 / tempoF + "%");
        }
        System.out.println(eventos.size());
    }

}