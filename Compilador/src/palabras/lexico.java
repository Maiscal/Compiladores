
package palabras;

import java.io.*;
public class lexico {

public class Main {
    public static void main(String[] args) {
        try {
            // Crea una instancia del analizador léxico
           

            // Lee los tokens uno por uno
            Token token;
            while ((token = lexer.yylex()) != null) {
                // Realiza acciones según el tipo de token
                switch (token) {
                    case NUMBER:
                        System.out.println("Número encontrado: " + lexer.yytext());
                        break;
                    case COMMA:
                        System.out.println("Coma encontrada");
                        break;
                    case STRING:
                        System.out.println("Cadena encontrada: " +lexer.yytext());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}
