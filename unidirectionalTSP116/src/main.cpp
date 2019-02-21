#include <iostream>

using namespace std;

int main () {
    int row, col;
    while ( scanf ("%d %d", &row, &col) != EOF ) {
        int matrix[row][col];
        int dist[row][col];
        int input = 0;
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                scanf("%d", &input);
                matrix[i][j] = input;
                dist[i][j] = input;
            }
        }
        
        int compW = INT_MAX;
        for (int j = col - 2; j >= 0; j--) {
            for (int i = 0; i < row; i++) {
                dist[i][j] = matrix[i][j] + dist[(row+i-1)%row][j+1];

                compW = matrix[i][j] + dist[i][j+1];
                if (compW < dist[i][j]) dist[i][j] = compW;
                
                compW = matrix[i][j] + dist[(row+i+1)%row][j+1];
                if (compW < dist[i][j]) dist[i][j] = compW;
            }
        }
        
        int lr = 0;
        for (int i = 0; i < row; i++) if (dist[i][0] < dist[lr][0]) lr = i;

        int lowestWeight = dist[lr][0];
        int clw, clr, r;
        cout << (lr + 1) << " ";

        for (int i = 1; i < col; i++) {
            r = (row + lr - 1) % row;
            clw = dist[r][i];
            clr = r;

            if (dist[lr][i] < clw || (dist[lr][i] == clw && lr < clr)) {
                clw = dist[lr][i];
                clr = lr;
            }
            r = (row + lr + 1) % row;
            if (dist[r][i] < clw || (dist[r][i] == clw && r < lr)) clr = r;

            if (i == col-1) cout << clr + 1 << endl;
            else cout << clr + 1 << " ";
            lr = clr;
        }
        cout << lowestWeight << endl;
    }
    return 0;
}