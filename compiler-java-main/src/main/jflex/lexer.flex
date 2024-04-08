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
Assig = "="
OpenBracket = "("
CloseBracket = ")"
Letter = [a-zA-Z]
Digit = [0-9]
//Agrego tokens
If = "if"
Else = "else"
Begin = "begin"
End = "end"
For = "for"
Write = "write"
Read = "read"
Init = "init"
Float = "Float"
String = "String"
While = "while"
Int = "Int"
Switch = "switch"
Case = "case"
Equal = "=="
Higher = ">"
Lower = "<"
HigherEqual = ">="
LowerEqual = "<="
And = "and"
Or = "or"
Not = "not"
Distinct  = "!="
True_Bool = "true"
False_Bool = "false"
Increment = "=+"
Decrement = "=-"
Semicolon = ";"
Comma = ","
OpenCurlyBracket = "{"
CloseCurlyBracket = "}"
OpenSquareBracket = "["
CloseSquareBracket = "]"

WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Digit}+

%%


/* keywords */

<YYINITIAL> {
  /* identifiers */
  {Identifier}                             { return symbol(ParserSym.IDENTIFIER, yytext()); }
  /* Constants */
  {IntegerConstant}                        { return symbol(ParserSym.INTEGER_CONSTANT, yytext()); }

  /* operators */
  {Plus}                                    { return symbol(ParserSym.PLUS); }
  {Sub}                                     { return symbol(ParserSym.SUB); }
  {Mult}                                    { return symbol(ParserSym.MULT); }
  {Div}                                     { return symbol(ParserSym.DIV); }
  {Assig}                                   { return symbol(ParserSym.ASSIG); }
  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* Debería agregar todos los retornos de cada Token acá? */
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
