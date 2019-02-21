package sec1.BoxOfBricks591;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        LinkedList<Integer> numbOfMoves = new LinkedList<>();

        int numbOStacks, average, k, moves;

        while ((numbOStacks = Integer.parseInt(br.readLine())) != 0) {
            average = 0;
            int[] stacks = new int[numbOStacks];
            String[] sArr = br.readLine().split(" ");
            for (int i = 0; i < numbOStacks; i++) {
                stacks[i] = Integer.parseInt(sArr[i]);
                average += stacks[i];
            }
            average = average/numbOStacks;

            k = 0;
            moves = 0;
            while (k < numbOStacks) {
                if (stacks[k] == average) k++;
                else if (stacks[k] < average) {
                    for (int i = k+1; i < numbOStacks; i++) {
                        if (stacks[i] > average) {
                            stacks[i]--;
                            stacks[k]++;
                            moves++;
                            break;
                        }
                    }
                } else {
                    for (int i = k+1; i < numbOStacks; i++) {
                        if (stacks[i] < average) {
                            stacks[i]++;
                            stacks[k]--;
                            moves++;
                            break;
                        }
                    }
                }
            }
            numbOfMoves.add(moves);
        }

        for (int i = 0; i < numbOfMoves.size(); i++) {
            System.out.println("Set #" + (i + 1));
            System.out.println("The minimum number of moves is " + numbOfMoves.get(i) + ".");
            System.out.println();
        }
    }
}

