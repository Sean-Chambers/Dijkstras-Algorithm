import java.util.*;
class Graph{
   private ArrayList<Vertex> towns;
   
   public Graph(){
      towns = new ArrayList<Vertex>();
   }
   
   public void add(String townName,String destination, int distance){
      //if town exists
      for(Vertex town: towns){
         if(town.getName().equalsIgnoreCase(townName)){
            town.add(destination, distance);
            return;
         }
      }
      //else
      towns.add(new Vertex(townName,destination,distance));   
   }
   //returns a hashtable mapping the name of a town to a priorityqueue of its edges
   public Hashtable<String, ArrayList<Edge>> getGraphAsHashTable(){
      Hashtable<String, ArrayList<Edge>> arrayedTowns = new Hashtable<String, ArrayList<Edge>>();
      for(Vertex town: towns){
         arrayedTowns.put(town.getName(), town.getRoads());
      }
      return arrayedTowns;
   }
   
   public int getNumberOfNodes(){
      return towns.size();
   }
   
   public String[] townArray(){
      String[] names = new String[getNumberOfNodes()];
      int count = 0;
      for(Vertex town: towns){
         names[count++] = town.getName();
      }
      return names;
   }
   
   public boolean contains(String townName){
      for(Vertex town: towns){
         if(town.getName().equals(townName))
            return true;
      }
      return false;
   }
   
}
