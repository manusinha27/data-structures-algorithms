/**
* Input:
* V = 2
* adj [] = {{{1, 9}}, {{0, 9}}}
* S = 0
* Output:
* 0 9
*
* Input:
* V = 3, E = 3
* adj = {{{1, 1}, {2, 6}}, {{2, 3}, {0, 1}}, {{1, 3}, {0, 6}}}
* S = 2
* Output:
* 4 3 0
*/

class Dijkstra
{
    //Function to find the shortest distance of all the vertices
    //from the source vertex S.
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
        // Write your code here
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[2]-b[2]);
        pq.offer(new int[]{S,S, 0});
        
        boolean[] visited = new boolean[V];
        int[] distance = new int[V];
        for(int i=0; i<V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        
        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            
            if(visited[node[0]]) {
                continue;
            }
            visited[node[0]] = true;
            
            if(node[2] < distance[node[0]]) {
                distance[node[0]] = node[2];
            }
            
            ArrayList<ArrayList<Integer>> neighbours = adj.get(node[0]);
            for(ArrayList<Integer> neighbour: neighbours) {
                int dest = neighbour.get(0);
                int weight = neighbour.get(1);
                
                if(!visited[dest]) {
                    pq.offer(new int[]{dest, node[0], weight+node[2]});
                }
            }
        }
        
        return distance;
        
    }
}
