/**
This class allows multiple clients to send and receive messages from the 
server and has method to write to a log file
@author Andre Sandoval
*/

import java.io.*;
import java.net.*;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

class ServerThread extends Thread
{
   Socket sock; // Socket connected to the Client
   PrintWriter writeSock; // Used to write data to socket
   PrintWriter writeLog; // Used to write the log file
   BufferedReader readSock; // Used to read data from socket
   boolean quitTime = false; // server connection status
   
   public ServerThread (Socket s, PrintWriter p) throws IOException 
   {
      sock = s;
      writeLog = p;      
      writeSock = new PrintWriter(sock.getOutputStream(), true);
      readSock = new BufferedReader( new InputStreamReader
      (sock.getInputStream()));
      logfile();
      
   }

   /**
   Allows you to input messages to the server and get output from the server
   also closes the server then the quit command is typed in 
   */     
   @Override
   public void run()
   {
//      while( !quitTime )    
//      {
//         String inLine = null;    
//         try 
//         {
//            inLine = readSock.readLine();
//         } 
//         catch (IOException ex) 
//         {
//            Logger.getLogger
//            (ServerThread.class.getName()).log(Level.SEVERE, null, ex);
//         }   
//         if( inLine.equalsIgnoreCase("quit"))          
//         {    
//            quitTime = true;
//            try
//            {
//               logfile();
//               writeSock.println( "Good Bye!" );
//               sock.close();
//            }
//            catch (IOException ex)
//            {
//               Logger.getLogger
//               (ServerThread.class.getName()).log(Level.SEVERE, null, ex);
//            }   
//         } 
//         PolyAlphabet p = new PolyAlphabet();
//         String outLine =listFiles();// p.encryptMessage(inLine);
//         writeSock.println(outLine);
//      }
//      closeSocket();
         //PolyAlphabet p = new PolyAlphabet();
         String outLine =listFiles();// p.encryptMessage(inLine);
         writeSock.println(outLine);
   }
   
   private static String listFiles()
   {
       String split = "**";
       String p = "";
       String file = "";
       String Dir = "D:/ServerFiles";
       File dir = new File(Dir); // create a new instance of File class and set the pathname
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
                  (ServerThread.class.getName()).log(Level.SEVERE, null, ex);
      }      
   }      
         
   
   /**
   Prints out to the log file when there's a new connection to the server
   and also prints out when the connection is closed
   */     
   public void logfile()
   {
      Date date = new Date();
      InetAddress ipAddress = sock.getInetAddress();
      
      if(!quitTime)
      {
         writeLog.println("Got a connection: " + date.toString() + " Ip: " 
                 + ipAddress.toString() + " Port: " + sock.getPort());
      }
      else 
         writeLog.println("Connection closed. Port: " + sock.getPort());     
   }

}
