/*
 * Provided by instructor for creation of a graph
 * 
 * Edge represents roads between two Vertex or in this case two cities
 */
class Edge{
   private String destination;
   private int distance; 
   private String town;
   
   public Edge(String location, String dest, int dist){
      this.destination = dest;
      this.distance = dist;
      this.town = location;
   }
   
   public int getDistance(){
      return this.distance;
   }
   
   public String getDestination(){
      return this.destination;
   }
   
   public String getStart(){
      return this.town;   
   }
    
}
