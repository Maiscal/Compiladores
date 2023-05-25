package lyc.compiler;

import java_cup.runtime.Symbol;
import lyc.compiler.ParserSym;
import lyc.compiler.model.*;
import static lyc.compiler.constants.Constants.*;

%%

%public
%class Lexer
%unicode
%cup
%line
%column
%throws CompilerException
%eofval{
  return symbol(ParserSym.EOF);
%eofval}



        

%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}




Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
Assig = "="

Greater = ">"
/* GreaterEqual = ">=" */
Less = "<"
/* LessEqual = "<=" */
Equal = "=="

And = "&"
Or = "||"

OpenBracket = "("
CloseBracket = ")"
OpenCurlyBracket = "{"
CloseCurlyBracket = "}"
OpenSquareBracket = "["
CloseSquareBracket = "]"
Colon = ":"
Comma = ","
/*  Semicolon = ";"  */


Letter = [a-zA-Z]
Digit = [0-9]


Comment = {OpenComment} (.)+ {CloseComment}

While = while
If = if
Else = else

Iguales = Iguales

Int = Int
Float = Float
String = String

WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Sub}?{Digit}+
FloatConstant = {Digit}+ "." {Digit}+ | {Digit}+ "." | "." {Digit}+
StringConstant = {Quote} (.)+ {Quote}

%%


/* keywords */

<YYINITIAL> {

  /* operators */
  {Assig}                                   { return symbol(ParserSym.ASSIG); }
  {Plus}                                    { return symbol(ParserSym.PLUS); }
  {Sub}                                     { return symbol(ParserSym.SUB); }
  {Mult}                                    { return symbol(ParserSym.MULT); }
  {Div}                                     { return symbol(ParserSym.DIV); }

  {Greater}                                 { return symbol(ParserSym.GREATER); }
  /*{GreaterEqual}                            { return symbol(ParserSym.GREATEREQUAL); }*/
  {Less}                                    { return symbol(ParserSym.LESS); }
  /*{LessEqual}                               { return symbol(ParserSym.LESS,ParserSym.EQUAL);}*/
  {Equal}                                   { return symbol(ParserSym.EQUAL); }

  {And}                                     { return symbol(ParserSym.AND); }
  {Or}                                      { return symbol(ParserSym.OR); }

  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }
  {OpenCurlyBracket}                        { return symbol(ParserSym.OPEN_CURLY_BRACKET); }
  {CloseCurlyBracket}                       { return symbol(ParserSym.CLOSE_CURLY_BRACKET); }
  {OpenSquareBracket}                       { return symbol(ParserSym.OPEN_SQUARE_BRACKET); }
  {CloseSquareBracket}                      { return symbol(ParserSym.CLOSE_SQUARE_BRACKET); }
  {Colon}                                   { return symbol(ParserSym.COLON); }
  {Comma}                                   { return symbol(ParserSym.COMMA); }
  /*{Semicolon}                               { return symbol(ParserSym.SEMICOLON);}*/


  {While}                                   { return symbol(ParserSym.WHILE); }
  {If}                                      { return symbol(ParserSym.IF); }
  {Else}                                    { return symbol(ParserSym.ELSE); }

  {Int}                                     { return symbol(ParserSym.INT); }
  {Float}                                   { return symbol(ParserSym.FLOAT); }
  {String}                                  { return symbol(ParserSym.STRING); }

  /* identifiers */
  {Identifier}                              { if(yylength() > MAX_LENGTH){ throw new InvalidLengthException(yytext()); }
                                                else{return symbol(ParserSym.IDENTIFIER, yytext());}}
  /* Constants */
  {IntegerConstant}                         { long number = Long.parseLong(yytext());
                                                if(number > MAX_INT_16 || number < 0){ throw new InvalidIntegerException(yytext()); }
                                                else{return symbol(ParserSym.INTEGER_CONSTANT, yytext());}}

  {FloatConstant}                           { return symbol(ParserSym.FLOAT_CONSTANT, yytext()); }

  {StringConstant}                          { if(yylength() > MAX_LENGTH){ throw new InvalidLengthException(yytext()); }
                                                else{return symbol(ParserSym.STRING_CONSTANT, yytext());}}


  /* whitespace */
  {WhiteSpace}                              { /* ignore */ }
  {Comment}                                 { /* ignore */ }

}

/* Ignorar espacios en blanco y saltos de línea */
/* <YYINITIAL>[\n\r\t ]+  */

<YYINITIAL>[0-9]+ {
    int number = Integer.parseInt(yytext());
    System.out.println("Número encontrado: " + number);
}
<YYINITIAL>, {
    System.out.println("Coma encontrada");
}

/* error fallback */
[^]                                         { throw new UnknownCharacterException(yytext()); }


