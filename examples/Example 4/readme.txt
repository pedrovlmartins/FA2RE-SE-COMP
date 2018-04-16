EXAMPLE 4 - NONDETERMINISTIC FINITE AUTOMATON ACCEPTANCE:

==== Purpose ====

Using a multimap, our group was able to implement the algorithm accepting NFAs.
This example provides an NFA with transitions to more than one state given the same entry (namely state 1 and 2).
The mode is steps, showing to the user how the states were eliminated.
 
==== Expected result ==== 

A window with the automaton, the regular expression derived from it and a log with the elimination steps the algorithm used to do its job.

The regular expression should be this:

(a+ab*b)(a(a+ab*b)+b)*

The steps should be this:

Steps removal for state 2
-- Constructed path from state 1 to state 3: (a+ab*b)
Steps removal of initial and final states relations:
-- Added loop from final state to initial state: a(a+ab*b)
-- Added loop in final state transition to himself: ((a(a+ab*b)+b))*