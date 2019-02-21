package sec3.WineTradingInGergovia11054;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int in;
        long steps;
        int[] inhabitants;

        while ((in = sc.nextInt()) != 0) {
            steps = 0;
            inhabitants = new int[in];

            for (int i = 0; i < inhabitants.length; i++) {
                inhabitants[i] = sc.nextInt();
            }

            for (int i = 0; i < inhabitants.length; i++) {
                if (inhabitants[i] != 0) {
                    if (inhabitants[i] < 0) {
                        for (int j = i + 1; j < inhabitants.length; j++) {
                            if (inhabitants[j] > 0) {
                                steps += Math.abs(inhabitants[i]) * (j - i);
                                inhabitants[j] = inhabitants[j] + inhabitants[i];
                                inhabitants[i] = 0;
                                break;
                            }
                        }
                    } else if (inhabitants[i] > 0) {
                        for (int j = i + 1; j < inhabitants.length; j++) {
                            if (inhabitants[j] < 0) {
                                steps += inhabitants[i] * (j - i);
                                inhabitants[j] = inhabitants[i] + inhabitants[j];
                                inhabitants[i] = 0;
                                break;
                            }
                        }
                    }
                }
            }
            System.out.println(steps);
        }
    }
}
