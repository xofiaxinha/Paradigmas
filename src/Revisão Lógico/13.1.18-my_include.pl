/***********************************************************************************************************************
 ** EXERCÍCIO **
Implemente o predicado

   my_include(Pred,L,R)

que mantém em R apenas os elementos de L que satisfazem Pred.

Exemplo:

   par(X) :- 0 is X mod 2.

   ?- my_include(par,[1,2,3,4,5,6],R).
   R = [2,4,6].
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>

my_include(_,[],[]).
my_include(Pred, [A|B], [A|R]) :-
    call(Pred, A),
    my_include(Pred, B, R).

my_include(Pred, [A|B], R) :-
    \+ call(Pred, A),
    my_include(Pred, B, R).

/***********************************************************************************************************************
 * CASOS DE TESTE
 **********************************************************************************************************************/

:- use_module(library(plunit)).

par(X) :- 0 is X mod 2.
positivo(X) :- X > 0.

:- begin_tests(exercicio).

test('pares', [nondet]) :-
    my_include(par,[1,2,3,4,5,6],[2,4,6]).

test('positivos', [nondet]) :-
    my_include(positivo,[-2,-1,0,1,2],[1,2]).

test('vazia', [nondet]) :-
    my_include(par,[],[]).

:- end_tests(exercicio).
