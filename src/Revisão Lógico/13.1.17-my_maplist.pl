/***********************************************************************************************************************
 ** EXERCÍCIO **
Em Prolog, o predicado `call` permite invocar dinamicamente outro predicado.

Exemplos:

   dobro(X,Y) :- Y is 2*X.

   ?- call(dobro,5,R).
   R = 10.

O comando acima equivale a:

   ?- dobro(5,R).

Outro exemplo:

   quadrado(X,Y) :- Y is X*X.

   ?- call(quadrado,4,R).
   R = 16.

O predicado call permite escrever funções de ordem superior, isto é,
predicados que recebem outros predicados como parâmetro.

Seu objetivo é implementar o predicado:

   my_maplist(Pred,L1,L2)

que aplica Pred a cada elemento de L1 produzindo L2. 

Atenção: note que Pred é uma variável, logo deve iniciar com letra maiúscula. 
A variável Pred é uma variável que recebe um predicado fornecido pelo usuário.

Exemplos:

   dobro(X,Y) :- Y is 2*X.

   ?- my_maplist(dobro,[1,2,3],R).
   R = [2,4,6].

   quadrado(X,Y) :- Y is X*X.

   ?- my_maplist(quadrado,[1,2,3],R).
   R = [1,4,9].
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>

my_maplist(_, [], []).
my_maplist(Pred, [A], [R]) :-
   call(Pred, A, R).
my_maplist(Pred, [A|B], [C|R]) :-
   call(Pred, A, C),
   my_maplist(Pred,B,R).



/***********************************************************************************************************************
 * CASOS DE TESTE
 **********************************************************************************************************************/

:- use_module(library(plunit)).

dobro(X,Y) :- Y is 2*X.
quadrado(X,Y) :- Y is X*X.

:- begin_tests(exercicio).

test('lista vazia', [nondet]) :-
    my_maplist(dobro,[],[]).

test('dobro', [nondet]) :-
    my_maplist(dobro,[1,2,3],[2,4,6]).

test('quadrado', [nondet]) :-
    my_maplist(quadrado,[1,2,3],[1,4,9]).

test('um elemento', [nondet]) :-
    my_maplist(quadrado,[5],[25]).

:- end_tests(exercicio).
