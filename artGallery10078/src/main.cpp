#include <iostream>
using namespace std;

struct point {
	int x, y;
};

int cross(point p0, point p1, point p2) {
	int x1 = p1.x - p0.x;
	int x2 = p2.x - p0.x;
	int y1 = p1.y - p0.y;
	int y2 = p2.y - p0.y;
	return x1 * y2 - x2 * y1;
}

int main() {
	int n;
	for (cin >> n; n; cin >> n) {
		point p[51];

		for (int i = 0; i < n; i++)
			cin >> p[i].x >> p[i].y;

		p[n] = p[0];
		p[n + 1] = p[1];

		bool convex = true;
		int d = cross(p[0], p[1], p[2]);
		for (int i = 1; i < n; i++) {
			int c = d;
			d = cross(p[i], p[i + 1], p[i + 2]);
			if (c * d < 0) {
				convex = false;
				break;
			}
		}
		cout << (convex ? "No" : "Yes") << endl;
	}
	return 0;
}