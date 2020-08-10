# Secure-communication  ![tag](https://img.shields.io/badge/-Security-blue)
Secure communication between client and server(Java)

The code provided is the communication between a client and server. The below instructions should be followed in order to ensure secure communication through SSL.


## Creating a .jks
In order to apply SSL to a communication between, a .jks file (*keystore and truststore*) has to be created.

### server.jks
 
Open a command prompt and enter the following: 
#### keytool -genkey -alias server -keyalg RSA -keystore server.jks

You will be prompted to enter password. Enter a password(in this case I have used "*password*")and answer the questions prompted.

### client.jks

Open a command prompt and enter the following: 
#### keytool -genkey -alias client -keyalg RSA -keystore client.jks

You will be prompted to enter password. Enter a password(in this case I have used "*password*") and answer the questions prompted.

### Signing certificates
The next step involves getting the server’s self-signed public key certificate and storing it in the client’s KeyStore and getting the client’s self-signed public key and storing it in the server’s KeyStore. Enter the following commands in a command prompt.

#### keytool -export -file server.cert -keystore server.jks -storepass password -alias server
#### keytool -export -file client.cert -keystore client.jks -storepass password -alias client 

### Viewing certificates
To view the contents of the certificates

#### keytool -printcert -v -file server.cert

The certificates will be found along with a .jks file

   ![image](https://user-images.githubusercontent.com/33988886/89716426-85d26200-d9ca-11ea-8d07-7798a0e4fc16.png)
   ![image](https://user-images.githubusercontent.com/33988886/89716438-a4d0f400-d9ca-11ea-9ac8-9c30684e5fc4.png)


###  Import server.cert into client KeyStore and importing client.cert into server KeyStore.

Type the following commands in command prompt.

#### keytool -import -file client.cert -keystore server.jks -storepass password -alias client
#### keytool -import -file server.cert -keystore client.jks -storepass password -alias client


## Execution
The programs must be compiled, and the server must be run first. After that the client should be run. Use command prompt in the location of the files. Add your IP address according.

#### javac chatServer_S.java
#### javac chatClient_S.java

#### java chatServer_S
#### java chatClient_S *IP address*







