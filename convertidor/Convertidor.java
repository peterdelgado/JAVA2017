package convertidor;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;




public class Convertidor {

	public static void main(String[] args) {
         
		String line;
        

        try {
            // BufferedReader por donde recibe los datos que 
            // envia el proceso padre.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            
            
            while ((line = br.readLine() ) != "FIN") {
               
                
            	
            	 System.out.println(new StringBuilder(new String(line.toUpperCase())).reverse().toString());
               
                
            }
     
            
           
            System.out.println(line);
           
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
	}

}
