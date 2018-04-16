EXAMPLE 3 - DOT REPRESENTATION, ELIMINATION STEPS AND CONVERTED REGULAR EXPRESSION:

==== Purpose ====

This example shows the basic idea behind our work and motivation, and represents the core functionalities of our tool. It focus on four things:
 - how the .dot read from the DSL is parsed with the GraphViz library for Java and printed for the user.
 - how the user can see the elimination steps (provided "steps" is the string in the Mode section) the automaton went from the start until regular expression.
 - the order the algorithm used in order to remove the intermediate states.
 - the converted regular expression with ambiguous parentheses removed.
 
==== Expected result ==== 

A window with the automaton, the regular expression derived from it and a log with the elimination steps the algorithm used to do its job.

The regular expression should be this:

(a+ba*bb)*ba*ba((ba)+(a+bb)ba*ba)*

The steps should be this:

Steps removal for state 3
-- Constructed path from state 2 to state 1: bb
-- Constructed path from state 2 to state 4: ba
-- Constructed path from state 4 to state 1: (a+bb)
-- Constructed path from state 4 to state 4: (ba)
Steps removal for state 2
-- Constructed path from state 1 to state 1: a+ba*bb
-- Constructed path from state 1 to state 3: ba*b
-- Constructed path from state 1 to state 4: ba*ba
Steps removal of initial and final states relations:
-- Added loop from final state to initial state: (a+bb)ba*ba
-- Added loops in both initial and final state transitions to themselves: (a+ba*bb)* and (((ba)+(a+bb)ba*ba))*