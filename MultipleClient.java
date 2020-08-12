import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
  
public class MultipleClient { 
    public static void main(String[] args) throws IOException { 
        try { 
            //Scanner scan = new Scanner(System.in); 
              
            // Getting local IP Address (127.0.0.1)
            InetAddress ip = InetAddress.getByName("localhost"); 
      
            // Establish the connection with Server on port same as the other file
            Socket socket = new Socket(ip,1123);
            // This will trigger the accept() function of the Server
      
            // Receiving input and sending output to Server
//            DataInputStream inputFromServer = new DataInputStream(socket.getInputStream()); 
//            DataOutputStream outputToServer = new DataOutputStream(socket.getOutputStream()); 
            int filesize=1022386; 
            int bytesRead;
            int data = 0;
           
            byte [] bytearray  = new byte [filesize];
            InputStream is = socket.getInputStream();//to read the input from the socket
            FileOutputStream fos = new FileOutputStream("C:\\Users\\X\\Desktop\\Test.txt");//create a new file in the provided path
            BufferedOutputStream bos = new BufferedOutputStream(fos);//storing the data into a stream in order to send it
            bytesRead = is.read(bytearray,0,bytearray.length);//reading the data from bytearray[0] to bytearray[length]
            data = bytesRead;
            // System.out.println("Data:" + data);
     
            do {
               bytesRead =
                  is.read(bytearray, data, (bytearray.length-data));
               if(bytesRead >= 0) data += bytesRead;
            } while(bytesRead > -1);
            System.out.println(data);
            bos.write(bytearray, 0 , data);
            bos.flush();
            bos.close();
          //  socket.close();    
            // Closing resources 
//            scan.close(); 
//            inputFromServer.close(); 
//            outputToServer.close(); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
    } 
} 