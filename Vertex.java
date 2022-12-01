/*
 * Provided by instructor for use in making a graph
 * 
 * Vertex represents cities connected by Edges orin this case roads. 
 */
import java.util.*;
class Vertex{
   private ArrayList<Edge> roads;
   private String name;
   //-------------CONSTRUCTOR------------
   public Vertex(String town, String destination, int distance){
      this.name = town;
      this.roads  = new ArrayList<Edge>();
      add(destination,distance);
   }
   //---------END CONSTRUCTOR--------------
  
   //--------GETTERS----------------------- 
   public String getName(){
      return this.name;
   }
   
   public ArrayList<Edge> getRoads(){
      return this.roads;
   }
   
   public PriorityQueue<Edge> getRoadsAsQueue(){
      //establishes a comparator
      Comparator<Edge> compare = new Comparator<Edge>(){
         public int compare(Edge firstEdge, Edge secondEdge){
            if(firstEdge.getDistance() < secondEdge.getDistance())
               return -1;
            if(firstEdge.getDistance() == secondEdge.getDistance())
               return 0;
            else
               return 1;
         }
      };
      
      //Priority Queue Initialization
      PriorityQueue<Edge> queue = new PriorityQueue<Edge>(11, compare);
      
      for(Edge road: roads){
         queue.add(road);
      }
      return queue;
   }  
   //--------END GETTERS----------------------------
   
   public void add(String destination, int distance){
      //if road exists
      for(Edge road: roads){
         if(road.getDestination().equalsIgnoreCase(destination) && road.getDistance() == distance)
            return;
      }
      //else
      roads.add( new Edge(name, destination, distance));
   }
   
}
