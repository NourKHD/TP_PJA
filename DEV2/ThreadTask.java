import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ThreadTask {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			InetAddress serverAddress = InetAddress.getByName("localhost"); 			
			
			Thread C = new Thread ( new Runnable() {
					public void run() {
						try {
							String a;
							String serverInput;
							
							Socket socket = new Socket(serverAddress, 9090);
							PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
							BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							//System.out.println(input.readLine());
							do {
								
								do {
									serverInput = input.readLine();
									System.out.println(serverInput);
									
								}while(serverInput==null);
								
								a = sc.nextLine().toUpperCase();
								out.println(a);
								//String serverInput = input.readLine();
								//System.out.println(serverInput);
								 
							} while (!a.equals("QUIT"));
							
							input.close();
							out.close();
							socket.close();
							
							
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				C.start();
				
			
			
		}catch (Exception e) {
			System.out.println("Erreur");
	        System.exit(1);	
		}
	
	}

			
}
