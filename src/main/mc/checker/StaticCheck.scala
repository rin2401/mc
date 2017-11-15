package mc.checker

/**
  * @author nhphung
  * Modified by Van Huu Quoc - 1512723
  */

import mc.parser._
import mc.utils._
import java.io.{File, PrintWriter}

//import mc.codegen.Val
import org.antlr.v4.runtime.ANTLRFileStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree._

import scala.collection.JavaConverters._

class StaticChecker(ast:AST) extends BaseVisitor with Utils {  
  def check() = visit(ast,null)  

  override def visitProgram(ast: Program, c: Any): Any = {
    val funlst = List(
      Symbol("getInt",FunctionType(List(),IntType),null),
      Symbol("putInt",FunctionType(List(IntType),VoidType),null),
      Symbol("putIntLn",FunctionType(List(IntType),VoidType),null),
      Symbol("getFloat",FunctionType(List(),FloatType),null),
      Symbol("putFloat",FunctionType(List(FloatType),VoidType),null),
      Symbol("putFloatLn",FunctionType(List(FloatType),VoidType),null),
      Symbol("putBool",FunctionType(List(BoolType),VoidType),null),
      Symbol("putBoolLn",FunctionType(List(BoolType),VoidType),null),
      Symbol("putString",FunctionType(List(StringType),VoidType),null),
      Symbol("putStringLn",FunctionType(List(StringType),VoidType),null),
      Symbol("putLn",FunctionType(List(),VoidType),null))
    val lst = toListSymbol(ast.decl,funlst,null)
    ast.decl.filter(_.isInstanceOf[FuncDecl]).map(visit(_,lst))
  }
//Declaration
  override def visitVarDecl(ast: VarDecl, c: Any): Any = null
  override def visitFuncDecl(ast: FuncDecl, c: Any): Any = {
    val paramlst = toListSymbol(ast.param,List[Symbol](),Parameter)
    val lst = List(ast.returnType,false,c,paramlst) 
    visit(ast.body,lst) match {
      case List(_,false) => if(ast.returnType != VoidType) throw FunctionNotReturn(ast.name.name)
      case _ => null
    }
  }
//Statement
  override def visitBlock(ast: Block, c: Any): Any = {
    val cl = c.asInstanceOf[List[Any]]
    var loclst = toListSymbol(ast.decl,if(cl.size==4) cl(3) else List(),null)
    val glolst = cl(2).asInstanceOf[List[Symbol]]
    val lst = List(cl(0),cl(1),loclst:::glolst)
    ast.stmt.foldLeft(List(false,false))((r,s) => r match {
      case List(true,_) => throw UnreachableStatement(s)
      case _ => visit(s,lst) match {
        case List(a,b) => List(r(0)||a.asInstanceOf[Boolean],r(1)||b.asInstanceOf[Boolean])
        case _ => List(false,false) 
      } 
    }) 
  }
  override def visitIf(ast: If, c: Any): Any = {
    if(visit(ast.expr,c)!=BoolType) throw TypeMismatchInStatement(ast)
    visit(ast.thenStmt,c) match {
      case List(t1,t2) => ast.elseStmt match {
        case None => List(false,false)
        case Some(s) => visit(s,c) match {
          case List(e1,e2) => List(t1.asInstanceOf[Boolean]&&e1.asInstanceOf[Boolean],t2.asInstanceOf[Boolean]&&e2.asInstanceOf[Boolean])
          case _ => List(false,false) 
        }   
      } 
      case _ => List(false,false) 
    }  
  }
  override def visitFor(ast: For, c: Any): Any = {
    val lst = c.asInstanceOf[List[Any]].updated(1,true)
    if(visit(ast.expr1,c)!=IntType||visit(ast.expr2,c)!=BoolType||visit(ast.expr3,c)!=IntType) 
      throw TypeMismatchInStatement(ast)
    visit(ast.loop,lst)
    List(false,false)
  }
  override def visitDowhile(ast: Dowhile, c:Any): Any = {
    val lst = c.asInstanceOf[List[Any]].updated(1,true)
    val returnlst = ast.sl.foldLeft(List(false,false))((r,s) => r match {
      case List(true,_) | List(_,true) => throw UnreachableStatement(s)
      case _ => visit(s,lst) match {
        case List(a,b) => List(r(0)||a.asInstanceOf[Boolean],r(1)||b.asInstanceOf[Boolean])
        case _ => List(false,false) 
      } 
    })
    if(visit(ast.exp,c)!=BoolType) throw TypeMismatchInStatement(ast)
    List(returnlst(1),returnlst(1))     
  }   
  override def visitBreak(ast: Break.type, c: Any): Any = {
    val lst = c.asInstanceOf[List[Any]]
    val isloop = lst(1).asInstanceOf[Boolean]
    if(!isloop) throw BreakNotInLoop
    List(true,false)
  }
  override def visitContinue(ast: Continue.type, c: Any): Any = {
    val lst = c.asInstanceOf[List[Any]]
    val isloop = lst(1).asInstanceOf[Boolean]
    if(!isloop) throw ContinueNotInLoop
    List(true,false)
  }
  override def visitReturn(ast: Return, c: Any): Any = {
    val lst = c.asInstanceOf[List[Any]]
    val rt = lst(0).asInstanceOf[Type]
    ast.expr match {
      case None => if(rt!=VoidType) throw TypeMismatchInStatement(ast)
      case Some(s) => if(!checkType(rt,visit(s,c).asInstanceOf[Type])) throw TypeMismatchInStatement(ast)
    }
    List(true,true) 
  }
//Expression  
  override def visitBinaryOp(ast: BinaryOp, c: Any): Any = {
    (ast.op,visit(ast.left,c),visit(ast.right,c)) match {
      case ("&&"|"||",BoolType,BoolType) => BoolType
      case ("+"|"-"|"*"|"/",IntType,IntType) => IntType
      case ("+"|"-"|"*"|"/",FloatType|IntType,FloatType|IntType) => FloatType
      case ("=",IntType,IntType) => IntType  
      case ("=",FloatType,IntType|FloatType) => FloatType  
      case ("=",StringType,StringType) => StringType  
      case ("=",BoolType,BoolType) => BoolType  
      case (">"|"<"|">="|"<=",IntType|FloatType,IntType|FloatType) => BoolType  
      case ("=="|"!=",IntType,IntType) => BoolType  
      case ("=="|"!=",BoolType,BoolType) => BoolType
      case ("%",IntType,IntType) => IntType   
      case _ => throw TypeMismatchInExpression(ast)    
    }
  }
  override def visitUnaryOp(ast: UnaryOp, c: Any): Any = { 
    (ast.op,visit(ast.body,c)) match {
      case ("-",IntType) => IntType
      case ("-",FloatType) => FloatType
      case ("!",BoolType) => BoolType
      case _ => throw TypeMismatchInExpression(ast)
    } 
  }
  override def visitArrayCell(ast: ArrayCell, c: Any): Any = { 
    (visit(ast.arr,c),visit(ast.idx,c)) match {
      case (ArrayType(_,eleType),IntType) => eleType 
      case (ArrayPointerType(eleType),IntType) => eleType 
      case _ => throw TypeMismatchInExpression(ast) 
    }    
  }
  override def visitCallExpr(ast: CallExpr, c: Any): Any = {
    val lst = c.asInstanceOf[List[Any]]
    val symlst = lst(2).asInstanceOf[List[Symbol]]
    lookup(ast.method.name,symlst,(x:Symbol)=>x.name) match {
      case None => throw Undeclared(Function,ast.method.name)
      case Some(s) => s.typ match {
        case FunctionType(_,_) => {
          val ft = s.typ.asInstanceOf[FunctionType]
          val pt = FunctionType(ast.params.map(visit(_,c)).asInstanceOf[List[Type]],null)
          if(checkType(ft,pt)) ft.output
          else throw TypeMismatchInExpression(ast)
        }
        case _ => throw Undeclared(Function,ast.method.name)  
      } 
    }
  }    
  override def visitId(ast: Id, c: Any): Any = {
    val lst = c.asInstanceOf[List[Any]]
    val symlst = lst(2).asInstanceOf[List[Symbol]]
    lookup(ast.name,symlst,(x:Symbol)=>x.name) match {
      case None => throw Undeclared(Identifier,ast.name)
      case Some(s) => s.typ match {
        case FunctionType(_,_) => throw Undeclared(Identifier,ast.name)
        case _ => s.typ  
      }
    }
  }
  override def visitIntLiteral(ast: IntLiteral, c: Any): Type = IntType
  override def visitFloatLiteral(ast: FloatLiteral, c: Any): Type = FloatType
  override def visitStringLiteral(ast: StringLiteral, c: Any): Type = StringType
  override def visitBooleanLiteral(ast: BooleanLiteral, c: Any): Type = BoolType
//Check Type Params And Return
  def checkType(lhs:Type,rhs:Type):Boolean = (lhs,rhs) match {
    case (FunctionType(fi,_),FunctionType(pi,_)) => 
      if(fi.size==pi.size) Range(0,fi.size).toList.forall(x=>checkType(fi(x),pi(x)))
      else false 
    case (ArrayPointerType(t1),ArrayType(_,t2)) => t1==t2    
    case (VoidType,_) => false 
    case (FloatType,IntType) => true
    case _ => lhs == rhs    
  }
//Symbol Method  
  def toListSymbol(declst:List[Decl],symlst:Any,kind:Kind) =  {
    declst.foldLeft(symlst.asInstanceOf[List[Symbol]])((lst,decl)=> {
      val sym = decl match {
        case VarDecl(n,vt) => Symbol(n.name,vt,null)
        case FuncDecl(n,p,rt,_) => Symbol(n.name,FunctionType(p.map(_.varType),rt),null)
      }
      val k = sym.typ match {
        case FunctionType(_,_) => Function
        case _ => kind match {
          case Parameter => Parameter
          case _ => Variable 
        }
      } 
      lookup(sym.name,lst,(s:Symbol) => s.name) match {
        case None => sym::lst
        case Some(_) => throw Redeclared(k,sym.name) 
      }
    })
  }
}
//Symbol
case class Symbol(name:String,typ:Type,value:Val)
trait Val
case class FunctionType(input:List[Type],output:Type) extends Type