.source MCClass.java
.class public MCClass
.super java.lang.Object
.field static a [I

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

.method public static <clinit>()V
	iconst_3
	newarray int
	putstatic MCClass.a [I
	return
.limit stack 1
.limit locals 0
.end method

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	getstatic MCClass.a [I
	iconst_0
	iconst_1
	iastore
	getstatic MCClass.a [I
	iconst_0
	iaload
	invokestatic io/putIntLn(I)V
Label1:
	return
.limit stack 3
.limit locals 1
.end method
