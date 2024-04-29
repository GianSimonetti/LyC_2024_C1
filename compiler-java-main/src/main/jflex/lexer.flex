package lyc.compiler;

import java_cup.runtime.Symbol;
import lyc.compiler.ParserSym;
import lyc.compiler.model.*;
import static lyc.compiler.constants.Constants.*;
import java.math.BigInteger;

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


LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Identation =  [ \t\f]

Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
Assig = ":="
OpenBracket = "("
CloseBracket = ")"
Letter = [a-zA-Z]
Digit = [0-9]
//Agrego tokens
ContarPrimos = "ContarPrimos"
buscoYReemplazo = "buscoYReemplazo"
Si = "si"
Sino = "sino"
Begin = "begin"
End = "end"
For = "for"
Escribir = "escribir"
Leer = "leer"
Init = "init"
Float = "Float"
String = "String"
Mientras = "mientras"
Int = "Int"
Switch = "switch"
Case = "case"
Equal = "=="
Higher = ">"
Lower = "<"
Higher_Equal = ">="
Lower_Equal = "<="
And = "and"
Or = "or"
Not = "not"
Distinct  = "!="
True_Bool = "true"
False_Bool = "false"
Increment = "=+"
Decrement = "=-"
Colon = ":"
Semicolon = ";"
Comma = ","
Point = "."
Open_Curly_Bracket = "{"
Close_Curly_Bracket = "}"
Open_Square_Bracket = "["
Close_Square_Bracket = "]"

WhiteSpace = {LineTerminator} | {Identation}
Comment = "*-" ~ "-*"
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Sub}? {Digit}+ | {Digit}+
StringConstant =  \"({Letter}|{IntegerConstant}|" ")*\"
FloatConstant = {Sub}? ({Digit}+{Point}{Digit}* | {Digit}*{Point}{Digit}+)
%%


/* keywords */

<YYINITIAL> {

  /* operators */
  {Plus}                                    { return symbol(ParserSym.PLUS); }
  {Sub}                                     { return symbol(ParserSym.SUB); }
  {Mult}                                    { return symbol(ParserSym.MULT); }
  {Div}                                     { return symbol(ParserSym.DIV); }
  {Assig}                                   { return symbol(ParserSym.ASSIG); }
  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }
  {Equal}                                   { return symbol(ParserSym.EQUAL); }
  {Higher}                                  { return symbol(ParserSym.HIGHER); }
  {Lower}                                   { return symbol(ParserSym.LOWER); }
  {Higher_Equal}                            { return symbol(ParserSym.HIGHER_EQUAL); }
  {Lower_Equal}                             { return symbol(ParserSym.LOWER_EQUAL); }
  {And}                                     { return symbol(ParserSym.AND); }
  {Or}                                      { return symbol(ParserSym.OR); }
  {Not}                                     { return symbol(ParserSym.NOT); }
  {Distinct}                                { return symbol(ParserSym.DISTINCT); }
  {True_Bool}                               { return symbol(ParserSym.TRUE_BOOL); }
  {False_Bool}                              { return symbol(ParserSym.FALSE_BOOL); }
  {Increment}                               { return symbol(ParserSym.INCREMENT); }
  {Decrement}                               { return symbol(ParserSym.DECREMENT); }
  {Colon}                                   { return symbol(ParserSym.COLON); }
  {Semicolon}                               { return symbol(ParserSym.SEMICOLON); }
  {Comma}                                   { return symbol(ParserSym.COMMA); }
  {Point}                                   { return symbol(ParserSym.POINT); }
  {Open_Curly_Bracket}                      { return symbol(ParserSym.OPEN_CURLY_BRACKET); }
  {Close_Curly_Bracket}                     { return symbol(ParserSym.CLOSE_CURLY_BRACKET); }
  {Open_Square_Bracket}                     { return symbol(ParserSym.OPEN_SQUARE_BRACKET); }
  {Close_Square_Bracket}                    { return symbol(ParserSym.CLOSE_SQUARE_BRACKET); }

  {ContarPrimos}                            { return symbol(ParserSym.CONTAR_PRIMOS); }
  {buscoYReemplazo}                         { return symbol(ParserSym.BUSCO_Y_REEMPLAZO); }
  {Si}                                      { return symbol(ParserSym.SI); }
  {Sino}                                    { return symbol(ParserSym.SINO); }
  {Begin}                                   { return symbol(ParserSym.BEGIN); }
  {End}                                     { return symbol(ParserSym.END); }
  {For}                                     { return symbol(ParserSym.FOR); }
  {Escribir}                                { return symbol(ParserSym.ESCRIBIR); }
  {Leer}                                    { return symbol(ParserSym.LEER); }
  {Init}                                    { return symbol(ParserSym.INIT); }
  {Float}                                   { return symbol(ParserSym.FLOAT); }
  {String}                                  { return symbol(ParserSym.STRING); }
  {Mientras}                                { return symbol(ParserSym.MIENTRAS); }
  {Int}                                     { return symbol(ParserSym.INT); }
  {Float}                                   { return symbol(ParserSym.FLOAT); }
  {String}                                  { return symbol(ParserSym.STRING); }
  {Switch}                                  { return symbol(ParserSym.SWITCH); }
  {Case}                                    { return symbol(ParserSym.CASE); }

  {Comment}	                                { /* ignore */ }

  {StringConstant}                          { 
                                                String stringValue = yytext().substring(1, yytext().length() - 1); // Remueve las comillas
                                                if (stringValue.length() <= STRING_RANGE) {
                                                    return symbol(ParserSym.STRING_CONSTANT, stringValue);
                                                } else {
                                                    throw new InvalidLengthException("La constante de cadena [" + stringValue + "] excede los " + STRING_RANGE + " caracteres permitidos.");
                                                } 
                                            }
  /* identifiers */
  {Identifier}                              { 
                                                String stringValue = new String(yytext());
                                                if (stringValue.length() <= MAX_LENGTH) {
                                                    return symbol(ParserSym.IDENTIFIER, yytext()); 
                                                } else {
                                                    throw new InvalidLengthException("La constante de cadena [" + stringValue + "] excede los " + STRING_RANGE + " caracteres permitidos.");
                                                }
                                            }
  /* Constants */
  {IntegerConstant}                         { 
                                                BigInteger intValue = new BigInteger(yytext());
                                                BigInteger minValue = BigInteger.valueOf(-(1L << (BITS_INT - 1))); // Calcula el valor mínimo permitido
                                                BigInteger maxValue = BigInteger.valueOf((1L << (BITS_INT - 1)) - 1); // Calcula el valor máximo permitido

                                                if (intValue.compareTo(minValue) >= 0 && intValue.compareTo(maxValue) <= 0) {
                                                    return symbol(ParserSym.INTEGER_CONSTANT, yytext());
                                                } else {
                                                    String errorMessage;
                                                    if (intValue.compareTo(minValue) < 0) {
                                                        errorMessage = "La constante [" + yytext() + "] esta por debajo del limite de los enteros.";
                                                    } else {
                                                        errorMessage = "La constante [" + yytext() + "] esta por encima del limite de los enteros.";
                                                    }
                                                    throw new InvalidIntegerException(errorMessage);
                                                } 
                                            }
  {FloatConstant}                           { 
                                                double floatValue = Double.parseDouble(yytext());
                                                long minValue = (long) -Math.pow(2, BITS_FLOAT - 1); // Límite mínimo para flotante
                                                long maxValue = (long) Math.pow(2, BITS_FLOAT - 1) - 1; // Límite máximo para flotante

                                                if (floatValue >= minValue && floatValue <= maxValue) {
                                                    return symbol(ParserSym.FLOAT_CONSTANT, yytext());
                                                } else {
                                                    String errorMessage;
                                                    if (floatValue < minValue) {
                                                        errorMessage = "La constante [" + yytext() + "] está por debajo del límite de los números flotantes.";
                                                    } else {
                                                        errorMessage = "La constante [" + yytext() + "] está por encima del límite de los números flotantes.";
                                                    }
                                                    throw new InvalidFloatException(errorMessage);
                                                } 
                                            }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }


}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
