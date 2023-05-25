
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
        
         String input = "int adsqwe = 54;";
        /*Definir patrones de tokens*/ 
        Pattern keywordPattern = Pattern.compile("\\b(int|float|boolean)\\b");
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
        
        

      

