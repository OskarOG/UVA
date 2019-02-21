
#include <cstdio>
#include <cstring>
 
int solutionCount, lineLenght, uniqueC;
int change[200];
char str[100], uniq[8], romanChars[8] = {"IVXLCDM"};
bool visited[10] = {false};
 
bool isRoman() {
	int rec[200] = {0}, num[3] = {0}, cnt = 0;
 
	for (int i = 0; i < lineLenght; i++)
		if (str[i] != '+' && str[i] != '=') {
			num[cnt] += (change[str[i]] >= change[str[i + 1]] ? change[str[i]] : -change[str[i]]);
			if (rec[str[i]]++ ==  0)
				uniq[uniqueC++] = str[i];
		} else 
            cnt++;
 
	return num[0] + num[1] == num[2];
}

int backtrack(int cur) {
	if (cur == uniqueC) {
		int num[3] = {0}, cnt = 0;
		for (int i = 0; i < lineLenght; i++)
			str[i] != '+' && str[i] != '=' ? num[cnt] = num[cnt] * 10 + change[str[i]] : cnt++;
 
		if (num[0] + num[1] == num[2])
			solutionCount++;
	}
	else for (int i = 0; i < 10; i++) {
		change[uniq[cur]] = i;
		if (visited[i] || change[str[0]] == 0)
			continue;
		visited[i] = true;
		backtrack(cur + 1);
		visited[i] = false;
	}
	return solutionCount;
}

int main() {
	while (scanf("%s", str), str[0] != '#') {
		solutionCount = uniqueC = 0;
		lineLenght = strlen(str);
		change['I'] = 1;
		for (int i = 1; i < 7; i ++)
			change[romanChars[i]] = change[romanChars[i-1]] * (i % 2 ? 5 : 2);
 
		printf(isRoman() ? "Correct " : "Incorrect ");
		printf(backtrack(0) ? solutionCount > 1 ? "ambiguous\n" : "valid\n" : "impossible\n");
	}
	return 0;
}