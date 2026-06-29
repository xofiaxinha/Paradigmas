/***********************************************************************************************************************
 ** EXERCÍCIO **
Uma festa possui uma idade mínima para entrada.

Considere que a idade mínima é 18 anos.

Cada convidado é representado por uma lista contendo seu primeiro nome e
sua idade, no formato:

   [Nome,Idade]

Por exemplo:

   [[ana,18],[davi,34],[bia,15]]

Escreva um predicado

   allowed_guests(Guests,Allowed)

que recebe uma lista de convidados e produz uma lista contendo apenas os
nomes das pessoas autorizadas a entrar na festa.

IMPORTANTE:
Sua solução deve utilizar o predicado de ordem superior my_include/3
feito em exercício anterior.

Dica:
Após selecionar os convidados permitidos, você pode utilizar o predicado
my_maplist/3 para extrair apenas os nomes.

Exemplos:

   ?- allowed_guests([[ana,18],[davi,34],[bia,15]],X).
   X = [ana,davi].

   ?- allowed_guests([[joao,12],[maria,14]],X).
   X = [].

   ?- allowed_guests([[pedro,20],[lucia,18]],X).
   X = [pedro,lucia].
***********************************************************************************************************************/

% <FORNEÇA AQUI SUA SOLUÇÃO>

age_ver([_, I]) :- I >= 18.
justName([A,_], A).

my_maplist(_, [], []).
my_maplist(Pred, [A], [R]) :-
   call(Pred, A, R).
my_maplist(Pred, [A|B], [C|R]) :-
   call(Pred, A, C),
   my_maplist(Pred,B,R).

my_include(_,[],[]).
my_include(Pred, [A|B], [A|R]) :-
    call(Pred, A),
    my_include(Pred, B, R).

my_include(Pred, [A|B], R) :-
    \+ call(Pred, A),
    my_include(Pred, B, R).

allowed_guests(L, A) :-
    my_include(age_ver, L, R),
    my_maplist(justName, R, A).



/***********************************************************************************************************************
 * CASOS DE TESTE
 **********************************************************************************************************************/

:- use_module(library(plunit)).
:- begin_tests(exercicio).

test('exemplo 1', [nondet]) :-
    allowed_guests(
        [[ana,18],[davi,34],[bia,15]],
        [ana,davi]
    ).

test('todos menores', [nondet]) :-
    allowed_guests(
        [[joao,12],[maria,14]],
        []
    ).

test('todos maiores', [nondet]) :-
    allowed_guests(
        [[pedro,20],[lucia,18]],
        [pedro,lucia]
    ).

test('lista vazia', [nondet]) :-
    allowed_guests([],[]).

test('idade exatamente 18', [nondet]) :-
    allowed_guests(
        [[carlos,18],[julia,17]],
        [carlos]
    ).

test('misturado', [nondet]) :-
    allowed_guests(
        [[a,10],[b,18],[c,25],[d,16],[e,30]],
        [b,c,e]
    ).

:- end_tests(exercicio).
