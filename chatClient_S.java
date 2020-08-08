import java.io.*;   //import .io and .net libraries
import java.net.*;
import javax.net.ssl.*;


public class chatClient_S
{
  //The Directory of the keystore(change accordingly)
  final static String pathToStores ="C:/Users/alexa/Desktop/Source Code/Question 5";  //path to keystore
  final static String trustStoreFile = "client.jks"; //File Name
  final static String password = "password";//password for the truststore

  static boolean debug =false;


  public static void main(String[] args) throws Exception
  {
    String trustFilename = pathToStores +"/"+trustStoreFile;

    System.setProperty("javax.net.ssl.trustStore",trustFilename);
    System.setProperty("javax.net.ssl.trustStorePassword",password);

    if(debug)
    {
      System.setProperty("javax.net.debug","all");
    }

    //accept client input for IP and bind new socket to port 3000
    InetAddress ip = InetAddress.getByName(args[0]);
    SSLSocketFactory sslsf =(SSLSocketFactory)SSLSocketFactory.getDefault();
    SSLSocket socket = (SSLSocket)sslsf.createSocket(ip,3000);

    //created to accept input from client
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
    //created to send user input to and receive input from server
    OutputStream os = socket.getOutputStream(); 
    PrintWriter pw = new PrintWriter(os, true);
    InputStream inp = socket.getInputStream();
    BufferedReader receiveRead = new BufferedReader(new InputStreamReader(inp));
 
    System.out.println("To start chat, type and press Enter key");
    String receiveMessage;
    String sendMessage;               
    
    while(true)
    {
      //accepts input and send input to server
      sendMessage = br.readLine();  
      pw.println(sendMessage);       
      pw.flush(); 

      if((receiveMessage = receiveRead.readLine()) != null) 
      {
        //if message is received from server, it outputs as the following
        System.out.println("\nServer Message: "+receiveMessage); 
      }         
    }               
  }                    
}                        