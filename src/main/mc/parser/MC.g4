/**
 * Student name: Van Huu Quoc
 * Student ID: 1512723
 */
grammar MC;

@lexer::header{
  package mc.parser;
}

@lexer::members{
  @Override
  public Token emit() {
    switch (getType()) {
    case UNCLOSE_STRING:       
      Token result = super.emit();
      // you'll need to define this method
      throw new UncloseString(result.getText());
        
    case ILLEGAL_ESCAPE:
      result = super.emit();
      throw new IllegalEscape(result.getText());
    
    case ERROR_CHAR:
      result = super.emit();
      throw new ErrorToken(result.getText()); 

    default:
      return super.emit();
    }
  }
}

@parser::header{
  package mc.parser;
}

options{
  language=Java;
}

//PARSER

//Program
program: (declaration)*;
declaration: variableDecl | functionDecl;
//Var Declaration
variableDecl: primitiveType variable(COMMA variable)* SEMI;
primitiveType: BOOLEAN | INT | FLOAT | STRING;
variable: ID | ID LSB INTLIT RSB;
//Funtion Declaration
functionDecl: functionType ID LB parameterList RB blockStatement;
functionType: primitiveType | arrayPointerType | VOID;
arrayPointerType: primitiveType LSB RSB;
parameterList: (parameterDecl (COMMA parameterDecl)*)?;
parameterDecl: primitiveType ID (LSB RSB)?;
//Statement
blockStatement: LP declarationPart statementPart RP;
declarationPart: variableDecl*;
statementPart: statement*;
statement : ifStatement 
          | doWhileStatement 
          | forStatement
          | breakStatement
          | continueStatement
          | returnStatement
          | expressionStatement
          | blockStatement
          ;
ifStatement: IF LB expression RB statement (ELSE statement)?;
forStatement: FOR LB expression SEMI expression SEMI expression RB statement;
doWhileStatement: DO statement+ WHILE expression SEMI;
breakStatement: BREAK SEMI;
continueStatement: CONTINUE SEMI;
returnStatement: RETURN expression? SEMI;
expressionStatement: expression SEMI;
//Expression 
expression  : expression1 ASSIGN expression
            | expression1
            ;
expression1 : expression1 OR expression2
            | expression2
            ;
expression2 : expression2 AND expression3
            | expression3
            ;
expression3 : expression4 (EQUAL|NOTEQUAL) expression4
            | expression4
            ;
expression4 : expression5 (LT|LE|GT|GE) expression5
            | expression5
            ;
expression5 : expression5 (ADD|SUB) expression6
            | expression6
            ;
expression6 : expression6 (DIV|MUL|MOD) expression7
            | expression7
            ;
expression7 : (SUB|NOT) expression7
            | expression8
            ;
expression8 : expression9 LSB expression RSB
            | expression9
            ;
expression9 : LB expression RB
            | literal
            | funcall
            | ID
            ;

literal: INTLIT | STRINGLIT | FLOATLIT | BOOLLIT;
funcall: ID LB expressionList RB;
expressionList: (expression(COMMA expression)*)?;

//LEXER

//Integer Literals
INTLIT: [0-9]+;

//Floating Literals
FLOATLIT 
    : INTLIT '.' INTLIT? EP?
    | '.' INTLIT EP?
    | INTLIT EP
    ;
fragment
EP: [eE][-]? INTLIT;

//Boolean Literals
BOOLLIT: TRUE | FALSE;

//String Literals
STRINGLIT: '"' STRINGCHAR* '"'
{
  String s = getText();
  s = s.substring(1,s.length()-1);
  setText(s);
};
fragment
STRINGCHAR: ~["\\\n\r] | ESCAPE;
fragment
ESCAPE: '\\' [btnfr"'\\];

//Keywords
BOOLEAN : 'boolean';
INT     : 'int';
FLOAT   : 'float';
STRING  : 'string';   
VOID    : 'void';
IF      : 'if';
ELSE    : 'else';
FOR     : 'for';
DO      : 'do';
WHILE   : 'while';
BREAK   : 'break';
CONTINUE: 'continue';
RETURN  : 'return';
TRUE    : 'true';
FALSE   : 'false';

//Operators
ADD             : '+';
SUB             : '-';
MUL             : '*';
DIV             : '/';
MOD             : '%';
AND             : '&&';
OR              : '||';
EQUAL           : '==';
NOTEQUAL        : '!=';
LE              : '<=';
GE              : '>=';
NOT             : '!';
LT              : '<';
GT              : '>';
ASSIGN          : '=';

//Separators
LB              : '(';
RB              : ')';
LP              : '{';
RP              : '}';
LSB             : '[';
RSB             : ']';
SEMI            : ';';
COMMA           : ',';

//Identifiers
ID : [_a-zA-Z][_a-zA-Z0-9]*;

//Blackspace
WS: [ \t\r\n]+ -> skip;

//Comments
BLOCK_COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;

//Error
ILLEGAL_ESCAPE: '"' STRINGCHAR* '\\' ~[btnfr"'\\]?
{
  setText(getText().substring(1));
};
UNCLOSE_STRING: '"' STRINGCHAR*
{
  setText(getText().substring(1));
};
ERROR_CHAR: .;