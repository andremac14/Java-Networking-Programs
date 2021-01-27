/**
This class allows the server to send messages back to the client
@author Andre Sandoval, Derek Schultz
*/


import java.io.*;
import java.net.*;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FTPThread extends Thread
{
   String Dir = "Files";
   boolean quitTime = false; // server connection status
   Socket sock; // Socket connected to the Client
   File dir = new File(Dir); 
   // create a new instance of File class and set the pathname
   File [] files = dir.listFiles(); // get the list of all files 
   PrintWriter fileNames; // file names sent through the data connection
   BufferedReader readSock; // Used to read data from socket
   boolean quit = false;

   public FTPThread(Socket s) throws IOException
   {
      sock = s;
      fileNames = new PrintWriter(sock.getOutputStream(), true);
      readSock = new BufferedReader( new InputStreamReader
        (sock.getInputStream()));
   }
   
   /**
   Allows you to input messages to the server and get output from the server
   also closes the server then the quit command is typed in 
   */ 
   @Override
   public void run()
   {
      printConnection();
      while( !quitTime )    
      {
         String inLine = null;    
         try 
         {
            inLine = readSock.readLine();
         } 
         catch (IOException ex) 
         {
            Logger.getLogger
            (FTPThread.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         if( inLine.equalsIgnoreCase("SendFile"))
         {
            try {   
               inLine = readSock.readLine();
               sendFile(inLine);            
             } catch (IOException ex) {
                 Logger.getLogger(FTPThread.class.getName())
                         .log(Level.SEVERE, null, ex);
             }
         }
         
         if( inLine.equalsIgnoreCase("quit"))          
         {    
            quitTime = true;
            printConnection();
            try
            {
               printConnection();
               fileNames.println( "Good Bye!" );
               sock.close();
            }
            catch (IOException ex)
            {
               Logger.getLogger
               (FTPThread.class.getName()).log(Level.SEVERE, null, ex);
            }   
         } 
         String outLine =listFiles();
         fileNames.println(outLine);      
      }
      closeSocket();
   }
   
   private String listFiles()
   {
       String split = " ";
       String p = "";
       String file = "";
       String Dir = "ServerFiles";
       File dir = new File(Dir); 
// create a new instance of File class and set the pathname
       File [] files = dir.listFiles(); // get the list of all files 
       Vector v = new Vector();
          
       for(int i = 0; i < files.length; i++)
       {
          file = files[i].getName();
          Collections.addAll(v, file);   
          file += split;
          p += file;
       }     
       return p;      
   }
   
   /**
   Sends a file from the server to the client
   */    
   private void sendFile(String fileName) 
           throws FileNotFoundException, IOException
   {
      OutputStream outputFile = new FileOutputStream("ClientFiles\\" 
              + fileName);
        
      File fileToSend = new File("ServerFiles\\" + fileName);
        
      byte[] mybytearray = new byte[(int) fileToSend.length() + 1];
        
      FileInputStream fis = new FileInputStream(fileToSend);
        
      BufferedInputStream bis = new BufferedInputStream(fis);   
      
      bis.read(mybytearray, 0, mybytearray.length);
      System.out.println("Sending the file...");
      outputFile.write(mybytearray, 0, mybytearray.length);
      outputFile.flush();
      
      System.out.println("File " + fileName);
      System.out.println(fileToSend.length() + " bytes sent");
   }
   
   
   /**
   Receives a file from the server
   */
   private void getFile(String filename)
   {
   
   }
   
   /**
   Prints out to the log file when there's a new connection to the server
   and also prints out when the connection is closed
   */     
   public void printConnection()
   {
      Date date = new Date();
      InetAddress ipAddress = sock.getInetAddress();
      
      if(!quitTime)
      {
         System.out.println("Got a connection: /" + ipAddress.toString() 
                 + " on port# " + sock.getPort());
      }
      else 
         System.out.println("Connection closed.");     
   }
   
   /**
   Closes the socket connection
   */     
   public void closeSocket()
   {
      try 
      {
         sock.close();
      } 
      catch (IOException ex) 
      {
           Logger.getLogger
                  (FTPThread.class.getName()).log(Level.SEVERE, null, ex);
      }      
   }
}
