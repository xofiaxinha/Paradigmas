/***********************************************************************************************************************
 ** EXERCÍCIO **
Escreva o predicado `compress(L,R)`, que remove repetições consecutivas de elementos.

Exemplos:

   compress([a,a,a,b,b,c,c,c,a],R).
   R = [a,b,c,a].

   compress([1,1,2,2,2,3,1,1],R).
   R = [1,2,3,1].

A implementação deve utilizar recursão de cauda com acumulador.
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>

excloi([],_,[]).
excloi([A|L], E, [A|L]):-
    A \= E.

excloi([E|L], E, R) :-
    excloi(L, E, R).

compress([],[]).
compress([A|L], [A|R]) :-
    excloi(L, A, R1),
    compress(R1, R).
/***********************************************************************************************************************
 * CASOS DE TESTE
 **********************************************************************************************************************/

:- use_module(library(plunit)).
:- begin_tests(exercicio).

test('lista vazia', [nondet]) :-
    compress([],[]).

test('sem repeticoes', [nondet]) :-
    compress([a,b,c],[a,b,c]).

test('somente repeticoes', [nondet]) :-
    compress([a,a,a,a],[a]).

test('exemplo 1', [nondet]) :-
    compress([a,a,a,b,b,c,c,c,a],[a,b,c,a]).

test('exemplo 2', [nondet]) :-
    compress([1,1,2,2,2,3,1,1],[1,2,3,1]).

test('alternando', [nondet]) :-
    compress([1,2,1,2,1,2],[1,2,1,2,1,2]).

:- end_tests(exercicio).
