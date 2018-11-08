import org.scalatest.FunSuite
import mc.utils._

/**
  * Created by nhphung on 4/29/17.
  *Modified by Van Huu Quoc 1512723
  */

class AstSuite extends FunSuite with TestAst {

  test("AST 1: declare a variable type int") {
    val input = """int a;"""
    val expected = Program(List(VarDecl(Id("a"),IntType)))
    assert(checkAst(input,expected,201))
  }

  test("AST 2: declare a variable type float") {
    val input = """float a;"""
    val expected = Program(List(VarDecl(Id("a"),FloatType)))
    assert(checkAst(input,expected,202))
  }

  test("AST 3: declare a array 3 elements type string") {
    val input = """string a[3];"""
    val expected = Program(List(VarDecl(Id("a"),ArrayType(IntLiteral(3),StringType))))
    assert(checkAst(input,expected,203))
  }

  test("AST 4: declare 2 variable type boolean inline") {
    val input = """boolean a,b;"""
    val expected = Program(List(VarDecl(Id("a"),BoolType),VarDecl(Id("b"),BoolType)))
    assert(checkAst(input,expected,204))
  }

  test("AST 5: declare a variable and a array type int inline") {
    val input = """int a,b[3];"""
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),ArrayType(IntLiteral(3),IntType))))
    assert(checkAst(input,expected,205))
  }

  test("AST 6: declare 2 variable in multiple line") {
    val input = """int a;float b;"""
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),FloatType)))
    assert(checkAst(input,expected,206))
  }

  test("AST 7: declare variable and array in multiple line") {
    val input = """string a;boolean b[3];"""
    val expected = Program(List(VarDecl(Id("a"),StringType),VarDecl(Id("b"),ArrayType(IntLiteral(3),BoolType))))
    assert(checkAst(input,expected,207))
  }

  test("AST 8: declare function no paramater no body") {
    val input = """int main(){}"""
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,208))
  }

  test("AST 9: declare function 1 paramater no body") {
    val input = """float main(string a){}"""
    val expected = Program(List(FuncDecl(Id("main"),List(VarDecl(Id("a"),StringType)),FloatType,Block(List(),List()))))
    assert(checkAst(input,expected,209))
  }

  test("AST 10: declare function with 1 paramater no body") {
    val input = """string main(int c[]){}"""
    val expected = Program(List(FuncDecl(Id("main"),List(VarDecl(Id("c"),ArrayPointerType(IntType))),StringType,Block(List(),List()))))
    assert(checkAst(input,expected,210))
  }

  test("AST 11: declare function with many paramater no body") {
    val input = """boolean main(string a, boolean b){}"""
    val expected = Program(List(FuncDecl(Id("main"),List(VarDecl(Id("a"),StringType),VarDecl(Id("b"),BoolType)),BoolType,Block(List(),List()))))
    assert(checkAst(input,expected,211))
  }

  test("AST 12: declare function with many paramater no body") {
    val input = """string main(int a, boolean b[]){}"""
    val expected = Program(List(FuncDecl(Id("main"),List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),ArrayPointerType(BoolType))),StringType,Block(List(),List()))))
    assert(checkAst(input,expected,212))
  }

  test("AST 13: declare function return array pointer type int") {
    val input = """int[] main(int a){}"""
    val expected = Program(List(FuncDecl(Id("main"),List(VarDecl(Id("a"),IntType)),ArrayPointerType(IntType),Block(List(),List()))))
    assert(checkAst(input,expected,213))
  }

  test("AST 14: declare function return array pointer type float") {
    val input = """float[] main(string a[]){}"""
    val expected = Program(List(FuncDecl(Id("main"),List(VarDecl(Id("a"),ArrayPointerType(StringType))),ArrayPointerType(FloatType),Block(List(),List()))))
    assert(checkAst(input,expected,214))
  }

  test("AST 15: declare variable and function") {
    val input = """
    int a;
    float main(){}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),FuncDecl(Id("main"),List(),FloatType,Block(List(),List()))))
    assert(checkAst(input,expected,215))
  }

  test("AST 16: declare many variable and function") {
    val input = """
    int a,b[3];
    float main(){}
    boolean c;
    string[] foo(int d){}
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),ArrayType(IntLiteral(3),IntType)),FuncDecl(Id("main"),List(),FloatType,Block(List(),List())),VarDecl(Id("c"),BoolType),FuncDecl(Id("foo"),List(VarDecl(Id("d"),IntType)),ArrayPointerType(StringType),Block(List(),List()))))
    assert(checkAst(input,expected,216))
  }

  test("AST 17: return statement no expression") {
    val input = """int main(){return;}"""
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(Return(None))))))
    assert(checkAst(input,expected,217))
  }

  test("AST 18: return statement with expression") {
    val input = """int main(){return true;}"""
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(Return(Some(BooleanLiteral(true))))))))
    assert(checkAst(input,expected,218))
  }

  test("AST 19: break statement") {
    val input = """int main(){break;}"""
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(Break)))))
    assert(checkAst(input,expected,219))
  }

  test("AST 20: continue statement") {
    val input = """int main(){continue;}"""
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(Continue)))))
    assert(checkAst(input,expected,220))
  }

  test("AST 21: simple if statement") {
    val input = """
    int main(){
      if(true) return; 
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(If(BooleanLiteral(true),Return(None),None))))))
    assert(checkAst(input,expected,221))
  }

  test("AST 22: simple if else statement") {
    val input = """
    int main(){
      if(false) break;
      else return;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(If(BooleanLiteral(false),Break,Some(Return(None))))))))
    assert(checkAst(input,expected,222))
  }

  test("AST 23: assign operator") {
    val input = """
    int main(){
      a = b;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("=",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,223))
  }

  test("AST 24: math operator") {
    val input = """
    int main(){
      a + b;
      a - b;
      a * b;
      a / b;
      a % b;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("+",Id("a"),Id("b")),BinaryOp("-",Id("a"),Id("b")),BinaryOp("*",Id("a"),Id("b")),BinaryOp("/",Id("a"),Id("b")),BinaryOp("%",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,224))
  }

  test("AST 25: multiple math operator") {
    val input = """
    int main(){
      a%1 - 1.2*a + a/3;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("+",BinaryOp("-",BinaryOp("%",Id("a"),IntLiteral(1)),BinaryOp("*",FloatLiteral(1.2f),Id("a"))),BinaryOp("/",Id("a"),IntLiteral(3))))))))
    assert(checkAst(input,expected,225))
  }

  test("AST 26: AND OR operator") {
    val input = """
    int main(){
      a||b;
      a&&b;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("||",Id("a"),Id("b")),BinaryOp("&&",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,226))
  }

  test("AST 27: equal operator") {
    val input = """
    int main(){
      a != 3;
      a == 1.2;
      a == true;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("!=",Id("a"),IntLiteral(3)),BinaryOp("==",Id("a"),FloatLiteral(1.2f)),BinaryOp("==",Id("a"),BooleanLiteral(true)))))))
    assert(checkAst(input,expected,227))
  }

  test("AST 28: compare operator") {
    val input = """
    int main(){
      a > 1;
      a < 1.2;
      a >= 1.2e3;
      a <= 2;
     }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp(">",Id("a"),IntLiteral(1)),BinaryOp("<",Id("a"),FloatLiteral(1.2f)),BinaryOp(">=",Id("a"),FloatLiteral(1200.0f)),BinaryOp("<=",Id("a"),IntLiteral(2)))))))
    assert(checkAst(input,expected,228))
  }

  test("AST 29: unary operator") {
    val input = """
    int main(){
      !a;
      !1;
      -2;
      -1.2e-3;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(UnaryOp("!",Id("a")),UnaryOp("!",IntLiteral(1)),UnaryOp("-",IntLiteral(2)),UnaryOp("-",FloatLiteral(0.0012f)))))))
    assert(checkAst(input,expected,229))
  }

  test("AST 30: multiple unary operator") {
    val input = """
    int main(){
      !!a;
      -!1;
      --1.2;
      ---false;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(UnaryOp("!",UnaryOp("!",Id("a"))),UnaryOp("-",UnaryOp("!",IntLiteral(1))),UnaryOp("-",UnaryOp("-",FloatLiteral(1.2f))),UnaryOp("-",UnaryOp("-",UnaryOp("-",BooleanLiteral(false)))))))))
    assert(checkAst(input,expected,230))
  }

  test("AST 31: multiple assign operator") {
    val input = """
    int main(){
      a=1="string"=1.2e3;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("=",Id("a"),BinaryOp("=",IntLiteral(1),BinaryOp("=",StringLiteral("string"),FloatLiteral(1200.0f)))))))))
    assert(checkAst(input,expected,231))
  }

  test("AST 32: nested if else statement inline") {
    val input = """
    void func(){
      if(a>1) if(a>2) a=1; else a=2;    
    }
    """
    val expected = Program(List(FuncDecl(Id("func"),List(),VoidType,Block(List(),List(If(BinaryOp(">",Id("a"),IntLiteral(1)),If(BinaryOp(">",Id("a"),IntLiteral(2)),BinaryOp("=",Id("a"),IntLiteral(1)),Some(BinaryOp("=",Id("a"),IntLiteral(2)))),None))))))
    assert(checkAst(input,expected,232))
  }

  test("AST 33: if else statement with block statement") {
    val input = """
    int main(){
      if(exp1){}
      else{}
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(If(Id("exp1"),Block(List(),List()),Some(Block(List(),List()))))))))
    assert(checkAst(input,expected,233))
  }

  test("AST 34: nested if else statement with block statement") {
    val input = """
    int main(){
      if(a>1){
        if(a>2) a=1;
      }
      else a=2;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(If(BinaryOp(">",Id("a"),IntLiteral(1)),Block(List(),List(If(BinaryOp(">",Id("a"),IntLiteral(2)),BinaryOp("=",Id("a"),IntLiteral(1)),None))),Some(BinaryOp("=",Id("a"),IntLiteral(2)))))))))
    assert(checkAst(input,expected,234))
  }

  test("AST 35: for statement") {
    val input = """
    int main(){
      for(i=1;i<10;i=i+1) print(i);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("<",Id("i"),IntLiteral(10)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),CallExpr(Id("print"),List(Id("i")))))))))
    assert(checkAst(input,expected,235))
  }

  test("AST 36: nested for statement") {
    val input = """
    int main(){
      for(i=1;i<10;i=i+1)
        for(j=1;j<10;j=j+1)
          print(i+j);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("<",Id("i"),IntLiteral(10)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),For(BinaryOp("=",Id("j"),IntLiteral(1)),BinaryOp("<",Id("j"),IntLiteral(10)),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),CallExpr(Id("print"),List(BinaryOp("+",Id("i"),Id("j")))))))))))
    assert(checkAst(input,expected,236))
  }

  test("AST 37: nested for statement with block statement") {
    val input = """
    int main(){
      for(i=1;i<10;i=i+1){
        for(j=1;j<10;j=j+1){
          print(i);
          print(j);
        }
      }
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("<",Id("i"),IntLiteral(10)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(For(BinaryOp("=",Id("j"),IntLiteral(1)),BinaryOp("<",Id("j"),IntLiteral(10)),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),Block(List(),List(CallExpr(Id("print"),List(Id("i"))),CallExpr(Id("print"),List(Id("j"))))))))))))))
    assert(checkAst(input,expected,237))
  }

  test("AST 38: do while statement") {
    val input = """
    int main(){
      do
        a=a+1;
        a=a+2;
      while(a<10);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(Dowhile(List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(1))),BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(2)))),BinaryOp("<",Id("a"),IntLiteral(10))))))))
    assert(checkAst(input,expected,238))
  }

  test("AST 39: do while with many statement") {
    val input = """
    int main(){
      do
        a=a+1;
        a=a+2;
      while(a<10);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(Dowhile(List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(1))),BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(2)))),BinaryOp("<",Id("a"),IntLiteral(10))))))))
    assert(checkAst(input,expected,239))
  }

  test("AST 40: do while with block statement") {
    val input = """
    int main(){
      do{
        a=a+1;
        a=a+2;
      }
      while(a<10);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(Dowhile(List(Block(List(),List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(1))),BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(2)))))),BinaryOp("<",Id("a"),IntLiteral(10))))))))
    assert(checkAst(input,expected,240))
  }

  test("AST 41: do while with many block statement") {
    val input = """
    int main(){
      do{
        a=a+1;
      }{
        a=a+2;
      }
      while(a<10);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(Dowhile(List(Block(List(),List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(1))))),Block(List(),List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(2)))))),BinaryOp("<",Id("a"),IntLiteral(10))))))))
    assert(checkAst(input,expected,241))
  }

  test("AST 42: declare variable in block statement") {
    val input = """
    int main(){
      int a;
      float b[3];
      string c,d[3];
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),ArrayType(IntLiteral(3),FloatType)),VarDecl(Id("c"),StringType),VarDecl(Id("d"),ArrayType(IntLiteral(3),StringType))),List()))))
    assert(checkAst(input,expected,242))
  }

  test("AST 43: declare and intital variable in block statement") {
    val input = """
    int main(){
      int a;
      a=1;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),IntType)),List(BinaryOp("=",Id("a"),IntLiteral(1)))))))
    assert(checkAst(input,expected,243))
  }

  test("AST 44: declare and if statement") {
    val input = """
    int main(){
      int a;
      a=1;
      if(a>0) println("a la so nguyen duong");
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),IntType)),List(BinaryOp("=",Id("a"),IntLiteral(1)),If(BinaryOp(">",Id("a"),IntLiteral(0)),CallExpr(Id("println"),List(StringLiteral("a la so nguyen duong"))),None))))))
    assert(checkAst(input,expected,244))
  }

  test("AST 45: factory function") {
    val input = """
    int fact(int n){
      if(n<=0) return 1;
      else return n*fact(n-1);
    }
    """
    val expected = Program(List(FuncDecl(Id("fact"),List(VarDecl(Id("n"),IntType)),IntType,Block(List(),List(If(BinaryOp("<=",Id("n"),IntLiteral(0)),Return(Some(IntLiteral(1))),Some(Return(Some(BinaryOp("*",Id("n"),CallExpr(Id("fact"),List(BinaryOp("-",Id("n"),IntLiteral(1))))))))))))))
    assert(checkAst(input,expected,245))
  }

  test("AST 46: sum function") {
    val input = """
    int sum(int n){
      int s,i;
      s=0;
      for(i=1;i<=n;i=i+1) s=s+i;
      return s;
    }
    """
    val expected = Program(List(FuncDecl(Id("sum"),List(VarDecl(Id("n"),IntType)),IntType,Block(List(VarDecl(Id("s"),IntType),VarDecl(Id("i"),IntType)),List(BinaryOp("=",Id("s"),IntLiteral(0)),For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("<=",Id("i"),Id("n")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),BinaryOp("=",Id("s"),BinaryOp("+",Id("s"),Id("i")))),Return(Some(Id("s"))))))))
    assert(checkAst(input,expected,246))
  }

  test("AST 47: funcall expression") {
    val input = """
    int main(){
      sum(10);
      fact(10);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("sum"),List(IntLiteral(10))),CallExpr(Id("fact"),List(IntLiteral(10))))))))
    assert(checkAst(input,expected,247))
  }

  test("AST 48: index operator") {
    val input = """
    int main(){
      int a[1];
      a[0]=5;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),ArrayType(IntLiteral(1),IntType))),List(BinaryOp("=",ArrayCell(Id("a"),IntLiteral(0)),IntLiteral(5)))))))
    assert(checkAst(input,expected,248))
  }

  test("AST 49: array function") {
    val input = """
    int[] array(int a,int b){
      int arr[2];
      arr[0]=a;
      arr[1]=b;
      return arr;
    }
    """
    val expected = Program(List(FuncDecl(Id("array"),List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),ArrayPointerType(IntType),Block(List(VarDecl(Id("arr"),ArrayType(IntLiteral(2),IntType))),List(BinaryOp("=",ArrayCell(Id("arr"),IntLiteral(0)),Id("a")),BinaryOp("=",ArrayCell(Id("arr"),IntLiteral(1)),Id("b")),Return(Some(Id("arr"))))))))
    assert(checkAst(input,expected,249))
  }

  test("AST 50: funcall with index operator") {
    val input = """
    int main(){
      array(1,2)[0];
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(ArrayCell(CallExpr(Id("array"),List(IntLiteral(1),IntLiteral(2))),IntLiteral(0)))))))
    assert(checkAst(input,expected,250))
  }

  test("AST 51: expression with bracket") {
    val input = """
    int main(){
      int a;
      a=(1+2)*sum(2);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),IntType)),List(BinaryOp("=",Id("a"),BinaryOp("*",BinaryOp("+",IntLiteral(1),IntLiteral(2)),CallExpr(Id("sum"),List(IntLiteral(2))))))))))
    assert(checkAst(input,expected,251))
  }

  test("AST 52: nested funcall statement") {
    val input = """
    int main(){
      sum(fact(10));
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("sum"),List(CallExpr(Id("fact"),List(IntLiteral(10))))))))))
    assert(checkAst(input,expected,252))
  }

  test("AST 53: funcall with many paramater") {
    val input = """
    int main(){
      complex(a[2]+1*fact(2)/-2-5,true,1.23);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("complex"),List(BinaryOp("-",BinaryOp("+",ArrayCell(Id("a"),IntLiteral(2)),BinaryOp("/",BinaryOp("*",IntLiteral(1),CallExpr(Id("fact"),List(IntLiteral(2)))),UnaryOp("-",IntLiteral(2)))),IntLiteral(5)),BooleanLiteral(true),FloatLiteral(1.23f))))))))
    assert(checkAst(input,expected,253))
  }

  test("AST 54: if statement inner for statement") {
    val input = """
    int main(){
      int i;
      for(i=0;i<10;i=i+1)
        if(i%2==0) print(i);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("i"),IntType)),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),IntLiteral(10)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),If(BinaryOp("==",BinaryOp("%",Id("i"),IntLiteral(2)),IntLiteral(0)),CallExpr(Id("print"),List(Id("i"))),None)))))))
    assert(checkAst(input,expected,254))
  }

  test("AST 55: if statement inner do while statement") {
    val input = """
    int main(){
      int i;
      i=0;
      do
        if(i%2==0) print(i);
        i=i+1;
      while(i<10);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("i"),IntType)),List(BinaryOp("=",Id("i"),IntLiteral(0)),Dowhile(List(If(BinaryOp("==",BinaryOp("%",Id("i"),IntLiteral(2)),IntLiteral(0)),CallExpr(Id("print"),List(Id("i"))),None),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1)))),BinaryOp("<",Id("i"),IntLiteral(10))))))))
    assert(checkAst(input,expected,255))
  }

  test("AST 56: continue innner for statement") {
    val input = """
    int main(){
      int i;
      for(i=0;i<10;i=i+1){
        if(i%2!=0) continue; 
        print(i);
      }
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("i"),IntType)),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),IntLiteral(10)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(If(BinaryOp("!=",BinaryOp("%",Id("i"),IntLiteral(2)),IntLiteral(0)),Continue,None),CallExpr(Id("print"),List(Id("i")))))))))))
    assert(checkAst(input,expected,256))
  }

  test("AST 57: break inner do while statement") {
    val input = """
    int main(){
      int i;
      i=0;
      do
        if(i%2==0) print(i);
        i=i+1;
        if(i>=10) break;
      while(true);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("i"),IntType)),List(BinaryOp("=",Id("i"),IntLiteral(0)),Dowhile(List(If(BinaryOp("==",BinaryOp("%",Id("i"),IntLiteral(2)),IntLiteral(0)),CallExpr(Id("print"),List(Id("i"))),None),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),If(BinaryOp(">=",Id("i"),IntLiteral(10)),Break,None)),BooleanLiteral(true)))))))
    assert(checkAst(input,expected,257))
  }

  test("AST 58: declare global and local variable") {
    val input = """
    int a;
    int main(){
      float a;
      string a[3];
    }
    """
    val expected = Program(List(VarDecl(Id("a"),IntType),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),FloatType),VarDecl(Id("a"),ArrayType(IntLiteral(3),StringType))),List()))))
    assert(checkAst(input,expected,258))
  }

  test("AST 59: nested do while statement") {
    val input = """
    int main(){
      int i,j;
      i=0;j=0;
      do 
        do
          print(i);
          print(j);
          j=j+1; 
        while(j<10);
        i=i+1; 
      while(i<10);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType)),List(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("=",Id("j"),IntLiteral(0)),Dowhile(List(Dowhile(List(CallExpr(Id("print"),List(Id("i"))),CallExpr(Id("print"),List(Id("j"))),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1)))),BinaryOp("<",Id("j"),IntLiteral(10))),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1)))),BinaryOp("<",Id("i"),IntLiteral(10))))))))
    assert(checkAst(input,expected,259))
  }

  test("AST 60: for statement innner do while statement") {
    val input = """
    int main(){
      int i,j;
      i=0;
      do
        for(j=0;j<10;j=j+1){
          print(i);
          print(j);
        }
        i=i+1;
      while(i<10);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType)),List(BinaryOp("=",Id("i"),IntLiteral(0)),Dowhile(List(For(BinaryOp("=",Id("j"),IntLiteral(0)),BinaryOp("<",Id("j"),IntLiteral(10)),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),Block(List(),List(CallExpr(Id("print"),List(Id("i"))),CallExpr(Id("print"),List(Id("j")))))),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1)))),BinaryOp("<",Id("i"),IntLiteral(10))))))))
    assert(checkAst(input,expected,260))
  }

  test("AST 61: many assign and equal operator") {
    val input = """
    int main(){
      (a=b==c)!=d=e;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("=",BinaryOp("!=",BinaryOp("=",Id("a"),BinaryOp("==",Id("b"),Id("c"))),Id("d")),Id("e")))))))
    assert(checkAst(input,expected,261))
  }

  test("AST 62: many compare operator") {
    val input = """
    int main(){
      (a>b)>=c==(d<e)<=f;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("==",BinaryOp(">=",BinaryOp(">",Id("a"),Id("b")),Id("c")),BinaryOp("<=",BinaryOp("<",Id("d"),Id("e")),Id("f"))))))))
    assert(checkAst(input,expected,262))
  }

  test("AST 63: return a funcall") {
    val input = """
    int main(){
      return sum(10);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(Return(Some(CallExpr(Id("sum"),List(IntLiteral(10))))))))))
    assert(checkAst(input,expected,263))
  }

  test("AST 64: return a string") {
    val input = """
    string main(){
      return "String";
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),StringType,Block(List(),List(Return(Some(StringLiteral("String"))))))))
    assert(checkAst(input,expected,264))
  }

  test("AST 65: return a expression") {
    val input = """
    float main(){
      return 1.23+4*a%5/b;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),FloatType,Block(List(),List(Return(Some(BinaryOp("+",FloatLiteral(1.23f),BinaryOp("/",BinaryOp("%",BinaryOp("*",IntLiteral(4),Id("a")),IntLiteral(5)),Id("b"))))))))))
    assert(checkAst(input,expected,265))
  }

  test("AST 66: return a float") {
    val input = """
    float main(){
      return 1.23E-4;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),FloatType,Block(List(),List(Return(Some(FloatLiteral(1.23E-4f))))))))
    assert(checkAst(input,expected,266))
  }

  test("AST 67: mix-up operator") {
    val input = """ 
    int foo(){
      foo(2)[3]=a||b&&(c-35.4e10)*9+10.0/4+true;
    }"""
    val expected = Program(List(FuncDecl(Id("foo"),List(),IntType,Block(List(),List(BinaryOp("=",ArrayCell(CallExpr(Id("foo"),List(IntLiteral(2))),IntLiteral(3)),BinaryOp("||",Id("a"),BinaryOp("&&",Id("b"),BinaryOp("+",BinaryOp("+",BinaryOp("*",BinaryOp("-",Id("c"),FloatLiteral(3.54000011E11f)),IntLiteral(9)),BinaryOp("/",FloatLiteral(10.0f),IntLiteral(4))),BooleanLiteral(true))))))))))
    assert(checkAst(input,expected,267))
  }

  test("AST 68: program with line comment") {
    val input = """
    //Main Function
    int main(){
      //Body
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,268))
  }

  test("AST 69: program with block comment") {
    val input = """
      int main(){
        /* Empty
        Body */
      }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,269))
  }

  test("AST 70: complex index expression") {
    val input = """
    int main(){
      int a[10];
      return a[(i-j)*(i+j)%2];
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("a"),ArrayType(IntLiteral(10),IntType))),List(Return(Some(ArrayCell(Id("a"),BinaryOp("%",BinaryOp("*",BinaryOp("-",Id("i"),Id("j")),BinaryOp("+",Id("i"),Id("j"))),IntLiteral(2))))))))))
    assert(checkAst(input,expected,270))
  }

  test("AST 71: do while statement innner for statement") {
    val input = """
    int main(){
      int i,j;
      j=0;
      for(i=0;i<10;i=i+1)
        do
          print(i);
          print(j);
          j==j+1;
        while i<10;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType)),List(BinaryOp("=",Id("j"),IntLiteral(0)),For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),IntLiteral(10)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Dowhile(List(CallExpr(Id("print"),List(Id("i"))),CallExpr(Id("print"),List(Id("j"))),BinaryOp("==",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1)))),BinaryOp("<",Id("i"),IntLiteral(10)))))))))
    assert(checkAst(input,expected,271))
  }

  test("AST 72: if else stmt with a function call in condition part") {
    val input = """
    void main(){
      if(sum(5)) a=b;
      else c=d;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(CallExpr(Id("sum"),List(IntLiteral(5))),BinaryOp("=",Id("a"),Id("b")),Some(BinaryOp("=",Id("c"),Id("d")))))))))
    assert(checkAst(input,expected,272))
  }

  test("AST 73: do while stmt with conditional expression is index expression") {
    val input = """
    void main(){
      do
      a=a+1;
      while array[1];
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Dowhile(List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(1)))),ArrayCell(Id("array"),IntLiteral(1))))))))
    assert(checkAst(input,expected,273))
  }

  test("AST 74: many block statement in block statement") {
    val input = """
    int main(){
      {
        {
          {
            {
              {
                return 0;
              }
            }
          }
        }
      }
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(Block(List(),List(Block(List(),List(Block(List(),List(Block(List(),List(Block(List(),List(Return(Some(IntLiteral(0))))))))))))))))))
    assert(checkAst(input,expected,274))
  }

  test("AST 75: do while stmt with conditional expression is a function call") {
    val input = """
    void main(){
      do
        a=a+1;
      while sum(3);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Dowhile(List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(1)))),CallExpr(Id("sum"),List(IntLiteral(3)))))))))
    assert(checkAst(input,expected,275))
  }

  test("AST 76: return an or statement") {
    val input = """
    void main(){
      return a||b;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Return(Some(BinaryOp("||",Id("a"),Id("b")))))))))
    assert(checkAst(input,expected,276))
  }

  test("AST 77: minus sign before bracket") {
    val input = """
    int main(){
      -(1+2);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(UnaryOp("-",BinaryOp("+",IntLiteral(1),IntLiteral(2))))))))
    assert(checkAst(input,expected,277))
  }

  test("AST 78: minus sign with index expression") {
    val input = """
    int main(){
      -array[1];
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(UnaryOp("-",ArrayCell(Id("array"),IntLiteral(1))))))))
    assert(checkAst(input,expected,278))
  }

  test("AST 79: minus sign with subtract operator in expression") {
    val input = """
    int main(){
      -1--2--3;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("-",BinaryOp("-",UnaryOp("-",IntLiteral(1)),UnaryOp("-",IntLiteral(2))),UnaryOp("-",IntLiteral(3))))))))
    assert(checkAst(input,expected,279))
  }

  test("AST 80: nested index operator") {
    val input = """
    int main(){
      array[array[array[1]]];
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(ArrayCell(Id("array"),ArrayCell(Id("array"),ArrayCell(Id("array"),IntLiteral(1)))))))))
    assert(checkAst(input,expected,280))
  }

  test("AST 81: expression statement with only a operand") {
    val input = """
    void main(){
      1;
      a;
      1.E-2;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(IntLiteral(1),Id("a"),FloatLiteral(0.01f))))))
    assert(checkAst(input,expected,281))
  }

  test("AST 82: or, and with not of a variable") {
    val input = """
    void main(){
      a || !b;
      a && !b;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("||",Id("a"),UnaryOp("!",Id("b"))),BinaryOp("&&",Id("a"),UnaryOp("!",Id("b"))))))))
    assert(checkAst(input,expected,282))
  }

  test("AST 83: add, sub a number with a negative number") {
    val input = """
    void main(){
      a + -b;
      a - -b;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("+",Id("a"),UnaryOp("-",Id("b"))),BinaryOp("-",Id("a"),UnaryOp("-",Id("b"))))))))
    assert(checkAst(input,expected,283))
  }

  test("AST 84: mul, div, mod a number with a negative number") {
    val input = """
    void main(){
      a % -b;
      a / -b;
      a * -b;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("%",Id("a"),UnaryOp("-",Id("b"))),BinaryOp("/",Id("a"),UnaryOp("-",Id("b"))),BinaryOp("*",Id("a"),UnaryOp("-",Id("b"))))))))
    assert(checkAst(input,expected,284))
  }

  test("AST 85: bracket and none associativity") {
    val input = """
    void main(){
      (a == b) == c;
      a = b == c = d == e;
      a = b == c = d;
      a=b=c=d;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("==",BinaryOp("==",Id("a"),Id("b")),Id("c")),BinaryOp("=",Id("a"),BinaryOp("=",BinaryOp("==",Id("b"),Id("c")),BinaryOp("==",Id("d"),Id("e")))),BinaryOp("=",Id("a"),BinaryOp("=",BinaryOp("==",Id("b"),Id("c")),Id("d"))),BinaryOp("=",Id("a"),BinaryOp("=",Id("b"),BinaryOp("=",Id("c"),Id("d")))))))))
    assert(checkAst(input,expected,285))
  }

  test("AST 86: two kind of none associativity") {
    val input = """
    void main(){
      a == b >= c;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("==",Id("a"),BinaryOp(">=",Id("b"),Id("c"))))))))
    assert(checkAst(input,expected,286))
  }

  test("AST 87: for,do while statement inside block of statement") {
    val input = """
    void main(){
      for(i=0;i<3;i=i+1) a=a+1;
      do
        a=a+1;
      while true;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),IntLiteral(3)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(1)))),Dowhile(List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(1)))),BooleanLiteral(true)))))))
    assert(checkAst(input,expected,287))
  }

  test("AST 88: ucln function") {
    val input = """
    int ucln(int a, int b){
      int u;
      if (a<b) u=a;
      else u=b;
      do u=u-1;
      while((a%u!=0)||(b%u!=0));
    }
    """
    val expected = Program(List(FuncDecl(Id("ucln"),List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),IntType,Block(List(VarDecl(Id("u"),IntType)),List(If(BinaryOp("<",Id("a"),Id("b")),BinaryOp("=",Id("u"),Id("a")),Some(BinaryOp("=",Id("u"),Id("b")))),Dowhile(List(BinaryOp("=",Id("u"),BinaryOp("-",Id("u"),IntLiteral(1)))),BinaryOp("||",BinaryOp("!=",BinaryOp("%",Id("a"),Id("u")),IntLiteral(0)),BinaryOp("!=",BinaryOp("%",Id("b"),Id("u")),IntLiteral(0)))))))))
    assert(checkAst(input,expected,288))
  }

  test("AST 89: program read a file") {
    val input = """
    int main(){
      fp = fopen("file.txt", "r");
      fclose(fp);
      return 0;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("=",Id("fp"),CallExpr(Id("fopen"),List(StringLiteral("file.txt"),StringLiteral("r")))),CallExpr(Id("fclose"),List(Id("fp"))),Return(Some(IntLiteral(0))))))))
    assert(checkAst(input,expected,289))
  }

  test("AST 90: complex expression") {
    val input = """
    void foo(){
      -1.23+!array[2]*sum(3);
    }
    """
    val expected = Program(List(FuncDecl(Id("foo"),List(),VoidType,Block(List(),List(BinaryOp("+",UnaryOp("-",FloatLiteral(1.23f)),BinaryOp("*",UnaryOp("!",ArrayCell(Id("array"),IntLiteral(2))),CallExpr(Id("sum"),List(IntLiteral(3))))))))))
    assert(checkAst(input,expected,290))
  }

  test("AST 91: none associativity of >= and >") {
    val input = """
    void main(){
      a >= (b > c);
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp(">=",Id("a"),BinaryOp(">",Id("b"),Id("c"))))))))
    assert(checkAst(input,expected,291))
  }

  test("AST 92: none associativity of <= and <") {
    val input = """
    void main(){
      (a < b) <= c;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("<=",BinaryOp("<",Id("a"),Id("b")),Id("c")))))))
    assert(checkAst(input,expected,292))
  }

  test("AST 93: nested block statement") {
    val input = """
    void main(){
      {
        int a, b;
        a = b;
        {
          int c;
          c = 0;
        }
      }
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("=",Id("a"),Id("b")),Block(List(VarDecl(Id("c"),IntType)),List(BinaryOp("=",Id("c"),IntLiteral(0)))))))))))
    assert(checkAst(input,expected,293))
  }

  test("AST 94: return an expression") {
    val input = """
    void main(){
      return a+1==b*2 = c%3!=d/4;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Return(Some(BinaryOp("=",BinaryOp("==",BinaryOp("+",Id("a"),IntLiteral(1)),BinaryOp("*",Id("b"),IntLiteral(2))),BinaryOp("!=",BinaryOp("%",Id("c"),IntLiteral(3)),BinaryOp("/",Id("d"),IntLiteral(4)))))))))))
    assert(checkAst(input,expected,294))
  }

  test("AST 95: do while statement") {
    val input = """
    void main(){
      do
        a = b;
        c = c + 1;
        foo();
        {
          int a;
          int b;
          a = b;
        }
      while a == 0;
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Dowhile(List(BinaryOp("=",Id("a"),Id("b")),BinaryOp("=",Id("c"),BinaryOp("+",Id("c"),IntLiteral(1))),CallExpr(Id("foo"),List()),Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("=",Id("a"),Id("b"))))),BinaryOp("==",Id("a"),IntLiteral(0))))))))
    assert(checkAst(input,expected,295))
  }

  test("AST 96: Hello Word") {
    val input = """
    int main(){
      print("Hello Word\n");
    }
    """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("print"),List(StringLiteral("Hello Word\\n"))))))))
    assert(checkAst(input,expected,296))
  }

  test("AST 97: fibonacci program") {
    val input = """
    int fibo(int n){
      if(n==0) return 0;
      if(n==1) return 1;
      else return fibo(n-1)+fibo(n-2);
    }
    int main(){
      int i;
      for(i=0;i<10;i=i+1) print(fibo(i));
    }
    """
    val expected = Program(List(FuncDecl(Id("fibo"),List(VarDecl(Id("n"),IntType)),IntType,Block(List(),List(If(BinaryOp("==",Id("n"),IntLiteral(0)),Return(Some(IntLiteral(0))),None),If(BinaryOp("==",Id("n"),IntLiteral(1)),Return(Some(IntLiteral(1))),Some(Return(Some(BinaryOp("+",CallExpr(Id("fibo"),List(BinaryOp("-",Id("n"),IntLiteral(1)))),CallExpr(Id("fibo"),List(BinaryOp("-",Id("n"),IntLiteral(2)))))))))))),FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("i"),IntType)),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),IntLiteral(10)),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),CallExpr(Id("print"),List(CallExpr(Id("fibo"),List(Id("i")))))))))))
    assert(checkAst(input,expected,297))
  }

  test("AST 98: bouble search function") {
    val input = """
    int[] bouble(int array[], int n){
      int i, j, swap;
      for(i=0; i<n-1; i=i+1){
        for (j=0; j<n-i-1; j=j+1){
          if (array[j]>array[j+1]){
            swap=array[j];
            array[j]=array[j+1];
            array[j+1]=swap;
          }
        }
      }
    }
    """
    val expected = Program(List(FuncDecl(Id("bouble"),List(VarDecl(Id("array"),ArrayPointerType(IntType)),VarDecl(Id("n"),IntType)),ArrayPointerType(IntType),Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType),VarDecl(Id("swap"),IntType)),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),BinaryOp("-",Id("n"),IntLiteral(1))),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(For(BinaryOp("=",Id("j"),IntLiteral(0)),BinaryOp("<",Id("j"),BinaryOp("-",BinaryOp("-",Id("n"),Id("i")),IntLiteral(1))),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(1))),Block(List(),List(If(BinaryOp(">",ArrayCell(Id("array"),Id("j")),ArrayCell(Id("array"),BinaryOp("+",Id("j"),IntLiteral(1)))),Block(List(),List(BinaryOp("=",Id("swap"),ArrayCell(Id("array"),Id("j"))),BinaryOp("=",ArrayCell(Id("array"),Id("j")),ArrayCell(Id("array"),BinaryOp("+",Id("j"),IntLiteral(1)))),BinaryOp("=",ArrayCell(Id("array"),BinaryOp("+",Id("j"),IntLiteral(1))),Id("swap")))),None))))))))))))
    assert(checkAst(input,expected,298))
  }

  test("AST 99: compare two string function") {
    val input = """
    int compareString(string str1, string str2){
      int result;
      if (strlen(str1) >= strlen(str2)){
        for (i = 0; i < strlen(str2); i)
          result =abs(str1[i] - str2[i]);
        for (i = strlen(str2); i < strlen(str1); i)
          result =str1[i];
      }
      else{
        for(i=0;i<strlen(str1);i)
          for (i = strlen(str1); i < strlen(str2); i)
            result = str2[i];
      }
      return result;
    }"""
    val expected = Program(List(FuncDecl(Id("compareString"),List(VarDecl(Id("str1"),StringType),VarDecl(Id("str2"),StringType)),IntType,Block(List(VarDecl(Id("result"),IntType)),List(If(BinaryOp(">=",CallExpr(Id("strlen"),List(Id("str1"))),CallExpr(Id("strlen"),List(Id("str2")))),Block(List(),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),CallExpr(Id("strlen"),List(Id("str2")))),Id("i"),BinaryOp("=",Id("result"),CallExpr(Id("abs"),List(BinaryOp("-",ArrayCell(Id("str1"),Id("i")),ArrayCell(Id("str2"),Id("i"))))))),For(BinaryOp("=",Id("i"),CallExpr(Id("strlen"),List(Id("str2")))),BinaryOp("<",Id("i"),CallExpr(Id("strlen"),List(Id("str1")))),Id("i"),BinaryOp("=",Id("result"),ArrayCell(Id("str1"),Id("i")))))),Some(Block(List(),List(For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("<",Id("i"),CallExpr(Id("strlen"),List(Id("str1")))),Id("i"),For(BinaryOp("=",Id("i"),CallExpr(Id("strlen"),List(Id("str1")))),BinaryOp("<",Id("i"),CallExpr(Id("strlen"),List(Id("str2")))),Id("i"),BinaryOp("=",Id("result"),ArrayCell(Id("str2"),Id("i"))))))))),Return(Some(Id("result"))))))))
    assert(checkAst(input,expected,299))
  }

  test("AST 100: check leap year function") {
    val input = """
    boolean isLeapYear(int year){
      if (year < 0 )
        cout ("This is a invalid Year." ,endl);
      else
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
          return true;
        else
          return false;
    }
    """
    val expected = Program(List(FuncDecl(Id("isLeapYear"),List(VarDecl(Id("year"),IntType)),BoolType,Block(List(),List(If(BinaryOp("<",Id("year"),IntLiteral(0)),CallExpr(Id("cout"),List(StringLiteral("This is a invalid Year."),Id("endl"))),Some(If(BinaryOp("||",BinaryOp("==",BinaryOp("%",Id("year"),IntLiteral(400)),IntLiteral(0)),BinaryOp("&&",BinaryOp("==",BinaryOp("%",Id("year"),IntLiteral(4)),IntLiteral(0)),BinaryOp("!=",BinaryOp("%",Id("year"),IntLiteral(100)),IntLiteral(0)))),Return(Some(BooleanLiteral(true))),Some(Return(Some(BooleanLiteral(false))))))))))))
    assert(checkAst(input,expected,300))
  }

  test("AST 101: newline in string") {
    val input = """int main(){"\n";}"""
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(StringLiteral("\\n"))))))
    assert(checkAst(input,expected,301))
  }

  test("AST 102: priority operator") {
    val input = """int main(){1 && 2 == 3 < 4 + 5 * 6 [7] = ! 8;}"""
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("=",BinaryOp("&&",IntLiteral(1),BinaryOp("==",IntLiteral(2),BinaryOp("<",IntLiteral(3),BinaryOp("+",IntLiteral(4),BinaryOp("*",IntLiteral(5),ArrayCell(IntLiteral(6),IntLiteral(7))))))),UnaryOp("!",IntLiteral(8))))))))
    assert(checkAst(input,expected,302))
  }

}