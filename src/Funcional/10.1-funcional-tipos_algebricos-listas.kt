package Funcional

import kotlin.math.PI
import kotlin.math.max
import kotlin.math.pow

/**
 * Nas questões a seguir, você irá implementar funções que utilizam os tipos algébricos (ADT) definidos abaixo.
 */

fun main() {

    // Função auxiliar para verificar os resultados dos testes (não modifique)
    fun <T> assertEquals(funcao: String, esperado: T, resultado: T, fun_compare: (T, T) -> Boolean = { a, b -> a == b }) {
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
     * Calcule a área de uma forma geométrica definida pelo tipo 'Forma'.
     * Se for um círculo, use a fórmula π * raio². Kotlin disponibiliza a constante PI.
     * Se for um retângulo, use a fórmula largura * altura.
     * Se for um triângulo, use a fórmula (base * altura) / 2.
     */
    fun calcularArea(forma: Forma): Double{
        return when(forma){
            is Circulo -> PI * forma.raio.pow(2)
            is Retangulo -> forma.largura * forma.altura
            is Triangulo -> (forma.base * forma.altura) / 2
        }
    }

    assertEquals("calcularArea", 78.53981633974483, calcularArea(Circulo(5.0)))
    assertEquals("calcularArea", 24.0, calcularArea(Retangulo(4.0, 6.0)))
    assertEquals("calcularArea", 12.0, calcularArea(Triangulo(3.0, 8.0)))
    println("Todos os testes passaram para a função calcularArea!")

    /**
     * Determine o valor de uma expressão aritmética definida pelo tipo 'Expressao'.
     * Se for um número, retorne seu valor.
     * Se for uma adição, some os valores das expressões à esquerda e à direita (recursivamente).
     * Se for uma multiplicação, multiplique os valores das expressões à esquerda e à direita (recursivamente).
     * Obs.: Quanto terminar, experimente avaliar apenas número e adição, para ver o erro de compilação indicando que o 
     *       when precisa ser exaustivo (faltando a multiplicação).
     */
    fun avaliarExpressao(expressao: Expressao): Int = when (expressao) {
        is Numero -> expressao.valor
        is Adicao -> avaliarExpressao(expressao.esquerda) + avaliarExpressao(expressao.direita)
        is Multiplicacao -> avaliarExpressao(expressao.esquerda) * avaliarExpressao(expressao.direita)
    }

    assertEquals("avaliarExpressao", 11, avaliarExpressao(Adicao(Numero(5), Multiplicacao(Numero(2), Numero(3)))))
    assertEquals("avaliarExpressao", 10, avaliarExpressao(Numero(10)))
    println("Todos os testes passaram para a função avaliarExpressao!")

    /**
     * Calcule o comprimento de uma Lista representada pelo tipo 'Lista'.
     * Uma lista vazia (Nil) tem comprimento 0.
     * Uma lista com pelo menos um elemento (Cons) tem comprimento 1 + comprimento da lista restante (tail).
     */
    fun <A> comprimentoLista(lista: Lista<A>): Int {
        tailrec fun soma(lista: Lista<A>, acc: Int): Int{
            return when (lista){
                is Nil -> acc
                is Cons -> soma(lista.tail, acc + 1)
            }
        }
        return soma(lista, 0)
    }

    val lista1: Lista<Int> = Cons(1, Cons(2, Cons(3, Nil)))
    val lista2: Lista<String> = Cons("a", Cons("b", Nil))
    assertEquals("comprimentoLista", 3, comprimentoLista(lista1))
    assertEquals("comprimentoLista", 2, comprimentoLista(lista2))
    assertEquals("comprimentoLista", 0, comprimentoLista(Nil))
    println("Todos os testes passaram para a função comprimentoLista!")

    /**
     * Inverta uma lista representada pelo tipo 'Lista'.
     * Crie uma função auxiliar recursiva, que acumula a lista invertida (construída com 'Cons' e inicializada com 'Nil').
     * Obs.: filter/map/fold são definidas para 'list', não para 'Lista'.
     */
    fun <A> inverterLista(lista: Lista<A>): Lista<A> {
        fun inverterAux(lista: Lista<A>, acumulador: Lista<A>): Lista<A> = when (lista) {
            is Nil -> acumulador
            is Cons -> inverterAux(lista.tail, Cons(lista.head, acumulador))
        }
        return inverterAux(lista, Nil)
    }

    val listaInvertida1: Lista<Int> = Cons(3, Cons(2, Cons(1, Nil)))
    val listaInvertida2: Lista<String> = Cons("b", Cons("a", Nil))
    assertEquals("inverterLista", listaInvertida1, inverterLista(lista1), ::comparaListas)
    assertEquals("inverterLista", listaInvertida2, inverterLista(lista2), ::comparaListas)
    assertEquals("inverterLista", Nil, inverterLista(Nil), ::comparaListas)
    println("Todos os testes passaram para a função inverterLista!")

    /**
     * Encontra o máximo valor em uma lista de Int representada pelo tipo 'Lista<Int>'.
     * Para listas vazias, retorne Int.MIN_VALUE.
     */
    fun maximoLista(lista: Lista<Int>): Int = when (lista) {
        Nil -> Int.MIN_VALUE
        is Cons -> when(lista.tail){
            Nil -> lista.head
            else -> max(lista.head, maximoLista(lista.tail))
        }
    }

    val listaMax: Lista<Int> = Cons(5, Cons(10, Cons(2, Cons(15, Nil))))
    assertEquals("maximoLista", 15, maximoLista(listaMax))
    assertEquals("maximoLista", 5, maximoLista(Cons(5, Nil)))
    println("Todos os testes passaram para a função maximoLista!")

    /**
     * Concatena duas Listas representadas pelo tipo 'Lista'.
     * Dica: Percorra recursivamente a primeira lista. 
     *       Recursivamente, adicione a cauda da primeira lista no início da segunda lista.
     */
    fun <A> concatenaListas(lista1: Lista<A>, lista2: Lista<A>): Lista<A> = when (lista1) {
        is Nil -> lista2
        is Cons -> Cons(lista1.head, concatenaListas(lista1.tail, lista2))
    }

    val r1: Lista<Int> = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
    val r2: Lista<String> = Cons("a", Cons("b", Cons("c", Cons("d", Nil))))
    assertEquals("concatenaListas", r1, concatenaListas(Cons(1, Cons(2, Nil)), Cons(3, Cons(4, Nil))), ::comparaListas)
    assertEquals("concatenaListas", r2, concatenaListas(Cons("a", Cons("b", Nil)), Cons("c", Cons("d", Nil))), ::comparaListas)
    assertEquals("concatenaListas", Nil, concatenaListas(Nil, Nil), ::comparaListas)
    println("Todos os testes passaram para a função concatenaListas!")
}