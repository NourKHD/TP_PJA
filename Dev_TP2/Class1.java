package Dev_TP2;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class Class1 implements Serializable {
	   public String name;
	   public String address;
	   public transient int SSN;
	   public int number;
	   static ArrayList<Class1> tab = new ArrayList<Class1>();
	   
	public Class1(String name, String address, int SSN, int number) {
		this.name = name;
		this.address = address;
		this.SSN = SSN;
		this.number = number;

	}
	public static void ser(String name, String address, int SSN, int number ) {
		try {
			FileOutputStream fileOut = new FileOutputStream("emp.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			Class1 e =null;
			e = new Class1(name,address,SSN,number);
			out.writeObject(e);
		    tab.add(e);
		    System.out.println(e);
		    out.flush();
			out.close();
			fileOut.close();
		}  catch (IOException i) {
			i.printStackTrace();
		}
		
	}
	public static void des() {
		Class1 e = null;
	      
		try
	      {
	    	 RandomAccessFile file = new RandomAccessFile("emp.dat","r");
	    	 FileInputStream fileIn = new FileInputStream("emp.dat");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         
	        	 e = (Class1) in.readObject();
	         
	         
	         System.out.println(file.readLine());
	         RandomAccessFile Rfile = new RandomAccessFile("empdirect.dat","rw");
	         
	         
	         for (int i = 0; i < e.tab.size() ; i++) {
	        	 
	        	 Class1 ex = e.tab.get(i);
	        	 
	        	 Rfile.writeChars("Employee :");
	        	 
	        	 Rfile.writeChars("\n");
	        	 
	        	 int B = ex.number;
	        	 Rfile.writeUTF(String.valueOf(B));
	        	 Rfile.writeChars("\n");
	        	 
	        	 
	        	 
	        	 String N = ex.name;
	        	 Rfile.writeChars(N);
	        	 
	        	 Rfile.writeChars("\n");
	        	 
	        	 
	        	 
	        	 String A = ex.address;
	        	 Rfile.writeChars(A);
	        	 
	        	 Rfile.writeChars("\n");
	        	 
	        	 
	        	 int S = ex.SSN;
	        	 Rfile.write(S);
	        	 Rfile.writeChars("\n");
	        	 
	        	 	        	 
	        	 Rfile.writeChar('/');
	        	 
	        	 
	      	}
	         in.close();
	         file.close();
	         fileIn.close();
	         Rfile.close();
	         
	  
	           
	      }
	      catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }
	      catch(ClassNotFoundException c)
	      {
	         System.out.println("classe Employee non trouvee");
	         c.printStackTrace();
	         return;
	      }
	    }
	public String toString() {
        return "Dev_TP2.Class1{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", SSN=" + SSN +
                ", number=" + number +
                '}';
    }
	
	public static void main(String [] args)
	   {
		
		Scanner a = new Scanner(System.in);
		
		System.out.println("Veuillez entrer le nombre d'objets que vous voulez serialiser ");
		
		int nbr = a.nextInt();
			  
			
			for (int i = 1; i <= nbr; i++) {
			
		    
				System.out.println("Entrer les informations de l'employee");
				
				System.out.println("Nom");
			    String name = a.next();
			    
			    a.nextLine();
			      
			    System.out.println("Adresse");
			    String address = a.nextLine();
			      
			    System.out.println("SSN");
			    int SSN = a.nextInt();
			     
			      
			    System.out.println("Numero");
			    int number = a.nextInt();
			    
			    ser(name,address,SSN,number);
			    
			    
			}
			des();  
	   }
}

