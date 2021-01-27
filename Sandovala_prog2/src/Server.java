/**
This class creates a new server and runs the server object. It creates a new
server socket and a server thread and runs the server thread from the start 
message
@author Andre Sandoval
*/

import java.io.*;
import java.net.*;

public class Server 
{
   
    /**
     *
     * @param args
     * @throws IOException
     */
   public static void main(String[] args) throws IOException
   {
      Server server = new Server();    
      server.run();
   }
   
   /**
   Creates the socket with the correct part number, creates a server thread,
   and creates the log file it also runs the start method to start the server
   thread
   */   
   public void run() throws IOException
   {
      int portNumber = 5764;    
      ServerSocket servSock = new ServerSocket( portNumber );
      PrintWriter logFile = new PrintWriter
      (new FileOutputStream("logfile.log"), true );
      
      while( true )
      {
         Socket sock = servSock.accept();
         ServerThread servThread = new ServerThread( sock, logFile );
         servThread.start();
      }  
   }
}
