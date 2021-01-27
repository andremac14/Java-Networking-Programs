/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sandovala
 */
public class Edge 
{
   private int fromNodeIndex;
   private int toNodeIndex;
   private int length;
   
   public Edge(int fromNodeIndex, int toNodeIndex, int length)
   {
      this.fromNodeIndex = fromNodeIndex;
      this.toNodeIndex = toNodeIndex;
      this.length = length;      
   }
   
   public int getFromNodeIndex()
   {
      return fromNodeIndex;
   }
   
   public int getToNodeIndex()
   {
      return fromNodeIndex;
   }
   
   public int getLength()
   {
      return length;
   }
   
   public int getNeighborIndex(int nodeIndex)
   {
      if(this.fromNodeIndex == nodeIndex) 
      {
         return this.toNodeIndex;
      }
      else
      {
         return this.fromNodeIndex;
      }
   
   }
}
