/***********************************************************************************************************************
 ** EXERCÍCIO **
Uma empresa utiliza um esquema simples para gerar senhas numéricas.
Cada dígito deve ser transformado segundo a regra:

   novo = (dígito + 3) mod 10

Escreva um predicado

   password_encode(Digits,Encoded)

que recebe uma lista de dígitos e produz uma nova lista contendo os
dígitos transformados.

IMPORTANTE:
Sua solução deve utilizar o predicado de ordem superior my_maplist/3 feito em um exercício anterior.

Exemplos:

   ?- password_encode([1,2,3,4],X).
   X = [4,5,6,7].

   ?- password_encode([8,9,0],X).
   X = [1,2,3].
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>
encode(X, Y) :- Y is (X + 3) mod 10.

my_maplist(_, [], []).
my_maplist(Pred, [A|B], [C|R]) :-
   call(Pred, A, C),
   my_maplist(Pred,B,R).

password_encode(L, X) :-
    my_maplist(encode,L,X).



/***********************************************************************************************************************
 * CASOS DE TESTE
 **********************************************************************************************************************/

:- use_module(library(plunit)).
:- begin_tests(exercicio).

test('lista vazia', [nondet]) :-
    password_encode([],[]).

test('exemplo 1', [nondet]) :-
    password_encode([1,2,3,4],[4,5,6,7]).

test('exemplo 2', [nondet]) :-
    password_encode([8,9,0],[1,2,3]).

test('todos zeros', [nondet]) :-
    password_encode([0,0,0],[3,3,3]).

:- end_tests(exercicio).
