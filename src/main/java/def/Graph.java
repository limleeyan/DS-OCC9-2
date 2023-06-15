package def;

import java.util.*;

public class Graph<T extends Comparable<T>> {
    Vertex<T> head;
    int size;

    public Graph() {
        head = null;
        size = 0;
    }

    private void removeEdgesFromVertex(Vertex<T> vertex) {
        Edge<T> currentEdge = vertex.firstEdge;
        while (currentEdge != null) {
            vertex.outdeg--;
            currentEdge = currentEdge.nextEdge;
        }
        vertex.firstEdge = null;
    }

    public void clear() {
        Vertex<T> currentVertex = head;
        while (currentVertex != null) {
            removeEdgesFromVertex(currentVertex);
            currentVertex = currentVertex.nextVertex;
        }
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean hasVertex(T v) {
        if (head == null)
            return false;
        Vertex<T> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0)
                return true;
            temp = temp.nextVertex;
        }
        return false;
    }

    public boolean addVertex(T v) {
        if (hasVertex(v) == false) {
            Vertex<T> temp = head;
            Vertex<T> newVertex = new Vertex<>(v, null);
            if (head == null) //graph is empty
                head = newVertex;
            else {
                Vertex<T> previous = head;
                while (temp != null) {
                    previous = temp;
                    temp = temp.nextVertex;
                }
                previous.nextVertex = newVertex; //add the vertex as last in the list
            }
            size++;
            return true;
        } else return false; //vertex already in the graph
    }

    public T getVertex(int pos) {
        if (pos > size - 1 || pos < 0)
            return null;
        Vertex<T> temp = head;
        for (int i = 0; i < pos; i++)
            temp = temp.nextVertex;
        return temp.vertexInfo;
    }

    public boolean addDirectedEdge(T source, T destination) {
        if (head == null)
            return false;
        if (!hasVertex(source) || !hasVertex(destination))
            return false;
        Vertex<T> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                Vertex<T> destinationVertex = head;
                while (destinationVertex != null) {
                    if (destinationVertex.vertexInfo.compareTo(destination) == 0) {
                        Edge<T> currentEdge = sourceVertex.firstEdge;
                        Edge<T> newEdge = new Edge<>(destinationVertex, currentEdge);
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

    public boolean addUndirectedEdge(T v1, T v2) {
        return (addDirectedEdge(v1, v2) && addDirectedEdge(v2, v1));
    }

    public ArrayList<T> getNeighbours(T v) {
        if (!hasVertex(v))
            return null;
        ArrayList<T> list = new ArrayList<>();
        Vertex<T> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0) {
                Edge<T> currentEdge = temp.firstEdge;
                while (currentEdge != null) {
                    list.add(currentEdge.toVertex.vertexInfo);
                    currentEdge = currentEdge.nextEdge;
                }
            }
            temp = temp.nextVertex;
        }
        return list;
    }

    class Vertex<T extends Comparable<T>> {
        T vertexInfo;
        int indeg;
        int outdeg;
        Vertex<T> nextVertex;
        Edge<T> firstEdge;

        public Vertex(T vInfo, Vertex<T> next) {
            vertexInfo = vInfo;
            indeg = 0;
            outdeg = 0;
            nextVertex = next;
            firstEdge = null;
        }
    }

    class Edge<T extends Comparable<T>> {
        Vertex<T> toVertex;
        Edge<T> nextEdge;

        public Edge(Vertex<T> destination, Edge<T> a) {
            toVertex = destination;
            nextEdge = a;
        }
    }
}