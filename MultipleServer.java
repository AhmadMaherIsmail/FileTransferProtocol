import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

public class MultipleServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(1123); //Any port number works

		// Server keeps on receiving new Clients
		while (true) {
			Socket clientSocket = null;
			try {
				// ServerSocket waits for a Client to connect
				clientSocket = serverSocket.accept();

				System.out.println("A new client is connected : " + clientSocket);

				// Receiving input and sending output to Client
				OutputStream os = null; // convert the file into a stream so
										// that we can send it to the client
				FileInputStream fin = null;// read the file we want to transfer
											// in where this file is located
				BufferedInputStream bin = null;// storing the data into a stream
												// in order to send it
				System.out.println("Assigning new thread for this client");

				// Create a new Thread object for the Client
				Thread thread = new ClientHandler(clientSocket, fin, bin, os);
				thread.start();

			} catch (Exception e) {
				clientSocket.close();
				e.printStackTrace();
			}
		}
	}
}

// ClientHandler class
class ClientHandler extends Thread {
	Socket clientSocket;
	FileInputStream fin;// to read the streams converted
	BufferedInputStream bin;
	OutputStream os;

	// Constructor
	public ClientHandler(Socket clientSocket, FileInputStream fin, BufferedInputStream bin, OutputStream os) {
		this.clientSocket = clientSocket;
		this.fin = fin;
		this.bin = bin;
		this.os = os;
	}

	@Override
	public void run() {
		try {

			File transferFile = new File("C:\\Test.txt");//copy the file test.txt from the C drive as provided
			byte[] bytearray = new byte[(int) transferFile.length()];
			fin = new FileInputStream(transferFile);
			bin = new BufferedInputStream(fin);
			bin.read(bytearray, 0, bytearray.length);
			os = clientSocket.getOutputStream();
			System.out.println("Sending Files...");
			os.write(bytearray, 0, bytearray.length);
			os.flush();
			clientSocket.close();
			System.out.println("File transfer complete");

		} catch (Exception e) {

		}
	}
}
