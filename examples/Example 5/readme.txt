EXAMPLE 5 - NO REGULAR EXPRESSION COULD BE FOUND:

==== Purpose ====

Our tool is ready for any automaton provided the correct states and transition to/from every state are correctly specified.
Sometimes, an automaton has a initial state that can't reach the final states. Our tool make sure to advert the user to such fact.
 
==== Expected result ==== 

The generated automaton and the string "No regular expression could be found" where would otherwise be the resulting regular expression.