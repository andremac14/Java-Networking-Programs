
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sandovala
 */
public class Node 
{
   private int distanceFromSource = Integer.MAX_VALUE;
   
   private boolean visited;
   
   private ArrayList<Edge> edges = new ArrayList<Edge>();
   
   public int getDistanceFromSource()
   {
      return distanceFromSource;
   }
   
   public void setDistanceFromSource(int distanceFromSource)
   {
      this.distanceFromSource = distanceFromSource;
   }
   
   public boolean isVisited()
   {
     return visited;
   }
   
   public void setVisited(boolean visited)
   {
      this.visited = visited;
   }

    ArrayList<Edge> getEdges() {
       return edges;
    }
}
