/***********************************************************************************************************************
 ** EXERCÍCIO **
Escreva um predicado `last(List,X)` que seja verdadeiro apenas quando `List`
for uma lista que contém pelo menos um elemento e `X` for o último elemento
dessa lista.

A implementação deve utilizar recursão.

EXEMPLOS:

   ?- last([a],X).
   X = a.

   ?- last([a,b,c],X).
   X = c.

   ?- last([1,2,3,4],X).
   X = 4.

   ?- last([],X).
   false.
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>

last([A], A).
last([A|B], X) :-
    last(B, X).

/***********************************************************************************************************************
 * CASOS DE TESTE (não modifique o restante do arquivo)
 * Para executar os testes, use o comando abaixo no terminal:
 *    $ swipl -s last.pl -g "run_tests" -t halt
 **********************************************************************************************************************/

:- use_module(library(plunit)).
:- begin_tests(exercicio).

test('lista vazia falha', [fail]) :-
    last([],_).

test('um elemento', [nondet]) :-
    last([a],a).

test('dois elementos', [nondet]) :-
    last([a,b],b).

test('três elementos', [nondet]) :-
    last([a,b,c],c).

test('quatro elementos', [nondet]) :-
    last([1,2,3,4],4).

test('átomos', [nondet]) :-
    last([foo,bar,baz],baz).

test('consulta com variável', all(X == [c])) :-
    last([a,b,c],X).

test('último elemento incorreto', [fail]) :-
    last([a,b,c],b).

test('lista maior', [nondet]) :-
    last([1,2,3,4,5,6,7,8,9],9).

:- end_tests(exercicio).
