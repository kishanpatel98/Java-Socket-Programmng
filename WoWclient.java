import java.io.*;
import java.net.*;


public class WoWclient {

  public static void main(String[] args) {
    String hostname ="";
    int port = -1;

    if (args.length != 2) {
      System.out.println("\nInvalid parameters! Please use correct syntax: WoWclient <hostname> <port>");
      return;
    }
    else{
      hostname = args[0];

      try{
      port = Integer.parseInt(args[1]);
      }
      catch(NumberFormatException e){
        System.out.println("\nInvalid port number!");
        return;
      }

      try {
        InetAddress address = InetAddress.getByName(hostname);
        DatagramSocket socket = new DatagramSocket();

        while (true) {
          DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
          socket.send(request);

          byte[] buffer = new byte[512];
          DatagramPacket response = new DatagramPacket(buffer, buffer.length);
          socket.receive(response);

          String quote = new String(buffer, 0, response.getLength());

          System.out.println("\n"+ quote);

          Thread.sleep(5000);
        }
      }
      catch (SocketTimeoutException e) {
        System.out.println("Timeout error: " + e.getMessage());
        e.printStackTrace();
      }
      catch (IOException e) {
        System.out.println("Client error: " + e.getMessage());
        e.printStackTrace();
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
