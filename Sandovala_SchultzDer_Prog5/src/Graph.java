
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
public class Graph 
{
   private Node[] nodes;
   private int noOfNodes;
   private Edge[] edges;
   private int noOfEdges;
   
   public Graph(Edge[] edges)
   {
      this.edges = edges;
      this.noOfNodes = calculateNoOfNodes(edges);
      this.nodes = new Node[this.noOfNodes];
      
      for(int n = 0; n < this.noOfNodes; n++)
      {
         this.nodes[n] = new Node();
      }
      
      this.noOfEdges = edges.length;
      
      for(int edgeToAdd = 0; edgeToAdd < this.noOfEdges; edgeToAdd++)
      {
         this.nodes[edges[edgeToAdd].getFromNodeIndex()].getEdges().add(edges[edgeToAdd]);
         this.nodes[edges[edgeToAdd].getToNodeIndex()].getEdges().add(edges[edgeToAdd]);
      }
   }
   
    private int calculateNoOfNodes(Edge[] edges) 
    {
       int noOfNodes = 0;
       
       for(Edge e : edges)
       {
          if(e.getFromNodeIndex() > noOfNodes)
             noOfNodes = e.getToNodeIndex();
          if(e.getFromNodeIndex() > noOfNodes)
             noOfNodes = e.getFromNodeIndex();    
       }
       noOfNodes++;
       
       return noOfNodes;
    }
    
    public void calculateShortestDistances()
    {
       this.nodes[0].setDistanceFromSource(0);
       int nextNode = 0;
       
       //visit every node
       for(int i = 0; i < this.nodes.length; i++)
       {
          ArrayList<Edge> currentNodeEdges = this.nodes[nextNode].getEdges();
          
          for(int joinedEdge = 0; joinedEdge < currentNodeEdges.size(); joinedEdge++)
          {
             int neighborIndex = currentNodeEdges.get(joinedEdge).getNeighborIndex(nextNode);
             
             if(this.nodes[neighborIndex].isVisited())
             {
                int tentative = this.nodes[nextNode].getDistanceFromSource() + currentNodeEdges.get(joinedEdge).getLength();
                
                if(tentative < nodes[neighborIndex].getDistanceFromSource())
                    nodes[neighborIndex].setDistanceFromSource(tentative);
             }
          }
          
          nodes[nextNode].setVisited(true);
          
          nextNode = getNodeShortestDistance();
          
          
       }
    }
       
   private int getNodeShortestDistance()
   {
      int storedNodeIndex = 0;
      int storedDist = Integer.MAX_VALUE;
                  
      for(int i = 0; i < this.nodes.length; i++)
      {
         int currentDist = this.nodes[i].getDistanceFromSource();
             
         if(this.nodes[i].isVisited() && currentDist < storedDist)
         {
            storedDist = currentDist;
            storedNodeIndex = i;
         }
      }
          
      return storedNodeIndex;
   }
   
   public void printResult()
   {
      String output = "Number of Nodes" + this.noOfNodes;
      output += "\nThe shortest distance from node 0";
      
      for(int i = 0; i < this.nodes.length; i++)
      {
         output += ("\nThe shortest distance from node 0 to node" + i + " is " + nodes[i].getDistanceFromSource());
      }
      System.out.println(output);
      
   }
   
   public Node[] getNodes()
   {
      return nodes;
   }
   
   public int getNoOfNodes()
   {
      return noOfNodes;
   }
   
   public Edge[] getEdges()
   {
      return edges;
   }
   
   public int getNoOfEdges()
   {
      return noOfEdges;
   }
   
}
   
   
   

