package convertidor;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;



public class Principal {

	public static void main(String[] args) {
		String line;

		 try {
	            // Ejecución proceso hijo (previamente empaquetado en un JAR)
	            Process hijo = new ProcessBuilder("java", "-jar", "C:/Users/pedro/Desktop/convertidor.jar").start();
	            // buffer de recepción de datos del proceso hijo
	            
	          
	           BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
	            // Stream de salida
	            PrintStream ps = new PrintStream(hijo.getOutputStream(), true);
	            // buffer de lectura de consola
	            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	            System.out.println("Ejemplo de comunicacion entre proceso padre e hijo");
	            System.out.println("Envía un mensaje al proceso hijo:");
	            /* Envio */
	            
	            line = in.readLine();

	            while(!line.equals("FIN") && line != null) {

	            	ps.println(line);
	            	ps.flush();
	            
	            	/* Recepcion */
	            	if ((line = br.readLine()) != null){

	               System.out.println(line);
	            	
	            	}
	            
	           
	            
	            line = br.readLine();
	           
	            } 

	        } catch (IOException e) {
	            System.out.println("Error : " + e.getMessage());
	        }
	
	
	
	
	}	

}
