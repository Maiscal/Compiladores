
package ejecutar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class LeerArchivo {
    
  
    public static void main(String[] args) {
        String filePath = "Demo.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                tokenizeLine(line, lineNumber);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tokenizeLine(String line, int lineNumber) {
        String[] tokens = line.split("\\s+");

        for (String token : tokens) {
            switch (token) {
                case "int":
                case "float":
                case "boolean":
                    System.out.println("Palabra clave: " + token + " en la línea " + lineNumber);
                    break;
                case "=":
                case "+":
                case "-":
                case "*":
                case "/":
                    System.out.println("Operador: " + token + " en la línea " + lineNumber);
                    break;
                default:
                    if (token.matches("[a-zA-Z_][a-zA-Z0-9]*")) {
                        System.out.println("Identificador: " + token + " en la línea " + lineNumber);
                    } else if (token.matches("[0-9]+")) {
                        System.out.println("Número: " + token + " en la línea " + lineNumber);
                    } else {
                        System.out.println("Token no reconocido: " + token + " en la línea " + lineNumber);
                    }
            }
        }
    }
}
