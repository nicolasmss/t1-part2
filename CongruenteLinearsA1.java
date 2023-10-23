import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CongruenteLinearsA1 {
    private long x0;
    private long a;
    private long c;
    private long m;

    public CongruenteLinearsA1(long x0, long a, long c, long m) {
        this.x0 = x0;
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public double nextRandom() {
        x0 = (a * x0 + c) % m;
        return (double) x0 / m;
    }

    public static void main(String[] args) {
        long x0 = 35216;
        long a = 1664525;
        long c = 1013904223;
        long m = (long) Math.pow(2, 32);
        int count = 1000;

        CongruenteLinearsA1 congruenteLinear = new CongruenteLinearsA1(x0, a, c, m);

        try {
            FileWriter fileWriter = new FileWriter("aleatorios.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < count; i++) {
                double random = congruenteLinear.nextRandom();
                printWriter.println(random);
            }

            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
