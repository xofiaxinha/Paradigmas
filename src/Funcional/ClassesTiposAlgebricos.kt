package Funcional

// Representa uma lista encadeada, onde 'Nil' é o final da lista e 'Cons' é um nó com dados do 1o elemento (head) e
// outra lista contendo os elementos restantes (tail). É um tipo recursivo.
sealed class Lista<out A>
object Nil : Lista<Nothing>()
data class Cons<A>(val head: A, val tail: Lista<A>) : Lista<A>()

// A definição de "Forma" aceitas círculos, retângulos e triângulos como formas geométricas (apenas).
sealed class Forma
data class Circulo(val raio: Double) : Forma()
data class Retangulo(val largura: Double, val altura: Double) : Forma()
data class Triangulo(val base: Double, val altura: Double) : Forma()

// Definição recursiva de uma expressão que contém números e permite as operações de adição e multiplicação.
sealed class Expressao
data class Numero(val valor: Int) : Expressao()
data class Adicao(val esquerda: Expressao, val direita: Expressao) : Expressao()
data class Multiplicacao(val esquerda: Expressao, val direita: Expressao) : Expressao()