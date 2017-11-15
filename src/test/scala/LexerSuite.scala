import org.scalatest.FunSuite

/**
  * Created by nhphung on 4/28/17.
  * Modified by Van Huu Quoc (1512723)
  */
class LexerSuite extends FunSuite with TestLexer {

  test("Lexer 1: Block comment in line") {
    val input = """/* This is a block comment */"""
    val expect = """<EOF>"""
    assert(checkLex(input,expect,1))
  }

  test("Lexer 2: Block comment in many line") {
    val input = """/* This 
    is
    a 
    block 
    comment */"""
    val expect = """<EOF>"""
    assert(checkLex(input,expect,2))
  }

  test("Lexer 3: /* in block comment") {
    val input = """/* This /*is a block */comment"""
    val expect = """comment,<EOF>"""
    assert(checkLex(input,expect,3))
  }  

  test("Lexer 4: */ in block comment") {
    val input = """/* This */is a block */comment"""
    val expect = """is,a,block,*,/,comment,<EOF>"""
    assert(checkLex(input,expect,4))
  }  

  test("Lexer 5: // in block comment") {
    val input = """/* This //is a block */comment"""
    val expect = """comment,<EOF>"""
    assert(checkLex(input,expect,5))
  }  

  test("Lexer 6: Line comment") {
    val input = """//This is a line comment"""
    val expect = """<EOF>"""
    assert(checkLex(input,expect,6))
  }

  test("Lexer 7: /* or */ or // in line comment") {
    val input = """//This is // a /*line*/ comment"""
    val expect = """<EOF>"""
    assert(checkLex(input,expect,7))
  }

  test("Lexer 8: Block and line comment") {
    val input = """/*This is 
    // a line comment*/"""
    val expect = """<EOF>"""
    assert(checkLex(input,expect,8))
  }  

  test("Lexer 9: Not close block comment") {
    val input = """/*This is 
    // a line comment"""
    val expect = """/,*,This,is,<EOF>"""
    assert(checkLex(input,expect,9))
  }

  test("Lexer 10: ErrorToken in Vietnamese comment") {
    val input = """/*Đây là #@$ một dòng bình luận*/"""
    val expect = """<EOF>"""
    assert(checkLex(input,expect,10))
  }

  test("Lexer 11: Simple identifier") {
    val input = """name Name name1 NAME2"""
    val expect = """name,Name,name1,NAME2,<EOF>"""
    assert(checkLex(input,expect,11))
  }

  test("Lexer 12: Identifier with underscore") {
    val input = """_ _name _Name _1 name_1"""
    val expect = """_,_name,_Name,_1,name_1,<EOF>"""
    assert(checkLex(input,expect,12))
  }

  test("Lexer 13: Identifier begin with number") {
    val input = """1Name 24name39"""
    val expect = """1,Name,24,name39,<EOF>"""
    assert(checkLex(input,expect,13))
  }

  test("Lexer 14: Identifier with special charater") {
    val input = """na/me Na-me Na+me Na*me"""
    val expect = """na,/,me,Na,-,me,Na,+,me,Na,*,me,<EOF>"""
    assert(checkLex(input,expect,14))
  }  

  test("Lexer 15: Invalid identifier") {
    val input = """$name #name"""
    val expect = """ErrorToken $"""
    assert(checkLex(input,expect,15))
  }

  test("Lexer 16: Keyword") {
    val input = """boolean break continue else for float if int return void do while true false string"""
    val expect = """boolean,break,continue,else,for,float,if,int,return,void,do,while,true,false,string,<EOF>"""
    assert(checkLex(input,expect,16))
  }

  test("Lexer 17: Declare variable") {
    val input = """ boolean check; 
                    string _name;
                    int age1, age2; 
                    float num;"""
    val expect = """boolean,check,;,string,_name,;,int,age1,,,age2,;,float,num,;,<EOF>"""
    assert(checkLex(input,expect,17))
  }

  test("Lexer 18: Declare function") {
    val input = """ void main(){} 
                    string[] getName(){}"""
    val expect = """void,main,(,),{,},string,[,],getName,(,),{,},<EOF>"""
    assert(checkLex(input,expect,18))
  }

  test("Lexer 19: If statement") {
    val input = """ if(true) x=1; 
                    else x=2;"""
    val expect = """if,(,true,),x,=,1,;,else,x,=,2,;,<EOF>"""
    assert(checkLex(input,expect,19))
  }

  test("Lexer 20: For statement") {
    val input = """ for(int x=1;x<5;x=x+1){
                      print(x);
                    }"""
    val expect = """for,(,int,x,=,1,;,x,<,5,;,x,=,x,+,1,),{,print,(,x,),;,},<EOF>"""
    assert(checkLex(input,expect,20))
  }

  test("Lexer 21: Do while statement") {
    val input = """ x=0;
                    do
                      x=x+1;
                      x=x*2;
                    while(x<100); """
    val expect = """x,=,0,;,do,x,=,x,+,1,;,x,=,x,*,2,;,while,(,x,<,100,),;,<EOF>"""
    assert(checkLex(input,expect,21))
  }

  test("Lexer 22: Return|Break|Continue statement") {
    val input = """ return a;
                    return;
                    break;
                    continue;"""
    val expect = """return,a,;,return,;,break,;,continue,;,<EOF>"""
    assert(checkLex(input,expect,22))
  }

  test("Lexer 23: Valid operator") {
    val input = """+ - ! * / % > < >= <= == != && || ="""
    val expect = """+,-,!,*,/,%,>,<,>=,<=,==,!=,&&,||,=,<EOF>"""
    assert(checkLex(input,expect,23))
  }

  test("Lexer 24: Invalid operator |") {
    val input = """true|false"""
    val expect = """true,ErrorToken |"""
    assert(checkLex(input,expect,24))
  }

  test("Lexer 25: Invalid operator &") {
    val input = """true&false"""
    val expect = """true,ErrorToken &"""
    assert(checkLex(input,expect,25))
  }

  test("Lexer 26: Invalid operator ^") {
    val input = """20^2"""
    val expect = """20,ErrorToken ^"""
    assert(checkLex(input,expect,26))
  }

  test("Lexer 27: Invalid operator >> <<") {
    val input = """20>>2<<3"""
    val expect = """20,>,>,2,<,<,3,<EOF>"""
    assert(checkLex(input,expect,27))
  }

  test("Lexer 28: Expression with math operator") {
    val input = """5%3+a/3-b*2.2E2"""
    val expect = """5,%,3,+,a,/,3,-,b,*,2.2E2,<EOF>"""
    assert(checkLex(input,expect,28))
  }

  test("Lexer 29: Expression with logic operator") {
    val input = """a||b&&c"""
    val expect = """a,||,b,&&,c,<EOF>"""
    assert(checkLex(input,expect,29))
  }

  test("Lexer 30: Expression with compair operator") {
    val input = """a>b<c>=5<=2"""
    val expect = """a,>,b,<,c,>=,5,<=,2,<EOF>"""
    assert(checkLex(input,expect,30))
  }

  test("Lexer 31: Separator") {
    val input = """( ) [ ] { } ; ,"""
    val expect = """(,),[,],{,},;,,,<EOF>"""
    assert(checkLex(input,expect,31))
  }

  test("Lexer 32: Expression with separator") {
    val input = """(5+a)*x[2]-b%2"""
    val expect = """(,5,+,a,),*,x,[,2,],-,b,%,2,<EOF>"""
    assert(checkLex(input,expect,32))
  }

  test("Lexer 33: Expression with separator") {
    val input = """(5+a)*x[2]-b%2"""
    val expect = """(,5,+,a,),*,x,[,2,],-,b,%,2,<EOF>"""
    assert(checkLex(input,expect,33))
  }

  test("Lexer 34: Expression with separator") {
    val input = """(5+a)*x[2]-b%2"""
    val expect = """(,5,+,a,),*,x,[,2,],-,b,%,2,<EOF>"""
    assert(checkLex(input,expect,34))
  }

  test("Lexer 35: Expression with separator") {
    val input = """(a&&b)||(b||c)"""
    val expect = """(,a,&&,b,),||,(,b,||,c,),<EOF>"""
    assert(checkLex(input,expect,35))
  }

  test("Lexer 36: Valid integer literal") {
    val input = """0 00 24 39 123456789 000000"""
    val expect = """0,00,24,39,123456789,000000,<EOF>"""
    assert(checkLex(input,expect,36))
  }

  test("Lexer 37: Not sign in integer literal") {
    val input = """+0 -00 +24 -39"""
    val expect = """+,0,-,00,+,24,-,39,<EOF>"""
    assert(checkLex(input,expect,37))
  }  

  test("Lexer 38: Not HEX in integer literal") {
    val input = """0x24EF39"""
    val expect = """0,x24EF39,<EOF>"""
    assert(checkLex(input,expect,38))
  }  

  test("Lexer 39: Float literal") {
    val input = """123. .123 1.23"""
    val expect = """123.,.123,1.23,<EOF>"""
    assert(checkLex(input,expect,39))
  }

  test("Lexer 40: Float with exponent part") {
    val input = """123.e2 .123E-2 1.23e0"""
    val expect = """123.e2,.123E-2,1.23e0,<EOF>"""
    assert(checkLex(input,expect,40))
  }

  test("Lexer 41: Invalid float + in exponent part") {
    val input = """12.3e+2 1.23E+2"""
    val expect = """12.3,e,+,2,1.23,E,+,2,<EOF>"""
    assert(checkLex(input,expect,41))
  }

  test("Lexer 42: Invalid float no number in exponent part") {
    val input = """12.3e 1.23E"""
    val expect = """12.3,e,1.23,E,<EOF>"""
    assert(checkLex(input,expect,42))
  }
  
  test("Lexer 43: Invalid float only have exponent part") {
    val input = """e2 E-2"""
    val expect = """e2,E,-,2,<EOF>"""
    assert(checkLex(input,expect,43))
  }

  test("Lexer 44: Invalid float no decimal part and fractional part") {
    val input = """.e2 .E-2"""
    val expect = """ErrorToken ."""
    assert(checkLex(input,expect,44))
  }

  test("Lexer 45: Invalid float exponent number is float ") {
    val input = """12.3e1.23 1.23E.123"""
    val expect = """12.3e1,.23,1.23,E,.123,<EOF>"""
    assert(checkLex(input,expect,45))
  }

  test("Lexer 46: Valid boolean literal ") {
    val input = """true false"""
    val expect = """true,false,<EOF>"""
    assert(checkLex(input,expect,46))
  }

  test("Lexer 47: Invalid boolean literal ") {
    val input = """True TRUE False FALSE"""
    val expect = """True,TRUE,False,FALSE,<EOF>"""
    assert(checkLex(input,expect,47))
  }

  test ("Lexer 48: String with number and special charater") { 
    val input  = """ "1234567890~!@#$%^&*()_+{}:;'<>,./?|" """
    val expect = """1234567890~!@#$%^&*()_+{}:;'<>,./?|,<EOF>"""
    assert(checkLex(input,expect,48))
  }

  test ("Lexer 49: Simple string") { 
    val input  = """ "This is a string" """
    val expect = """This is a string,<EOF>"""
    assert(checkLex(input,expect,49))
  }

  test ("Lexer 50: Empty string literal") { 
    val input  = """ "" """
    val expect = """,<EOF>"""
    assert(checkLex(input,expect,50))
  }

  test ("Lexer 51: Block comment in string") { 
    val input  = """ "This /*is a*/ string" """
    val expect = """This /*is a*/ string,<EOF>"""
    assert(checkLex(input,expect,51))
  }

  test ("Lexer 52: Line comment in string") { 
    val input  = """ "This is // a string" """
    val expect = """This is // a string,<EOF>"""
    assert(checkLex(input,expect,52))
  }

  test ("Lexer 53: Many string") { 
    val input  = """ "This is a string" "This is a string"
    "This is a string" """
    val expect = """This is a string,This is a string,This is a string,<EOF>"""
    assert(checkLex(input,expect,53))
  }

  test ("Lexer 54: Many empty string") { 
    val input  = """ "" "" "" """
    val expect = """,,,<EOF>"""
    assert(checkLex(input,expect,54))
  }

  test ("Lexer 55: String in string") { 
    val input  = """ "This "is" a string" """
    val expect = """This ,is, a string,<EOF>"""
    assert(checkLex(input,expect,55))
  }

  test ("Lexer 56: String in string") { 
    val input  = """ "This 'is' a string" """
    val expect = """This 'is' a string,<EOF>"""
    assert(checkLex(input,expect,56))
  }

  test ("Lexer 57: String with escape sequence") { 
    val input  = """ "This is \b \n \r \f \t \\ \" \' a string" """
    val expect = """This is \b \n \r \f \t \\ \" \' a string,<EOF>"""
    assert(checkLex(input,expect,57))
  }

  test ("Lexer 58: Complex string") { 
    val input  = """ "This $is #a \n &string" """
    val expect = """This $is #a \n &string,<EOF>"""
    assert(checkLex(input,expect,58))
  }

  test ("Lexer 59: Unclosed string ") { 
    val input  = """ "This is a string"""
    val expect = """Unclosed string: This is a string"""
    assert(checkLex(input,expect,59))
  }

  test ("Lexer 60: Unclosed string ") { 
    val input  = """ "This is
    a string" """
    val expect = """Unclosed string: This is"""
    assert(checkLex(input,expect,60))
  }

  test ("Lexer 61: Unclosed string ") { 
    val input  = """ "This is a string" "This is
    a string" """
    val expect = """This is a string,Unclosed string: This is"""
    assert(checkLex(input,expect,61))
  }

  test ("Lexer 62: Unclosed string") { 
    val input  = """ """"
    val expect = """Unclosed string: """
    assert(checkLex(input,expect,62))
  }

  test ("Lexer 63: Unclosed string") { 
    val input  = """ " " " """
    val expect = """ ,Unclosed string:  """
    assert(checkLex(input,expect,63))
  }

  test ("Lexer 64: Ilegal escape string") { 
    val input  = """ "This is \ a string" """
    val expect = """Illegal escape in string: This is \ """
    assert(checkLex(input,expect,64))
  } 

  test ("Lexer 65: Ilegal escape string") { 
    val input  = """ "This is \a string " """
    val expect = """Illegal escape in string: This is \a"""
    assert(checkLex(input,expect,65))
  }

  test ("Lexer 66: Ilegal escape string") { 
    val input  = """ "This is \~ a string" """
    val expect = """Illegal escape in string: This is \~"""
    assert(checkLex(input,expect,66))
  }

  test ("Lexer 67: Ilegal escape string") { 
    val input  = """ "This is a \string"""
    val expect = """Illegal escape in string: This is a \s"""
    assert(checkLex(input,expect,67))
  }

  test ("Lexer 68: Unclosed string") { 
    val input  = """ "This is a string\" """
    val expect = """Unclosed string: This is a string\" """
    assert(checkLex(input,expect,68))
  }

  test ("Lexer 69: ErrorToken") { 
    val input  = """ abc#123 """
    val expect = """abc,ErrorToken #"""
    assert(checkLex(input,expect,69))
  }

  test ("Lexer 70: ErrorToken") { 
    val input  = """ abc@gmail.com """
    val expect = """abc,ErrorToken @"""
    assert(checkLex(input,expect,70))
  }

  test ("Lexer 71: ErrorToken") { 
    val input  = """ ~abc """
    val expect = """ErrorToken ~"""
    assert(checkLex(input,expect,71))
  }

  test ("Lexer 72: ErrorToken") { 
    val input  = """abc$123"""
    val expect = """abc,ErrorToken $"""
    assert(checkLex(input,expect,72))
  }

  test ("Lexer 73: ErrorToken") { 
    val input  = """abc^123"""
    val expect = """abc,ErrorToken ^"""
    assert(checkLex(input,expect,73))
  }

  test("Lexer 74: Progarm") {
    val input ="""int main () {
                    //int a;
                    a=a+1;
                  }"""
    val expect ="""int,main,(,),{,a,=,a,+,1,;,},<EOF>"""
    assert(checkLex(input,expect,74))
  }
  test("Lexer 75: Progarm") {
    val input ="""int foo (int x) {
                    /*int x;
                    x=1;*/
                    print(x);
                  }"""
    val expect ="""int,foo,(,int,x,),{,print,(,x,),;,},<EOF>"""
    assert(checkLex(input,expect,57))
  }
  test("Lexer 76: Progarm") {
    val input ="""string[] text () {
                    continue;
                    a=a*b/c%5;
                  }"""
    val expect ="""string,[,],text,(,),{,continue,;,a,=,a,*,b,/,c,%,5,;,},<EOF>"""
    assert(checkLex(input,expect,76))
  }
  test("Lexer 77: Progarm") {
    val input ="""int main () {
                    string s = "This is a string;
                  }"""
    val expect ="""int,main,(,),{,string,s,=,Unclosed string: This is a string;"""
    assert(checkLex(input,expect,77))
  }
  test("Lexer 78: Progarm") {
    val input ="""float main (int a, float b) {
                    string s = "This is a \p string";
                  }"""
    val expect ="""float,main,(,int,a,,,float,b,),{,string,s,=,Illegal escape in string: This is a \p"""
    assert(checkLex(input,expect,78))
  }
  test("Lexer 79: Progarm") {
    val input ="""int int = 5;//error"""
    val expect ="""int,int,=,5,;,<EOF>"""
    assert(checkLex(input,expect,79))
  }
  test("Lexer 80: Progarm") {
    val input ="""/*
                  int main (){
                    int a, b[3];//comment */
                  }"""
    val expect ="""},<EOF>"""
    assert(checkLex(input,expect,80))
  }
  test("Lexer 81: Progarm") {
    val input ="""int main (){
                    int *a = &b;
                  }"""
    val expect ="""int,main,(,),{,int,*,a,=,ErrorToken &"""
    assert(checkLex(input,expect,81))
  }
  test("Lexer 82: Progarm") {
    val input ="""int main (){
                    putInt(&a);
                  }"""
    val expect ="""int,main,(,),{,putInt,(,ErrorToken &"""
    assert(checkLex(input,expect,82))
  }
  
  test("Lexer 83: Operator ++") {
    val input ="""a++"""
    val expect ="""a,+,+,<EOF>"""
    assert(checkLex(input,expect,83))
  }

  test("Lexer 84: Operator --") {
    val input ="""a--b"""
    val expect ="""a,-,-,b,<EOF>"""
    assert(checkLex(input,expect,84))
  }

  test("Lexer 86: OR expression") {
    val input =  """a || b || c || d"""
    val expect = "a,||,b,||,c,||,d,<EOF>"
    assert(checkLex(input,expect,86))
  }
    
  test("Lexer 86: AND expression") {
    val input =  """a && b && c && d"""
    val expect = "a,&&,b,&&,c,&&,d,<EOF>"
    assert(checkLex(input,expect,86))
  }
  test("Lexer 87: Less than operator") {
    val input = """x<y"""
    val expect = """x,<,y,<EOF>"""
    assert(checkLex(input,expect,87))
  }
  test("Lexer 88: Greater than operator"){
    val input = """x>y"""
    val expect = """x,>,y,<EOF>"""
    assert(checkLex(input,expect,88))
  }
  test("Lexer 89: Less than or equal operator"){
    val input = "x<=y"
    val expect = """x,<=,y,<EOF>"""
    assert(checkLex(input,expect,89))
  }
  test("Lexer 90: Greater than or equal operator"){
    val input = "x>=y"
    val expect = """x,>=,y,<EOF>"""
    assert(checkLex(input,expect,90))
  }
    test("Lexer 91: Not equal operator") {
    val input =  """x!=y"""
    val expect = "x,!=,y,<EOF>"
    assert(checkLex(input,expect,91))
  }
  test("Lexer 92: Equal operator") {
    val input = """x==y"""
    val expect = """x,==,y,<EOF>"""
    assert(checkLex(input,expect,92))
  }
  test("Lexer 93: Negative number"){
    val input = """-123 -1.23"""
    val expect = """-,123,-,1.23,<EOF>"""
    assert(checkLex(input,expect,93))
  }
  test("Lexer 94: Initialize int variable"){
    val input = """int age = 20;"""
    val expect = """int,age,=,20,;,<EOF>"""
    assert(checkLex(input,expect,94))
  }
  test("Lexer 95: Initialize float variable"){
    val input = """float height = 1.68"""
    val expect = """float,height,=,1.68,<EOF>"""
    assert(checkLex(input,expect,95))
  }
  test("Lexer 96: Initialize boolean variable") {
    val input =  """boolean check = true;"""
    val expect = """boolean,check,=,true,;,<EOF>"""
    assert(checkLex(input,expect,96))
  }
  test("Lexer 97: Initialize string variable") {
    val input = """string name = "Nam""""
    val expect = """string,name,=,Nam,<EOF>"""
    assert(checkLex(input,expect,97))
  }
  test("Lexer 98: Array declaration"){
    val input = """float arr[10];"""
    val expect = """float,arr,[,10,],;,<EOF>"""
    assert(checkLex(input,expect,98))
  }
  test("Lexer 99: Array index"){
    val input = """a[b[c[1]]] = 1;"""
    val expect = """a,[,b,[,c,[,1,],],],=,1,;,<EOF>"""
    assert(checkLex(input,expect,99))
  }
  test("Lexer 100: Hello World"){
    val input = """int main(){
                      print("Hello World");
                      return 0;}"""
    val expect = """int,main,(,),{,print,(,Hello World,),;,return,0,;,},<EOF>"""
    assert(checkLex(input,expect,100))
  }


}