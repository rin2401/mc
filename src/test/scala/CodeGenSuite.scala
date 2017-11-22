import org.scalatest.FunSuite

/**
  * Created by nhphung on 4/30/17.
  */
class CodeGenSuite extends FunSuite with TestCodeGen {
  test("simple program => print 5") {
    val input = "void main () {putIntLn(5);}"
    val expected = "5"
    assert(checkCode(input,expected,501))
  }
  test("another simple program => print 125") {
    val input = "void main () {putIntLn(125);}"
    val expected = "125"
    assert(checkCode(input,expected,502))
  }
  test("special program => print 0") {
    val input = "void main () {putIntLn(000);}"
    val expected = "0"
    assert(checkCode(input,expected,503))
  }
}
