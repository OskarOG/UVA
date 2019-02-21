package sec1.DecodeTheTape10878;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        StringBuilder res = new StringBuilder();

        while (!(input = br.readLine()).equals("___________")) {

            StringBuilder line = new StringBuilder();

            for (char inBit : input.toCharArray()) {
                if (inBit == 'o') line.append("1");
                else if (inBit == ' ') line.append("0");
            }

            char c = (char)Integer.parseInt(line.toString(), 2);
            res.append(c);
        }
        System.out.println(res.substring(0, res.length() - 1));
    }
}
