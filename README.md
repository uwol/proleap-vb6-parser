ANTLR4-based grammar and parser for Visual Basic 6.0
====================================================

<a href="https://travis-ci.org/uwol/vb6grammar"><img src="https://api.travis-ci.org/uwol/vb6grammar.png"></a>

This is an approximate grammar and parser for Visual Basic 6.0, which generates an 
Abstract Syntax Tree (AST) and Abstract Semantic Graph (ASG) for Visual Basic 6.0 code.

The AST represents plain Visual Basic 6.0 source code in a syntax tree structure. 
The ASG is generated from the AST by a semantic analysis and provides data and control 
flow information (e. g. variable access).

The grammar is derived from the Visual Basic 6.0 language reference
http://msdn.microsoft.com/en-us/library/aa338033%28v=vs.60%29.aspx
and tested against MSDN VB6 statement examples as well as several Visual
Basic 6.0 code repositories.


Characteristics
---------------

1. The grammar is line-based and takes into account whitespace, so that
   member calls (e.g. "A.B") are distinguished from contextual object calls
   in WITH statements (e.g. "A .B").

2. Keywords can be used as identifiers depending on the context, enabling
   e.g. "A.Type", but not "Type.B".


Example
-------

### Input: VB6 code

```
Private Sub Command1_Click ()
   Text1.Text = "Hello, world!"
End Sub
```

### Output: abstract syntax tree (AST)

```
(startRule
	(module
		(moduleBody
			(moduleBodyElement
				(subStmt
					(visibility Private) Sub
					(ambiguousIdentifier Command1_Click)
					(argList ( ))
					(block
						(blockStmt
							(letStmt
								(implicitCallStmt_InStmt
									(iCS_S_MembersCall
										(iCS_S_VariableOrProcedureCall
											(ambiguousIdentifier Text1))
										(iCS_S_MemberCall .
											(iCS_S_VariableOrProcedureCall
												(ambiguousIdentifier
													(ambiguousKeyword Text)))))) =
								(valueStmt
									(literal "Hello, world!"))))) End Sub)))) <EOF>)
```


Execution
---------

### Abstract Semantic Graph (ASG) parsing

```java
io.proleap.vb6.parser.applicationcontext.VbParserContextFactory.configureDefaultApplicationContext();

java.io.File inputFile = new java.io.File("src/test/resources/io/proleap/vb6/gpl/parser/HelloWorld.cls");

/*
 * semantic analysis
 */
io.proleap.vb6.parser.metamodel.Program program = io.proleap.vb6.parser.applicationcontext.VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

Module module = program.getClazzModule("HelloWorld");
Variable variableI = module.getVariable("I");
Type typeI = variableI.getType();
Variable variableJ = module.getVariable("J");
Type typeJ = variableI.getType();
```

### Abstract Syntax Tree (AST) parsing

```java
java.io.File inputFile = new java.io.File("src/test/resources/io/proleap/vb6/gpl/HelloWorld.cls");
java.io.InputStream inputStream = new java.io.FileInputStream(inputFile);

/*
* lexer
*/
org.antlr.v4.runtime.ANTLRInputStream antlrInputStream = new org.antlr.v4.runtime.ANTLRInputStream(inputStream);
io.proleap.vb6.VisualBasic6Lexer lexer = new io.proleap.vb6.VisualBasic6Lexer(antlrInputStream);
org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);

/*
* parser
*/
io.proleap.vb6.VisualBasic6Parser parser = new io.proleap.vb6.VisualBasic6Parser(tokens);
io.proleap.vb6.VisualBasic6Parser.StartRuleContext ctx = parser.startRule();

/*
* traverse AST with ANTLR visitor
*/
io.proleap.vb6.VisualBasic6BaseVisitor<Boolean> visitor = new io.proleap.vb6.VisualBasic6BaseVisitor<Boolean>();
visitor.visit(ctx);
```

### Combined Abstract Syntax Tree (AST) and Abstract Semantic Graph (ASG) parsing

```java
io.proleap.vb6.parser.applicationcontext.VbParserContextFactory.configureDefaultApplicationContext();

java.io.File inputFile = new java.io.File("src/test/resources/io/proleap/vb6/gpl/parser/HelloWorld.cls");

/*
 * semantic analysis
 */
io.proleap.vb6.parser.metamodel.Program program = io.proleap.vb6.parser.applicationcontext.VbParserContext.getInstance().getParserRunner().analyzeFile(inputFile);

/*
* traverse AST with ANTLR visitor
*/
io.proleap.vb6.VisualBasic6BaseVisitor<Boolean> visitor = new io.proleap.vb6.VisualBasic6BaseVisitor<Boolean>() {
  @Override
  public Boolean visitVariableSubStmt(final io.proleap.vb6.VisualBasic6Parser.VariableSubStmtContext ctx) {
    io.proleap.vb6.parser.metamodel.Variable variable = (io.proleap.vb6.parser.metamodel.Variable) io.proleap.vb6.parser.applicationcontext.VbParserContext.getInstance().getASGElementRegistry().getASGElement(ctx);
    String name = variable.getName();
    io.proleap.vb6.parser.metamodel.oop.Type type = variable.getType();

    return visitChildren(ctx);
  }
};

for (final io.proleap.vb6.parser.metamodel.Module module : program.getModules()) {
  visitor.visit(module.getCtx());
}
```


VM args
-------

* For parsing large VB6 source code files, following VM args have to be set: -Xmx2048m -XX:MaxPermSize=256m


Build process
-------------

* The build process is based on Maven (version 3 or higher). Building requires a JDK 8.
* To build, run:

```
$ mvn clean package
```

* The test suite executes tests against MSDN statement examples and GPLed VB6 test files. Unit tests and parse tree files were generated by class io.proleap.vb6.TestGenerator from those VB6 test files.
* To only run the tests:

```
$ mvn clean test
```

* You should see output like this:

```
[INFO] Scanning for projects...
...
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running io.proleap.vb6.gpl.calls.CallsTest
Parsing file Calls.cls.
Comparing parse tree with file Calls.cls.tree.
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 6.991 sec
Running io.proleap.vb6.gpl.calls.Module1Test
...
Results :

Tests run: 128, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

* To install the jar in your local Maven repository:

```
$ mvn clean install
```

* To use it in your Maven project, add following dependency to your pom.xml:

```
<dependency>
  <groupId>io.github.uwol</groupId>
  <artifactId>vb6grammar</artifactId>
  <version>1.4.0</version>
</dependency>
```


Release process
---------------

* Milestones are published in the [ANTLR grammars repo](https://github.com/antlr/grammars-v4).


License
-------

Licensed under the BSD 3-Clause License. See LICENSE for details.

### And finally...

Patches accepted, or create an issue!
I'd love, if you could send me a note in which context you're using the grammar. Thank you!
