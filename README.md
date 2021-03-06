# FA2RE-SE

The framework receives the finite automaton in dot
format and DSL code with rules for
selecting each state to eliminate, influencing the ordering.
The project includes parsing the dot file
and representing the finite automaton as a graph, parsing the
DSL code and then use the rules specified
in the DSL code in the context of the state
elimination method. The output of the
framework is a regular expression
representing the input FA language and
also a report of the steps performed
during the conversion. 

| Successful parsing | Unsuccessful parsing | Output example |
|---|---|---|
| ![Success](https://raw.githubusercontent.com/pedrovlmartins/FA2RE-SE-COMP/master/testsuite/success.JPG)  | ![Unsuccess](https://raw.githubusercontent.com/pedrovlmartins/FA2RE-SE-COMP/master/testsuite/unsuccess.JPG)  | ![Input](https://raw.githubusercontent.com/pedrovlmartins/FA2RE-SE-COMP/master/testsuite/parsingResult.PNG)  |

Project developed by me, [Pedro Fraga](https://github.com/pedrofraga), [Miguel Pereira](https://github.com/mgpsp) and [Luís Oliveira](https://github.com/luisoliveira8).
