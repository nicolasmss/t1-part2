Link para video apresentação - FAZER VIDEO

Para rodar o programa deve se executar a classe t1 e alterar os valores das duas filas, variação de tempo de chegada e atendimento, servidores, chegada inicial,e a seed(x0)

por exemplo: em G/G/2/3, chegadas entre 1..3, atendimento entre 2..4 e chegada inicial 2,5
	     e G/G/1/5, com atendimento 1..3
    
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
    // intervalo de tempo de atendimento
    static double f2itAtendimento0 = 1;
    static double f2itAtendimento1 = 3;
    // numero de servidores
    static int f2numServ = 1;
    // capacidade da fila
    static int f2capFila = 5;

    // semente
    static long x0 = 15893;