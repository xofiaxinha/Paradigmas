package Funcional
fun main() {

    // Função auxiliar para verificar os resultados dos testes (não modifique)
    fun assertEquals(funcao: String, esperado: Any, resultado: Any) {
        if (esperado != resultado) {
            throw AssertionError("Falha em $funcao: Esperado $esperado, Obtido $resultado")
        }
    }

    /**
     * Função que recebe uma lista L e retorna uma nova lista com os elementos de L invertidos.
     * Versão usando recursão de cauda.
     * Dica: Uma opção é passar para a função auxiliar recursiva uma lista com os elementos ainda
     *       não acessados de L e uma nova lista com os elementos invertidos até então.
     *       Na auxiliar recursiva, extraia a cabeça e a cauda da lista usando first() e drop(1).
     */
    fun inverterLista(lista: List<Int>): List<Int> {
        tailrec fun inverte(cabeca: List<Int>, cauda: List<Int>, acc: List<Int> = listOf()): List<Int>{
            if(cauda.isEmpty()) return cabeca + acc
            return inverte(listOf(cauda.first()), cauda.drop(1), cabeca+acc)
        }
        return inverte(listOf(lista.first()), lista.drop(1))
    }

    // <INCLUA O TRECHO ABAIXO PARA TESTAR SUA SOLUÇÃO>

    assertEquals("inverterLista(listOf(1, 2, 3, 4, 5))", listOf(5, 4, 3, 2, 1), inverterLista(listOf(1, 2, 3, 4, 5)))
    assertEquals("inverterLista(listOf(1, 4, 2, 5, 3, 4, 5))", listOf(5, 4, 3, 5, 2, 4, 1), inverterLista(listOf(1, 4, 2, 5, 3, 4, 5)))
    println("Todos os testes passaram para a função inverterLista!")


    /**
     * Função que recebe uma lista L de inteiros e retorna uma nova lista sem os elementos repetidos
     * de L, mantendo a ordem de ocorrência dos elementos. Se houver dois ou mais elementos com o mesmo valor, 
     * então apenas a primeira ocorrência é mantida.
     * Versão usando recursão de cauda.
     * Dica: Uma opção é passar para a função auxiliar recursiva uma lista com os elementos ainda
     *       não acessados de L e uma nova lista com os elementos únicos encontrados até então.
     */
    fun removerRepetidos(lista: List<Int>): List<Int> {
        tailrec fun rem(l1: List<Int>, unicos: List<Int> = listOf()): List<Int>{
            if(l1.isEmpty()) return unicos
            if(l1.first() in unicos) return rem(l1.drop(1), unicos)
            else return rem(l1.drop(1), unicos + l1.first())
        }
        return rem(lista)
    }

    // <INCLUA O TRECHO ABAIXO PARA TESTAR SUA SOLUÇÃO>

    assertEquals("removerRepetidos(listOf(1, 2, 1, 3, 2, 4, 5, 4, 6, 7, 8, 7, 9, 10, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20))",
                 listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20),
                 removerRepetidos(listOf(1, 2, 1, 3, 2, 4, 5, 4, 6, 7, 8, 7, 9, 10, 9, 11,
                                         12, 13, 14, 15, 16, 17, 18, 19, 20)))
    assertEquals("removerRepetidos(listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1))", listOf(1), removerRepetidos(listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)))
    assertEquals("removerRepetidos(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))", listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), removerRepetidos(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
    assertEquals("removerRepetidos(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))",
                 listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                 removerRepetidos(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                                         1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
    println("Todos os testes passaram para a função removerRepetidos!")


    /**
     * Função que recebe duas listas de inteiros e retorna uma nova lista contendo os elementos que 
     * estão em ambas as listas, em ordem crescente e sem repetir elementos.
     * A função de List<Int> que ordena é a função sorted().
     * Versão usando recursão de cauda.
     * Dica: Uma opção é passar para a função auxiliar recursiva os elementos que ainda precisam ser 
     *       acessados da primeira lista, a segunda lista, e uma nova lista com os elementos em comum 
     *       encontrados até então.
     */
    fun interseccaoListas(lista1: List<Int>, lista2: List<Int>): List<Int> {
        tailrec fun inter(l1: List<Int>, l2: List<Int>, inter: List<Int> = listOf()): List<Int>{
            if(l1.isEmpty() || l2.isEmpty()) return inter
            if(l1.first() in l2 && l1.first() !in inter) return inter(l1.drop(1), l2.drop(1), inter + l1.first())
            else return inter(l1.drop(1), l2.drop(1), inter)
        }
        return inter(lista1.sorted(), lista2.sorted())
    }

    // <INCLUA O TRECHO ABAIXO PARA TESTAR SUA SOLUÇÃO>

    assertEquals("interseccaoListas(listOf(1, 2, 3, 4, 5), listOf(1, 2, 3, 4, 5))",
                 listOf(1, 2, 3, 4, 5).sorted(),
                 interseccaoListas(listOf(1, 2, 3, 4, 5), listOf(1, 2, 3, 4, 5)))
    assertEquals("interseccaoListas(listOf(1, 2, 3, 4, 5), listOf(6, 7, 8, 9, 10))",
                 listOf<Int>().sorted(),
                 interseccaoListas(listOf(1, 2, 3, 4, 5), listOf(6, 7, 8, 9, 10)))
    assertEquals("interseccaoListas(listOf(1, 2, 3, 4, 5), listOf(5, 4, 3, 2, 1))",
                 listOf(1, 2, 3, 4, 5).sorted(),
                 interseccaoListas(listOf(1, 2, 3, 4, 5), listOf(5, 4, 3, 2, 1)))
    assertEquals("interseccaoListas(listOf(1, 2, 3, 4, 5), listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))",
                 listOf(1, 2, 3, 4, 5).sorted(),
                 interseccaoListas(listOf(1, 2, 3, 4, 5), listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
    assertEquals("interseccaoListas(listOf(1, 2, 3, 4, 5), listOf(6, 7, 8, 9, 10, 1, 2, 3, 4, 5))",
                 listOf(1, 2, 3, 4, 5).sorted(),
                 interseccaoListas(listOf(1, 2, 3, 4, 5), listOf(6, 7, 8, 9, 10, 1, 2, 3, 4, 5)))
    println("Todos os testes passaram para a função interseccaoListas!")


    /**
     * Função que recebe uma lista L de inteiros e retornar um Pair com o maior e o menor valor da lista.
     * Você pode supor que a lista L original é não vazia.
     * Versão usando recursão de cauda.
     * Dica: Uma opção é passar para a função auxiliar recursiva uma lista com os elementos ainda 
     *       não acessados de L e o maior e menor elementos encontrados até então.
     *       Você pode usar as funções maxOf() e minOf() para pegar o maior e o menor dentre 2 valores, 
     *       respectivamente.
     */
    fun maiorMenorLista(lista: List<Int>): Pair<Int, Int> {
        tailrec fun mm(list: List<Int>, maior: Int = -100, menor: Int = 100): Pair<Int, Int>{
            if(list.isEmpty()) return Pair<Int, Int>(maior, menor)
            return mm(list.drop(1), maxOf(maior, list.first()), minOf(list.first(), menor))
        }
        return mm(lista)
    }

    // <INCLUA O TRECHO ABAIXO PARA TESTAR SUA SOLUÇÃO>

    assertEquals("maiorMenorLista(listOf(1, 2))", Pair(2, 1), maiorMenorLista(listOf(1, 2)))
    assertEquals("maiorMenorLista(listOf(1, 5, 4, 3))", Pair(5, 1), maiorMenorLista(listOf(1, 5, 4, 3)))
    assertEquals("maiorMenorLista(listOf(9, 6, 4, 8, 1, 2))", Pair(9, 1), maiorMenorLista(listOf(9, 6, 4, 8, 1, 2)))
    assertEquals("maiorMenorLista(listOf(9, 6, 4, 8, 1, 2, 1))", Pair(9, 1), maiorMenorLista(listOf(9, 6, 4, 8, 1, 2, 1)))
    println("Todos os testes passaram para a função maiorMenorLista!")

}