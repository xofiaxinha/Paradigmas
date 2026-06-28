/***********************************************************************************************************************
 ** EXERCÍCIO **
Escreva o predicado `quicksort(L,Sorted)`: Recebe como primeiro componente 
uma lista de inteiros L e a ordena em ordem crescente em Sorted. O predicado é
recursivo, logo deve ter caso base e caso geral. O algoritmo Quicksort funciona da
seguinte maneira: 
1. Dada uma lista L e um elemento pivô K pertencente a L, particionamos a lista L em duas outras 
   listas Left e Right tal que: todos os elementos de Left são menores ou iguais ao pivô K,
   e todos os elementos de Right são maiores que o pivô K.
2. Ordenamos a lista Left recursivamente.
3. Ordenamos a lista Right recursivamente.
4. Concatenamos as listas Left e Right com o elemento pivô entre elas: [Left|K|Right]

Exemplos:
   ?- quicksort([33,1,7,9,3,0,2], Sorted).
   Sorted  =  [0,1,2,3,7,9,33].
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>
partition(_, [], [], []).
partition(E, [A|B], [A|L], R) :-
    partition(E, B, L, R),
    A =< E.
partition(E, [A|B], L, [A|R]) :-
    partition(E,B,L,R),
    A > E.


quicksort([],[]).
quicksort([P|L], S) :-
    partition(P, L, L1, L2),
    quicksort(L1, S1),
    quicksort(L2, S2),
    append(S1, [P|S2], S).



/***********************************************************************************************************************
 * CASOS DE TESTE (não modifique o restante do arquivo)
 * Para executar os testes, use o comando abaixo no terminal:
 *    $ swi-prolog.swipl -s <nome_do_arquivo.pl> -g "run_tests" -t halt
 **********************************************************************************************************************/

:- use_module(library(plunit)).
:- begin_tests(exercicio).

test('lista vazia',
     all(Sorted == [[]])) :-
    quicksort([], Sorted).

test('lista unitária',
     all(Sorted == [[5]])) :-
    quicksort([5], Sorted).

test('exemplo do enunciado',
     all(Sorted == [[0,1,2,3,7,9,33]])) :-
    quicksort([33,1,7,9,3,0,2], Sorted).

test('já ordenada',
     all(Sorted == [[1,2,3,4,5]])) :-
    quicksort([1,2,3,4,5], Sorted).

test('ordem inversa',
     all(Sorted == [[1,2,3,4,5]])) :-
    quicksort([5,4,3,2,1], Sorted).

test('elementos repetidos',
     all(Sorted == [[1,2,2,3,3,3,5]])) :-
    quicksort([3,2,5,3,1,2,3], Sorted).

test('números negativos',
     all(Sorted == [[-5,-2,0,3,7]])) :-
    quicksort([0,-2,7,-5,3], Sorted).

:- end_tests(exercicio).