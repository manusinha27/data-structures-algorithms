class Prim {
    public int minCostConnectPoints(int[][] points) {

        PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> (a.getKey() - b.getKey()));

        int n = points.length;
        int minCost = 0;
        int edges = 0;
        boolean[] visited = new boolean[n];

        queue.offer(new Pair(0,0));

        while(edges < n) {
            Pair<Integer, Integer> p = queue.poll();
            int currCost = p.getKey();
            int pointIndex = p.getValue();

            if(visited[pointIndex]) {
                continue;
            }

            visited[pointIndex] = true;

            minCost += currCost;
            edges++;

            for(int i=0; i<n; i++) {
                if(!visited[i]) {
                    int cost = Math.abs(points[pointIndex][0]-points[i][0]) + Math.abs(points[pointIndex][1]-points[i][1]);
                    queue.offer(new Pair(cost, i));
                }
            }
        }

        return minCost;
    }
}
