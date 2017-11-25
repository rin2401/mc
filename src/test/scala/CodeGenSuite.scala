import org.scalatest.FunSuite

/**
  * Created by nhphung on 4/30/17.
  */
class CodeGenSuite extends FunSuite with TestCodeGen {
//Declaration
  //Variable
  // test("Gen 1: declare global int a, print a") {
  //   val input = """
  //   int a;
  //   void main(){
  //     a = 1;
  //     putIntLn(a);
  //   }
  //   """
  //   val expected = "1"
  //   assert(checkCode(input,expected,501))
  // }  

  // test("Gen 1: print int 309") {
  //   val input = """
  //   void main(){
  //     putIntLn(309);
  //   }
  //   """
  //   val expected = "309"
  //   assert(checkCode(input,expected,502))
  // }  

  // test("another simple program => print 125") {
  //   val input = "void main () {putIntLn(125);}"
  //   val expected = "125"
  //   assert(checkCode(input,expected,502))
  // }
  // test("special program => print 0") {
  //   val input = "void main () {putIntLn(000);}"
  //   val expected = "0"
  //   assert(checkCode(input,expected,503))
  // }
  //Function



//Statement


//Expression
  //BinOp +
  // test("Gen 2: BinOp + print int 1 + 2") {
  //   val input = """
  //   void main(){
  //     putIntLn(1+2);
  //   }
  //   """
  //   val expected = "3"
  //   assert(checkCode(input,expected,503))
  // } 

  // test("Gen 2: BinOp + print float 1 + 2.3") {
  //   val input = """
  //   void main(){
  //     putFloatLn(1 + 2.3);
  //   }
  //   """
  //   val expected = "3.3"
  //   assert(checkCode(input,expected,503))
  // } 

  // test("Gen 2: BinOp + print float 1 + 2.3 + 4") {
  //   val input = """
  //   void main(){
  //     putFloatLn(1 + 2.3 + 4);
  //   }
  //   """
  //   val expected = "7.3"
  //   assert(checkCode(input,expected,503))
  // } 

  // test("Gen 2: BinOp + print float 1 + 2 + 3.4") {
  //   val input = """
  //   void main(){
  //     putFloatLn(1 + 2 + 3.4);
  //   }
  //   """
  //   val expected = "6.4"
  //   assert(checkCode(input,expected,503))
  // } 

  // test("Gen 2: BinOp + print float (1 + 2) + (3.4 + 5)") {
  //   val input = """
  //   void main(){
  //     putFloatLn((1 + 2) + (3.4 + 5));
  //   }
  //   """
  //   val expected = "11.4"
  //   assert(checkCode(input,expected,503))
  // } 
  
  // test("Gen 2: BinOp * print float (1 * 2.3) + (4 * 5)") {
  //   val input = """
  //   void main(){
  //     putFloatLn((1 * 2.3) + (4 * 5));
  //   }
  //   """
  //   val expected = "22.3"
  //   assert(checkCode(input,expected,503))
  // } 

  // test("Gen 2: BinOp * print float (0.1 * 2.3) + (4 / 5)") {
  //   val input = """
  //   void main(){
  //     putFloatLn((0.1 * 2.3) + (4 / 5));
  //   }
  //   """
  //   val expected = "0.23"
  //   assert(checkCode(input,expected,503))
  // } 

  // test("Gen 2: BinOp > print bool 1 > 2  ") {
  //   val input = """
  //   void main(){
  //     putBoolLn(1>2);
  //   }
  //   """
  //   val expected = "false"
  //   assert(checkCode(input,expected,503))
  // } 

  // test("Gen 2: BinOp < print bool 0.1 < 2  ") {
  //   val input = """
  //   void main(){
  //     putBoolLn(0.1<2);
  //   }
  //   """
  //   val expected = "true"
  //   assert(checkCode(input,expected,503))
  // }  
  //UnaryOp -
  // test("Gen 2: UnaryOp - print int -1  ") {
  //   val input = """
  //   void main(){
  //     putIntLn(-1);
  //   }
  //   """
  //   val expected = "-1"
  //   assert(checkCode(input,expected,503))
  // }  

  // test("Gen 2: UnaryOp - print float -1.2e3  ") {
  //   val input = """
  //   void main(){
  //     putFloatLn(-1.2e3);
  //   }
  //   """
  //   val expected = "-1200.0"
  //   assert(checkCode(input,expected,503))
  // }  

  test("Gen 2: UnaryOp ! print !(0.1 < 2)  ") {
    val input = """
    void main(){
      boolean a;
      putBoolLn(!(0.1<2));
    }
    """
    val expected = "false"
    assert(checkCode(input,expected,503))
  }  

  // test("Gen 2: BinOp && print bool (1 > 2) && (0.1 < 2)") {
  //   val input = """
  //   void main(){
  //     putBoolLn((1>2)&&(0.1<2));
  //   }
  //   """
  //   val expected = "false"
  //   assert(checkCode(input,expected,503))
  // }

  // BinOp =
  // test("Gen 2: BinOp =  a=b=1 print a") {
  //   val input = """
  //   void main(){
  //     int a,b;
  //     a = b = 1;
  //     putIntLn(a);
  //   }
  //   """
  //   val expected = "1"
  //   assert(checkCode(input,expected,503))
  // } 

//CallExpr
  // test("Gen 2: print float 24.01") {
  //   val input = """
  //   void main(){
  //     putFloatLn(24.01);
  //   }
  //   """
  //   val expected = "24.01"
  //   assert(checkCode(input,expected,503))
  // } 

  // test("Gen 3: print string rin2401") {
  //   val input = """
  //   void main(){
  //     putStringLn("rin2401");
  //   }
  //   """
  //   val expected = "rin2401"
  //   assert(checkCode(input,expected,504))
  // } 

  // test("Gen 4: print boolean true") {
  //   val input = """
  //   void main(){
  //     putIntLn(true);
  //   }
  //   """
  //   val expected = "1"
  //   assert(checkCode(input,expected,505))
  // } 

  
  // test("simple program => print 5") {
  //   val input = """
  //   int x;
  //   float y; 
  //   void main(){
  //     //foo(10,20);
  //     fun();
  //   }
    
  //   // void foo(int a, int b){
  //   //   putIntLn(b);
  //   //   putIntLn(a);
  //   // }

  //   void fun(){
  //     int a;
  //     a = 2;
  //     {
  //       int b;
  //       b = 3 + 1;
  //       b = b - 1;
  //       putIntLn(b);
  //     }
  //     {
  //       string c;
  //       c = "hello";
  //       putStringLn(c);
  //     }
  //     putIntLn(a);
  //     putFloatLn(1.1+1.1);
  //   }

  //   """
  //   val expected = "3hello22.2"
  //   assert(checkCode(input,expected,599))
  // }

}
