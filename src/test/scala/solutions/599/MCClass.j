.source MCClass.java
.class public MCClass
.super java.lang.Object
.field static x I
.field static y F

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	invokestatic MCClass/fun()V
Label1:
	return
.limit stack 0
.limit locals 1
.end method

.method public static fun()V
.var 0 is arg0 I from Label0 to Label1
Label0:
	iconst_2
	istore_0
.var 1 is arg1 I from Label2 to Label3
Label2:
	iconst_3
	istore_1
	iload_1
	invokestatic io/putIntLn(I)V
Label3:
.var 1 is arg1 Ljava/lang/String; from Label4 to Label5
Label4:
	ldc "hello"
	astore_1
	aload_1
	invokestatic io/putStringLn(Ljava/lang/String;)V
Label5:
	iload_0
	invokestatic io/putIntLn(I)V
Label1:
	return
.limit stack 1
.limit locals 2
.end method

.method public <init>()V
.var 0 is this LMCClass; from Label0 to Label1
Label0:
	aload_0
	invokespecial java/lang/Object/<init>()V
Label1:
	return
.limit stack 1
.limit locals 1
.end method
