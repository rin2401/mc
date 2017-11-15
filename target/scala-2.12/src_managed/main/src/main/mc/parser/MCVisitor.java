// Generated from src/main/mc/parser/MC.g4 by ANTLR 4.6

  package mc.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MCVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MCParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MCParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(MCParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#variableDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDecl(MCParser.VariableDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(MCParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(MCParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#functionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDecl(MCParser.FunctionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#functionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionType(MCParser.FunctionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#arrayPointerType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayPointerType(MCParser.ArrayPointerTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(MCParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#parameterDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDecl(MCParser.ParameterDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(MCParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#declarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationPart(MCParser.DeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#statementPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementPart(MCParser.StatementPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MCParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(MCParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(MCParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#doWhileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileStatement(MCParser.DoWhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(MCParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(MCParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(MCParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(MCParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MCParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expression1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression1(MCParser.Expression1Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expression2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression2(MCParser.Expression2Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expression3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression3(MCParser.Expression3Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expression4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression4(MCParser.Expression4Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expression5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression5(MCParser.Expression5Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expression6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression6(MCParser.Expression6Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expression7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression7(MCParser.Expression7Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expression8}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression8(MCParser.Expression8Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expression9}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression9(MCParser.Expression9Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(MCParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#funcall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncall(MCParser.FuncallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(MCParser.ExpressionListContext ctx);
}