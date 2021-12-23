import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer {
	public static void clientService(Socket socket) throws IOException, FileNotFoundException {
		String[] str;
		File name;
		String contenu=" ";
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		out.println("Server Listening on Port 9090");
		
		while (true) {
			//mettre la chaine de caractere envoyer par le client dans un tableau 
			str = input.readLine().toString().split(" ");
			str[0] = str[0].toLowerCase();
			// verifier si le premier mot de la cc est quit 
			if (str[0].equals("quit")) {
				// sortir de la boucle 
				break;
			}
			//creer un File avec la 2eme case du tableau qui fait reference à un nom de fichier ou le chemin d'un dossier
			name = new File(str[1]);
				// verifier si le fichier ou le chemin du dossier exists
				if (name.exists()) {
					// verifier si le premier mot de la cc est list 
					if (str[0].equals("list")) {
						// verifier si name est un chemin de dossier 
						if (name.isDirectory()) {
							// mettre les fichiers de ce dossier dans un tableau 
							File[] fichiers = name.listFiles();
							contenu = " ";
							contenu = "Ce dossier contient: ";
								// une boucle pour faire une concatenation des noms de fichiers de ce dossier
								for (File file : fichiers) {
									if(file.isFile()) {
										contenu = contenu.concat(file.getName()+" | ");
									}
								}
							// envoyer la cc obtenue au client 
							out.println(contenu);
							
						
						}else { // si name est un fichier on envoie au client que c'est un fichier 
							
							out.println("C'est un fichier");
						}
						// vider le tableau
						str = null;
						
					
					} else {
						// verifier si le premier mot de la cc est get 
						if (str[0].equals("get")) {
							// verifier si name est un fichier
							if (name.isFile()) {
								// creer un randomaccess pour acceder au fichier 
								RandomAccessFile file = new RandomAccessFile(name,"r");
								contenu = " ";
								// une boucle pour lire le contenu du fichier et le mettre dans une cc 
								while ( file.getFilePointer() < file.length()) {
									contenu = contenu.concat(file.readLine()+" ");
								}
								
								// envoyer la cc obtenue au client 
								out.println(contenu);
								
							}else { // si name est un chemin de dossier on envoie au client que c'est un dossier 
								out.println("C'est un dossier");
							
							}
							// vider le tableau
							str = null;
						
						} else {
							// verifier si le premier mot de la cc est autre chose on envoie au client erreur
							out.println("Erreur");
							str = null;
							
					}
				}
				
			} else {
				// si le fichier ou le chemin du dossier n'existe pas on envoie au client ce message
				out.println("ERROR : File/directory does not exist");
				str = null;
			}
				str = null;
			
		}
		input.close();
		out.close();
		socket.close();
		
	}

	public static void main(String[] args) {
		try {
			
			ServerSocket server = new ServerSocket(9090);
			while (true) {
				Socket socket = server.accept();
				System.out.println("Accepted connection from : "+socket);
				//creer un thread avec une classe anonyme
				Thread S = new Thread ( new Runnable() {
					// on definit la methode run() qui contiendra notre code
					public void run() {
						try {
							// on appelle la methode clientService avec le socket du client 
							clientService(socket);
							
						} catch (IOException e) {
							e.printStackTrace();
							
						}finally { //si le client se déconnecte on affiche ce message
						      System.out.println("Le client s'est déconnecté");
					    }
					}
				});
				// on utilise la methode start() pour executer le thread
				S.start();
				
			}
			
		}catch (Exception e) {
			
		}
	
	}

			
}

