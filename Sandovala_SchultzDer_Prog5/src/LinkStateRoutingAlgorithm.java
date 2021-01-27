
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sandovala
 */
public class LinkStateRoutingAlgorithm 
{
 
    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException 
    {          
//      Scanner sc = new Scanner(new BufferedReader(new FileReader("SmallGraph.txt")));
//      int rows = 10;
//      int columns = 12;
//      int [][] myArray = new int[rows][columns];
//      while(sc.hasNextLine()) {
//         for (int i=0; i<myArray.length; i++) 
//         {
//            String[] line = sc.nextLine().trim().split(" ");
//            for (int j=0; j<line.length; j++) 
//            {
//               myArray[i][j] = Integer.parseInt(line[j]);
//            }
//         }
//      }
//      System.out.println(Arrays.deepToString(myArray));
       Edge[] edges = {
               new Edge(0,2,1),
               new Edge(0,3,4)
       };
       
       Graph g = new Graph(edges);
       g.calculateShortestDistances();
       
       g.printResult();
       
       
       
    }                
}
