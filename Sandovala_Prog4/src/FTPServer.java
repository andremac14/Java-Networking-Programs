/**
This class creates a new server and runs the server object. It creates a new
server socket and a server thread and runs the server thread from the start 
message
@author Andre Sandoval, Derek Schultz
*/

import java.io.*;
import java.net.*;

public class FTPServer 
{
   public static void main(String[] args) throws IOException
   {
      FTPServer server = new FTPServer();    
      server.run();
   }
   
   /**
   Creates the socket with the correct part number, creates a server thread,
   and creates the log file it also runs the start method to start the server
   thread
   */ 
   public void run() throws IOException
   {
      int portNumber = 5921;    
      ServerSocket servSock = new ServerSocket( portNumber );
      System.out.println("FTP Server running...");
      
      while( true )
      {
         Socket sock = servSock.accept();
         FTPThread servThread = new FTPThread(sock);
         servThread.start();
      }     
   }
}
