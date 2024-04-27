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
IntegerConstant = {Digit}+
StringConstant =  \"({Letter}|{IntegerConstant}|" ")*\"
FloatConstant = ({Digit}*{Point}{Digit}+)|({Digit}+{Point}{Digit}*)

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

  {StringConstant}                          { return symbol(ParserSym.STRING_CONSTANT, yytext()); }
  /* identifiers */
  {Identifier}                              { return symbol(ParserSym.IDENTIFIER, yytext()); }
  /* Constants */
  {IntegerConstant}                         { return symbol(ParserSym.INTEGER_CONSTANT, yytext()); }
  {FloatConstant}                           { return symbol(ParserSym.FLOAT_CONSTANT, yytext()); }


  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }


}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
