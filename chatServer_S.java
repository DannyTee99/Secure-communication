import java.io.*; //import .io and .net libraries
import java.net.*;
import javax.net.ssl.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;


public class chatServer_S
{
  final static String pathToStores ="C:/Users/alexa/Desktop/Source Code/Question 5"; //The Directory of the keystore(change if necessary)
  final static String keyStoreFile = "server.jks"; //File Name
  final static String password = "password";//password of keystore

  
  static boolean debug =false;

  public static void main(String[] args) throws IOException
  {
    String trustFilename = pathToStores +"/"+keyStoreFile;

    System.setProperty("javax.net.ssl.keyStore",trustFilename);
    System.setProperty("javax.net.ssl.keyStorePassword",password);

    if(debug)
    {
      System.setProperty("javax.net.debug","all");
    }
    
    //creating secured sockets
    SSLServerSocketFactory sslssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
    SSLServerSocket serversocket = (SSLServerSocket) sslssf.createServerSocket(3000);

    System.out.println("Ready!");
    SSLSocket socket = (SSLSocket)serversocket.accept( );
    
    //created to accept input from server
    BufferedReader obuf = new BufferedReader(new InputStreamReader(System.in));
	   
    //created to send user input to and receive input from client 
    OutputStream os = socket.getOutputStream(); 
    PrintWriter pw = new PrintWriter(os, true);
 
    //receiving from client
    InputStream inp = socket.getInputStream();
    BufferedReader receiveRead = new BufferedReader(new InputStreamReader(inp));
 
    String receiveMessage;
    String sendMessage;

    while(true)
    {
      if((receiveMessage = receiveRead.readLine()) != null)  
      {
        //if message is received from client, it outputs as the following
        System.out.println("\nClient Message: "+receiveMessage);         
      }
      //accepts input and send input to client         
      sendMessage = obuf.readLine(); 
      pw.println(sendMessage);             
      pw.flush();
    }               
  }                      
}                       
