import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try {
			String a;
			String serverInput;
			
			Scanner sc = new Scanner(System.in);
			InetAddress serverAddress = InetAddress.getByName("localhost"); 
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
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
