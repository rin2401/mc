import org.scalatest.FunSuite

/**
  * Created by nhphung on 4/28/17.
  * Modified by Van Huu Quoc (1512723)
  */
class ParserSuite  extends FunSuite with TestParser {

  test("Parser 1: A simple program") {
    val input = """string[] a (int b, string c[]) {} 
    """
    val expect = "sucessful"
    assert(checkRec(input,expect,101))
  }

  test("Parser 2: More complex program") {
    val input = """ int main () {
                      put(4);
                    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,102))
  }

  test("Parser 3: Wrong program"){
    val input = "} int main {"
    val expect = "Error on line 1 col 1: }"
    assert(checkRec(input,expect,103))
  }

  test("Parser 4: Valid variable declaration") {
    val input = """int a;"""
    val expect = "sucessful"
    assert(checkRec(input,expect,104))
  }

  test("Parser 5: Valid variable declaration") {
    val input = """float b,c,d;"""
    val expect = "sucessful"
    assert(checkRec(input,expect,105))
  }

  test("Parser 6: Valid variable declaration") {
    val input = """string s[5]; """
    val expect = "sucessful"
    assert(checkRec(input,expect,106))
  }

  test("Parser 7: Valid variable declaration") {
    val input = """boolean d,e[5]; """
    val expect = "sucessful"
    assert(checkRec(input,expect,107))
  }

  test("Parser 8: Valid variable declaration") {
    val input = """int a; float b,c[3]; boolean d,e; string s; """
    val expect = "sucessful"
    assert(checkRec(input,expect,108))
  }        

  test("Parser 9: Invalid variable declaration") {
    val input = """int a = 3; """
    val expect = "Error on line 1 col 7: ="
    assert(checkRec(input,expect,109))
  }

  test("Parser 10: Invalid array declaration") {
    val input = """int a[]; """
    val expect = "Error on line 1 col 7: ]"
    assert(checkRec(input,expect,110))
  }

  test("Parser 11: Invalid variable declaration") {
    val input = """void a;"""
    val expect = "Error on line 1 col 7: ;"
    assert(checkRec(input,expect,111))
  }

  test("Parser 12: Invalid array declaration") {
    val input = """int a[3][5];"""
    val expect = "Error on line 1 col 9: ["
    assert(checkRec(input,expect,112))
  }

  test("Parser 13: Invalid array declaration") {
    val input = """int a[-3];"""
    val expect = "Error on line 1 col 7: -"
    assert(checkRec(input,expect,113))
  }

  test("Parser 14: Invalid array declaration") {
    val input = """int a[1.23E2];"""
    val expect = "Error on line 1 col 7: 1.23E2"
    assert(checkRec(input,expect,114))
  }

  test("Parser 15: Invalid array declaration") {
    val input = """int a[b];"""
    val expect = "Error on line 1 col 7: b"
    assert(checkRec(input,expect,115))
  }

  test("Parser 16: Invalid array declaration") {
    val input = """int a[true];"""
    val expect = "Error on line 1 col 7: true"
    assert(checkRec(input,expect,116))
  }

  test("Parser 17: Invalid variable declaration") {
    val input = """int a"""
    val expect = "Error on line 1 col 6: <EOF>"
    assert(checkRec(input,expect,117))
  }

  test("Parser 18: Valid function declaration") {
    val input = """ int[] hello(){ println("Hello World");}"""
    val expect = "sucessful"
    assert(checkRec(input,expect,118))
  }

  test("Parser 19: Valid function declaration") {
    val input = """ int func(int a, float b[]){return a;}"""
    val expect = "sucessful"
    assert(checkRec(input,expect,119))
  }  

  test("Parser 20: Valid function declaration") {
    val input = """ boolean[] func(int a, float b[]){}"""
    val expect = "sucessful"
    assert(checkRec(input,expect,120))
  }

  test("Parser 21: Valid function declaration") {
    val input = """ void func(){ int a; a=1; return;}"""
    val expect = "sucessful"
    assert(checkRec(input,expect,121))
  }

  test("Parser 22: Invalid function type") {
    val input = """void[] func(){}"""
    val expect = "Error on line 1 col 5: ["
    assert(checkRec(input,expect,122))
  }

  test("Parser 23: Invalid array parameter") {
    val input = """void func(int a[5]){}"""
    val expect = "Error on line 1 col 17: 5"
    assert(checkRec(input,expect,123))
  }

  test("Parser 24: Invalid type parameter") {
    val input = """void func(void a){}"""
    val expect = "Error on line 1 col 11: void"
    assert(checkRec(input,expect,124))
  }

  test("Parser 25: Invalid type parameter") {
    val input = """void func(int[] a){ return; }"""
    val expect = "Error on line 1 col 14: ["
    assert(checkRec(input,expect,125))
  }

  test("Parser 26: No body function") {
    val input = """void func()"""
    val expect = "Error on line 1 col 12: <EOF>"
    assert(checkRec(input,expect,126))
  }

  test("Parser 27: Nested function") {
    val input = """ void func1(){
                      int func2(){}
                    }"""
    val expect = "Error on line 2 col 32: ("
    assert(checkRec(input,expect,127))
  }

  test("Parser 28: Error in block statement") {
    val input = """ void func(int a[],int b){
                      int a;
                      a=1;      
                      int b;
                    }"""
    val expect = "Error on line 4 col 23: int"
    assert(checkRec(input,expect,128))
  }

  test("Parser 29: Valid block statement") {
    val input = """ void func(int a[],int b){      
                      int a;
                      a=1;
                    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,129))
  }

  test("Parser 30: If statement") {
    val input = """ void func(){
                      int a;
                      if(true) a=1;
                    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,130))
  }

  test("Parser 31: If else statement") {
    val input = """ void func(){
                      int a;
                      if(true) a=1; else a=2;
                    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,131))
  }

  test("Parser 32: For statement") {
    val input = """ void func(){
                      int i,a;
                      for(i=0;i<10;i=i+1) a=1;
                    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,132))
  }

  test("Parser 33: Valid nested if-else statement") {
    val input = """ void func(){
                      int a;
                      if(true) if(false) a=1; else a=2;    
                    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,133))
  }

  test("Parser 34: Valid break-continue in for statement") {
    val input = """ void func(){
                      int i;
                      for(i=0;i<10;i=i+1){
                        if(i==3) continue;
                        if(i==9) break;
                      } 
                    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,134))
  }

  test("Parser 35: Valid while statement") {
    val input = """ void func(){
                      a=0;
                      do
                        a=a+1;      
                      while(a<10);
                    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,135))
  }

  test("Parser 36: Valid while statement") {
    val input = """ void func(){
                      a=0;
                      do
                        a=a+1;
                        print(a);      
                      while(a<10);
                    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,136))
  }

  test("Parser 37: Nested loop statement") {
    val input = """ void func(){
                      int i,j,a;
                      for(i=0;i<5;i=i+1)
                        for(j=10;j>5;j=j-1)
                          a=1;
                    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,137))
  }

  test("Parser 38: Nested loop statement") {
    val input = """ void func(){
                      int i,j;
                      for(i=0;i<5;i=i+1){
                        j=0;
                        do
                          j = j+1;
                        while (j<5);
                      }
                    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,138))
  }

  test("Parser 39: Not statement follow if") {
    val input = """ int func(){
                      if(true)
                    }"""
    val expect = "Error on line 3 col 21: }"
    assert(checkRec(input,expect,139))
  }

  test("Parser 40: Declare variable in block statement follow if") {
    val input = """ int func(){
                      int a;
                      if(a==0){
                        int b;
                      }
                    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,140))
  }

  test("Parser 41: Valid for statement") {
    val input = """ int func(){
                      int i;
                      for(i=0;i+3;i=i+1){}
                    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,141))
  }

  test("Parser 42: No statement follow for ") {
    val input = """ int func(){
                      int i;
                      for(i=0;i<=3;i=i+1)
                    }"""
    val expect = "Error on line 4 col 21: }"
    assert(checkRec(input,expect,142))
  }

  test("Parser 43: Not expression1 in for statement ") {
    val input = """ int func(){
                      int a;
                      for(;a+3;a=a+1)
                    }"""
    val expect = "Error on line 3 col 27: ;"
    assert(checkRec(input,expect,143))
  }

  test("Parser 44: Not statement follow do") {
    val input = """ int func(){
                      do
                      while(true);
                    }"""
    val expect = "Error on line 3 col 23: while"
    assert(checkRec(input,expect,144))
  }

  test("Parser 45: Miss SEMI after while") {
    val input = """ int func(){
                      do{}
                      while(true)
                    }"""
    val expect = "Error on line 4 col 21: }"
    assert(checkRec(input,expect,145))
  }

  test("Parser 46: Invalid expression condition") {
    val input = """ void func(int mark){
                      if(0<a<=10)a=1;
                    }"""
    val expect = "Error on line 2 col 29: <="
    assert(checkRec(input,expect,146))
  }

  test("Parser 47: Many relational operator(>,<,<=,>=)") {
    val input = """ void func(){
                      a>b<c;
                    }"""
    val expect = "Error on line 2 col 26: <"
    assert(checkRec(input,expect,147))
  }

  test("Parser 48: Many equality operator") {
    val input = """ void func(){
                      a==b==c;
                    }"""
    val expect = "Error on line 2 col 27: =="
    assert(checkRec(input,expect,148))
  }

  test("Parser 49: Invalid AND operator"){
    val input = """ int func(){
                      a&&&&b;
                    }"""
    val expect = "Error on line 2 col 26: &&";
    assert(checkRec(input,expect,149))
  }

  test("Parser 50: Invalid || operator"){
    val input = """ int func(){
                      a=b||c||;
                    }"""
    val expect = "Error on line 2 col 31: ;";
    assert(checkRec(input,expect,150))
  }

  test("Parser 51: invalid  ! operator"){
    val input = """ int func(){
                      a!b;
                    }"""
    val expect = "Error on line 2 col 24: !";
    assert(checkRec(input,expect,151))
  }

  test("Parser 52: Invalid [] operator"){
    val input = """ int foo(){
                      a[1][1];
                    }"""
    val expect = "Error on line 2 col 27: [";
    assert(checkRec(input,expect,152))
  }

  test("Parser 53: Invalid [] operator"){
    val input = """ int foo(){
                      b[];
                    }"""
    val expect = "Error on line 2 col 25: ]";
    assert(checkRec(input,expect,153))
  }

  test("Parser 54: valid [] operator"){
    val input = """ int foo(){
                      a=b[c]+24[true];
                    }"""
    val expect = "sucessful";
    assert(checkRec(input,expect,154))
  }

  test("Parser 55: valid [] operator"){
    val input = """ int foo(){
                      foo(3)[9];
                    }"""
    val expect = "sucessful";
    assert(checkRec(input,expect,155))
  }

  test("Parser 56: Invalid != operator"){
    val input = """ int foo(){
                      a!=b!=c;
                    }"""
    val expect = "Error on line 2 col 27: !=";
    assert(checkRec(input,expect,156))
  }

  test("Parser 57: Many assigment statement"){
    val input = """ int foo(){
                      a=b=c=d;
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,157))
  }

  test("Parser 58: Many assigment statement"){
    val input = """ int foo(){
                      foo(2)=arr[7]="string"=10=false=3.2e2;
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,158))
  }

  test("Parser 59: Mix-up operator"){
    val input = """ int foo(){
                      foo(2)[3]=a||b&&(c-35.4e10)*9+10.0/4+true;
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,159))
  }

  test("Parser 60: Valid == != operator"){
    val input = """ int foo(){
                      (a==b)!=c;
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,160))
  }

  test("Parser 61: Valid < >= > <= operator"){
    val input = """ int foo(){
                      ((a>b)<d)>=(c<=d);
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,161))
  }

  test("Parser 62: Valid [] operator"){
    val input = """ int foo() {
          foo[3]= ((a(c,c*d+false,10.99e1)[3])[10[1]])[c+2];
        }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,162))
  }

  test("Parser 63: Valid expression statement"){
    val input = """ int foo() {
                      true;
                      false;
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,163))
  }

  test("Parser 64: Valid expression statement"){
    val input = """ int foo() {
                      39;
                      12.3e2;
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,164))
  }

  test("Parser 65: Valid expression statement"){
    val input = """ int foo() {
                      "string";
                      "";
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,165))
  }
  test("Parser 66: Valid expression statement"){
    val input = """ int foo() {
                      arr[3];
                      arr(2)[3];
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,166))
  }
  test("Parser 67: Valid expression statement"){
    val input = """ int foo() {
                      foo();
                      foo(3);
                      foo("",true);
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,167))
  }

  test("Parser 68: Valid expression statement"){
    val input = """ int foo() {
                      c==d=f=g;
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,168))
  }

  test("Parser 69: Valid expression statement"){
    val input = """ int foo() {
                      c==d=f=g==h;
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,169))
  }

  test("Parser 70: Invalid if-else statement"){
    val input = """ int foo() {
                      if(1) 1;
                      else 2;
                      else 3;
                    }"""
    val expect = """Error on line 4 col 23: else"""
    assert(checkRec(input,expect,170))
  }

  test("Parser 71: Invalid if-else statement"){
    val input = """ int foo() {
                      if(1) 1;
                      else ;
                    }"""
    val expect = """Error on line 3 col 28: ;"""
    assert(checkRec(input,expect,171))
  }

  test("Parser 72: Invalid if-else statement"){
    val input = """ int foo() {
                      if(1) int a;
                    }"""
    val expect = """Error on line 2 col 29: int"""
    assert(checkRec(input,expect,172))
  }

  test("Parser 73: Invalid - operator"){
    val input = """ int foo() {
                      a--;
                    }"""
    val expect = """Error on line 2 col 26: ;"""
    assert(checkRec(input,expect,173))
  }  

  test("Parser 74: Valid any - operator"){
    val input = """ int foo() {
                    ---a;
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,174))
  }

  test("Parser 75: Valid many ! operator"){
    val input = """ int foo() {
                    !!!a;
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,175))
  }

  test("Parser 76: Invalid ! operator"){
    val input = """ int foo() {
                      a!;
                    }"""
    val expect = """Error on line 2 col 24: !"""
    assert(checkRec(input,expect,176))
  }

  test("Parser 77: Invalid + operator"){
    val input = """ int foo() {
                      a++;
                    }"""
    val expect = """Error on line 2 col 25: +"""
    assert(checkRec(input,expect,177))
  }

  test("Parser 78: Valid - operator"){
    val input = """ int foo() {
                      a----b;
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,178))
  }

  test("Parser 79: Invalid for-loop"){
    val input = """ int foo() {
                      for(1;2;) 1;
                    }"""
    val expect = """Error on line 2 col 31: )"""
    assert(checkRec(input,expect,179))
  }

  test("Parser 80: Valid for-loop"){
    val input = """ int foo() {
                      for(1;2;3) 1;
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,180))
  }

  test("Parser 81: Invalid name function"){
    val input = """ int 20day() {} """
    val expect = """Error on line 1 col 6: 20"""
    assert(checkRec(input,expect,181))
  }

  test("Parser 82: Invalid do-while statement"){
    val input = """ int foo() {
                      do {}
                      while();
                    }"""
    val expect = """Error on line 3 col 29: )"""
    assert(checkRec(input,expect,182))
  }

  test("Parser 83: Valid multiple block do-while statement"){
    val input = """ int foo() {
                      do
                      {}{}{}
                      while(1);
                    }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,183))
  }

  test("Parser 84: Error Token"){
    val input = """ int func(){
                      float a;
                      a = .e2;
                    }"""
    val expect = "Error Token ."
    assert(checkRec(input,expect,184))
  }

  test("Parser 85: Unclose string") {
    val input = """ int func(){
                      print("string);
                    }"""
    val expect = "Unclosed String: string);"
    assert(checkRec(input,expect,185))
  }

  test("Parser 86: Unclose string") {
    val input = """ int func(){
                      string a;
                      a = "string;
                    }"""
    val expect = "Unclosed String: string;"
    assert(checkRec(input,expect,186))
  }

  test("Parser 87: Illegal escape string") {
    val input = """ float main (int a, float b) {
                      s = "This is a \p string";
                    }"""
    val expect ="""Illegal Escape In String: This is a \p"""
    assert(checkRec(input,expect,187))
  }

  test("Parser 88: Invalid return statement") {
    val input = """ int func(){
                      return a
                    }"""
    val expect = "Error on line 3 col 21: }"
    assert(checkRec(input,expect,188))
  }

  test("Parser 89: Invalid return statement") {
    val input = """ int func(){
                      return a b;
                    }"""
    val expect = "Error on line 2 col 32: b"
    assert(checkRec(input,expect,189))
  }

  test("Parser 90: Complex program") {
    val input ="""
    void putIn(int itemSize, int itemValue) {
      int chart;   
      int numItem;
      int bagSize;
      int sizeBag;
      for (i = numItem; i > 0; i=i-1) {
        if ((chart[i])[sizeBag] != (chart[i - 1])[sizeBag]) {
          size=value = itemSize_at(i - 1);
          addItem(size, value);
          sizeBag = sizeBag-size;
        }
      }
      cout("Put these items in bag from set");
      showItem();
    }
    """
    val expect ="sucessful"
    assert(checkRec(input,expect,190))
  }

  test("Parser 91: Complex program") {
    val input ="""
    void main(){
      for (i = 0; i < numItem + 1; i) {
        (chart[i])[0] = 0;
      }
      for (i = 0; i < bagSize + 1; i) {
        (chart[0])[i] = 0;
      }
      for (item = 1; item < numItem + 1; item) {
        for (size = 0; size < bagSize + 1; size) {
          S = itemSizeat(item - 1);
          V = itemValueat(item - 1);
          if (S <= size) {
            if (V + (chart[item - 1])[size - S] >(chart[item - 1])[size])
              (chart[item])[size] = V + chart[size - S];
            else
              chart[size] = chart[item - 1];
          }
          else
            chart[item] = chart[size];
        }
      }
      return chart;
    }
    """
    val expect ="sucessful"
    assert(checkRec(input,expect,191))
  }

  test("Parser 92: Complex program") {
    val input ="""
    int zeroOne_myAttemp(){
      for ( i = place; i < numItem; i) {
        if (last_one || no_more_space(i == numItem - 1) || (size - itemSize == 0)) {
          if (itemSize > size) {
            if (value > max){
              max = value;
              return;
            }
            if (value + itemValue[at(i)] > max)
              max = value + itemValue;
            return;
          }
          if (size < itemSize)
            continue;
          for (j = 1; j < numItem - i + 1;j+1) {
            findMax(size - itemSizeat(i),i + j,value + itemValue[at(i)]);
          }
        }
      }
      return max;
    }
    """
    val expect ="sucessful"
    assert(checkRec(input,expect,192))
  }

  test("Parser 93: Complex program") {
    val input ="""
    boolean isIsomorphic(string s, string t) {
      if (s_length() != t_length())
        return false;
      dodai   = s_length();
      compare = "";
      for (i = 0; i < dodai; i=i+1) {
        compare = " ";
      }
      for (i = 0; i < dodai; i) {
        if (compare[i] != " ")
          continue;
        for (j = i; j < dodai; j)
          if (s[i] == s[j])
            compare[j] = t[i];
      }
      return compare == t;
    }
    """
    val expect ="sucessful"
    assert(checkRec(input,expect,193))
  }

  test("Parser 94: Complex program") {
    val input ="""
    int main(){
      int array[5],a,i,j;
      for (i = 0; i < 5; i){
        cout("array[",i,"] = ");
        cin(array[i]);
      }  
      for (i = 0; i < 5;i)
        cout(array[i]," ");
      for (i = 0; i < 2; i){
        a = array[i];
        array[i] = array[4 - i];
        array[4 - i] = a;
      }
      system("pause");
    }
    """
    val expect ="sucessful"
    assert(checkRec(input,expect,194))
  }

  test("Parser 95: Complex program") {
    val input ="""
    int main(){
      int array[2],i,j;
      for (i = 0; i < 2; i){
        for (j = 0; j < 3; j){
          cout;
          cin(array[i]);
        }
      }
      for (i = 0; i < 2; i){
        for (i = 0; j < 3; j){
          cout((array[i])[j]);
          if (j == 2) cout(endl);
        }
      }
      system("pause");
    }
    """
    val expect ="sucessful"
    assert(checkRec(input,expect,195))
  }

  test("Parser 96: Complex program") {
    val input ="""
    int fibonacci(int N){
      if (N == 0) return  0;
      if (N == 1) return 1;
      else return fibonacci(N - 1) + fibonacci(N - 2);
    }
    int main(){
      int N,i;
      N=0;
      for (i = 0; i < N; i)
        cout(fibonacci(i), " ");
        system("pause");
      }
    """
    val expect ="sucessful"
    assert(checkRec(input,expect,196))
  }

  test("Parser 97: Complex program") {
    val input ="""
    int a[10];
    void printArray(int begin, int end){
      if(begin < end){ 
        cout(a[begin]);
        printArray(begin + 1, end);
      }
    }
    int minimum(int b[], int begin, int end){
      if (begin < end){
        if (b[begin] < b[begin + 1]) 
          small = b[begin];
        minimum(b, begin + 1, end);
      }
      return small;
    }
    """
    val expect ="sucessful"
    assert(checkRec(input,expect,197))
  }

  test("Parser 98: Complex program") {
    val input ="""
    int a[10];
    void ngto(int a, int b){
      for (i = a; counter=0; i <= b){
        for (j = 2; j <= sqrt(i); j)
          if (i%j == 0) counter=couter+1;
        if (counter == 0 && i!=1) cout(i);
        counter = 0;
      }
    }
    """
    val expect ="sucessful"
    assert(checkRec(input,expect,196))
  }

  test("Parser 99: Complex program") {
    val input ="""
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
    val expect ="sucessful"
    assert(checkRec(input,expect,199))
  }

  test("Parser 100: Complex program") {
    val input ="""
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
    }
    """
    val expect ="sucessful"
    assert(checkRec(input,expect,200))
  }

}