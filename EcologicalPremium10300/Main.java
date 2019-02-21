package sec1.EcologicalPremium10300;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] vals;
        double cases, farmers, area, animals, environment, totalMoney;

        cases = Integer.parseInt(br.readLine());

        for (int i = 0; i < cases; i++) {
            totalMoney = 0;
            farmers = Integer.parseInt(br.readLine());

            for (int j = 0; j < farmers; j++) {
                vals = br.readLine().split(" ");
                area = Double.parseDouble(vals[0]);
                animals = Double.parseDouble(vals[1]);
                environment = Double.parseDouble(vals[2]);

                double singleAnimalArea = area / animals;
                totalMoney += singleAnimalArea * environment * animals;
            }
            System.out.println((int)totalMoney);
        }
    }
}
