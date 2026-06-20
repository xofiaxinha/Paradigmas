package Imperativo
fun main() {

    // Função auxiliar para verificar os resultados dos testes (não modifique)
    fun assertEquals(funcao: String, esperado: Any, resultado: Any) {
        if (esperado != resultado) {
            throw AssertionError("Falha em $funcao: Esperado $esperado, Obtido $resultado")
        }
    }

    /*
        Mário só consegue pular até um bloco acima do nível
        atual, ou descer um bloco. Se a diferença entre os
        níveis for maior que um bloco ele precisará fazer
        um movimento de parkour.

        Dado um cenário, calcule quantos movimentos de parkour
        o Mário vai precisar para percorrer o cenário.

        Um cenário é um vetor de números positivos onde o vetor
        representa a altura dos blocos.

        O vetor 1, 1, 3, 3, 4, 6, 4, 2, 2, 1 seria como o
        seguinte cenário:

        _ _ _ _ _ # _ _ _ _
        _ _ _ _ _ # _ _ _ _
        _ _ _ _ # # # _ _ _
        _ _ # # # # # _ _ _
        _ _ # # # # # # # _
        # # # # # # # # # #
        Nele, o Mário precisa de 4 movimentos de parkour.

        Entrada: lista de inteiros positivos representando o cenário.
        Saída: a quantidade de movimentos de parkour.
     */
    fun parkour(cenario: List<Int>): Int {
        var movimentos = 0
        for(i in 0..cenario.size-2){
            var distancia = cenario[i] - cenario[i+1]
            if(distancia < 0) distancia *= -1
            if(distancia > 1) movimentos++
        }
        return movimentos
    }

    // <INCLUA O TRECHO ABAIXO PARA TESTAR SUA SOLUÇÃO>

    assertEquals("parkour(listOf(1, 1, 3, 3, 4, 6, 4, 2, 2, 1))", 4, parkour(listOf(1, 1, 3, 3, 4, 6, 4, 2, 2, 1)))
    assertEquals("parkour(listOf(1, 2, 1, 2, 3))", 0, parkour(listOf(1, 2, 1, 2, 3)))
    assertEquals("parkour(listOf(4, 2, 5, 4, 5, 3))", 3, parkour(listOf(4, 2, 5, 4, 5, 3)))
    assertEquals("parkour(listOf(1, 3, 5, 7, 6, 5, 4, 3))", 3, parkour(listOf(1, 3, 5, 7, 6, 5, 4, 3)))
    assertEquals("parkour(listOf(4, 2, 5, 3, 6, 4, 3, 4, 3, 2, 3, 2, 3, 5))", 6, parkour(listOf(4, 2, 5, 3, 6, 4, 3, 4, 3, 2, 3, 2, 3, 5)))
    println("Todos os testes passaram para a função parkour!")


    /*
        Baruel Ruel tem muitas figurinhas do álbum de futebol. 
        Ele estava indo para uma feira de troca de figurinhas quando tropeçou e misturou as figurinhas todas. 
        Ele não sabe mais quais figurinhas estão repetidas e tem pra trocar, nem quais estão faltando pra completar a coleção. 
        Ajude Baruel Ruel com essa tarefa.

        Entrada: Número total N de figurinhas do álbum e a lista de figurinhas que Baruel possui.
                 As figurinhas do álbum são numeradas de 1 a N.
        Saída: Uma lista com os números das figurinhas repetidas e outra com os números das figurinhas que faltam 
               (ambas em ordem crescente).

        Exemplos:
        
        Entrada: N = 5, figurinhas = [1, 1, 1, 1, 2, 2, 3, 5]
        Saída: [1, 1, 1, 2], [4]
        Explicação: Baruel tem 4 figurinhas repetidas (3 cópias da 1 e uma da 2) e falta a figurinha 4.

        Entrada: N = 2, figurinhas = [1, 1, 2, 2]
        Saída: [1, 2], []
        Explicação: Baruel tem 2 figurinhas repetidas (1 cópia da 1 e uma da 2) e não falta nenhuma figurinha.

        Entrada: N = 5, figurinhas = [4, 5]
        Saída: [], [1, 2, 3]
        Explicação: Baruel não tem figurinhas repetidas e faltam as figurinhas 1, 2 e 3.
     */
    fun figurinhas(N: Int, figurinhas: List<Int>): Pair<List<Int>, List<Int>> {
        var repetidas = mutableListOf<Int>()
        var faltando = mutableListOf<Int>()
        for(i in 1..N){
            var qtdRepetida = 0
            for(j in 0..figurinhas.size-1){
                if(figurinhas[j] == i){
                    qtdRepetida++
                    if(qtdRepetida > 1) repetidas.add(i)
                }
            }
            if(qtdRepetida == 0) faltando.add(i)
        }

        return Pair(repetidas, faltando)
    }

    // <INCLUA O TRECHO ABAIXO PARA TESTAR SUA SOLUÇÃO>

    assertEquals("figurinhas(5, listOf(1, 1, 1, 1, 2, 2, 3, 5))", Pair(listOf(1, 1, 1, 2), listOf(4)), figurinhas(5, listOf(1, 1, 1, 1, 2, 2, 3, 5)))
    assertEquals("figurinhas(2, listOf(1, 1, 2, 2))", Pair(listOf(1, 2), listOf<Int>()), figurinhas(2, listOf(1, 1, 2, 2)))
    assertEquals("figurinhas(5, listOf(4, 5))", Pair(listOf<Int>(), listOf(1, 2, 3)), figurinhas(5, listOf(4, 5)))
    assertEquals("figurinhas(5, listOf(1, 1, 2, 3, 3))", Pair(listOf(1, 3), listOf(4, 5)), figurinhas(5, listOf(1, 1, 2, 3, 3)))
    println("Todos os testes passaram para a função figurinhas!")


    /*
        Dados dois vetores, verifique se o primeiro está contido no segundo (ignorando os repetidos).
        Entrada: duas listas de inteiros.
        Saída: true se o primeiro vetor está contido no segundo, false caso contrário.

        Restrição: Para essa questão, está proibido usar as funções in ou !in

        Exemplos:

        Entrada: [2, 1, 3], [3, 1, 5, 3]
        Saída: false

        Entrada: [2, 1, 3], [3, 6, 5, 3]
        Saída: false

        Entrada: [3, 1, 3, 2], [6, 1, 5, 3, 6, 8, 2]
        Saída: true
     */
    fun contido(vetor1: List<Int>, vetor2: List<Int>): Boolean {
        for(i in vetor1){
            var achou = false
            for(j in vetor2){
                if(i == j){
                    achou = true
                }
            }
            if(achou == false) return false
        }
        return true
    }

    // <INCLUA O TRECHO ABAIXO PARA TESTAR SUA SOLUÇÃO>

    assertEquals("contido(listOf(1, 2, 3, 4), listOf(2, 3, 4, 1, 8))", true, contido(listOf(1, 2, 3, 4), listOf(2, 3, 4, 1, 8)))
    assertEquals("contido(listOf(7, 8), listOf(3, 4, 5))", false, contido(listOf(7, 8), listOf(3, 4, 5)))
    assertEquals("contido(listOf(3, 4, 4, 5), listOf(3, 4, 4, 5, 1))", true, contido(listOf(3, 4, 4, 5), listOf(3, 4, 4, 5, 1)))
    assertEquals("contido(listOf(3, 7, 4, 5), listOf(3, 4, 4, 5, 1))", false, contido(listOf(3, 7, 4, 5), listOf(3, 4, 4, 5, 1)))
    assertEquals("contido(listOf(3, 4, 3, 2), listOf(3, 4, 5, 7, 3))", false, contido(listOf(3, 4, 3, 2), listOf(3, 4, 5, 7, 3)))
    println("Todos os testes passaram para a função contido!")


    /*
        Forneça uma função que recebe uma lista de inteiros e retorna uma lista com os mesmos elementos em ordem não decrescente, 
        utilizando o método de ordenação por seleção (selection sort).
        Obs.: Não use funções prontas de ordenação, como sort ou sorted (está proibido).

        Exemplo:
        Entrada: [3, 1, 4, 1, 5, 9]
        Saída:   [1, 1, 3, 4, 5, 9]

        O algoritmo funciona assim:
        1. Percorra a lista e encontre o índice do menor elemento.
        2. Troque o menor elemento com o primeiro elemento da lista.
        3. Percorra a lista (ignorando o primeiro elemento) e encontre o índice do segundo menor elemento.
        4. Troque o segundo menor elemento com o segundo elemento da lista.
        5. Repita o processo para o restante da lista, ignorando os elementos anteriores que já foram ordenados.
        4. Continue até que toda a lista esteja ordenada.
        5. Retorne a lista ordenada.
     */
    fun ordenacaoPorSelecao(lista: List<Int>): List<Int> {
        var novaLista = lista.toMutableList()
        for(i in 0..novaLista.size-1){
            var aux = novaLista[i]
            var indiceMenor = i
            for(j in i+1..novaLista.size-1){
                if(novaLista[j] < aux) {
                    aux = novaLista[j]
                    indiceMenor = j
                }
            }// achar o menor elemento
            // trocar os dois de lugar
            aux = novaLista[i]
            novaLista[i] = novaLista[indiceMenor]
            novaLista[indiceMenor] = aux
        }
        return novaLista
    }

    // <INCLUA O TRECHO ABAIXO PARA TESTAR SUA SOLUÇÃO>

    assertEquals("ordenacaoPorSelecao(listOf(3, 1, 4, 1, 5, 9))", listOf(1, 1, 3, 4, 5, 9), ordenacaoPorSelecao(listOf(3, 1, 4, 1, 5, 9)))
    assertEquals("ordenacaoPorSelecao(listOf(5, 4, 3, 2, 1))", listOf(1, 2, 3, 4, 5), ordenacaoPorSelecao(listOf(5, 4, 3, 2, 1)))
    assertEquals("ordenacaoPorSelecao(listOf(1, 2, 3, 4, 5))", listOf(1, 2, 3, 4, 5), ordenacaoPorSelecao(listOf(1, 2, 3, 4, 5)))
    assertEquals("ordenacaoPorSelecao(listOf(3, 2, 1))", listOf(1, 2, 3), ordenacaoPorSelecao(listOf(3, 2, 1)))
    assertEquals("ordenacaoPorSelecao(listOf(1, 3, 2))", listOf(1, 2, 3), ordenacaoPorSelecao(listOf(1, 3, 2)))
    println("Todos os testes passaram para a função ordenacaoPorSelecao!")


    /*
        Dada uma lista com zeros e uns, calcule a quantidade de zeros que não é vizinha de um 1.
        Um zero é vizinho de um 1 se o 1 está na posição imediatamente anterior ou posterior ao zero.
        Entrada: lista de inteiros (0s e 1s).
        Saída: a quantidade de zeros que não são vizinhos de um 1.

        Considere que a lista tem pelo menos um elemento.

        Exemplos:
        Entrada: [0, 1, 0, 0, 1]
        Saída: 0

        Entrada: [0, 0, 1, 0, 0]
        Saída: 2

        Entrada: [0, 0, 0, 0, 0]
        Saída: 5
     */
    fun vizinhos(lista: List<Int>): Int {
        var v = 0
        if(lista.size == 1){ // casos lista unitária
            when(lista[0]){
                0 -> return 1
                1 -> return 0
            }
        }
        if(lista[0] == 0 && lista[1] == 0) v++// se o primeiro elemento for 0 e o seu vizinho for 0, conta
        for(i in 1..lista.size-2){
            if(lista[i] == 0){
                if(lista[i-1] == 0 && lista[i+1] == 0) v++
            }
        }
        if(lista[lista.size-1] == 0 && lista[lista.size-2] == 0) v++ // se o último elemento for 0 e o seu vizinho for 0, conta
        return v
    }

    // <INCLUA O TRECHO ABAIXO PARA TESTAR SUA SOLUÇÃO>

    assertEquals("vizinhos(listOf(0,1))", 0, vizinhos(listOf(0, 1))) 
    assertEquals("vizinhos(listOf(1,1))", 0, vizinhos(listOf(1, 1)))
    assertEquals("vizinhos(listOf(0))", 1, vizinhos(listOf(0)))
    assertEquals("vizinhos(listOf(1))", 0, vizinhos(listOf(1)))
    assertEquals("vizinhos(listOf(0, 1, 0, 0, 1))", 0, vizinhos(listOf(0, 1, 0, 0, 1)))
    assertEquals("vizinhos(listOf(0, 0, 0, 0, 0))", 5, vizinhos(listOf(0, 0, 0, 0, 0)))
    assertEquals("vizinhos(listOf(1, 1, 0, 0))", 1, vizinhos(listOf(1, 1, 0, 0)))
    assertEquals("vizinhos(listOf(0, 0, 1, 0, 0))", 2, vizinhos(listOf(0, 0, 1, 0, 0)))
    assertEquals("vizinhos(listOf(1, 0, 0, 0, 0, 0, 1))", 3, vizinhos(listOf(1, 0, 0, 0, 0, 0, 1)))
    assertEquals("vizinhos(listOf(0, 1, 0, 0, 1, 0))", 0, vizinhos(listOf(0, 1, 0, 1, 0)))
    println("Todos os testes passaram para a função vizinhos!")

}