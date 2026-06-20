
/***********************************************************************************************************************
 ** EXERCÍCIO **
Escreva o predicado `conta_ocorrencias(L,X,K)` que é verdadeiro quando o elemento X ocorre K vezes na lista L.

Exemplos:
   ?- conta_ocorrencias([a,b,c,d,a],a,K).
   K  =  2.
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>

contado([],_,K,K).
contado([A|B], A, K, J) :-
   K1 is K+1,
   contado(B,A,K1, J).
contado([A|B], C, K, J) :-
   A \= C,
   contado(B,C,K,J).

conta_ocorrencias(L,X,K) :-
   contado(L,X,0,K).




/***********************************************************************************************************************
 * CASOS DE TESTE (não modifique o restante do arquivo)
 * Para executar os testes, use o comando abaixo no terminal:
 *    $ swi-prolog.swipl -s <nome_do_arquivo.pl> -g "run_tests" -t halt
 **********************************************************************************************************************/

:- use_module(library(plunit)).
:- begin_tests(exercicio).

test('conta_ocorrencias([a,b,c,d,a],a,K)', all(K == [2])) :- conta_ocorrencias([a,b,c,d,a],a,K).
test('conta_ocorrencias([a,b,c,d,a],b,K)', all(K == [1])) :- conta_ocorrencias([a,b,c,d,a],b,K).
test('conta_ocorrencias([a,b,c,d,a],c,K)', all(K == [1])) :- conta_ocorrencias([a,b,c,d,a],c,K).
test('conta_ocorrencias([a,b,c,d,a],d,K)', all(K == [1])) :- conta_ocorrencias([a,b,c,d,a],d,K).
test('conta_ocorrencias([a,b,c,d,a],e,K)', all(K == [0])) :- conta_ocorrencias([a,b,c,d,a],e,K).

:- end_tests(exercicio).
