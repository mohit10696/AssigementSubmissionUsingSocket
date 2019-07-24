import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

  public final static int SOCKET_PORT = 1456;  // you may change this
  public static String FILES_TO_SEND = "H:/server/";  // you may change this

  public static void main (String [] args ) throws IOException {
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
    Socket sock = null;
    String filelist=" ";
    try {
      servsock = new ServerSocket(SOCKET_PORT);
      File folder = new File(FILES_TO_SEND);
      
      String[] files = folder.list();

      for (String file : files)
      {
          //System.out.println(file);
          filelist = filelist+"\n"+file;
      }
      while (true) {
        System.out.println("Waiting...");
        try {
          sock = servsock.accept();
          DataOutputStream output = new DataOutputStream(sock.getOutputStream());
          DataInputStream input = new DataInputStream(sock.getInputStream());
          System.out.println("Accepted connection : " + sock);
          output.writeUTF("Which File You want to Download?\n"+filelist);
          String filename = input.readUTF();
          String temp = FILES_TO_SEND;
          FILES_TO_SEND = FILES_TO_SEND+filename;
          File myFile = new File (FILES_TO_SEND);
          byte [] mybytearray  = new byte [(int)myFile.length()];
          fis = new FileInputStream(myFile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          os = sock.getOutputStream();
          System.out.println("Sending " + filename + "(" + mybytearray.length + " bytes)");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Done.");
          FILES_TO_SEND=temp;
        }
        finally {
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();
        }
      }
    }
    finally {
      if (servsock != null) servsock.close();
    }
  }
}
