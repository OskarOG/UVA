package sec2.KillingAliensInBorgMaze10307;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);

        int testCases = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < testCases; i++) {
            String[] inSize = sc.nextLine().split(" ");
            int cols = Integer.parseInt(inSize[0]);
            int rows = Integer.parseInt(inSize[1]);

            int[][] vTitle = new int[rows][cols];
            char[][] maze = new char[rows][cols];
            ArrayList<Vertex> vertices = new ArrayList<>(cols*rows/2);
            int startIndex = 0;
            char ch;

            for (int r = 0; r < rows; r++) {
                char[] cha = sc.nextLine().toCharArray();

                for (int c = 0; c < cha.length; c++) {
                    ch = cha[c];
                    if (ch == 'S') {
                        startIndex = vTitle[r][c] = vertices.size();
                        vertices.add(new Vertex(r, c));
                    } else if (ch == 'A') {
                        vTitle[r][c] = vertices.size();
                        vertices.add(new Vertex(r, c));
                    }
                    maze[r][c] = ch;
                }
            }

            Graph G = new Graph(vertices, vTitle, maze);
            G.findWeight();
        }
    }

    private class Graph {
        ArrayList<Vertex> v;
        int[][] vt;
        char[][] m;
        ArrayList<Edge> edges;

        Graph(ArrayList<Vertex> vertices, int[][] vtitles, char[][] maze) {
            this.v = vertices;
            this.vt = vtitles;
            this.m = maze;

            edges = new ArrayList<>();
        }

        void findWeight() {
            for (int i = 0; i < v.size(); i++) {
                bfs(i);
            }

            int weight = kruskalMST();
            System.out.println(weight);
        }

        void bfs(int startPos) {
            int step = 0;
            boolean[][] isVisited = new boolean[m.length][m[0].length];
            Vertex start = v.get(startPos);

            Queue<Vertex> queue = new ArrayDeque<>();
            queue.add(start);
            isVisited[start.row][start.col] = true;
            queue.add(new NewRow());

            while (!queue.isEmpty()) {
                Vertex cv = queue.poll();

                if (cv instanceof NewRow) {
                    step++;
                    queue.add(new NewRow());
                    if (queue.peek() instanceof NewRow) return;
                } else {
                    if ((m[cv.row][cv.col] == 'A' || m[cv.row][cv.col] == 'S') && step > 0) {
                        edges.add(new Edge(startPos, vt[cv.row][cv.col], step));
                    }

                    if (!isVisited[cv.row][cv.col - 1] && m[cv.row][cv.col - 1] != '#') {
                        isVisited[cv.row][cv.col - 1] = true;
                        queue.add(new Vertex(cv.row, cv.col - 1));
                    }
                    if (!isVisited[cv.row - 1][cv.col] && m[cv.row - 1][cv.col] != '#') {
                        isVisited[cv.row - 1][cv.col] = true;
                        queue.add(new Vertex(cv.row - 1, cv.col));
                    }
                    if (!isVisited[cv.row][cv.col + 1] && m[cv.row][cv.col + 1] != '#') {
                        isVisited[cv.row][cv.col + 1] = true;
                        queue.add(new Vertex(cv.row, cv.col + 1));
                    }
                    if (!isVisited[cv.row + 1][cv.col] && m[cv.row + 1][cv.col] != '#') {
                        isVisited[cv.row + 1][cv.col] = true;
                        queue.add(new Vertex(cv.row + 1, cv.col));
                    }
                }
            }
        }

        int kruskalMST() {
            ArrayList<Edge> res = new ArrayList<>();

            edges.sort(Comparator.comparingInt(o -> o.weight));
            Subset[] subsets = new Subset[v.size()];
            for (int j = 0; j < subsets.length; j++) {
                subsets[j] = new Subset();
                subsets[j].parent = j;
                subsets[j].rank = 0;
            }

            int i = 0;
            while (res.size() < v.size() - 1) {
                Edge nextEdge = edges.get(i++);

                int x = find(subsets, nextEdge.src);
                int y = find(subsets, nextEdge.dest);

                if (x != y) {
                    res.add(nextEdge);
                    union(subsets, x, y);
                }
            }

            int totWeight = 0;
            for (Edge re : res) {
                totWeight += re.weight;
            }
            return totWeight;
        }

        int find(Subset[] subsets, int i) {
            if (subsets[i].parent != i)
                subsets[i].parent = find(subsets, subsets[i].parent);

            return subsets[i].parent;
        }

        void union(Subset[] subsets, int x, int y) {
            int xroot = find(subsets, x);
            int yroot = find(subsets, y);

            if (subsets[xroot].rank < subsets[yroot].rank)
                subsets[xroot].parent = yroot;
            else if (subsets[xroot].rank > subsets[yroot].rank)
                subsets[yroot].parent = xroot;
            else {
                subsets[yroot].parent = xroot;
                subsets[xroot].rank++;
            }
        }
    }

    private class Vertex {
        int row;
        int col;

        Vertex() { }

        Vertex(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return Integer.toString(row) + Integer.toString(col);
        }
    }

    private class Edge {
        int src;
        int dest;
        int weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    private class Subset {
        int parent, rank;
    }

    private class NewRow extends Vertex { }
}
