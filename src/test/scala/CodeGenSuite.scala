import org.scalatest.FunSuite

/**
  * Created by nhphung on 4/30/17.
  */
class CodeGenSuite extends FunSuite with TestCodeGen {
//Declaration
//Variable
  test("Gen 1: declare global int a") {
    val input = """
    int a;
    void main(){
      a = 1;
      putInt(a);
    }
    """
    val expected = "1"
    assert(checkCode(input,expected,501))
  }  
  
  test("Gen 2: declare global float a") {
    val input = """
    float a;
    void main(){
      a = 1.2;
      putFloat(a);
    }
    """
    val expected = "1.2"
    assert(checkCode(input,expected,502))
  }

  test("Gen 3: declare global string a") {
    val input = """
    string a;
    void main(){
      a = "hello";
      putString(a);
    }
    """
    val expected = "hello"
    assert(checkCode(input,expected,503))
  }

  test("Gen 4: declare global boolean a") {
    val input = """
    boolean a;
    void main(){
      a = true;
      putBool(a);
    }
    """
    val expected = "true"
    assert(checkCode(input,expected,504))
  }

  test("Gen 5: declare global array int a") {
    val input = """
    int a[3];
    void main(){
      a[0] = 1;
      putIntLn(a[0]);
    }
    """
    val expected = "1"
    assert(checkCode(input,expected,505))
  }

  test("Gen 6: declare global array float a") {
    val input = """
    float a[3];
    void main(){
      a[0] = 1.2;
      putFloatLn(a[0]);
    }
    """
    val expected = "1.2"
    assert(checkCode(input,expected,506))
  }

  test("Gen 7: declare global array string a") {
    val input = """
    string a[3];
    void main(){
      a[0] = "hello";
      putStringLn(a[0]);
    }
    """
    val expected = "hello"
    assert(checkCode(input,expected,507))
  }

  test("Gen 8: declare global array boolean a") {
    val input = """
    boolean a[3];
    void main(){
      a[0] = true;
      putBoolLn(a[0]);
    }
    """
    val expected = "true"
    assert(checkCode(input,expected,508))
  }

  test("Gen 9: declare local int a") {
    val input = """
    void main(){
      int a;
      a = 1;
      putInt(a);
    }
    """
    val expected = "1"
    assert(checkCode(input,expected,509))
  }  
  
  test("Gen 10: declare local float a") {
    val input = """
    void main(){
      float a;
      a = 1.2;
      putFloat(a);
    }
    """
    val expected = "1.2"
    assert(checkCode(input,expected,510))
  }

  test("Gen 11: declare local string a") {
    val input = """

    void main(){
      string a; 
      a = "hello";
      putString(a);
    }
    """
    val expected = "hello"
    assert(checkCode(input,expected,511))
  }

  test("Gen 12: declare local boolean a") {
    val input = """
    void main(){
      boolean a;
      a = true;
      putBool(a);
    }
    """
    val expected = "true"
    assert(checkCode(input,expected,512))
  }

  test("Gen 13: declare local array int a") {
    val input = """
    void main(){
      int a[3]; 
      a[0] = 1;
      putIntLn(a[0]);
    }
    """
    val expected = "1"
    assert(checkCode(input,expected,513))
  }

  test("Gen 14: declare local array float a") {
    val input = """
    void main(){
      float a[3];
      a[0] = 1.2;
      putFloatLn(a[0]);
    }
    """
    val expected = "1.2"
    assert(checkCode(input,expected,514))
  }

  test("Gen 15: declare local array string a") {
    val input = """
    void main(){
      string a[3];
      a[0] = "hello";
      putStringLn(a[0]);
    }
    """
    val expected = "hello"
    assert(checkCode(input,expected,515))
  }

  test("Gen 16: declare local array boolean a") {
    val input = """
    void main(){
      boolean a[3];
      a[0] = true;
      putBoolLn(a[0]);
    }
    """
    val expected = "true"
    assert(checkCode(input,expected,516))
  }  

//Paramater

  test("Gen 17: declare function foo param primitive type") {
    val input = """
    void main(){
      foo(1, 2, "hello", false);
    }
    void foo(int a, float b, string c, boolean d){
      putIntLn(a);
      putFloatLn(b);
      putStringLn(c);
      putBoolLn(d);
    }
    """
    val expected = "12.0hellofalse"
    assert(checkCode(input,expected,517))
  }

  test("Gen 18: declare function foo with param array type") {
    val input = """
    void main(){
      int a[3];
      foo(a);
      putIntLn(a[0]);      
    }
    void foo(int a[]){
      a[0] = 2;
    }
    """
    val expected = "2"
    assert(checkCode(input,expected,518))
  }

//Statement
//If statement
  
  test("Gen 19: simple if statement") {
    val input = """
    void main(){
      if(true) putStringLn("hello");
    }
    """
    val expected = "hello"
    assert(checkCode(input,expected,519))
  }

  test("Gen 20: simple if else statement") {
    val input = """
    void main(){
      if(true) putIntLn(1);
      else putIntLn(2);
    }
    """
    val expected = "1"
    assert(checkCode(input,expected,520))
  }

  test("Gen 21: if(1>2) print 1 else print 2") {
    val input = """
    void main(){
      if(1>2) putIntLn(1);
      else putIntLn(2);
    }
    """
    val expected = "2"
    assert(checkCode(input,expected,521))
  }   

  test("Gen 22: if(a<=b) print 1 else print 2") {
    val input = """
    void main(){
      int a;
      float b;
      a = 10;
      b = 5.5;
      if(a<=b) putIntLn(1);
      else putIntLn(2);
    }
    """
    val expected = "2"
    assert(checkCode(input,expected,522))
  } 

//For Statement

  test("Gen 23: simple for statement") {
    val input = """
    void main(){
      int i;
      for(i = 1; i <= 5; i = i + 1) putIntLn(i);
    }
    """
    val expected = "12345"
    assert(checkCode(input,expected,523))
  } 

  test("Gen 24: if statement in for statement") {
    val input = """
    void main(){
      int i;
      for(i = 1; i <= 10; i = i + 1) {
        if(i % 2 == 0) putInt(i);
      } 
    }
    """
    val expected = "246810"
    assert(checkCode(input,expected,524))
  }   
  
  test("Gen 25: for statement in for statement") {
    val input = """
    void main(){
      int i, j;
      for(i = 1; i <= 3; i = i + 1) {
        for(j = 1; j <= 2; j = j + 1) {
          putInt(i);
          putString(",");
          putInt(j);
          putString(";");
        }
      } 
    }
    """
    val expected = "1,1;1,2;2,1;2,2;3,1;3,2;"
    assert(checkCode(input,expected,525))
  }

  test("Gen 26: do statement in for statement") {
    val input = """
    void main(){
      int i, j;
      for(i = 1; i <= 3; i = i + 1) {
        j = 1;
        do 
          putInt(i);
          putString(",");
          putInt(j);
          putString(";");
          j = j + 1;
        while (j<=2);
      } 
    }
    """
    val expected = "1,1;1,2;2,1;2,2;3,1;3,2;"
    assert(checkCode(input,expected,526))
  }

//Dowhile Statement

  test("Gen 27: do print 1 while i<=5") {
    val input = """
    void main(){
      int i;
      i = 1;
      do {
        putIntLn(1);
        i = i + 1;
      }
      while(i<=5);
    }
    """
    val expected = "11111"
    assert(checkCode(input,expected,527))
  }

  test("Gen 28: do print 1 while i<=5 no block") {
    val input = """
    void main(){
      int i;
      i = 1;
      do
        putIntLn(1);
        i = i + 1;
      while(i<=5);
    }
    """
    val expected = "11111"
    assert(checkCode(input,expected,528))
  } 

  test("Gen 29: do print 1 while i<=5 2 block") {
    val input = """
    void main(){
      int i;
      i = 1;
      do
        {putIntLn(1);}
        {i = i + 1;}
      while(i<=5);
    }
    """
    val expected = "11111"
    assert(checkCode(input,expected,529))
  }   

//Continue Statement
  
  test("Gen 30: continue in for statement ") {
    val input = """
    void main(){
      int i;
      for(i = 1; i <= 5; i = i + 1) {
        if(i==2) continue;
        putIntLn(i);
      }
    }
    """
    val expected = "1345"
    assert(checkCode(input,expected,530))
  } 

  test("Gen 31: continue in do while statement") {
    val input = """
    void main(){
      int i;
      i = 0;
      do {
        i = i + 1;
        if(i==3) continue;
        putIntLn(i);
      } while(i < 5);
    }
    """
    val expected = "1245"
    assert(checkCode(input,expected,531))
  }

//Break Statement

  test("Gen 32: break in for statement ") {
    val input = """
    void main(){
      int i;
      for(i = 1; i <= 5; i = i + 1) {
        if(i==3) break;
        putIntLn(i);
      }
    }
    """
    val expected = "12"
    assert(checkCode(input,expected,532))
  } 

  test("Gen 33: break in do while statement") {
    val input = """
    void main(){
      int i;
      i = 0;
      do {
        i = i + 1;
        if(i==3) break;
        putIntLn(i);
      } while(i<5);
    }
    """
    val expected = "12"
    assert(checkCode(input,expected,533))
  }

//Return Statement

  test("Gen 34: return void ") {
    val input = """
    void main(){
      putIntLn(12);
      return;
    }
    """
    val expected = "12"
    assert(checkCode(input,expected,534))
  }

  test("Gen 35: return int") {
    val input = """
    void main(){
      putIntLn(foo());
    }
    int foo() {
      return 1;
    }

    """
    val expected = "1"
    assert(checkCode(input,expected,535))
  } 

  test("Gen 36: return float") {
    val input = """
    void main(){
      putFloat(foo());
    }
    float foo() {
      return 1.2;
    }

    """
    val expected = "1.2"
    assert(checkCode(input,expected,536))
  }

  test("Gen 37: return string") {
    val input = """
    void main(){
      putString(foo());
    }
    string foo() {
      return "hello";
    }
    """
    val expected = "hello"
    assert(checkCode(input,expected,537))
  }

  test("Gen 38: return boolean") {
    val input = """
    void main(){
      putBool(foo());
    }
    boolean foo() {
      return true;
    }
    """
    val expected = "true"
    assert(checkCode(input,expected,538))
  }

  test("Gen 39: return array") {
    val input = """
    void main(){
      putString(foo()[0]);
      putString(" ");
      putString(foo()[1]);
    }
    string[] foo() {
      string a[2];
      a[0] = "hello";
      a[1] = "world";
      return a;
    }
    """
    val expected = "hello world"
    assert(checkCode(input,expected,539))
  }

  test("Gen 40: return void in for stmt") {
    val input = """
    void main(){
      int i;
      for(i = 1; i <= 5; i = i + 1) {
        if(i==3) return;
        putIntLn(i);
      }
    }
    """
    val expected = "12"
    assert(checkCode(input,expected,540))
  }    

  test("Gen 41: return float in for stmt") {
    val input = """
    void main(){
      putFloatLn(foo());
    }
    float foo(){
      for(1;true;1) return 24.01;
      return 0.0;
    }
    """
    val expected = "24.01"
    assert(checkCode(input,expected,41))
  } 

  test("Gen 42: return in if stmt") {
    val input = """
    void main(){
      putIntLn(foo());
    }
    int foo(){
      if(true) return 1;
      return 0;
    }
    """
    val expected = "1"
    assert(checkCode(input,expected,542))
  } 

  test("Gen 43: return in do while stmt") {
    val input = """
    void main(){
      putBoolLn(foo());
    }
    boolean foo(){
      do
        return false;
      while(true);
    }
    """
    val expected = "false"
    assert(checkCode(input,expected,543))
  }    

  test("Gen 44: return if true stmt") {
    val input = """
    void main(){
      putStringLn(foo());
    }
    string foo(){
      if(true) return "hello";
      return "goodbye";
    }
    """
    val expected = "hello"
    assert(checkCode(input,expected,544))
  }  

  test("Gen 45: return int cast to float") {
    val input = """
    void main(){
      putFloatLn(foo());
    }
    float foo(){
      return 1;
    }
    """
    val expected = "1.0"
    assert(checkCode(input,expected,545))
  }

//Expression
//BinOp 
// [+-*/]
  test("Gen 46: [+] intlit + intlit") {
    val input = """
    void main(){
      putIntLn(1+2);
    }
    """
    val expected = "3"
    assert(checkCode(input,expected,546))
  } 

  test("Gen 47: [+] intlit + floatlit") {
    val input = """
    void main(){
      putFloatLn(1 + 2.3);
    }
    """
    val expected = "3.3"
    assert(checkCode(input,expected,547))
  } 

  test("Gen 48: [+] floatlit + floatlit") {
    val input = """
    void main(){
      putFloatLn(20.3 + 10.2);
    }
    """
    val expected = "30.5"
    assert(checkCode(input,expected,548))
  }

 test("Gen 49: [-] intlit - intlit") {
    val input = """
    void main(){
      putIntLn(1-2);
    }
    """
    val expected = "-1"
    assert(checkCode(input,expected,549))
  } 

  test("Gen 50: [-] floatvar - intlit") {
    val input = """
    void main(){
      float a;
      a = 2.3;
      putFloatLn(a - 1);
    }
    """
    val expected = "1.3"
    assert(checkCode(input,expected,550))
  }

  test("Gen 51: [+-] intlit + floatlit - intlit") {
    val input = """
    void main(){
      putFloatLn(1 + 2.3 - 2);
    }
    """
    val expected = "1.3"
    assert(checkCode(input,expected,551))
  } 

  test("Gen 52: [+-] intlit - intlit + floatlit") {
    val input = """
    void main(){
      putFloatLn(1 - 2 + 3.4);
    }
    """
    val expected = "2.4"
    assert(checkCode(input,expected,552))
  } 

  test("Gen 53: [+] intexp + floatexp") {
    val input = """
    void main(){
      putFloatLn((1 + 2) + (3.4 + 5));
    }
    """
    val expected = "11.4"
    assert(checkCode(input,expected,553))
  } 
  
  test("Gen 54: [+-*/] print float 1 * 2.3 + 4 * 5") {
    val input = """
    void main(){
      putFloatLn(1 * 2.3 + 4 * 5);
    }
    """
    val expected = "22.3"
    assert(checkCode(input,expected,554))
  } 

  test("Gen 55: [+-*/] print float (0.1 * 2.3) + (4 / 5)") {
    val input = """
    void main(){
      putFloatLn((0.1 * 2.3) + (4 / 5));
    }
    """
    val expected = "0.23"
    assert(checkCode(input,expected,555))
  } 

// [%]

  test("Gen 56: [%] intlit % intlit") {
    val input = """
    void main(){
      putIntLn(10 % 3);
    }
    """
    val expected = "1"
    assert(checkCode(input,expected,556))
  } 

  test("Gen 57: [%] intvar % intexp") {
    val input = """
    void main(){
      int a;
      a = 10;
      putIntLn(a % (a % 3 + 2));
    }
    """
    val expected = "1"
    assert(checkCode(input,expected,557))
  } 

// [><>=<=]

  test("Gen 58: [>] intlit > intlit") {
    val input = """
    void main(){
      putBoolLn(1 > 2);
    }
    """
    val expected = "false"
    assert(checkCode(input,expected,558))
  } 

  test("Gen 59: [>] intlit >= intlit") {
    val input = """
    void main(){
      putBoolLn(2 >= 2);
    }
    """
    val expected = "true"
    assert(checkCode(input,expected,559))
  }

  test("Gen 60: [<] intlit < floatlit") {
    val input = """
    void main(){
      putBoolLn(10 < 20.5);
    }
    """
    val expected = "true"
    assert(checkCode(input,expected,560))
  }

  test("Gen 61: [<=] floatvar <= intvar") {
    val input = """
    void main(){
      float a;
      int b;
      a = 0.1;
      b = 3;
      putBoolLn(a <= b);
    }
    """
    val expected = "true"
    assert(checkCode(input,expected,561))
  }  

// [&&||]

  test("Gen 62: [&&] boolexp && boolexp") {
    val input = """
    void main(){
      putBoolLn((1>2)&&(0.1<2));
    }
    """
    val expected = "false"
    assert(checkCode(input,expected,562))
  }

  test("Gen 63: [&&] boolvar && boolvar") {
    val input = """
    void main(){
      boolean a,b;
      a = false;
      b = true;
      putBoolLn(a&&b);
    }
    """
    val expected = "false"
    assert(checkCode(input,expected,563))
  }

  test("Gen 64: [||] boolexp || boolexp") {
    val input = """
    void main(){
      putBoolLn(1 > 2 || 0.1 < 2);
    }
    """
    val expected = "true"
    assert(checkCode(input,expected,564))
  }

  test("Gen 65: [||] boolvar || boolvar") {
    val input = """
    void main(){
      boolean a,b;
      a = false;
      b = true;
      putBoolLn(a||b);
    }
    """
    val expected = "true"
    assert(checkCode(input,expected,565))
  }

  test("Gen 66: [||&&] varbool and boollit ") {
    val input = """
    void main(){
      boolean a,b;
      a = false;
      b = true;
      putBoolLn(a || b && false);
    }
    """
    val expected = "false"
    assert(checkCode(input,expected,566))
  }

  test("Gen 57: [&&] check short circuit &&") {
    val input = """
    void main(){
      int a;
      boolean b;
      a = 1;
      b = true && false && (a=2)==2;
      putIntLn(a);
    }
    """
    val expected = "1"
    assert(checkCode(input,expected,567))
  }

  test("Gen 68: [&&] check short-circuit && div 0") {
    val input = """
    void main(){
      int a;
      boolean b;
      a = 2;
      b = false && (a/0)==0;
      putBoolLn(b);
    }
    """
    val expected = "false"
    assert(checkCode(input,expected,568))
  }

  test("Gen 69: [||] check short-circuit ||") {
    val input = """
    void main(){
      int a;
      boolean b;
      a = 1;
      b = true || (a=2)==2;
      putIntLn(a);
    }
    """
    val expected = "1"
    assert(checkCode(input,expected,569))
  }

//[==!=]

  test("Gen 70: [==] intvar == intexp") {
    val input = """
    void main(){
      int a;
      a = 100;
      putBoolLn(a == 50 + 50);
    }
    """
    val expected = "true"
    assert(checkCode(input,expected,570))
  }

  test("Gen 71: [==] boolvar == boollit") {
    val input = """
    void main(){
      boolean a;
      a = true;
      putBoolLn(a==false);
    }
    """
    val expected = "false"
    assert(checkCode(input,expected,571))
  }

  test("Gen 72: [!=] intvar != intlit") {
    val input = """
    void main(){
      int a;
      a = 100;
      putBoolLn(a != 50);
    }
    """
    val expected = "true"
    assert(checkCode(input,expected,572))
  }

  test("Gen 73: [!=] boolvar != boollit") {
    val input = """
    void main(){
      boolean a;
      a = true;
      putBoolLn(a!=false);
    }
    """
    val expected = "true"
    assert(checkCode(input,expected,573))
  }

// [=]
  
  test("Gen 74: [=] int a=1; print a;") {
    val input = """
    void main(){
      int a;
      a = 1;
      putIntLn(a);
    }
    """
    val expected = "1"
    assert(checkCode(input,expected,574))
  }

  test("Gen 75: [=] print a=1; print 1;") {
    val input = """
    void main(){
      int a;
      putIntLn(a = 1);
      putIntLn(a);
    }
    """
    val expected = "11"
    assert(checkCode(input,expected,575))
  }

  test("Gen 76: [=] floatvar = floatexp") {
    val input = """
    void main(){
      float a;
      a = 1.2 + 12;
      putFloatLn(a);
    }
    """
    val expected = "13.2"
    assert(checkCode(input,expected,576))
  }

  test("Gen 77: [=] floatvar = intlit") {
    val input = """
    void main(){
      float a;
      a = 1;
      putFloatLn(a);
    }
    """
    val expected = "1.0"
    assert(checkCode(input,expected,577))
  }

  test("Gen 78: [=] intvar = intvar = intlit") {
    val input = """
    void main(){
      int a,b;
      a = b = 1;
      putIntLn(a);
      putIntLn(b);
    }
    """
    val expected = "11"
    assert(checkCode(input,expected,578))
  } 

  test("Gen 79: [=] floatvar = intvar = intlit") {
    val input = """
    void main(){
      float a;
      int b;
      a = b = 1;
      putFloatLn(a);
      putIntLn(b);
    }
    """
    val expected = "1.01"
    assert(checkCode(input,expected,579))
  }

  test("Gen 80: [=] intlit + (intvar == intlit)") {
    val input = """
    void main(){
      int a;
      999 + (a = 5);
      putIntLn(a);
    }
    """
    val expected = "5"
    assert(checkCode(input,expected,580))
  }

  test("Gen 81: [=] int b[0] = 1 print b[0]") {
    val input = """
    void main(){
      int b[3];
      b[0] = 1;
      putIntLn(b[0]);
    }
    """
    val expected = "1"
    assert(checkCode(input,expected,581))
  }

  test("Gen 82: [=] float b[0] = 1.2 print b[0]") {
    val input = """
    void main(){
      float b[3];
      b[0] = 1.2;
      putFloatLn(b[0]);
    }
    """
    val expected = "1.2"
    assert(checkCode(input,expected,582))
  }

  test("Gen 83: [=] float b[0] = 1 print b[0]") {
    val input = """
    void main(){
      float b[3];
      b[0] = 1;
      putFloatLn(b[0]);
    }
    """
    val expected = "1.0"
    assert(checkCode(input,expected,583))
  }

  test("Gen 84: [=] int b[0]=b[1]=b[2]=1 print b[0]+b[1]+b[2]") {
    val input = """
    void main(){
      int b[3];
      b[0] = b[1] = b[2] = 1;
      putIntLn(b[0]+b[1]+b[2]);
    }
    """
    val expected = "3"
    assert(checkCode(input,expected,584))
  }

  test("Gen 85: [=] float a[0]= int b[0] =1 print a[0]+b[0]") {
    val input = """
    void main(){
      float a[3];
      int b[3];
      a[0] = b[0] = 1;
      putFloatLn(a[0]+b[0]);
    }
    """
    val expected = "2.0"
    assert(checkCode(input,expected,585))
  }

//UnaryOp

// [-]  
  
  test("Gen 86: [-] print int -1  ") {
    val input = """
    void main(){
      putIntLn(-1);
    }
    """
    val expected = "-1"
    assert(checkCode(input,expected,586))
  }  

  test("Gen 87: [-] print float -1.2e3  ") {
    val input = """
    void main(){
      putFloatLn(-1.2e3);
    }
    """
    val expected = "-1200.0"
    assert(checkCode(input,expected,587))
  }  

// [!]

  test("Gen 88: [!] print !(0.1 < 2)  ") {
    val input = """
    void main(){
      boolean a;
      putBoolLn(!(0.1<2));
    }
    """
    val expected = "false"
    assert(checkCode(input,expected,588))
  }  

//CallExpr

  test("Gen 89: funcall param inttype cast to float type ") {
    val input = """
    void main(){
      putFloatLn(24);
    }
    """
    val expected = "24.0"
    assert(checkCode(input,expected,589))
  } 

  test("Gen 90: funcall as param") {
    val input = """
    void main(){
      putIntLn(foo(foo(foo(foo(foo(5))))));
    }
    int foo(int a) {
      return a - 1;
    }
    
    """
    val expected = "0"
    assert(checkCode(input,expected,590))
  }  

//ArrayCell
  test("Gen 91: arraycell id[intlit]") {
    val input = """
    void main(){
      int a[3];
      a[0] = 1000; 
      putIntLn(a[0]);
    }
    """
    val expected = "1000"
    assert(checkCode(input,expected,591))
  }
  test("Gen 92: test arraycell id[intexp]") {
    val input = """
    void main(){
      int a[3];
      a[0] = 1000; 
      putIntLn(a[10-10]);
    }
    """
    val expected = "1000"
    assert(checkCode(input,expected,592))
  } 
  test("Gen 93: test arraycell id[arraycell]") {
    val input = """
    void main(){
      int a[3];
      a[0] = 1000; 
      a[1] = 0;
      putIntLn(a[a[1]]);
    }
    """
    val expected = "1000"
    assert(checkCode(input,expected,593))
  }

  test("Gen 94: arraycell funcall[intlit]") {
    val input = """
    void main(){ 
      putIntLn(foo()[0]);
    }
    int [] foo(){
      int a[3];
      a[0] = 1000; 
      return a;
    }
    """
    val expected = "1000"
    assert(checkCode(input,expected,594))
  }
  test("Gen 95: test arraycell funcall[intexp]") {
    val input = """
    void main(){
      putIntLn(foo()[10-10]);
    }
    int [] foo(){
      int a[3];
      a[0] = 1000; 
      return a;
    }    
    """
    val expected = "1000"
    assert(checkCode(input,expected,595))
  } 
  test("Gen 96: test arraycell funcall[arraycell]") {
    val input = """
    void main(){
      putIntLn(foo()[foo()[1]]);
    }
    int [] foo(){
      int a[3];
      a[0] = 1000; 
      a[1] = 0;
      return a;
    } 
    """
    val expected = "1000"
    assert(checkCode(input,expected,596))
  }

//Program

  test("Gen 97: fact function") {
    val input = """
    void main(){
      putIntLn(fact(6));
    }
    int fact(int n) {
      if(n==0) return 1;
      else return n*fact(n-1);
    }
    """
    val expected = "720"
    assert(checkCode(input,expected,597))
  } 

  test("Gen 98: sort function") {
    val input = """
    void main(){
      int a[5];
      a[0] = 9;
      a[1] = 6;
      a[2] = 8;
      a[3] = 2;
      a[4] = 5;
      sort(a,5);
    }
    void sort(int a[], int n) {
      int i,j;
      for(i = 0; i < n - 1; i = i +1)
        for(j = i + 1; j < n; j = j + 1)
          if(a[i] > a[j]) {
            a[i] = a[i] + a[j];
            a[j] = a[i] - a[j];
            a[i] = a[i] - a[j];
          }
      for (i = 0; i < n; i = i + 1) putIntLn(a[i]);
    }
    """
    val expected = "25689"
    assert(checkCode(input,expected,598))
  } 

  test("Gen 99: sum function") {
    val input = """
    void main(){
      putIntLn(sum(6));
    }
    int sum(int n) {
      if(n==0) return 0;
      else return n + sum(n-1);
    }
    """
    val expected = "21"
    assert(checkCode(input,expected,599))
  } 

  test("Gen 100: check leap year function") {
    val input = """
    void main(){
      is_leap_year(2018);
    }
    void is_leap_year(int year) {
      if(year % 400 == 0 || year % 100 != 0 && year % 4 == 0) {
        putInt(year);
        putString(" is a leap year");
      }
      else {
        putInt(year);
        putString(" is not a leap year");
      }
    }
    """
    val expected = "2018 is not a leap year"
    assert(checkCode(input,expected,600))
  } 
}
