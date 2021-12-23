import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			String [] str;
			File name;
			String contenu=" ";
			
			ServerSocket server = new ServerSocket(9090);
			Socket socket = server.accept();
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			out.println("Server Listening on Port 9090");
			
			while (true) {
				
				str = input.readLine().toString().split(" ");
					name = new File(str[1]);
					str[0] = str[0].toLowerCase();
					if (name.exists()) {
						if (str[0].equals("list")) {
							if (name.isDirectory()) {
							
							File[] fichiers = name.listFiles();
							contenu = "Ce dossier contient: ";
							for (File file : fichiers) {
								if(file.isFile()) {
									contenu = contenu.concat(file.getName()+" | ");
								}
							}
							out.println(contenu);
							
							}else {
								out.println("C'est un fichier");
							}
						
					} else {
						if (str[0].equals("get")) {
							
							if (name.isFile()) {
								RandomAccessFile file = new RandomAccessFile(name,"r");
								
								while ( file.getFilePointer() < file.length()) {
									contenu = contenu.concat(file.readLine()+" ");
								}
								out.println(contenu);
							}else {
								out.println("C'est un dossier");
							}
							
						} else {
							if (str[0].equals("quit")) {
								System.exit(0);
								input.close();
								out.close();
								socket.close();
							}else {
								out.println("Erreur");
							}
						}
					}
					
				} else {
					out.println("ERROR : File/directory does not exist");
				}
					str = null;
				
			}
			
			} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
