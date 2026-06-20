package Funcional

/**
 * Nas questões a seguir, você irá implementar funções que utilizam
 * os tipos algébricos (ADT) definidos abaixo.
 */
// Representa uma lista encadeada, onde 'Nil' é o final da lista e
// 'Cons' é um nó com dados do 1o elemento (head) e
// outra lista contendo os elementos restantes (tail). É um tipo recursivo.

fun main() {

    // Função auxiliar para verificar os resultados dos testes (não modifique)
    fun <T> assertEquals(
        funcao: String,
        esperado: T,
        resultado: T,
        fun_compare: (T, T) -> Boolean = { a, b -> a == b }
    ) {
        if (!fun_compare(esperado, resultado)) {
            throw AssertionError("Falha em $funcao: Esperado $esperado, Obtido $resultado")
        }
    }

    // Função auxiliar para comparar duas listas do tipo 'Lista<A>' (utilizada nos casos de teste).
    fun <A> comparaListas(lista1: Lista<A>, lista2: Lista<A>): Boolean = when (lista1) {
        is Nil -> lista2 is Nil
        is Cons -> lista2 is Cons && (lista1.head == lista2.head) && comparaListas(lista1.tail, lista2.tail)
    }

    /**
     * Escreva a função drop, que remove os n primeiros elementos de uma lista.
     * Se o usuário pode passar o valor de n negativo, então nesse caso a lista permanece intacta.
     */
    tailrec fun <A> drop(lista: Lista<A>, n: Int): Lista<A> {
        return when(lista){
            is Nil -> lista
            is Cons -> if(n>0) drop(lista.tail, n-1) else lista
        }
    }


    val lstDrop1: Lista<Int> = Cons(5, Cons(10, Cons(2, Cons(15, Nil))))
    val lstDrop2: Lista<Int> = Cons(10, Cons(2, Cons(15, Nil)))
    val lstDrop3: Lista<Int> = Cons(2, Cons(15, Nil))
    val lstDrop4: Lista<Int> = Cons(15, Nil)
    val lstDrop5: Lista<Int> = Nil
    assertEquals("drop", lstDrop2, drop(lstDrop1,1), ::comparaListas)
    assertEquals("drop", lstDrop3, drop(lstDrop1,2), ::comparaListas)
    assertEquals("drop", lstDrop4, drop(lstDrop1,3), ::comparaListas)
    assertEquals("drop", lstDrop5, drop(lstDrop1,4), ::comparaListas)
    println("Todos os testes passaram para a função drop!")


    /**
     * Escreva a função 'dropWhile' que remove elementos do prefixo de uma Lista<A>
     * enquanto cada um deles satisfizer um predicado f.
     * Exemplo: dada a Lista<Int> a1 = [1,2,34,5,6,7], se invocarmos:
     *      val a2 = a1.dropWhile { it < 8 }
     * então, obteremos a nova lista a2 = [34,5,6,7]
     */
    tailrec fun <A> dropWhile(lista: Lista<A>, f: (A) -> Boolean): Lista<A> =
        when(lista){
            is Nil -> Nil
            is Cons -> if(!f(lista.head)) lista else dropWhile(lista.tail, f)
        }


    val lstDropW1: Lista<Int> = Cons(1, Cons(2, Cons(20, Cons(50, Cons(3, Nil)))))
    val lstDropW2: Lista<Int> = Cons(20, Cons(50, Cons(3, Nil)))
    assertEquals("dropWhile", lstDropW2, dropWhile(lstDropW1){ it < 10 }, ::comparaListas)
    val lstDropW3: Lista<Int> = Cons(14, Cons(13, Cons(12, Cons(11, Cons(10, Cons(2, Cons(15, Nil)))))))
    val lstDropW4: Lista<Int> = Cons(2, Cons(15, Nil))
    assertEquals("dropWhile", lstDropW4, dropWhile(lstDropW3){ it > 2 }, ::comparaListas)
    val lstDropW5: Lista<Int> = Cons(2, Cons(15, Nil))
    val lstDropW6: Lista<Int> = Nil
    assertEquals("dropWhile", lstDropW6, dropWhile(lstDropW5){ it >= 1 }, ::comparaListas)
    println("Todos os testes passaram para a função dropWhile!")


    /**
     * Escreva uma função que remove o último elemento de uma lista
     */
    fun <A> dropLast(lista: Lista<A>): Lista<A> {
        return when(lista){
            is Nil -> Nil
            is Cons -> if(lista.tail == Nil) Nil else Cons(lista.head, dropLast(lista.tail))
        }
    }

    val lstDropL1: Lista<Int> = Cons(1, Cons(2, Cons(20, Cons(50, Cons(3, Nil)))))
    val lstDropL2: Lista<Int> = Cons(1, Cons(2, Cons(20, Cons(50, Nil))))
    assertEquals("dropLast", lstDropL2, dropLast(lstDropL1), ::comparaListas)
    val lstDropL3: Lista<Int> = Cons(14, Cons(13, Cons(12, Cons(11, Cons(10, Cons(2, Cons(15, Nil)))))))
    val lstDropL4: Lista<Int> = Cons(14, Cons(13, Cons(12, Cons(11, Cons(10, Cons(2, Nil))))))
    assertEquals("dropLast", lstDropL4, dropLast(lstDropL3), ::comparaListas)
    val lstDropL5: Lista<Int> = Cons(15, Nil)
    val lstDropL6: Lista<Int> = Nil
    assertEquals("dropLast", lstDropL6, dropLast(lstDropL5), ::comparaListas)
    println("Todos os testes passaram para a função dropLast!")


    /**
     * Escreva a função foldRight (associatividade à direita)
     * Esta função é aplicada sobre uma lista xs = [a_1,a_2,...,a_n] de elementos do tipo A,
     * e recebe como entrada um elemento z do tipo B e uma função f: (A, B) -> B.
     * Essa função então computa f(a_1,f(a_2,...f(a_n, z))...))
     */
    fun <A,B> foldRight(lista: Lista<A>, z: B, f: (A, B) -> B): B =
        when(lista){
            is Nil -> z
            is Cons -> f(lista.head, foldRight(lista.tail, z, f))
        }


    val lstFR: Lista<Int> = Cons(3,Cons(2,Cons(4, Nil)))
    assertEquals("foldRight", 5, foldRight(lstFR, 0){ a, acc -> a - acc }, {a,b -> a==b})
    println("Todos os testes passaram para a função foldRight!")


    /**
     * Escreva a função foldLeft (associatividade à esquerda)
     * Esta função é aplicada sobre uma lista xs = [a_1,a_2,...,a_n] de elementos do tipo A,
     * e recebe como entrada um elemento z do tipo B e uma função f: (B,A) -> B.
     * Essa função então computa f(f(...f(f(f(z,a_1),a_2),a_3)...),a_n)
     * Observação: foldLeft é tailrec por definição.
     */
    tailrec fun <A,B> foldLeft(lista: Lista<A>, z: B, f: (B, A) -> B): B =
        when(lista){
            is Nil -> z
            is Cons -> foldLeft(lista.tail, f(z, lista.head), f)
        }


    val lstFL: Lista<Int> = Cons(3,Cons(2,Cons(4, Nil)))
    assertEquals("foldLeft", 9, foldLeft(lstFL, 0){ acc, a -> acc + a }, {a,b -> a==b})
    val lstFL2: Lista<Int> = Cons(3,Cons(2,Cons(4, Nil)))
    assertEquals("foldLeft", 24, foldLeft(lstFL2, 1){ acc, a -> acc * a }, {a,b -> a==b})
    println("Todos os testes passaram para a função foldLeft!")


    /**
     * Escreva uma função que calcula o comprimento de uma lista.
     * Dica: use foldLeft que é tail-recursive
     */
    fun <A> length(lista: Lista<A>): Int =
        foldLeft(lista, 0){acc, _ -> acc + 1}


    val lstl1: Lista<Int> = Cons(12,Cons(10,Cons(9,Cons(2,Cons(3,Cons(2,Cons(4, Nil)))))))
    assertEquals("length", 7, length(lstl1), {a,b -> a==b})
    val lstl2: Lista<Int> = Cons(45,Cons(99,Cons(77,Cons(12,Cons(10,Cons(9,Cons(2,Cons(3,Cons(2,Cons(4, Nil))))))))))
    assertEquals("length", 10, length(lstl2), {a,b -> a==b})
    val lstl3: Lista<Int> = Nil
    assertEquals("length", 0, length(lstl3), {a,b -> a==b})
    println("Todos os testes passaram para a função length!")


    /**
     * Escreva uma função que retorna o reverso de uma lista, ou seja,
     * dada a lista Lista(1,2,3), ela retorna uma nova lista Lista(3,2,1).
     * Dica: use foldLeft, que é tail-recursive.
     */
    fun <A> reverse(lista: Lista<A>): Lista<A>{
        fun <A> novaLista(lista: Lista<A>, acc: Lista<A>): Lista<A>{
            return when(lista){
                is Nil -> acc
                is Cons -> novaLista(lista.tail, Cons(lista.head, acc))
            }
        }
        return novaLista(lista, Nil)
    }


    val lstRev1: Lista<Int> = Cons(12,Cons(10,Cons(9,Cons(2,Cons(3,Cons(2,Cons(4, Nil)))))))
    val lstRev2: Lista<Int> = Cons(4,Cons(2,Cons(3,Cons(2,Cons(9,Cons(10,Cons(12, Nil)))))))
    assertEquals("reverse", lstRev2, reverse(lstRev1), ::comparaListas)
    println("Todos os testes passaram para a função reverse!")


    /**
     * Implemente a função 'foldRightL', que é uma versão de foldRight em termos da função foldLeft.
     * Veja que isso é útil, pois obteremos uma implementação de foldRight que é tail-recursive,
     * o que significa que ela trabalhará em listas longas sem ocasionar estouro de pilha.
     * Dica: use as funções 'reverse' e 'foldLeft' que foram implementadas acima.
     */
    fun <A,B> foldRightL(lista: Lista<A>, z: B, f: (A, B) -> B): B  =
        TODO()


    /*
    var lst: Lista<Int> = Nil
    for (i in 100000 downTo 1) {
        lst = Cons(i, lst)
    }
    assertEquals("foldRightL", 100000, foldRightL(lst, 0) { _, acc -> acc + 1 }, {a,b -> a == b})
    println("Todos os testes passaram para a função foldRightL!")
    */



    /**
     * Escreva uma função map que transforma cada elemento da lista aplicando
     * uma função f em cada elemento.
     * Dica: Use foldRightL, não implemente do zero.
     */
    fun <A, B> map(lista: Lista<A>, f: (A) -> B): Lista<B> =
        TODO()

    /*
    var lstMap1: Lista<Int> = Nil
    for (i in 1..10) {
        lstMap1 = Cons(i, lstMap1)
    }
    val lstMap2: Lista<Int> = Cons(head=11, tail=Cons(head=10, tail=Cons(head=9, tail=Cons(head=8, tail=Cons(head=7, tail=Cons(head=6, tail=Cons(head=5, tail=Cons(head=4, tail=Cons(head=3, tail=Cons(head=2, Nil))))))))))
    assertEquals("map", lstMap2, map(lstMap1){it+1}, ::comparaListas)
    println("Todos os testes passaram para a função map!")
    */


    /**
     * Escreva uma função filter que remove elementos da lista a menos que eles
     * satisfaçam um dado predicado.
     * Dica: use a função foldRightL, não implemente do zero.
     */
    fun <A> filter(lista: Lista<A>, f: (A) -> Boolean): Lista<A> =
        TODO()

    /*
    var lstFilt1: Lista<Int> = Nil
    for (i in 1..10) {
        lstFilt1 = Cons(i, lstFilt1)
    }
    val lstFilt2: Lista<Int> = Cons(head=10, tail=Cons(head=8, tail=Cons(head=6, tail=Cons(head=4, tail=Cons(head=2, Nil)))))
    assertEquals("filter", lstFilt2, filter(lstFilt1){ it % 2 == 0 }, ::comparaListas)
    println("Todos os testes passaram para a função filter!")
    */

    /**
     * Essa função retorna a cabeça de uma lista.
     * Essa função pode ser utilizada na solução do próximo exercício.
     */
    fun <A> head(lista: Lista<A>): A =
        when (lista) {
            is Nil -> throw IllegalStateException("Nil cannot have a head")
            is Cons -> lista.head
        }

    /**
     * Escreva a função 'suffixes', que calcula todos os sufixos de uma lista.
     * Exemplo: dada a lista a1 = [1,2,3], essa é a lista dos sufixos: [[1,2,3],[2,3],[3],[]]
     * Dica: use a função foldRightL implementada na questão acima.
     */
    fun <A> suffixes(lista: Lista<A>): Lista<Lista<A>> =
        TODO()

    /*
    val lstSuf: Lista<Int> = Cons(1,Cons(2,Cons(3,Cons(4,Nil))))
    val res1: Lista<Lista<Int>> = Cons(Cons(1,Cons(2,Cons(3,Cons(4,Nil)))), Cons(Cons(2,Cons(3,Cons(4,Nil))), Cons(Cons(3,Cons(4,Nil)), Cons(Cons(4,Nil), Cons(Nil, Nil)))))
    assertEquals("suffixes", res1, suffixes(lstSuf), ::comparaListas)
    println("Todos os testes passaram para a função suffixes!")
    */

    /**
     * Escreva a função zipWith que recebe duas listas l1 e l2 e uma função f: (A,A) -> A.
     * A função zipWith cria uma nova lista que é o resultado da aplicação de f a cada
     * par de elementos (l1[i],l2[i]).
     * Por exemplo, se l1 = [1,2,3], l2 = [2,3,4] e f(x,y) = x + y
     * então a lista resultante é l3 = [3,5,7].
     *
     * Mais um exemplo: se l1 = [1,2,3], l2 = [2,3,4,5,6] e f(x,y) = x + y
     * então a lista resultante é l3 = [3,5,7].
     *
     * Mais um exemplo: se l1 = [1,2,3,4,5], l2 = [2,3,4] e f(x,y) = x + y
     * então a lista resultante é l3 = [3,5,7].
     */
    fun <A> zipWith(lista: Lista<A>, lb: Lista<A>, f: (A, A) -> A): Lista<A> =
        TODO()

    /*
    val ll1: Lista<Int> = Cons(1,Cons(2,Cons(3,Cons(4,Nil))))
    val ll2: Lista<Int> = Cons(2,Cons(3,Cons(4,Nil)))
    val ll4: Lista<Int> = Cons(1,Cons(1,Cons(1,Nil)))
    val ll3: Lista<Int> = Cons(head=3, tail=Cons(head=5, tail=Cons(head=7, tail=Nil)))
    val ll5: Lista<Int> = Cons(3,Cons(4,Cons(5,Nil)))
    assertEquals("zipWith", ll3, zipWith(ll1,ll2){ a, b -> a + b }, ::comparaListas)
    assertEquals("zipWith", ll3, zipWith(ll2,ll1){ a, b -> a + b }, ::comparaListas)
    assertEquals("zipWith", ll5, zipWith(ll2,ll4){ a, b -> a + b }, ::comparaListas)
    println("Todos os testes passaram para a função zipWith!")
    */
}
