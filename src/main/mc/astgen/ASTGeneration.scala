package mc.astgen
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ParserRuleContext
import java.io.{PrintWriter,File}
import org.antlr.v4.runtime.ANTLRFileStream
import mc.utils._
import scala.collection.JavaConverters._
import org.antlr.v4.runtime.tree._
import mc.parser._
import mc.parser.MCParser._

class ASTGeneration extends MCBaseVisitor[Any] {

  override def visitProgram(ctx:ProgramContext) = {
    val decl = ctx.declaration.asScala.toList.map(_.accept(this)).asInstanceOf[List[List[Decl]]].flatten
    Program(decl)
  }

  override def visitDeclaration(ctx:DeclarationContext) = {
    ctx.getChild(0).accept(this)
  }

  override def visitVariableDecl(ctx:VariableDeclContext) = { 
    val pType = ctx.primitiveType.accept(this).asInstanceOf[Type]
    ctx.variable.asScala.toList.map(x => {
      val varName = Id(x.ID.getText)
      val varType = if(x.getChildCount == 1) pType else ArrayType(IntLiteral(x.INTLIT.getText.toInt),pType)
      VarDecl(varName,varType)
    })
  }

  override def visitPrimitiveType(ctx:PrimitiveTypeContext) = {
    if(ctx.INT != null) IntType
    else if(ctx.FLOAT != null) FloatType
    else if(ctx.STRING != null) StringType
    else BoolType
  }

  override def visitFunctionDecl(ctx:FunctionDeclContext) = {
    val name = Id(ctx.ID.getText)
    val param = ctx.parameterList.accept(this).asInstanceOf[List[VarDecl]]
    val returnType = ctx.functionType.accept(this).asInstanceOf[Type]
    val body = ctx.blockStatement.accept(this).asInstanceOf[Stmt]
    List(FuncDecl(name,param,returnType,body))
  }

  override def visitFunctionType(ctx:FunctionTypeContext) = {
    if(ctx.primitiveType != null) ctx.primitiveType.accept(this)
    else if(ctx.arrayPointerType != null) ctx.arrayPointerType.accept(this)
    else VoidType
  }

  override def visitArrayPointerType(ctx:ArrayPointerTypeContext) = {
    val eleType = ctx.primitiveType.accept(this).asInstanceOf[Type]
    ArrayPointerType(eleType)
  }

  override def visitParameterList(ctx:ParameterListContext) = {
    ctx.parameterDecl.asScala.toList.map(_.accept(this))

  }

  override def visitParameterDecl(ctx:ParameterDeclContext) = {
    val variable = Id(ctx.ID.getText)
    val varType = if(ctx.getChildCount == 2) ctx.primitiveType.accept(this).asInstanceOf[Type]
                  else ArrayPointerType(ctx.primitiveType.accept(this).asInstanceOf[Type]) 
    VarDecl(variable,varType)
  } 

  override def visitBlockStatement(ctx:BlockStatementContext) = {
    val decl = ctx.declarationPart.accept(this).asInstanceOf[List[VarDecl]]
    val stmt = ctx.statementPart.accept(this).asInstanceOf[List[Stmt]]
    Block(decl,stmt)
  }

  override def visitDeclarationPart(ctx:DeclarationPartContext) = {
    ctx.variableDecl.asScala.toList.map(_.accept(this)).asInstanceOf[List[List[Decl]]].flatten
  }

  override def visitStatementPart(ctx:StatementPartContext) = {
    ctx.statement.asScala.toList.map(_.accept(this))
  }

  override def visitStatement(ctx:StatementContext) = {
    ctx.getChild(0).accept(this)
  }

  override def visitIfStatement(ctx:IfStatementContext) = {
    val expr = ctx.expression.accept(this).asInstanceOf[Expr]
    val thenStmt = ctx.statement(0).accept(this).asInstanceOf[Stmt]
    val elseStmt = if(ctx.statement.size == 1) None else Some(ctx.statement(1).accept(this).asInstanceOf[Stmt]) 
    If(expr,thenStmt,elseStmt)
  }

  override def visitForStatement(ctx:ForStatementContext) = {
    val expr1 = ctx.expression(0).accept(this).asInstanceOf[Expr] 
    val expr2 = ctx.expression(1).accept(this).asInstanceOf[Expr]
    val expr3 = ctx.expression(2).accept(this).asInstanceOf[Expr]
    val loop = ctx.statement.accept(this).asInstanceOf[Stmt]
    For(expr1,expr2,expr3,loop)
  }

  override def visitDoWhileStatement(ctx:DoWhileStatementContext) = {
    val sl = ctx.statement.asScala.toList.map(_.accept(this)).asInstanceOf[List[Stmt]]
    val expr = ctx.expression.accept(this).asInstanceOf[Expr]  
    Dowhile(sl,expr)
  }

  override def visitBreakStatement(ctx:BreakStatementContext) = {
    Break
  }

  override def visitContinueStatement(ctx:ContinueStatementContext) = {
    Continue
  }

  override def visitReturnStatement(ctx:ReturnStatementContext) = {
    Return(if(ctx.expression == null) None else Some(ctx.expression.accept(this).asInstanceOf[Expr]))
  }

  override def visitExpressionStatement(ctx:ExpressionStatementContext) = {
    ctx.expression.accept(this)
  }

  override def visitExpression(ctx:ExpressionContext) = {
    if(ctx.getChildCount == 1) ctx.expression1.accept(this)
    else{
      val op    = ctx.ASSIGN.getText
      val left  = ctx.expression1.accept(this).asInstanceOf[Expr]
      val right = ctx.expression.accept(this).asInstanceOf[Expr]
      BinaryOp(op,left,right)
    }
  }

  override def visitExpression1(ctx:Expression1Context) = {
    if(ctx.getChildCount == 1) ctx.expression2.accept(this)
    else{
      val op    = ctx.OR.getText
      val left  = ctx.expression1.accept(this).asInstanceOf[Expr]
      val right = ctx.expression2.accept(this).asInstanceOf[Expr]
      BinaryOp(op,left,right)
    }
  }

  override def visitExpression2(ctx:Expression2Context) = {
    if(ctx.getChildCount == 1) ctx.expression3.accept(this)
    else{
      val op    = ctx.AND.getText
      val left  = ctx.expression2.accept(this).asInstanceOf[Expr]
      val right = ctx.expression3.accept(this).asInstanceOf[Expr]
      BinaryOp(op,left,right)
    }
  }

  override def visitExpression3(ctx:Expression3Context) = {
    if(ctx.getChildCount == 1) ctx.expression4(0).accept(this)
    else{
      val op    = ctx.getChild(1).getText
      val left  = ctx.expression4(0).accept(this).asInstanceOf[Expr]
      val right = ctx.expression4(1).accept(this).asInstanceOf[Expr]
      BinaryOp(op,left,right)
    }
  }

  override def visitExpression4(ctx:Expression4Context) = {
    if(ctx.getChildCount == 1) ctx.expression5(0).accept(this)
    else{
      val op    = ctx.getChild(1).getText
      val left  = ctx.expression5(0).accept(this).asInstanceOf[Expr]
      val right = ctx.expression5(1).accept(this).asInstanceOf[Expr]
      BinaryOp(op,left,right)
    }   
  }

  override def visitExpression5(ctx:Expression5Context) = {
    if(ctx.getChildCount == 1) ctx.expression6.accept(this)
    else{
      val op    = ctx.getChild(1).getText
      val left  = ctx.expression5.accept(this).asInstanceOf[Expr]
      val right = ctx.expression6.accept(this).asInstanceOf[Expr]
      BinaryOp(op,left,right)
    }       
  }

  override def visitExpression6(ctx:Expression6Context) = {
    if(ctx.getChildCount == 1) ctx.expression7.accept(this)
    else{
      val op    = ctx.getChild(1).getText
      val left  = ctx.expression6.accept(this).asInstanceOf[Expr]
      val right = ctx.expression7.accept(this).asInstanceOf[Expr]
      BinaryOp(op,left,right)       
    }
  }

  override def visitExpression7(ctx:Expression7Context) = {
    if(ctx.getChildCount == 1) ctx.expression8.accept(this)
    else{
      val op    = ctx.getChild(0).getText
      var expr  = ctx.expression7.accept(this).asInstanceOf[Expr]
      UnaryOp(op,expr)
    }
  }

  override def visitExpression8(ctx:Expression8Context) = {
    if(ctx.getChildCount == 1) ctx.expression9.accept(this)
    else{
      val arr   = ctx.expression9.accept(this).asInstanceOf[Expr]
      val idx   = ctx.expression.accept(this).asInstanceOf[Expr]
      ArrayCell(arr,idx)
    }
  }

  override def visitExpression9(ctx:Expression9Context) = {
    if(ctx.literal != null) ctx.literal.accept(this)
    else if(ctx.funcall != null) ctx.funcall.accept(this)
    else if(ctx.ID != null) Id(ctx.ID.getText)
    else ctx.expression.accept(this)
  }

  override def visitLiteral(ctx:LiteralContext) = {
    if(ctx.INTLIT != null) IntLiteral(ctx.INTLIT.getText.toInt)
    else if(ctx.STRINGLIT != null) StringLiteral(ctx.STRINGLIT.getText)
    else if(ctx.FLOATLIT != null) FloatLiteral(ctx.FLOATLIT.getText.toFloat)
    else BooleanLiteral(ctx.BOOLLIT.getText.toBoolean)

  }

  override def visitFuncall(ctx:FuncallContext) = {
    val method = Id(ctx.ID.getText)
    val params = ctx.expressionList.accept(this).asInstanceOf[List[Expr]]
    CallExpr(method,params)
  }

  override def visitExpressionList(ctx:ExpressionListContext) = {
    ctx.expression.asScala.toList.map(_.accept(this))
  }
}
