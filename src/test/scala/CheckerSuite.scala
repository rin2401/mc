import org.scalatest.FunSuite
import mc.checker._
import mc.utils._

/**
  * Created by nhphung on 4/29/17.
  * Modified by Van Huu Quoc - 1512723
  */
class CheckerSuite extends FunSuite with TestChecker {

// Redeclared Variable

  test("Checker 1: Not Redeclared Variable") {
    val input = """
    int a;
    int b;"""
    val expected = ""
    assert(checkCkr(input,expected,401))
  }

  test("Checker 2: Redeclared Variable a") {
    val input = """
    int a; 
    int b; 
    string a;"""
    val expected = "Redeclared Variable: a"
    assert(checkCkr(input,expected,402))
  }

  test("Checker 3: Redeclared Variable b") {
    val input = """
    int a; 
    int b; 
    string b;"""
    val expected = "Redeclared Variable: b"
    assert(checkCkr(input,expected,403))
  }

  test("Checker 4: Variable Redeclared Function foo") {
    val input = """
    int foo(){
      return 1;
    } 
    int foo;"""
    val expected = "Redeclared Variable: foo"
    assert(checkCkr(input,expected,404))
  }

  test("Checker 5: Variable Redeclared Function foo1") {
    val input = """
    int foo; 
    int foo1(){
      return 1;
    } 
    int foo1;"""
    val expected = "Redeclared Variable: foo1"
    assert(checkCkr(input,expected,405))
  }
  
  test("Checker 6: Variable getInt Redeclared Build In Function getInt") {
    val input = """
    int getInt; 
    int foo(){
      return 1;
    }"""
    val expected = "Redeclared Variable: getInt"
    assert(checkCkr(input,expected,406))
  }

// Redeclared Function

  test("Checker 7: Not Redeclared Function") {
    val input = """
    int foo(){
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,407))
  }
  
  test("Checker 8: Redeclared Function foo") {
    val input = """
    int foo(){
      return 1;
    } 
    float foo(){
      return 1;
    }"""
    val expected = "Redeclared Function: foo"
    assert(checkCkr(input,expected,408))
  }

  test("Checker 9: Function Redeclared Variable foo") {
    val input = """
    int foo;
    int foo(){
      return 1;
    }"""
    val expected = "Redeclared Function: foo"
    assert(checkCkr(input,expected,409))
  }

  test("Checker 10: Function getFloat Redeclared Build In Function getFloat") {
    val input = """
    void getFloat(int a){
      return;
    } 
    int foo(){
      return 1;
    }"""
    val expected = "Redeclared Function: getFloat"
    assert(checkCkr(input,expected,410))
  }

// Redeclared Paramater

  test("Checker 11: Not Redeclared Paramater") {
    val input = """
    int foo(int a){
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,411))
  }

  test("Checker 12: Redeclared Paramater a") {
    val input = """
    int foo(int a, float a){
      return 1;
    }"""
    val expected = "Redeclared Parameter: a"
    assert(checkCkr(input,expected,412))
  }

  test("Checker 13: Redeclared Paramater b") {
    val input = """
    int foo(int a, float b, string b){
      return 1;
    }"""
    val expected = "Redeclared Parameter: b"
    assert(checkCkr(input,expected,413))
  }

// Redeclared Local Variable

  test("Checker 14: Not Redeclared Local Variable") {
    val input = """
    int foo(){
      int a; 
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,414))
  }
  
  test("Checker 15: Redeclared Local Variable a") {
    val input = """
    int foo(){
      float a; 
      int a; 
      return 1;
    }"""
    val expected = "Redeclared Variable: a"
    assert(checkCkr(input,expected,415))
  }

  test("Checker 16: Local Variable Redeclared Paramater a") {
    val input = """
    int foo(int a){
      float a; 
      int b;
      return 1;
    }"""
    val expected = "Redeclared Variable: a"
    assert(checkCkr(input,expected,416))
  }

  test("Checker 17: Redeclared Local Variable in Block Of Block") {
    val input = """
    int main(){
      {
        int a;
        float a;
        return 1;
      }
    }"""
    val expected = "Redeclared Variable: a"
    assert(checkCkr(input,expected,417))
  }

//Hide Declaration
  
  //Variable
  
  test("Checker 18: Parameter a StringType Hide Global Variable a IntType") {
    val input = """
    int a; 
    int foo(string a){
      a = 1;
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("=",Id("a"),IntLiteral(1)).toString
    assert(checkCkr(input,expected,418))
  }

  test("Checker 19: Local Variable a BoolType Hide Global Variable a IntType ") {
    val input = """
    int a;
    int foo(){
      boolean a; 
      a = 1; 
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("=",Id("a"),IntLiteral(1)).toString
    assert(checkCkr(input,expected,419))
  }

  test("Checker 20: Local Variable foo1 IntType Hide Function foo1") {
    val input = """
    void foo1(){
      return;
    } 
    int foo(){
      int foo1;
      foo1();
      return 1;
    }"""
    val expected = "Undeclared Function: foo1"
    assert(checkCkr(input,expected,420))
  }

  //Function

  //Build In Function

  test("Checker 21: Build In Function") {
    val input = """
    int foo(){
      getInt();
      putInt(1);
      putIntLn(2);
      getFloat();
      putFloat(1);
      putFloatLn(1.2);
      putBool(true);
      putBoolLn(false);
      putString("rin2401");
      putStringLn("6un4u");
      putLn();
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,421))
  }
  
// Undeclared Identifier
  
  test("Checker 22: Declared Variable") {
    val input = """
    int a;
    boolean check; 
    int main(){
      a;
      if(check) 1; else 2;
      for(a;true;1){a;}
      do a; while true;
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,422))
  }

  test("Checker 23: Undeclared Variable a In Id Expresstion") {
    val input = """
    int main() {
      a;
      return 1;
    }"""
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input,expected,423))
  }

  test("Checker 24: Undeclared Variable b In BinaryOp Expresstion") {
    val input = """
    int a; 
    int main(){
      a + b;
      return 1;
    }"""
    val expected = "Undeclared Identifier: b"
    assert(checkCkr(input,expected,424))
  }

  test("Checker 25: Undeclared Variable a In UnOp Expresstion") {
    val input = """
    int main(){
      !a;
      return 1;
    }"""
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input,expected,425))
  }

  test("Checker 26: Undeclared Variable a In ArrayCell Expresstion") {
    val input = """
    int main(){
      a[1];
      return 1;
    }"""
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input,expected,426))
  }

  test("Checker 27: Undeclared Paramater a In Function Call Expresstion") {
    val input = """
    int foo(int a){
      return 1;
    } 
    int main(){
      foo(a);
      return 1;
    }"""
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input,expected,427))
  }

  test("Checker 28: Undeclared Variable a In Return Statement") {
    val input = """
    int main(){
      return a;
    }"""
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input,expected,428))
  }

  test("Checker 29: Undeclared Variable a In For Statement") {
    val input = """
    int main(){
      for(a;true;1){} 
      return 1;
    }"""
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input,expected,429))
  }

  test("Checker 30: Undeclared Variable a In If Statement") {
    val input = """
    int main(){
      if(true)a;
      return 1;
    }"""
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input,expected,430))
  }

  test("Checker 31: Undeclared Variable a Do While Statement") {
    val input = """
    int main(){
      do a;
      while true;
      return 1;
    }"""
    val expected = "Undeclared Identifier: a"
    assert(checkCkr(input,expected,431))
  }

// Undeclared Function

  test("Checker 32: Declared Function foo Before and foo1 After") {
    val input = """
    int foo(){
      return 1;
    } 
    int main(){
      foo();
      foo1();
      return 1;
    }
    int foo1(){
      return 1;
    }
    """
    val expected = ""
    assert(checkCkr(input,expected,432))
  }

  test("Checker 33: Undeclared Function foo") {
    val input = """
    int main(){
      foo();
      return 1;
    }"""
    val expected = "Undeclared Function: foo"
    assert(checkCkr(input,expected,433))
  }

//Type Mismatch In Expression
  
  //BinaryOp(+ - * /)
  
  test("Checker 34: Type Match In BinaryOp(+ - * /) Expression") {
    val input = """
    int a;
    float b;
    int main(){
      a+241;
      39-b;
      b*a;
      3.9/24.1;
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,434))
  }

  test("Checker 35: Type Mismatch In BinaryOp(+ - * /) Expression StringLiteral And Any") {
    val input = """
    int main(){
      "1"+2;
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("+",StringLiteral("1"),IntLiteral(2)).toString
    assert(checkCkr(input,expected,435))
  }

  test("Checker 36: Type Match In BinaryOp(+ - * /) Expression BooleanLiteral And Any") {
    val input = """
    int main(){
      true-1.2;
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("-",BooleanLiteral(true),FloatLiteral(1.2f)).toString
    assert(checkCkr(input,expected,436))
  }
  
  //BinaryOp(<><=>=)
  
  test("Checker 37: Type Match In BinaryOp(<><=>=) Expression FloatLiteral And IntLiteral") {
    val input = """
    int a;
    float b;
    int main(){
      a > 241;
      39 >= b;
      b < a;
      3.9 >= 24.1;
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,437))
  }

  test("Checker 38: Type Match In BinaryOp(<><=>=) Expression Variable IntType And FloatLiteral") {
    val input = """
    int a;
    int main(){
      a>=1.2;
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,438))
  }

  //BinaryOp(=)

  test("Checker 39: Type Match In BinaryOp(=) Expression") {
    val input = """
    int a;
    float b;
    string c;
    boolean d;
    int e[3];
    int main(){
      3.9 = 241;
      a = 39;
      b = 24.1;
      b = a = e[1];
      c = "rin2401";
      d = true;
      {
        int b;
        b = 39;
      }
      b = 1+2*1.2-1/8;
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,439))
  }

  test("Checker 40: Type Mismatch In BinaryOp(=) Expression Variable ArrayType And Variable ArrayType") {
    val input = """
    int a[3],b[3];
    int main(){
      a=b;
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("=",Id("a"),Id("b")).toString
    assert(checkCkr(input,expected,440))
  }

  test("Checker 41: Type Mismatch In BinaryOp(=) Expression Variable IntType And FloatLiteral") {
    val input = """
    int a;
    int main(){
      a=1.2;
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("=",Id("a"),FloatLiteral(1.2f)).toString
    assert(checkCkr(input,expected,441))
  }

  test("Checker 42: Type Mismatch In BinaryOp(=) Expression ArrayCell a[1] IntType And StringType") {
    val input = """
    int a[3];
    int main(){
      a[1]="rin2401";
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("=",ArrayCell(Id("a"),IntLiteral(1)),StringLiteral("rin2401")).toString
    assert(checkCkr(input,expected,442))
  }

  //BinaryOp(%)

  test("Checker 43: Type Match In BinaryOp(%) Expression IntLiteral And IntLiteral") {
    val input = """
    int a;
    int main(){
      39%241;
      a%39;
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,443))
  }

  test("Checker 44: Type Mismatch In BinaryOp(%) Expression FloatLiteral And IntLiteral") {
    val input = """
    int main(){
      1.2%5;
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("%",FloatLiteral(1.2f),IntLiteral(5)).toString
    assert(checkCkr(input,expected,444))
  }

  test("Checker 45: Type Mismatch In BinaryOp(%) Expression IntLiteral And StringLiteral") {
    val input = """
    int main(){
      309%"rin2401";
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("%",IntLiteral(309),StringLiteral("rin2401")).toString
    assert(checkCkr(input,expected,445))
  }
  
  test("Checker 46: Type Mismatch In BinaryOp(%) Expression IntLiteral And BooleanLiteral") {
    val input = """
    int main(){
      309%true;
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("%",IntLiteral(309),BooleanLiteral(true)).toString
    assert(checkCkr(input,expected,446))
  }  

  //BinaryOp(&&||)
  
  test("Checker 47: Type Match In BinaryOp(&&||) Expression BooleanLiteral And BooleanLiteral") {
    val input = """
    boolean a;
    int main(){
      a || true;
      true && false;
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,447))
  }

  test("Checker 48: Type Mismatch In BinaryOp(&&||) Expression IntLiteral And BooleanLiteral") {
    val input = """
    int main(){
      309 || true;
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("||",IntLiteral(309),BooleanLiteral(true)).toString
    assert(checkCkr(input,expected,448))
  }

  test("Checker 49: Type Mismatch In BinaryOp(&&||) Expression StringLiteral And BooleanLiteral") {
    val input = """
    int main(){
      "rin2401" && false;
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("&&",StringLiteral("rin2401"),BooleanLiteral(false)).toString
    assert(checkCkr(input,expected,449))
  }

  test("Checker 50: Type Mismatch In BinaryOp(&&||) Expression BooleanLiteral And FloatLiteral") {
    val input = """
    int main(){
      true||1.2;
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("||",BooleanLiteral(true),FloatLiteral(1.2f)).toString
    assert(checkCkr(input,expected,450))
  }
  
  //UnaryOp(-!)

  test("Checker 51: Type Match UnaryOp") {
    val input = """
    int a;
    float b;
    boolean c;
    int main(){
      -241;
      -3.9;
      -a;
      -b;
      !true;
      !false;
      !c;
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,451))
  }

  test("Checker 52: Type Match In UnaryOp - FloatLiteral") {
    val input = """
    int main(){
      -1.2;
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,452))
  }
  
  test("Checker 53: Type Mismatch In UnaryOp - StringLiteral") {
    val input = """
    int main(){
      -"a";
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+UnaryOp("-",StringLiteral("a")).toString
    assert(checkCkr(input,expected,453))
  }
  
  test("Checker 54: Type Mismatch In UnaryOp - BooleanLiteral") {
    val input = """
    int main(){
      -true;
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+UnaryOp("-",BooleanLiteral(true)).toString
    assert(checkCkr(input,expected,454))
  }

  test("Checker 55: Type Mismatch In UnaryOp ! FloatLiteral") {
    val input = """
    int main(){
      !1.2;
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+UnaryOp("!",FloatLiteral(1.2f)).toString
    assert(checkCkr(input,expected,455))
  }

  test("Checker 56: Type Mismatch UnaryOp ! StringLiteral") {
    val input = """
    int main(){
      !"a";
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+UnaryOp("!",StringLiteral("a")).toString
    assert(checkCkr(input,expected,456))
  }
  
  //ArrayCell

  test("Checker 57: Type Match ArrayCell") {
    val input = """
    int[] foo(){
      int a[3];
      return a;
    } 
    int a[3],b[3],c;
    int main(int d[]){
      a[1];
      a[c];
      a[b[1]];
      a[c+1];
      d[1];
      foo()[1];
      return 1;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,457))
  }

  test("Checker 58: Type Mismatch ArrayCell a[1] Variable a IntType") {
    val input = """
    int a;
    int main(){
      a[1];
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+ArrayCell(Id("a"),IntLiteral(1)).toString
    assert(checkCkr(input,expected,458))
  }

  test("Checker 59: Type Mismatch ArrayCell a[i] Index Variable i FloatType") {
    val input = """
    float i;
    int a[3];
    int main(){
      a[i];
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+ArrayCell(Id("a"),Id("i")).toString
    assert(checkCkr(input,expected,459))
  }


  test("Checker 60: Type Mismatch ArrayCell foo()[true] FunCall Index BooleanLiteral") {
    val input = """
    int[] foo(){
      int a[3]; 
      return a;
    } 
    int main(){
      foo()[true];
      return 1;
    }"""
    val expected = "Type Mismatch In Expression: "+ArrayCell(CallExpr(Id("foo"),List()),BooleanLiteral(true)).toString
    assert(checkCkr(input,expected,460))
  }

  //Call Expression

  test("Checker 61: Type Match In Call Expression With Param IntType Arg IntType") {
    val input = """
    void foo(int a, float b, string c, boolean d){
      return;
    }
    void foo1(int a[]){
      int b[2];
      foo1(a);
      foo1(b);
      return;
    } 
    void main(){
      foo(1,1.2,"rin2401",true);
      return;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,461))
  }

  test("Checker 62: Type Mismatch In Call Expression With Param IntType Arg None") {
    val input = """void foo(int a){return;}  void main(){foo();return;}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("foo"),List()).toString
    assert(checkCkr(input,expected,462))
  }

  test("Checker 63: Type Mismatch In Call Expression With Param IntType Arg FloatType") {
    val input = """void foo(int a){return;}  void main(){foo(1.2);return;}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("foo"),List(FloatLiteral(1.2f))).toString
    assert(checkCkr(input,expected,463))
  }

  test("Checker 64: Type Mismatch In Call Expression With Param StringType Arg BooleanType") {
    val input = """void foo(string a){return;}  void main(){foo(true);return;}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("foo"),List(BooleanLiteral(true))).toString
    assert(checkCkr(input,expected,464))
  }

  test("Checker 65: Type Mismatch In Call Expression With Param BooleanType Arg IntType") {
    val input = """void foo(boolean a){return;}  void main(){foo(1);return;}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("foo"),List(IntLiteral(1))).toString
    assert(checkCkr(input,expected,465))
  }

  test("Checker 66: Type Mismatch In Call Expression With Param ArrayType FloatType - ArrayPoiterType IntType") {
    val input = """void foo(int a[]){return;}  void main(){float b[3]; foo(b);return;}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("foo"),List(Id("b"))).toString
    assert(checkCkr(input,expected,466))
  }

  test("Checker 67: Type Mismatch In Call Expression With Param ArrayPoiterType IntType - ArrayPoiterType FloatType  ") {
    val input = """void foo(float a[]){return;}  void main(int b[]){foo(b);return;}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("foo"),List(Id("b"))).toString    
    assert(checkCkr(input,expected,467))
  }

//Type Mismatch In Statement
  //If Statement

  test("Checker 68: Type Match In If Statement") {
    val input = """int main(){if(true) 1;return 1;}"""
    val expected = ""
    assert(checkCkr(input,expected,468))
  }

  test("Checker 69: Type Mismatch In If Statement Expresstion") {
    val input = """int main(){if(1) 1;return 1;}"""
    val expected = "Type Mismatch In Statement: "+If(IntLiteral(1),IntLiteral(1),None).toString
    assert(checkCkr(input,expected,469))
  }

  //For Statement

  test("Checker 70: Type Match In For Statement") {
    val input = """int main(){for(1;true;1)1; return 1;}"""
    val expected = ""
    assert(checkCkr(input,expected,470))
  }

  test("Checker 71: Type Mismatch In For Statement Expresstion 1") {
    val input = """int main(){for("a";true;1)1;return 1;}"""
    val expected = "Type Mismatch In Statement: "+For(StringLiteral("a"),BooleanLiteral(true),IntLiteral(1),IntLiteral(1)).toString
    assert(checkCkr(input,expected,471))
  }

  test("Checker 72: Type Mismatch In For Statement Expresstion 2") {
    val input = """int main(){for(1;1;1)1;return 1;}"""
    val expected = "Type Mismatch In Statement: "+For(IntLiteral(1),IntLiteral(1),IntLiteral(1),IntLiteral(1)).toString
    assert(checkCkr(input,expected,472))
  }

  test("Checker 73: Type Mismatch In For Statement Expresstion 3") {
    val input = """int main(){for(1;false;1.2)1;return 1;}"""
    val expected = "Type Mismatch In Statement: "+For(IntLiteral(1),BooleanLiteral(false),FloatLiteral(1.2f),IntLiteral(1)).toString
    assert(checkCkr(input,expected,473))
  }

  //Dowhile Statement

  test("Checker 74: Type Match In Dowhile Statement") {
    val input = """int main(){do 1; while true;return 1;}"""
    val expected = ""
    assert(checkCkr(input,expected,474))
  }

  test("Checker 75: Type Mismatch In Dowhile Statement") {
    val input = """int main(){do 1; while 1;return 1;}"""
    val expected = "Type Mismatch In Statement: "+Dowhile(List(IntLiteral(1)),IntLiteral(1)).toString
    assert(checkCkr(input,expected,475))
  }

  //Return Statement
  
  test("Checker 76: Type Match Return Statement VoidType Exp None") {
    val input = """
    void main(){
      return;
    }
    int foo(){
      return 1;
    }
    string foo1(string a){
      return a;
    }
    boolean[] foo2(){
      boolean a[3];
      return a;
    }
    float[] foo3(float a[]){
      {
        return a;
      }
    }
    """
    val expected = ""
    assert(checkCkr(input,expected,476))
  }

  test("Checker 77: Type Mismatch Return Statement ArrayPointerType FloatType Exp ArrayPointerType IntType") {
    val input = """float[] main(int a[]){ return a;}"""
    val expected = "Type Mismatch In Statement: "+Return(Some(Id("a"))).toString
    assert(checkCkr(input,expected,477))
  }

  test("Checker 78: Type Mismatch Return Statement VoidType Exp CallExpr VoidType") {
    val input = """void main(){return main();}"""
    val expected = "Type Mismatch In Statement: "+Return(Some(CallExpr(Id("main"),List()))).toString
    assert(checkCkr(input,expected,478))
  }

  test("Checker 79: Type Mismatch Return Statement IntType Exp None") {
    val input = """int main(){return;}"""
    val expected = "Type Mismatch In Statement: "+Return(None).toString
    assert(checkCkr(input,expected,479))
  }

//Function Not Return

  //Only Return Statement

  test("Checker 80: Function Not Return") {
    val input = """int main(){}"""
    val expected = "Function Not Return: main"
    assert(checkCkr(input,expected,480))
  }

  // Return Statement In If Statement

  test("Checker 81: Function Return In If Then Statement") {
    val input = """int main(){if(true) return 1; return 1;}"""
    val expected = ""
    assert(checkCkr(input,expected,481))
  }

  test("Checker 82: Function Return In If Statement") {
    val input = """
    int main(){
      if(true) return 1; 
      else return 1;
    }    
    """
    val expected = ""
    assert(checkCkr(input,expected,482))
  }
  
  test("Checker 83: Function Not Return In If Then Statement") {
    val input = """
    int main(){
      if(true) return 1;
    }"""
    val expected = "Function Not Return: main"
    assert(checkCkr(input,expected,483))
  }
  
  test("Checker 84: Function Not Return In If Else Statement") {
    val input = """
    int main(){
      if(true) 1; 
      else return 1;
    }"""
    val expected = "Function Not Return: main"
    assert(checkCkr(input,expected,484))
  }
  
  test("Checker 85: Function Not Return In If Then Else Statement") {
    val input = """
    int main(){
      if(true) 1; 
      else 1;
    }"""
    val expected = "Function Not Return: main"
    assert(checkCkr(input,expected,485))
  }

  // Return Statement In For Statement

  test("Checker 86: Function Return Statement In For Statement") {
    val input = """
    int main(){
      for(1;true;1) return 1;
    }"""
    val expected = "Function Not Return: main"
    assert(checkCkr(input,expected,486))
  }

  test("Checker 87: Function Not Return Statement In For Statement") {
    val input = """
    int main(){
      for(1;true;1) 1;
    }"""
    val expected = "Function Not Return: main"
    assert(checkCkr(input,expected,487))
  }

  test("Checker 88: Function Not Return Statement In For Statement Inner If Then Statement") {
    val input = """
    int main(){
      if(true) for(1;true;1) 1; 
      else return 1;
    }"""
    val expected = "Function Not Return: main"
    assert(checkCkr(input,expected,488))
  }

//Break Not In Loop

  test("Checker 89: Break In For Statement") {
    val input = """void main(){for(1;true;1) break; return;}"""
    val expected = ""
    assert(checkCkr(input,expected,489))
  }

  test("Checker 90: Break Not In For Statement") {
    val input = """
    void main(){
      for(1;true;1) 1; 
      break; 
      return;
    }"""
    val expected = "Break Not In Loop"
    assert(checkCkr(input,expected,490))
  }

//Continue Not In Loop

  test("Checker 91: Continie In For Statement") {
    val input = """
    void main(){
      for(1;true;1) continue; 
      return;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,491))
  }

  test("Checker 92: Continue Not In For Statement") {
    val input = """
    void main(){
      for(1;true;1) 1; 
      continue; 
      return;
    }"""
    val expected = "Continue Not In Loop"
    assert(checkCkr(input,expected,492))
  }

//Unreachable Statement

  test("Checker 93: Unreachable Statement After Return Statement") {
    val input = """
    void main(){
      return; 
      1;
    }"""
    val expected = "Unreachable Statement: "+IntLiteral(1).toString
    assert(checkCkr(input,expected,493))
  }

  test("Checker 94: Unreachable Statement After Break Statement") {
    val input = """
    void main(){
      for(1;true;1){
        break; 
        continue; 
        2; 
      } 
      return;
    }"""
    val expected = "Unreachable Statement: "+Continue.toString
    assert(checkCkr(input,expected,494))
  }

  test("Checker 95: Unreachable Statement After Continue In Dowhile Statement") {
    val input = """
    void main(){
      do
        {
          continue;
          1;
        }
        2;
      while(true); 
    }"""
    val expected = "Unreachable Statement: "+IntLiteral(1).toString
    assert(checkCkr(input,expected,495))
  }

  test("Checker 96: Unreachable Statement After Block Contain Continue In Dowhile Statement") {
    val input = """
    void main(){
      do
        {continue;}
        {1;}
      while(true); 
      return;
    }"""
    val expected = "Unreachable Statement: "+Block(List(),List(IntLiteral(1))).toString
    assert(checkCkr(input,expected,496))
  }

  test("Checker 97: Reachable Statement After If Statement Not Full Break/Continue/Return In For Statement") {
    val input = """
    void main(){
      for(1;true;1){
        if(true) break; else 1;
        if(true) continue; else 1; 
        if(true) return; else 1; 
        "rin2401";
      } 
      return;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,497))
  }


  test("Checker 98: Reachable Statement After For Statement Contain Continue And Break") {
    val input = """
    void main(){
      for(1;true;1){
        1;
        if(true) continue;
        else break;
      } 
      "rin2401"; 
      return;}"""
    val expected = ""
    assert(checkCkr(input,expected,498))
  }

  test("Checker 99: Unreachable Statement After If Statement Contain Return And Continue") {
    val input = """
    void main(){
      do
        if(true) return;
        else continue;
        "rin2401";  
      while(true); 
      return;}"""
    val expected = "Unreachable Statement: "+StringLiteral("rin2401").toString
    assert(checkCkr(input,expected,499))
  }

  test("Checker 100: Unreachable Statement After Return In If Else Statement") {
    val input = """
    void main(){
      int i; 
      if(i==5) return; 
      else return; 
      i=i+1;
    }"""
    val expected = "Unreachable Statement: "+BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))).toString
    assert(checkCkr(input,expected,500))
  }

}