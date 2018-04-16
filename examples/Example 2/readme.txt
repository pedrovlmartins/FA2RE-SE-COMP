EXAMPLE 2 - SEMANTICAL ANALYSIS WITH ERROR REPORTING:

==== Purpose ====

Although not exactly "Semantical analysis" in the way it was thought during the semester, after the correct lexical/syntatical analysis there's a few more things to be checked
before the tool can be ran. This example proves the following points:

 - our DSL can't run a ".dot" file it can't find. The .dot file must be in the directory it exists.
 - if indeed the .dot file can be found, it can't run if the states in the Ordering part aren't valid states (non-initial, non-final).
 
In this case, the 1 in Ordering is initial, therefore the ordering is semantically invalid.
 
==== Expected result ==== 

Error window after clicking "run DSL":

ERROR: File not found
The file couldn't be found or does not exist.

After the file is placed in the directory, the following errors are thrown after clicking "run DSL":

ERROR: Unmatched states
The states in the DSL didn't match the ones in the .dot file.
They have to exist and can't be either the initial or the final states.

The "run DSL" button can be clicked but won't progress to the next window. The .dot image is generated.