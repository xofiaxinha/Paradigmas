/***********************************************************************************************************************
 ** EXERCÍCIO **
Em um jogo eletrônico, cada fase concluída gera uma certa quantidade
de pontos.

Escreva um predicado

   total_score(LevelScores,Total)

que recebe uma lista contendo as pontuações obtidas em cada fase e
calcula a pontuação total do jogador.

IMPORTANTE:
Sua solução deve utilizar o predicado de ordem superior my_foldl/4 feito em exercício anterior.

Exemplos:

   ?- total_score([100,250,50,300],X).
   X = 700.

   ?- total_score([],X).
   X = 0.
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>
add(A, Acc, Ret) :- Ret is Acc + A.

my_foldl(_,[],A,A).
my_foldl(Pred,[A|B],Acc,R) :-
    call(Pred, A, Acc, Acc2),
    my_foldl(Pred, B, Acc2, R).

total_score(L, X) :-
    my_foldl(add, L, 0, X).



/***********************************************************************************************************************
 * CASOS DE TESTE
 **********************************************************************************************************************/

:- use_module(library(plunit)).
:- begin_tests(exercicio).

test('lista vazia', [nondet]) :-
    total_score([],0).

test('uma fase', [nondet]) :-
    total_score([100],100).

test('varias fases', [nondet]) :-
    total_score([100,250,50,300],700).

test('zeros', [nondet]) :-
    total_score([0,0,0],0).

test('pontuacao grande', [nondet]) :-
    total_score([500,400,300,200,100],1500).

:- end_tests(exercicio).
