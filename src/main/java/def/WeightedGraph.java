package def;

import java.util.*;

public class WeightedGraph <T extends Comparable<T>, N extends Comparable<N>,G extends Comparable<G>>{
    Vertex<T,N,G> head;
    int size;

    public WeightedGraph(){
        head = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    //check whether has vertex or not
    public boolean hasVertex(T v){
        if(head==null){
            return false;
        }
        Vertex<T,N,G> temp = head;
        while(temp!=null){
            if(temp.vertexInfo.compareTo(v)==0){
                return true;
            }
            temp = temp.nextVertex;
        }
        return false;
    }

    //get indeg of a vertex
    public int getIndeg(T v){
        if(hasVertex(v)==true){
            Vertex<T,N,G> temp = head;
            while(temp!=null){
                if(temp.vertexInfo.compareTo(v)==0){
                    return  temp.indeg;
                }
                temp = temp.nextVertex;
            }
        }
        return -1;
    }

    //get outdeg of a vertex
    public int getOutdeg(T v){
        if(hasVertex(v) == true){
            Vertex<T,N,G> temp = head;
            while(temp!=null){
                if(temp.vertexInfo.compareTo(v)==0){
                    return temp.outdeg;
                }
                temp = temp.nextVertex;
            }
        }
        return -1;
    }

    //to add vertex
    public boolean addVertex(T v){
        if(!hasVertex(v)){
            Vertex<T,N,G> temp = head;
            Vertex<T,N,G> newVertex = new Vertex<>(v,null);

            if(head==null){ //graph is empty
                head = newVertex; //why is head = newVertex and not another way round
            }
            else {
                Vertex<T, N, G> previous = head;
                while (temp != null) {
                    previous = temp;
                    temp = temp.nextVertex;
                }
                temp.nextVertex = newVertex;
            }
            size++;
            return true;
        }else
            return false;
    }

    //to get index of the vertex
    public int getIndex(T v){
        Vertex<T,N,G> temp = head;
        int index = 0;
        while(temp!=null){
            if(temp.vertexInfo.compareTo(v)==0){
                return index;
            }
            temp = temp.nextVertex;
            index++;
        }
        return -1;
    }

    //to get all vertex object;
    public ArrayList<T> getAllVertex(){
        Vertex<T,N,G> temp = head;
        ArrayList<T> list = new ArrayList<>();
        while(temp!=null){
            list.add(temp.vertexInfo);
            temp = temp.nextVertex;
        }
        return list;
    }

    //To get vertex of particular index
    public T getVertex(int index){
        if(index<0 || index>size-1){
            return null;
        }
        Vertex<T,N,G> temp = head;
        for(int i=0; i<index; i++){
            temp = temp.nextVertex;
        }
        return temp.vertexInfo;
    }

    // Check whether have edge
    public boolean hasEdge(T source, T destination){
        if(head==null)
            return false;
        if(!hasVertex(source) || !hasVertex(destination))
            return false;
        Vertex<T,N,G> sourceVertex = head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.compareTo(source)==0){
                Edge<T,N,G> currentEdge = sourceVertex.firstEdge;
                while (currentEdge!=null){
                    if(currentEdge.toVertex.vertexInfo.compareTo(destination)==0)
                        return true;
                    currentEdge = currentEdge.nextEdge;
                }

            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    //add Directed edge
    public boolean addDirectedEdge(T source, T destination, N distance, G roadType){
        if(head == null)
            return false;
        if(!hasVertex(source) || !hasVertex(destination))
            return false;
        Vertex<T,N,G> sourceVertex = head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.compareTo(source)==0){
                Vertex<T,N,G> destinationVertex = head;
                while(destinationVertex!=null){
                    if(destinationVertex.vertexInfo.compareTo(destination)==0){
                        Edge<T,N,G> currentEdge = sourceVertex.firstEdge;
                        Edge<T,N,G> newEdge = new Edge<>(destinationVertex,distance,currentEdge,roadType);
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

    //Add undirected edge
    public boolean addUndirectedEdge(T source, T destination, N distance, G roadType){
        return addDirectedEdge(source, destination,distance, roadType) && addDirectedEdge(destination, source, distance, roadType);
    }

    //To get edge distance
    public N getEdgeDistance(T source, T destination){
        N notFound = null;
        if(head==null)
            return notFound;
        if(!hasVertex(source) || !hasVertex(destination))
            return notFound;
        Vertex<T,N,G> sourceVertex = head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.compareTo(source)==0){
                Edge<T,N,G> currentEdge = sourceVertex.firstEdge;
                while(currentEdge!=null){
                    if(currentEdge.toVertex.vertexInfo.compareTo(destination)==0){
                        return currentEdge.distance;
                    }
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return notFound;
    }
    //get neighbour
    public ArrayList<T> getNeighbour(T v){
        if (!hasVertex(v))
            return null;
        ArrayList<T> list = new ArrayList<>();
        Vertex<T,N,G> sourceVertex = head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.compareTo(v)==0){
                Edge<T,N,G> currentEdge = sourceVertex.firstEdge;
                while(currentEdge!=null){
                    list.add(currentEdge.toVertex.vertexInfo);
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return list;
    }

//    public void printEdges() {
//        WeightedGraph.Vertex<T,N,G> temp = head;
//        while (temp != null) {
//            System.out.print("# " + temp.vertexInfo + " : ");
//            WeightedGraph.Edge<T,N,G> currentEdge = temp.firstEdge;
//            while (currentEdge != null) {
//                System.out.print("[" + temp.vertexInfo + ", " + currentEdge.toVertex.vertexInfo + "]\t");
//                currentEdge = currentEdge.nextEdge;
//            }
//            System.out.println();
//            temp = temp.nextVertex;
//        }
//    }

    class Vertex<T extends Comparable<T>,N extends Comparable<N>, G extends Comparable<G>>{
        T vertexInfo;
        int indeg;
        int outdeg;
        Vertex<T,N,G> nextVertex;
        Edge<T,N,G> firstEdge; //why this call first edge?


        public Vertex(){
            indeg = 0;
            outdeg = 0;
            nextVertex = null;
            firstEdge = null;
        }
        public Vertex(T vertexInfo, Vertex<T,N,G> nextVertex){
            this.vertexInfo = vertexInfo;
            this.nextVertex = nextVertex;
            indeg = 0;
            outdeg = 0;
            firstEdge = null;
        }
    }
    class Edge<T extends Comparable<T>, N extends Comparable<N>, G extends Comparable<G>>{
        Vertex<T,N,G> toVertex;
        Edge<T,N,G> nextEdge;
        G roadType;
        N distance;

        public Edge(){
            toVertex = null;
            nextEdge = null;
            distance = null;
        }
        public Edge(Vertex<T,N,G> destination, N distance, Edge<T,N,G> nextEdge,G roadType){
            this.toVertex = destination;
            this.distance = distance;
            this.nextEdge = nextEdge;
            this.roadType = roadType;
        }
    }
}


