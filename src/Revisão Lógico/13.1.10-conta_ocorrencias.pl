
/***********************************************************************************************************************
 ** EXERCÍCIO **
Escreva o predicado `conta_ocorrencias(L,X,K)` que é verdadeiro quando o elemento X ocorre K vezes na lista L.

Exemplos:
   ?- conta_ocorrencias([a,b,c,d,a],a,K).
   K  =  2.
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>

conta([],_,0,0).
conta([],_,K,K):- K \= 0.
conta([A|L],A,C,K) :- 
   K1 is C+1,
   conta(L,A,K1,K).
conta([B|L],A,C,K) :- 
   conta(L,A,C,K),
   B \= A.

conta_ocorrencias(L,E,K):-
   conta(L,E,0,K).

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
