/*
runs the entire program
provided by instructor for use in implementing Dijkstras Algorithm
valid town names can be found in RoadData.csv
*/
import java.io.*;
import java.lang.*;
import java.util.*;
class Assignment3{
   public static Graph myGraph;
    
   public static void main(String[] args){
      myGraph = new Graph();
      readFile("RoadData.csv");   //change to args[0]
      System.out.println(myGraph.getNumberOfNodes() + " towns added to graph");
      while(true){
         String firstTown, secondTown;
         while(true){
            System.out.print("First Town: ");
            firstTown = getInput();
            if(firstTown.length()==0)
               System.exit(0);
            System.out.print("Second Town: ");
            secondTown = getInput();
            if(secondTown.length()==0)
               System.exit(0);
            if(myGraph.contains(firstTown) && myGraph.contains(firstTown))
               break;
         }      
         DijkstraTable table = new DijkstraTable(myGraph, firstTown);
         printTable(table, secondTown, firstTown);
      }      
   }
   
   //------------READERS------------------------
   
   public static String getInput(){
      //create buffered reader
      BufferedReader userIn = null;
      try{
         userIn = new BufferedReader(new InputStreamReader(System.in));
      }catch(Exception e){
         System.out.println("Cannot Read Input: " + e);
         System.exit(0);
      }
      //grab line
      String line = "";
      try {
         line = userIn.readLine();
      } catch (Exception e) {
         System.out.println("I/O error: " + e);
         System.exit(0);
      }
      return line;
   }
   
   public static void readFile(String filename){
      //create buffered reader
      BufferedReader in = null;
      try {
         in = new BufferedReader(new FileReader(filename));
      } catch (Exception e) {
         System.out.println("Cannot open file " + e);
         return;
      }
      //read line of file
      while(true){
         String line = "";
         try {
            line = in.readLine();
         }catch (Exception e) {
            System.out.println("I/O error: " + e);
         }
         if(line == null)
            break;
         //tokenize the file
         StringTokenizer tokenizer = new StringTokenizer(line, ",");
         String town1 = tokenizer.nextToken();
         String town2 = tokenizer.nextToken();
         int distance = 0;
         try {
            distance = Integer.parseInt(tokenizer.nextToken());
         } catch (Exception e) {
            System.out.println("Data error: " + e);
         }
         //add towns to graph
         myGraph.add(town1,town2,distance);
         myGraph.add(town2,town1,distance);
      }
   }
   //---------END READERS----------------------------
   
   //prints the distances out to console. 
   public static void printTable(DijkstraTable dijkstra, String name, String firstName){
      Hashtable<String, String> table = dijkstra.getTable();
      while(true){
         if(name.equals(firstName)){
            System.out.println(firstName);
            break;
         }
         System.out.println(name + " (" + table.get(name).split("/")[1] + ")");
         name = table.get(name).split("/")[0];
      }
   }
  
}
