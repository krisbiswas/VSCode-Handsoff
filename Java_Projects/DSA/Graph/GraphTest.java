package Java_Projects.DSA.Graph;

public class GraphTest {
    public static void main(String[] args) {
        Graph map = new Graph(4);
        map.add(0, 1, 8);
        map.add(0, 3, 5);
        map.add(1, 2, 2);
        map.add(2, 3, 1);
        map.add(3, 2, 4);
        map.print();
        // GraphUtils.dijkstra(map, 0, 2);
    }
}
