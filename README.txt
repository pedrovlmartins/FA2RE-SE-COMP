PROJECT TITLE FA2RE-SE
GROUP 3MIEIC01 – Group 2
Name António Pedro Fraga, Nr up201303095
Name Luís Oliveira, Nr up201304515
Name Miguel Pereira, Nr up201305998
Name Pedro Martins, Nr up201005350

O grupo FA2RE-SE, tal como tinha proposto, melhorou o seu projeto adicionando as seguintes funcionalidades:
 - Aceitação de autómatos NFA com transições espontâneas $ (e-NFAS).
 - Simplificação da expressão regular resultante.

Transições espontâneas são tratadas no projeto com o símbolo cifrão $.
Foram adicionadas várias leis para simplificar a expressão resultante do algoritmo (sendo ø o conjunto vazio, $ a transição espontânea e 'a' uma expressão qualquer):

a + ø = ø + a= a (através de uma reformulação na aceitação de estados mortos)

As seguintes leis foram adicionadas através da definição de regras usando a classe regex (Pattern e Matcher) da biblioteca Java e adicionadas no conversor:

a + a = a
$a = a$ = a
$ + aa* = $ + a*a = a* 
($ + a)* = a*
$$* = $

A implementação das regras seguiu um rumo bastante semelhante: criação de um objeto Pattern com a respetiva expressão regex chamada pela função compile,
criação de um objecto Matcher que associa o Pattern criado com a expressão regular resultante, e a sequência de regras que utilizam a função
replaceAll para substituir/modificar os respetivos padrões que dão match pelos padrões mais simplificados (pelas leis já representadas)

Foi ainda incluída uma função para retirar parênteses desnecessários e outras ambiguidades de fechos de kleene (tais como (a*)* = a*)
Foi adicionada ainda uma nova barra na interface que compara a expressão derivada do algoritmo com a expressão posteriormente simplificada (caso as expressões
regulares sejam iguais, a interface fica igual).

As funcionalidades encontram-se comentadas no código para fácil localização.

