
package ejecutar;

import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import palabras.lexico;



public class LeerArchivo {
    
  
    public static void main(String[] args) {
        /*
        String rutaArchivo = "ruta_del_archivo.txt";

        try {
            // Crea una instancia de la clase File
            File archivo = new File(rutaArchivo);

            // Crea una instancia de la clase Scanner para leer el archivo
            Scanner scanner = new Scanner(archivo);

            // Lee el archivo línea por línea
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println(linea);
            }

            // Cierra el Scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no pudo ser encontrado.");
            e.printStackTrace();
        }
    }
}
        */
        
        
         String input = "string adsqwe = 54 + 50;";
        /*Definir patrones de tokens*/ 
        Pattern keywordPattern = Pattern.compile("\\b(int|float|string)\\b");
        Pattern identifierPattern = Pattern.compile("\\b[a-zA-Z][a-zA-Z0-9]*\\b");
        Pattern operatorPattern = Pattern.compile("[=+\\-*/]");

        Matcher matcher = Pattern.compile("\\s+|;").matcher(input);
        int prevEnd = 0;
        
        while (matcher.find()) {
            String token = input.substring(prevEnd, matcher.start());
            // Verificar el tipo de token
            if (keywordPattern.matcher(token).matches()) {
                System.out.println("Palabra clave: " + token);
            } else if (identifierPattern.matcher(token).matches()) {
                System.out.println("Identificador: " + token);
            } else if (operatorPattern.matcher(token).matches()) {
                System.out.println("Operador: " + token);
            } else {
                System.out.println("Token no reconocido: " + token);
            }

            prevEnd = matcher.end();
        }
    }
}
        
        

      

