package def;

import java.util.*;

public class Graph<T extends Comparable<T>> {
    Vertex<T> head;
    int size;

    public Graph() {
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

    public int getIndeg(T v) {
        if (hasVertex(v) == true) {
            Vertex<T> temp = head;
            while (temp != null) {
                if (temp.vertexInfo.compareTo(v) == 0)
                    return temp.indeg;
                temp = temp.nextVertex;
            }
        }
        return -1;
    }

    public int getOutdeg(T v) {
        if (hasVertex(v) == true) {
            Vertex<T> temp = head;
            while (temp != null) {
                if (temp.vertexInfo.compareTo(v) == 0)
                    return temp.outdeg;
                temp = temp.nextVertex;
            }
        }
        return -1;
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

    public int getIndex(T v) {
        Vertex<T> temp = head;
        int pos = 0;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0) //vertex is found
                return pos;
            temp = temp.nextVertex;
            pos++;
        }
        return -1;
    }

    public ArrayList<T> getAllVertexObjects() {
        ArrayList<T> list = new ArrayList<>();
        Vertex<T> temp = head;
        while (temp != null) {
            list.add(temp.vertexInfo);
            temp = temp.nextVertex;
        }
        return list;
    }

    public T getVertex(int pos) {
        if (pos > size - 1 || pos < 0)
            return null;
        Vertex<T> temp = head;
        for (int i = 0; i < pos; i++)
            temp = temp.nextVertex;
        return temp.vertexInfo;
    }

    public boolean hasEdge(T source, T destination) {
        if (head == null)
            return false;
        if (!hasVertex(source) || !hasVertex(destination))
            return false;
        Vertex<T> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                Edge<T> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination) == 0)
                        return true;
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
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

    public void printEdges() {
        Vertex<T> temp = head;
        while (temp != null) {
            System.out.print("# " + temp.vertexInfo + " : ");
            Edge<T> currentEdge = temp.firstEdge;
            while (currentEdge != null) {
                System.out.print("[" + temp.vertexInfo + ", " + currentEdge.toVertex.vertexInfo + "]\t");
                currentEdge = currentEdge.nextEdge;
            }
            System.out.println();
            temp = temp.nextVertex;
        }
    }

    public boolean removeVertex(T v) {
        if (!hasVertex(v))
            return false;

        // If the vertex to be removed is the head vertex
        if (head.vertexInfo.equals(v)) {
            head = head.nextVertex;
            size--;
            return true;
        }

        Vertex<T> prev = head;
        Vertex<T> current = head.nextVertex;

        // Traverse the list to find the vertex to be removed
        while (current != null) {
            if (current.vertexInfo.equals(v)) {
                // Remove the vertex by adjusting the pointers
                prev.nextVertex = current.nextVertex;
                size--;
                removeEdgesToVertex(current); // Remove all edges connected to the vertex
                return true;
            }
            prev = current;
            current = current.nextVertex;
        }

        return false;
    }

    private void removeEdgesToVertex(Vertex<T> vertex) {
        Vertex<T> temp = head;
        while (temp != null) {
            Edge<T> prevEdge = null;
            Edge<T> currentEdge = temp.firstEdge;
            while (currentEdge != null) {
                if (currentEdge.toVertex == vertex) {
                    // Remove the edge by adjusting the pointers
                    if (prevEdge == null)
                        temp.firstEdge = currentEdge.nextEdge;
                    else
                        prevEdge.nextEdge = currentEdge.nextEdge;
                    temp.outdeg--;
                    vertex.indeg--;
                    break;
                }
                prevEdge = currentEdge;
                currentEdge = currentEdge.nextEdge;
            }
            temp = temp.nextVertex;
        }
    }

    class Vertex<T extends Comparable<T>> {
        T vertexInfo;
        int indeg;
        int outdeg;
        Vertex<T> nextVertex;
        Edge<T> firstEdge;

        public Vertex() {
            vertexInfo = null;
            indeg = 0;
            outdeg = 0;
            nextVertex = null;
            firstEdge = null;
        }

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

        public Edge() {
            toVertex = null;
            nextEdge = null;
        }

        public Edge(Vertex<T> destination, Edge<T> a) {
            toVertex = destination;
            nextEdge = a;
        }
    }
}