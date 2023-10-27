import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class t1 {
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
    static double chegada = 2.5;

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
    //static long x0 = 15893;
    //static long x0 = 31598;
    //static long x0 = 68252;
    //static long x0 = 6341;
    static long x0 = 12345;

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

        int semAtendimento = 0;

        int fila = 0;
        int serv = 0;
        int fila2 = 0;
        int serv2 = 0;

        double tempo = 0;
        double novotempo = chegada;
        tempo += novotempo;
        eventos.add(new Evento(chegada, chegada, fila, "chegada", capFila, chegada));
        fila++;

        // calcula saida do primeiro
        double tempoS = (itAtendimento1 - itAtendimento0) * cl.resultados.get(0) + itAtendimento0;
        saidas.add(new Evento(tempo + tempoS, tempoS, fila, "saida", capFila, tempoS));
        serv++;

        for (int i = 1; i < cl.resultados.size(); i++) {
            // System.out.println(i);
            if (fila == 0 && serv == 1) {
                System.out.println(i);
            }
            if (i >= 99999) {
                System.out.println("oi");
                System.out.println(cl.resultados.size());
                System.out.println(eventos.size());
            }

            Collections.sort(saidas, (obj1, obj2) -> Double.compare(obj1.tempo, obj2.tempo));
            Collections.sort(saidas2, (obj1, obj2) -> Double.compare(obj1.tempo, obj2.tempo));

            double tempoI = (itChegada1 - itChegada0) * cl.resultados.get(i) + itChegada0;
            double tempoS2;
            if (saidas2.size() > 0) {
                tempoS2 = saidas2.get(0).tempo;
            } else {
                tempoS2 = -1;
            }
            boolean lol = true;
            boolean lol2 = true;

            while ((serv >= 1 || serv2 >= 1) && (lol || lol2)) {
                while (serv >= 1) { // checar saida1 é menor tempo
                    tempoS = saidas.get(0).tempo;
                    if (tempoI + tempo > tempoS &&
                            (-1 == tempoS2 || tempoS2 > tempoS) &&
                            !(fila >= serv + 1 && serv < numServ) &&
                            !(fila2 >= serv2 + 1 && serv2 < f2numServ)) {
                        // tempo Saida é menor que tempo chegada &&
                        // tempo saida 1 é menor que tempo saida 2 &&
                        // nao tem ngm na fila esperando com caixa disponivel &&
                        // nao tem ngm na fila2 esperando com caixa2 disponivel

                        for (int j = 0; j < saidas.get(0).temposX.length; j++) {
                            double x = eventos.get(eventos.size() - 1).temposX[j];
                            if (fila == j) {
                                saidas.get(0).temposX[j] = saidas.get(0).tempo
                                        + eventos.get(eventos.size() - 1).temposX[j]
                                        - eventos.get(eventos.size() - 1).tempo;
                                // saidas.get(0).temposX[j] = eventos.get(eventos.size() - 1).temposX[j];
                                // chegada esta entrando com tempo maior que a saida esta tendo algum erro de
                                // que a ultima chegada (evento)
                                // tem tempo maior que a saida
                            } else {
                                saidas.get(0).temposX[j] = eventos.get(eventos.size() - 1).temposX[j];
                            }
                        }
                        eventos.add(saidas.get(0));
                        tempo = saidas.get(0).tempo;

                        // eventos2.add(saidas.get(0)); // adiciona uma chegada2
                        if (eventos2.size() == 0) { // primeiro evento2
                            Evento evento = new Evento(tempo, tempo, fila2, "chegada", f2capFila, tempo);
                            eventos2.add(evento);

                        } else { // demais eventos2
                            double auxt = eventos2.get(eventos2.size() - 1).tempo;
                            Evento evento = new Evento(tempo, tempo - auxt, fila2, "chegada", f2capFila, tempo - auxt);
                            for (int j = 0; j < evento.temposX.length; j++) {
                                evento.temposX[j] = eventos2.get(eventos2.size() - 1).temposX[j];
                                if (fila2 == j) {
                                    evento.temposX[j] += tempo - auxt;
                                }
                            }
                            eventos2.add(evento);
                        }
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

                if (saidas.size() > 0) {
                    tempoS = saidas.get(0).tempo;
                } else {
                    tempoS = -1;
                }

                while (serv2 >= 1) { // checar saida2 é menor tempo
                    if (tempoI + tempo > tempoS2 &&
                            (-1 == tempoS || tempoS2 < tempoS) &&
                            !(fila >= serv + 1 && serv < numServ) &&
                            !(fila2 >= serv2 + 1 && serv2 < f2numServ)) {
                        // tempo Saida é menor que tempo chegada &&
                        // tempo saida 1 é menor que tempo saida 2 &&
                        // nao tem ngm na fila esperando com caixa disponivel &&
                        // nao tem ngm na fila2 esperando com caixa2 disponivel
                        for (int j = 0; j < saidas2.get(0).temposX.length; j++) {
                            saidas2.get(0).temposX[j] = eventos2.get(eventos2.size() - 1).temposX[j];
                            if (fila2 == j) {
                                saidas2.get(0).temposX[j] += saidas2.get(0).tempo
                                        - eventos2.get(eventos2.size() - 1).tempo;
                            }
                        }
                        eventos2.add(saidas2.get(0));
                        tempo = saidas2.get(0).tempo;

                        saidas2.remove(0);
                        serv2--;
                        fila2--;
                    } else {
                        break;
                    }
                }
                if (serv == 0) {
                    lol = false;
                    tempoS = -1;
                } else if ((tempoI + tempo < tempoS) || (fila >= serv + 1 && serv < numServ)
                        || (fila2 >= serv2 + 1 && serv2 < f2numServ)) {
                    lol = false;
                }
                if (serv2 == 0) {
                    lol2 = false;
                    tempoS2 = -1;
                } else if (tempoI + tempo < tempoS2 || (fila >= serv + 1 && serv < numServ)
                        || (fila2 >= serv2 + 1 && serv2 < f2numServ)) {
                    lol2 = false;
                }
                // !(fila >= serv + 1 && serv < numServ) &&
                // !(fila2 >= serv2 + 1 && serv2 < f2numServ)

            }

            tempoI = (itChegada1 - itChegada0) * cl.resultados.get(i) + itChegada0;

            if (serv2 < f2numServ && fila2 > 0 && fila2 >= serv2 + 1) { // adiciona saida2 em saidas
                tempoS = (f2itAtendimento1 - f2itAtendimento0) * cl.resultados.get(i) + f2itAtendimento0;
                saidas2.add(new Evento(tempo + tempoS, tempoS, 0, "saida", f2capFila, tempoS));
                serv2++;

            } else if (serv < numServ && fila > 0 && fila >= serv + 1) { // adiciona saida1 em saidas
                tempoS = (itAtendimento1 - itAtendimento0) * cl.resultados.get(i) + itAtendimento0;
                saidas.add(new Evento(tempo + tempoS, tempoS, 0, "saida", capFila, tempoS));
                serv++;

            } else if (fila < capFila && (tempoI + tempo < tempoS || tempoS == -1)
                    && (tempoI + tempo < tempoS2 || tempoS2 == -1)) { // adiciona uma chegada1
                tempoI = (itChegada1 - itChegada0) * cl.resultados.get(i) + itChegada0;

                double tempoantigo = tempo;
                tempo = tempoI + tempo;
                Evento evento = new Evento(tempo, tempoI, fila, "chegada", capFila, tempoI);

                for (int j = 0; j < evento.temposX.length; j++) {
                    evento.temposX[j] = eventos.get(eventos.size() - 1).temposX[j];
                    if (fila == j) {
                        evento.temposX[j] = evento.tempo + eventos.get(eventos.size() - 1).temposX[j]
                                - eventos.get(eventos.size() - 1).tempo;
                    }
                }

                eventos.add(evento);
                fila++;
            } else if ((tempoI < tempoS || tempoS == -1) && (tempoI < tempoS2 || tempoS2 == -1)) { // fila cheia
                                                                                                   // chegada1 sai fora
                tempoI = (itChegada1 - itChegada0) * cl.resultados.get(i) + itChegada0;

                double tempoantigo = tempo;
                tempo += tempoI;
                Evento evento = new Evento(tempo, tempoI, fila, "chegada", capFila, tempoI);

                for (int j = 0; j < evento.temposX.length; j++) {
                    evento.temposX[j] = eventos.get(eventos.size() - 1).temposX[j];
                    if (fila == j) {
                        evento.temposX[j] = evento.tempo + eventos.get(eventos.size() - 1).temposX[j]
                                - eventos.get(eventos.size() - 1).tempo;
                    }
                }
                eventos.add(evento);
                semAtendimento++;

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

        System.out.println("-------------/--------------");

        System.out.println(cl.resultados.size());
        System.out.println("eventos size: " + eventos.size());
        System.out.println("eventos2 size: " + eventos2.size());

        int c = 0;
        for (int i = 0; i < eventos.size(); i++) {
            if (eventos.get(i).tipo.equals("saida")) {
                c++;
            }
        }
        System.out.println("saidas: " + c);

        System.out.println("-------------/--------------");

        double[] vetor2 = eventos2.get(eventos2.size() - 1).temposX;
        double tempoF2 = eventos2.get(eventos2.size() - 1).tempo;

        System.out.println("TOTAL=" + tempoF2);
        System.out.println("tam " + vetor2.length + "/// seed: " + x0);
        for (int i = 0; i < vetor2.length; i++) {
            System.out.println(vetor2[i]);
        }
        System.out.println("tam " + vetor2.length + "/// seed: " + x0);
        for (int i = 0; i < vetor2.length; i++) {
            System.out.println(vetor2[i] * 100 / tempoF2 + "%");
        }
    }

}