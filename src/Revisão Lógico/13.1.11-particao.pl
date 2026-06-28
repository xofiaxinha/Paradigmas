/***********************************************************************************************************************
 ** EXERCÍCIO **
Escreva o predicado `partition(Pivot,A,L,R)`: esse predicado recebe como
componentes um elemento pivô (Pivot), uma lista de inteiros A e particiona A em
duas listas L e R tal que todos os elementos de L são menores ou
iguais ao pivô e todos os elementos de R são maiores que o pivô.
Atenção: O predicado é recursivo, logo ele deve ser dividido em
caso base e caso geral. Pode haver mais de um caso geral.

Exemplos:
   ?- partition(5,[33,1,7,9,3,0,2],L,R).
   L  =  [1,3,0,2].
   R  =  [33,7,9].
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>

partition(_, [], [], []).
partition(E, [A|B], [A|L], R) :-
    partition(E, B, L, R),
    A =< E.
partition(E, [A|B], L, [A|R]) :-
    partition(E,B,L,R),
    A > E.

/***********************************************************************************************************************
 * CASOS DE TESTE (não modifique o restante do arquivo)
 * Para executar os testes, use o comando abaixo no terminal:
 *    $ swi-prolog.swipl -s <nome_do_arquivo.pl> -g "run_tests" -t halt
 **********************************************************************************************************************/

:- use_module(library(plunit)).
:- begin_tests(exercicio).

test('partition exemplo do enunciado',
     all((L,R) == [([1,3,0,2],[33,7,9])])) :-
    partition(5,[33,1,7,9,3,0,2],L,R).

test('lista vazia',
     all((L,R) == [([],[])])) :-
    partition(5,[],L,R).

test('todos menores ou iguais ao pivo',
     all((L,R) == [([1,2,3,4,5],[])])) :-
    partition(5,[1,2,3,4,5],L,R).

test('todos maiores que o pivo',
     all((L,R) == [([],[6,7,8,9])])) :-
    partition(5,[6,7,8,9],L,R).

test('elementos iguais ao pivo ficam em L',
     all((L,R) == [([5,5,1,5],[7,8])])) :-
    partition(5,[5,7,5,1,8,5],L,R).

test('lista unitária menor',
     all((L,R) == [([3],[])])) :-
    partition(5,[3],L,R).

test('lista unitária igual',
     all((L,R) == [([5],[])])) :-
    partition(5,[5],L,R).

test('lista unitária maior',
     all((L,R) == [([],[8])])) :-
    partition(5,[8],L,R).

test('preserva ordem relativa dos elementos',
     all((L,R) == [([4,1,3,2],[8,9,7])])) :-
    partition(4,[4,8,1,9,3,7,2],L,R).

:- end_tests(exercicio).