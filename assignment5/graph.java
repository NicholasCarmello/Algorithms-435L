import java.util.ArrayList;

public class graph {
    ArrayList<graphNode> vertices = new ArrayList<graphNode>();
    ArrayList<graphNode> edges = new ArrayList<graphNode>();

    public static void bellman(graph G) {
        int distanceAttribute[] = new int[G.vertices.size()];
        boolean newBool = true;
        singleSource(G, distanceAttribute);

        for (int i = 1; i < G.vertices.size(); i++) {
            for (int j = 0; j < G.edges.size(); j++) {
                relax(G.edges.get(j).weight, G.edges.get(j).from, G.edges.get(j).to, distanceAttribute);
            }
        }
        for (int j = 0; j < G.edges.size(); j++) {
            if (distanceAttribute[G.edges.get(j).to -1 ] > (distanceAttribute[G.edges.get(j).from - 1] + G.edges.get(j).weight)){
                newBool = false;
          }

        }
        if (newBool){
            System.out.println("\n" + "New Graph " );
            for(int i = 0;i <G.vertices.size(); i ++){
                System.out.println(1 + " -> " + (i + 1) + " cost is " + distanceAttribute[i]);
            }
        } 
        

    }
    
    public static void singleSource(graph newGraph,  int distanceAttribute[]) {
        for (int i = 0; i < newGraph.vertices.size(); ++i) {
            distanceAttribute[i] = 1000;

        }
        distanceAttribute[0] = 0;
    }

    public static void relax(int weight, int from, int to, int[] distanceAttribute) {

        if (distanceAttribute[from - 1] != 1000 && distanceAttribute[from - 1] + weight < distanceAttribute[to - 1]) {
            distanceAttribute[to - 1] = distanceAttribute[from - 1] + weight;
        }
    }

}
