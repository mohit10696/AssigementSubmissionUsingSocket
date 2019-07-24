
  
import java.io.*;  
public class BufferedOutputStreamExample{    
public static void main(String args[])throws Exception{    
	 byte[] buffer = new byte[9000000];        
	    try {
	        FileInputStream fis = new FileInputStream("D:/CD/Khichadi.mp3");
	        BufferedInputStream bis = new BufferedInputStream(fis);
	        
	        FileOutputStream fos = new FileOutputStream("D:/testout.mp3");
	        BufferedOutputStream bos = new BufferedOutputStream(fos);

	        int numBytes;
	        int counter=0;
	        bos.write(buffer,0,bis.read(buffer));
//	        while ((numBytes = bis.read(buffer))!= -1)
//	        {	counter++;
//	        	
//	            bos.write(buffer,0,numBytes);
//	        }
//	        //bos.flush();
	        //bos.write("\u001a");
	        System.out.println(counter);
	        System.out.println(" is successfully copied to ");

	        bis.close();
	        bos.close();
	    } catch (IOException e)
	    {
	        e.printStackTrace();
	    }
}}