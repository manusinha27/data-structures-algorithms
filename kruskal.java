/**
* You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
* The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
* Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
*
* Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
* Output: 20
*/

class Kruskal {
    public int minCostConnectPoints(int[][] points) {
        // 1. From point create a List of Edges where each edge is int[3]
        List<int[]> list = new ArrayList<int[]>();
        for(int i=0; i<points.length; i++) {
            for(int j=i+1; j<points.length; j++) {
                int distance = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                list.add(new int[]{distance, i, j});
            }
        }

        // 2. Sort the edges by weight
        Collections.sort(list, (a,b) -> Integer.compare(a[0], b[0]));


        // 3. Do a Union join till edges count in union is n-1. Keep counting the weight
        UnionFind uf = new UnionFind(points.length);
        int edges = 0;
        int totalWeight=0;
        for(int i=0; i<list.size() && edges<points.length-1; i++) {
            int[] currPoint = list.get(i);
            boolean edgeCreated = uf.union(currPoint[1], currPoint[2]);
            if(edgeCreated) {
                edges++;
                totalWeight = totalWeight + currPoint[0];
            }
        }

        return totalWeight;
    }

    class UnionFind {
        
        private int[] root;
        private int[] rank;

        public UnionFind(int size) {
            root = new int[size];
            rank = new int[size];

            for(int i=0; i<size; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        public boolean union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if(xRoot!=yRoot) {
                if(rank[xRoot]>rank[yRoot]) {
                    root[yRoot] = xRoot;
                } else if(rank[xRoot]<rank[yRoot]) {
                    root[xRoot] = yRoot;
                } else {
                    root[xRoot] = yRoot;
                    rank[yRoot] = rank[yRoot] + 1;
                }
                return true;
            }

            return false;
        }

        public int find(int x) {
            if(root[x]==x) {
                return x;
            }

            return root[x] = find(root[x]);
        }
    
    }
}
