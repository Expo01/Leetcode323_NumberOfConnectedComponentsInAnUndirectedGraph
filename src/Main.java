import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

// so close to what i was trying to do. both encouraging and frustrating...
class Solution {
    public int countComponents(int n, int[][] edges) {
        int components = 0;

        boolean[] visited = new boolean[n]; // holds to avoid trivial cycles
        List<Integer>[] adjacencyList = new ArrayList[n]; // is an array of Lists... could also jsut do list of lists?

        for(int i = 0; i < n ; i++){
            adjacencyList[i] = new ArrayList<Integer>(); // empty arraylist at each vertex index
        }

        for(int i = 0; i < edges.length; i++){ // construct reciprocal edges
            adjacencyList[edges[i][0]].add(edges[i][1]);
            adjacencyList[edges[i][1]].add(edges[i][0]);
        }

        for(int i = 0; i < n; i++){ // loop every vertex and DFS if not already visited in prior DFS
            if (!visited[i]){
                components++;
                DFS(adjacencyList,visited,i);
            }
        }

        return components;

    }

    private void DFS(List<Integer>[] adjacencyList, boolean[] visited, int startNode){
        visited[startNode] = true; // mark vertex as visited
        for(int neighbor: adjacencyList[startNode]){ // traverse arraylist of neighbors of that vertex
            if(!visited[neighbor]){ // if not visited, recurse
                DFS(adjacencyList,visited,neighbor);
            }
        }
    }

}

// passes half of cases. gotta throw in the towel. been on this for 1.5 hr
class Solution {
    Map<Integer, ArrayList<Integer>> adjacencyMap = new HashMap<>();

    public int countComponents(int n, int[][] edges) {

        boolean[] visited = new boolean[n];

        for (int[] edge : edges) {
            if (!adjacencyMap.containsKey(edge[0])) {
                adjacencyMap.put(edge[0], new ArrayList<>());
            }
            adjacencyMap.get(edge[0]).add(edge[1]);
        }

        int componentCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && adjacencyMap.containsKey(i)) {
                componentCount++;
                DFS(i, visited);
            }
        }
        return componentCount;
    }

    private void DFS(int key, boolean[] visited) {
        visited[key] = true;
        System.out.println("key" + key);
        if(!adjacencyMap.containsKey(key)){
            System.out.println("shouldn't happen");
            return;
        }
        ArrayList<Integer> curNeighbors = adjacencyMap.get(key);

        for (Integer curNeighbor : curNeighbors) {
            if (visited[curNeighbor] == false) {
                DFS(curNeighbor, visited);
            }
        }
    }
}

/*
- create adjacency map with node key and neighbor vals in arraylist
- loop through 0-(n-1) calling DFS from separate method, each time asking if key exists and if
yes, then increment the count, similar to # of islands, but now with known nodes of 0-(n-1)
instead of a mxn matrix
- after initial DFS, get the arraylist and DFS through its arraylist recursively calling itself.
each time delete the neighbor from arraylist of calling node and from arraylist of neighbor
to prevent trivial cycle. if arraylist value is empty then terminate the instance of DFS to pop
off stack...
- alternatively instead of deconstructing the adjacency list each time and having to delete edge
frmo both ends, just use a 'visited' boolean array or a set to prevent trial cycle so that node
can only be the 'primary' node of a DFS call on a single occurence. sounds easier and maybe better
practice then deconstructing
 */

// adjacency map? then start at 0 and loop through arraylist values for key 0 and
// delete frrom arrraylist? if only 1 iteeem in arrrraylist deeleeete the key?
// inc a count for each time DFS has to be calleed frrom loop 0- (n-1) for numberrr
// of separatee graphs present?

