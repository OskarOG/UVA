package sec2.NotSoMobile839;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        int testCases = Integer.parseInt(sc.readLine());
        sc.readLine();

        for (int i = 0; i < testCases; i++) {
            int res = calcMobile(sc.readLine(), sc);
            if (res < 0) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
            if (i != testCases - 1) {
                System.out.println();
            }
            sc.readLine();
        }
    }

    private static int calcMobile(String input, BufferedReader sc) {
        int lw, rw;
        try {
            String[] str = input.split(" ");
            int[] sub = new int[str.length];
            for (int i = 0; i < sub.length; i++) sub[i] = Integer.parseInt(str[i]);

            if (sub[0] == 0) {
                lw = calcMobile(sc.readLine(), sc);
            } else {
                lw = sub[0];
            }

            if (sub[2] == 0) {
                rw = calcMobile(sc.readLine(), sc);
            } else {
                rw = sub[2];
            }

            if (lw * sub[1] == rw * sub[3]) return lw + rw;
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }
}