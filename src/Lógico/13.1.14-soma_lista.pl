/***********************************************************************************************************************
 ** EXERCÍCIO **
Escreva o predicado `sum_list_tail(L,S)`, que é verdadeiro quando S é a soma de todos os elementos
numéricos da lista L.

A implementação deve utilizar recursão de cauda com acumulador.

Exemplos:

   sum_list_tail([1,2,3,4],S).
   S = 10.

   sum_list_tail([],S).
   S = 0.
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>

sum([], A, A).
sum([A|B], Acc, R) :-
    A1 is Acc + A,
    sum(B, A1, R).

sum_list_tail(A, S) :- sum(A, 0, S).


/***********************************************************************************************************************
 * CASOS DE TESTE
 **********************************************************************************************************************/

:- use_module(library(plunit)).
:- begin_tests(exercicio).

test('lista vazia') :-
    sum_list_tail([],0).

test('um elemento') :-
    sum_list_tail([5],5).

test('varios elementos') :-
    sum_list_tail([1,2,3,4],10).

test('com zero') :-
    sum_list_tail([0,1,2],3).

test('todos zeros') :-
    sum_list_tail([0,0,0],0).

test('maiores') :-
    sum_list_tail([10,20,30],60).

:- end_tests(exercicio).
