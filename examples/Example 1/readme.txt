EXAMPLE 1 - LEXICAL ANALYSIS AND SYNTATICAL ANALYSIS WITH ERROR REPORTING:

==== Purpose ====

This example exists in order to prove that our parser and lexer, generated using ANTLR4, is fully functional.
The parser/lexer are entirely integrated in the program, resulting in errors being thrown at the user after loading the DSL.
This example shows a DSL with two problems:
 - a lexical one (not recognizing token StateEliminatio)
 - a syntatical one (missing ':' after Filename).
The tool doesn't progress while those errors aren't fixed.
 
==== Expected result ==== 

The parser has encountered some errors. Please fix them:

line 1:0 token recognition error at: 'StateEliminatio '
line 1:16 missing 'StateElimination' at '{'
line 2:13 missing ':' at 'teste5.dot'"

The "run DSL" button can't be clicked.