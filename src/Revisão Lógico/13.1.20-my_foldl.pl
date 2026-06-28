/***********************************************************************************************************************
 ** EXERCÍCIO **
Implemente o predicado

   my_foldl(Pred,L,Acc0,AccFinal)

que percorre a lista L da esquerda para a direita,
atualizando um acumulador.

Pred deve possuir a assinatura:

   Pred(Elemento,AccAnterior,AccNovo)

Exemplo:

   soma(X,Acc,Res) :-
       Res is Acc + X.

   ?- my_foldl(soma,[1,2,3,4],0,R).
   R = 10.

Outro exemplo:

   produto(X,Acc,Res) :-
       Res is Acc * X.

   ?- my_foldl(produto,[2,3,4],1,R).
   R = 24.
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>

my_foldl(_,[],A,A).
my_foldl(Pred,[A|B],Acc,R) :-
    call(Pred, A, Acc, Acc2),
    my_foldl(Pred, B, Acc2, R).


/***********************************************************************************************************************
 * CASOS DE TESTE
 **********************************************************************************************************************/

:- use_module(library(plunit)).

soma(X,Acc,Res) :-
    Res is Acc + X.

produto(X,Acc,Res) :-
    Res is Acc * X.

:- begin_tests(exercicio).

test('soma vazia', [nondet]) :-
    my_foldl(soma,[],0,0).

test('soma', [nondet]) :-
    my_foldl(soma,[1,2,3,4],0,10).

test('produto', [nondet]) :-
    my_foldl(produto,[2,3,4],1,24).

test('um elemento', [nondet]) :-
    my_foldl(produto,[5],1,5).

:- end_tests(exercicio).
