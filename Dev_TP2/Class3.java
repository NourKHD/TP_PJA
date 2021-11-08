package Dev_TP2;

import java.io.*;
import java.util.Scanner;

public class Class3 {

	public static void main(String[] args) {
		
		int i;
		char C;
		int j = 0;
		int k = 0;
		
		String str ="";
		String str1 = "";
		String str2 = "";
		
		Scanner a = new Scanner(System.in);
		
		System.out.println("Vous cherchez quel employee ?!");
		
		String N = String.valueOf(a.nextInt());
		try {
			
			FileInputStream fichier = new FileInputStream("empdirect.dat");
			i= fichier.read();
			
			int Ts = str.length();
			
				while (i != -1) {
					C = (char)i;
					
						
						if(C == N.charAt(j)) {
							str = str+ N.charAt(j);
							j++;
							Ts++;
						}
						if (Ts <= 2 && str.equals(N)) {
							break;
						}
						
					
					i = fichier.read();
			}
			
			String arret ="/E";
			int Ta = arret.length();
			int Ts2 = str2.length();
			i = fichier.read();
			
			if (str.equals(N)) {
				
				while(i != -1 ){
					C = (char)i;
					str1 = str1+C;

						
						if(C == arret.charAt(k)) {
							str2 = str2 + arret.charAt(k);
							k++;
							Ts2++;
						}
						if (str2.equals(arret) || Ts2 == Ta) {
							break;
						}
					
					i = fichier.read();
					
			}				
				
				System.out.println(str1);
				
			} else {
				System.out.println("Cet employee n'existe pas");

			}
			
			
		} catch(IOException e)
	      {
	    	  e.printStackTrace();
		      return ;
		  }

	}

}
