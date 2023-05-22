package def;

import java.util.ArrayList;
import java.util.List;

public class Graph<T extends Comparable<T>, N extends Comparable<N>> {
    Vertex<T,N> head;
    int size;

    public Graph(){
        head = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean hasVertex(T v){
        if (head==null)
            return false;
        Vertex<T,N> temp = head;
        while (temp!=null){
            if (temp.vertexInfo.compareTo(v)==0)
                return true;
            temp = temp.nextVertex;
        }
        return false;
    }

    public int getIndeg(T v){
        if (hasVertex(v)==true){
            Vertex<T,N> temp = head;
            while (temp!=null){
                if (temp.vertexInfo.compareTo(v)==0)
                    return temp.indeg;
                temp = temp.nextVertex;
            }
        }
        return -1;
    }

    public int getOutdeg(T v){
        if (hasVertex(v)==true){
            Vertex<T,N> temp = head;
            while (temp!=null){
                if (temp.vertexInfo.compareTo(v)==0)
                    return temp.outdeg;
                temp = temp.nextVertex;
            }
        }
        return -1;
    }

    public boolean addVertex(T v){
        if (hasVertex(v)==false){
            Vertex<T,N> temp = head;
            Vertex<T,N> newVertex = new Vertex<>(v,null);
            if (head==null) //graph is empty
                head = newVertex;
            else {
                Vertex<T,N> previous = head;
                while (temp!=null){
                    previous = temp;
                    temp = temp.nextVertex;
                }
                previous.nextVertex = newVertex; //add the vertex as last in the list
            }
            size++;
            return true;
        }
        else return false; //vertex already in the graph
    }

    public int getIndex(T v){
        Vertex<T,N> temp = head;
        int pos = 0;
        while (temp!=null){
            if (temp.vertexInfo.compareTo(v)==0) //vertex is found
                return pos;
            temp = temp.nextVertex;
            pos++;
        }
        return -1;
    }

    public ArrayList<T> getAllVertexObjects(){
        ArrayList<T> list = new ArrayList<>();
        Vertex<T,N> temp = head;
        while (temp!=null){
            list.add(temp.vertexInfo);
            temp = temp.nextVertex;
        }
        return list;
    }

    public T getVertex(int pos){
        if (pos>size-1 || pos<0)
            return null;
        Vertex<T,N> temp = head;
        for (int i=0; i<pos; i++)
            temp = temp.nextVertex;
        return temp.vertexInfo;
    }

    public boolean hasEdge(T source, T destination){
        if (head==null)
            return false;
        if (!hasVertex(source) || !hasVertex(destination))
            return false;
        Vertex<T,N> sourceVertex = head;
        while (sourceVertex!=null){
            if (sourceVertex.vertexInfo.compareTo(source)==0){
                Edge<T,N> currentEdge = sourceVertex.firstEdge;
                while (currentEdge!=null){
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination)==0)
                        return true;
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    public boolean addDirectedEdge(T source, T destination, N w){
        if (head==null)
            return false;
        if (!hasVertex(source) || !hasVertex(destination))
            return false;
        Vertex<T,N> sourceVertex = head;
        while (sourceVertex!=null){
            if (sourceVertex.vertexInfo.compareTo(source)==0){
                Vertex<T,N> destinationVertex = head;
                while (destinationVertex!=null){
                    if (destinationVertex.vertexInfo.compareTo(destination)==0){
                        Edge<T,N> currentEdge = sourceVertex.firstEdge;
                        Edge<T,N> newEdge = new Edge<>(destinationVertex,w,currentEdge);
                        sourceVertex.firstEdge = newEdge;
                        sourceVertex.outdeg++;
                        destinationVertex.indeg++;
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    public boolean addUndirectedEdge(T v1, T v2, N w){
        return (addDirectedEdge(v1,v2,w) && addDirectedEdge(v2,v1,w));
    }

    public N getEdgeWeight(T source, T destination){
        N notFound = null;
        if (head==null)
            return notFound;
        if (!hasVertex(source)||!hasVertex(destination))
            return notFound;
        Vertex<T,N> sourceVertex = head;
        while (sourceVertex!=null){
            if (sourceVertex.vertexInfo.compareTo(source)==0){
                Edge<T,N> currentEdge = sourceVertex.firstEdge;
                while (currentEdge!=null){
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination)==0)
                        return currentEdge.weight;
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return notFound;
    }

    public ArrayList<T> getNeighbours(T v){
        if (!hasVertex(v))
            return null;
        ArrayList<T> list = new ArrayList<>();
        Vertex<T,N> temp = head;
        while (temp!=null){
            if (temp.vertexInfo.compareTo(v)==0){
                Edge<T,N> currentEdge = temp.firstEdge;
                while (currentEdge!=null){
                    list.add(currentEdge.toVertex.vertexInfo);
                    currentEdge = currentEdge.nextEdge;
                }
            }
            temp = temp.nextVertex;
        }
        return list;
    }

    public void printEdges(){
        Vertex<T,N> temp = head;
        while (temp!=null){
            System.out.print("# "+temp.vertexInfo+" : ");
            Edge<T,N> currentEdge = temp.firstEdge;
            while (currentEdge!=null){
                System.out.print("[" + temp.vertexInfo + ", " + currentEdge.toVertex.vertexInfo+"]\t");
                currentEdge = currentEdge.nextEdge;
            }
            System.out.println();
            temp = temp.nextVertex;
        }
    }

    /** food harvesting using Hamiltonian cycle **/
    public List<T> findHamiltonianCycle() {
        int numVertices = getSize();
        boolean[] visited = new boolean[numVertices];
        List<T> cycle = new ArrayList<>();
        cycle.add(getVertex(0));  // Start from the first vertex
        visited[0] = true;

        if (findHamiltonianCycleUtil(0, numVertices, visited, cycle)) {
            return cycle;
        } else {
            return new ArrayList<>(); // No Hamiltonian Cycle found
        }
    }

    private boolean findHamiltonianCycleUtil(int currentVertex, int remainingVertices,
                                             boolean[] visited, List<T> cycle) {
        if (remainingVertices == 1) {
            // All vertices have been visited, check if there is an edge to the starting vertex
            T startVertex = cycle.get(0);
            T lastVertex = cycle.get(cycle.size() - 1);
            if (hasEdge(lastVertex, startVertex)) {
                cycle.add(startVertex);  // Add the starting vertex to complete the cycle
                return true;
            } else {
                return false;
            }
        }

        List<T> neighbors = getNeighbours(cycle.get(currentVertex));
        for (T neighbor : neighbors) {
            int neighborIndex = getIndex(neighbor);
            if (!visited[neighborIndex]) {
                // Check if the neighbor node has food
//                if (!hasFood(neighbor)) {
//                    continue;  // Skip this neighbor if it does not have food
//                }

                cycle.add(neighbor);
                visited[neighborIndex] = true;

                if (findHamiltonianCycleUtil(neighborIndex, remainingVertices - 1, visited, cycle)) {
                    return true;
                }

                // Backtrack
                cycle.remove(cycle.size() - 1);
                visited[neighborIndex] = false;
            }
        }

        return false;
    }
}

class Vertex<T extends Comparable<T>, N extends Comparable<N>>{
    T vertexInfo;
    int indeg;
    int outdeg;
    Vertex<T,N> nextVertex;
    Edge<T,N> firstEdge;

    public Vertex(){
        vertexInfo = null;
        indeg = 0;
        outdeg = 0;
        nextVertex = null;
        firstEdge = null;
    }

    public Vertex(T vInfo, Vertex<T,N> next){
        vertexInfo = vInfo;
        indeg = 0;
        outdeg = 0;
        nextVertex = next;
        firstEdge = null;
    }
}

class Edge<T extends Comparable<T>, N extends Comparable<N>>{
    Vertex<T,N> toVertex;
    N weight;
    Edge<T,N> nextEdge;

    public Edge(){
        toVertex = null;
        weight = null;
        nextEdge = null;
    }

    public Edge(Vertex<T,N> destination, N w, Edge<T,N> a){
        toVertex = destination;
        weight = w;
        nextEdge = a;
    }
}