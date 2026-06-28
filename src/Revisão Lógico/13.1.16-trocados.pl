/***********************************************************************************************************************
 ** EXERCÍCIO **
Escreva um predicado `swapfl(List1,List2)` que verifica se `List1` é idêntica a `List2`,
exceto que o primeiro e o último elemento são trocados.

A implementação deve utilizar recursão.

EXEMPLOS:

   ?- swapfl([],X).
   false.

   ?- swapfl([a],X).
   false.

   ?- swapfl([a,b],X).
   X = [b,a].

   ?- swapfl([a,b,c],X).
   X = [c,b,a].

   ?- swapfl([a,b,c,d],X).
   X = [d,b,c,a].
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>

last([A], A).
last([A|B], X) :-
    last(B, X).

geraLista([_], C, [C]).
geraLista([A|B], C, [A|R]) :-
    geraLista(B, C, R).

swapfl([A|B], [X|R]) :-
    last([A|B], X),
    geraLista(B, A, R).


/***********************************************************************************************************************
 * CASOS DE TESTE (não modifique o restante do arquivo)
 * Para executar os testes, use o comando abaixo no terminal:
 *    $ swipl -s swapfl.pl -g "run_tests" -t halt
 **********************************************************************************************************************/

:- use_module(library(plunit)).
:- begin_tests(exercicio).

test('lista vazia falha', [fail]) :-
    swapfl([],_).

test('lista unitária falha', [fail]) :-
    swapfl([a],_).

test('dois elementos', all(X == [[b,a]])) :-
    swapfl([a,b],X).

test('três elementos', all(X == [[c,b,a]])) :-
    swapfl([a,b,c],X).

test('quatro elementos', all(X == [[d,b,c,a]])) :-
    swapfl([a,b,c,d],X).

test('números', all(X == [[5,2,3,4,1]])) :-
    swapfl([1,2,3,4,5],X).

test('verificação direta 1', [nondet]) :-
    swapfl([a,b,c],[c,b,a]).

test('verificação direta 2', [nondet]) :-
    swapfl([1,2,3,4],[4,2,3,1]).

test('verificação direta incorreta', [fail]) :-
    swapfl([a,b,c],[a,c,b]).

:- end_tests(exercicio).
