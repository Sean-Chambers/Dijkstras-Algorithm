/*
 * Written by Sean Chambers
 * 
 * Implements Dijkstras Algorithm to create a shortest path table 
 */
import java.util.*;
class DijkstraTable{
   private Hashtable<String, ArrayList<Edge>> reference;
   private String[] towns;
   private Hashtable<String, String> table;
   
   private Comparator<Edge> compare = new Comparator<Edge>(){
      public int compare(Edge firstEdge, Edge secondEdge){
         if(Integer.parseInt(table.get(firstEdge.getDestination()).split("/")[1]) < Integer.parseInt(table.get(secondEdge.getDestination()).split("/")[1]))
            return -1;
         if(Integer.parseInt(table.get(firstEdge.getDestination()).split("/")[1]) < Integer.parseInt(table.get(secondEdge.getDestination()).split("/")[1]))
            return 0;
         else
            return 1;
      }
    };
   
   public DijkstraTable(Graph newGraph, String town){
      reference = newGraph.getGraphAsHashTable();
      table = new Hashtable<String, String>(newGraph.getNumberOfNodes());
      towns = newGraph.townArray();
      //initialize
      for(String name: towns){
         table.put(name, "/-1");
      }
      table.put(town, "/0");
      createTable(town,0,new ArrayList<String>());
   }
   //recursivly impliments dijkstras algorithm
   private void createTable(String town,int dist, ArrayList<String> visited){
      //track what has been visited
      visited.add(town);
      int totalDist;
      for(Edge edge: reference.get(town)){
         if(!visited.contains(edge.getDestination())){
            //shortest known path to unvisited town
            int townDist = Integer.parseInt(table.get(edge.getDestination()).split("/")[1]);
            //distance to the new town
            int vertexDist = edge.getDistance();
            //distance from begining city to the newest node 
            totalDist = dist + vertexDist;
            //if shortest know path is longer than current path
            if(townDist> totalDist){
               table.put(edge.getDestination(),town + "/" + totalDist);
            }
            //if no known shortest path exists 
            if(townDist == -1){
               table.put(edge.getDestination(),town + "/" + totalDist);
            }
         }
      }
      
      PriorityQueue<Edge> queue = new PriorityQueue<Edge>(11, compare);
      for(String node: visited){
         for(Edge road: reference.get(node)){
            //avoid going to visited cities
            if(!visited.contains(road.getDestination()))
               queue.add(road);
         }
      }
      
      //no nodes are unvisited
      if(queue.size()==0)
         return;
      
      //grab shortest edge to unvisited node
      Edge shortest = queue.poll();
      //finds the name of the next two to visit
      String dest = shortest.getDestination();
      String[] prev = table.get(shortest.getStart()).split("/");
      //name of node the road starts at
      String node = prev[0];
      //finds distance from origin town to knew town
      totalDist = Integer.parseInt(prev[1]) + shortest.getDistance();
      
      createTable(dest, totalDist, visited);
      
   }
   
   public int getDistanceTo(String town){
      int distance = Integer.parseInt(table.get(town).split("/")[1]);
      return distance;
   }
   
   public Hashtable<String, String> getTable(){
      return table;
   }
}