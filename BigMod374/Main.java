package sec3.BigMod374;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while ((input = sc.readLine()) != null) {
            System.out.println(modexp(Integer.parseInt(input), Integer.parseInt(sc.readLine()), Integer.parseInt(sc.readLine())));
            sc.readLine();
        }
    }

    static long modexp(int x, int y, int n) {
        if (y == 0) return 1;
        long z = modexp(x, y / 2, n);
        if (y % 2 == 0) {
            return (z * z) % n;
        } else {
            return (x * (z * z)) % n;
        }
    }
}
