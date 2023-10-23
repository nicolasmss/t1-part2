Link para video apresentação - https://www.youtube.com/watch?v=3Cwuj1XAXog

Para rodar o programa deve se executar a classe t1 e alterar os valores das fila, variação de tempo de chegada e atendimento, servidores, chegada inicial,e a seed(x0)

por exemplo: em G/G/1/5, chegadas entre 1..3, atendimento entre 2..4 e chegada inicial 3
    
    // intervalo de tempo de chegada dos clientes
    static double itChegada0 = 1;
    static double itChegada1 = 3;

    // intervalo de tempo de atendimento
    static double itAtendimento0 = 2;
    static double itAtendimento1 = 4;

    // numero de servidores
    static int numServ = 1;

    // capacidade da fila
    static int capFila = 5;

    // primeira entrada
    static double chegada = 3;

    // semente
    static double x0 = 1.5893;