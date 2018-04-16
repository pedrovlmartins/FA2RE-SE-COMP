PROJECT TITLE FA2RE-SE
GROUP 3MIEIC01 � Group 2
Name Ant�nio Pedro Fraga, Nr up201303095
Name Lu�s Oliveira, Nr up201304515
Name Miguel Pereira, Nr up201305998
Name Pedro Martins, Nr up201005350

O grupo FA2RE-SE, tal como tinha proposto, melhorou o seu projeto adicionando as seguintes funcionalidades:
 - Aceita��o de aut�matos NFA com transi��es espont�neas $ (e-NFAS).
 - Simplifica��o da express�o regular resultante.

Transi��es espont�neas s�o tratadas no projeto com o s�mbolo cifr�o $.
Foram adicionadas v�rias leis para simplificar a express�o resultante do algoritmo (sendo � o conjunto vazio, $ a transi��o espont�nea e 'a' uma express�o qualquer):

a + � = � + a= a (atrav�s de uma reformula��o na aceita��o de estados mortos)

As seguintes leis foram adicionadas atrav�s da defini��o de regras usando a classe regex (Pattern e Matcher) da biblioteca Java e adicionadas no conversor:

a + a = a
$a = a$ = a
$ + aa* = $ + a*a = a* 
($ + a)* = a*
$$* = $

A implementa��o das regras seguiu um rumo bastante semelhante: cria��o de um objeto Pattern com a respetiva express�o regex chamada pela fun��o compile,
cria��o de um objecto Matcher que associa o Pattern criado com a express�o regular resultante, e a sequ�ncia de regras que utilizam a fun��o
replaceAll para substituir/modificar os respetivos padr�es que d�o match pelos padr�es mais simplificados (pelas leis j� representadas)

Foi ainda inclu�da uma fun��o para retirar par�nteses desnecess�rios e outras ambiguidades de fechos de kleene (tais como (a*)* = a*)
Foi adicionada ainda uma nova barra na interface que compara a express�o derivada do algoritmo com a express�o posteriormente simplificada (caso as express�es
regulares sejam iguais, a interface fica igual).

As funcionalidades encontram-se comentadas no c�digo para f�cil localiza��o.

