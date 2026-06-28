/***********************************************************************************************************************
 ** EXERCÍCIO **
Escreva o predicado `remove_all(E,L,R)`, que é verdadeiro quando R é obtida removendo todas as
ocorrências do elemento E da lista L.

A implementação deve utilizar recursão de cauda com acumulador.

Exemplos:

   remove_all(a,[a,b,a,c,a],R).
   R = [b,c].

   remove_all(2,[1,2,3,2,4],R).
   R = [1,3,4].
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>

remove_all(_, [], []).
remove_all(E, [E|L], R) :-
    remove_all(E, L, R).
remove_all(E, [A|L], [A|R]) :-
    remove_all(E, L, R),
    E \= A.



/***********************************************************************************************************************
 * CASOS DE TESTE
 **********************************************************************************************************************/

:- use_module(library(plunit)).
:- begin_tests(exercicio).

test('lista vazia', [nondet]) :-
    remove_all(a,[],[]).

test('remove varios', [nondet]) :-
    remove_all(a,[a,b,a,c,a],[b,c]).

test('remove nenhum', [nondet]) :-
    remove_all(z,[a,b,c],[a,b,c]).

test('remove todos', [nondet]) :-
    remove_all(1,[1,1,1],[]).

test('numeros', [nondet]) :-
    remove_all(2,[1,2,3,2,4],[1,3,4]).

:- end_tests(exercicio).
