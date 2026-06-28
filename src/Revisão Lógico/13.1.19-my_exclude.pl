/***********************************************************************************************************************
 ** EXERCÍCIO **
Implemente o predicado

   my_exclude(Pred,L,R)

que remove de L todos os elementos que satisfazem Pred.

Exemplo:

   par(X) :- 0 is X mod 2.

   ?- my_exclude(par,[1,2,3,4,5,6],R).
   R = [1,3,5].
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>

my_exclude(_,[],[]).
my_exclude(Pred, [A|B], R) :-
    call(Pred, A),
    my_exclude(Pred, B, R).

my_exclude(Pred, [A|B], [A|R]) :-
    \+ call(Pred, A),
    my_exclude(Pred, B, R).


/***********************************************************************************************************************
 * CASOS DE TESTE
 **********************************************************************************************************************/

:- use_module(library(plunit)).

par(X) :- 0 is X mod 2.
positivo(X) :- X > 0.

:- begin_tests(exercicio).

test('pares', [nondet]) :-
    my_exclude(par,[1,2,3,4,5,6],[1,3,5]).

test('positivos', [nondet]) :-
    my_exclude(positivo,[-2,-1,0,1,2],[-2,-1,0]).

test('vazia', [nondet]) :-
    my_exclude(par,[],[]).

:- end_tests(exercicio).
