#include <iostream>
#include <vector>
#include <algorithm>
#include <iomanip>
#include <cmath>
using namespace std;

struct Point { int x, y; };

double dist(Point a, Point b) {
    return sqrt((double)(a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
}

bool comp(const Point &a, const Point &b) {
    return b.x < a.x;
}

int main() {    
    int testCases;
    cin >> testCases;
    while ( testCases-- ) {
        int coordC;
        cin >> coordC;
        vector<Point> points;
        for (int i = 1; i <= coordC; ++i) {
            Point point;
            cin >> point.x >> point.y;
            points.push_back(point);
        }

        sort(points.begin(), points.end(), comp);

        int currentMaxY = 0;
        double length = 0;
        for (int i = 1; i < points.size(); ++i) {
            if (points[i].y > currentMaxY) {
                length += dist(points[i], points[i - 1]) * (points[i].y - currentMaxY) / (points[i].y - points[i - 1].y); 
                currentMaxY = points[i].y;
            }
        }
        cout << setprecision(2) << setiosflags(ios::fixed) << length << endl;
    }
    return 0;
}
