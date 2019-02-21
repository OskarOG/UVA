#include <stdio.h>
#include <string.h>

using namespace std;

int matrix[101][101];

int calc(int n, int k) {
    if (k <= 1) return 1;
    if (matrix[n][k] != -1) return matrix[n][k];

    matrix[n][k] = 0;
    for (int i = 0; i <= n; i++) {
        matrix[n][k] = (matrix[n][k] + calc(i, k-1)) % 1000000;
    }
    return matrix[n][k];
}

int main() {
    int N, K;
    memset(matrix, -1, sizeof(matrix));

    while(scanf("%d %d", &N, &K) && !(N == 0 && K == 0)) {
        printf("%d\n", calc(N, K));        
    }
    return 0;
}