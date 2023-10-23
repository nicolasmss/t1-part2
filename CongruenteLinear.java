import java.util.ArrayList;
import java.util.List;

public class CongruenteLinear {
    private long x0;
    private long a;
    private long c;
    private long m;

    List<Double> resultados = new ArrayList<>();

    public CongruenteLinear(long x0, long a, long c, long m) {
        this.x0 = x0;
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public CongruenteLinear(long x0){
        this.x0 = x0;
        a = 1664525;
        c = 1013904223;
        m = (long) Math.pow(2, 32);
    }

    public double generate() {
        x0 = (a * x0 + c) % m;
        return (double) x0 / m;
    }

    public void gerarX(int x) {
        for (int i = 0; i < x; i++) {
            resultados.add(generate());
        }
    }

    public void print() {
        for (Object object : resultados) {
            System.out.println(object);
        }
    }

    //teste
    public static void main(String[] args) {
        long x0 = 35216;
        long a = 1664525;
        long c = 1013904223;
        long m = (long) Math.pow(2, 32);
        CongruenteLinear congruenteLinear = new CongruenteLinear(x0, a, c, m);

        for (int i = 0; i < 100; i++) {
            System.out.println(congruenteLinear.generate());
        }
        
    }
}
