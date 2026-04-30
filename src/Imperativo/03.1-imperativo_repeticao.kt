package Imperativo
fun main() {

    // Função auxiliar para verificar os resultados dos testes (não modifique)
    fun assertEquals(funcao: String, esperado: Any, resultado: Any) {
        if (esperado != resultado) {
            throw AssertionError("Falha em $funcao: Esperado $esperado, Obtido $resultado")
        }
    }

    /**
     * Dados dois valores inteiros A e B recebidos como parâmetro, retorne a soma de todos os valores de A até B (incluindo A e B).
     * Se A for maior que B, retorne 0.
     * Versão usando comandos de repetição.
     */
    fun somaIntervaloRepeticao(a: Int, b: Int): Int {
        if (a>b) return 0
        var soma = 0
        for(i in a..b){
            soma += i
        }
        return soma
    }

    // <INCLUA O TRECHO ABAIXO PARA TESTAR SUA SOLUÇÃO>

    assertEquals("somaIntervaloRepeticao(1, 10)", 55, somaIntervaloRepeticao(1, 10))
    assertEquals("somaIntervaloRepeticao(10, 20)", 165, somaIntervaloRepeticao(10, 20))
    assertEquals("somaIntervaloRepeticao(1, 100)", 5050, somaIntervaloRepeticao(1, 100))
    assertEquals("somaIntervaloRepeticao(10, 5)", 0, somaIntervaloRepeticao(10, 5))
    assertEquals("somaIntervaloRepeticao(10, 10)", 10, somaIntervaloRepeticao(10, 10))
    println("Todos os testes passaram para a função somaIntervaloRepeticao!")


    /**
     * Dados dois valores inteiros A e B recebidos como parâmetro, retorne a soma de todos os valores pares de A até B (possivelmente incluindo A e B).
     * Se A for maior que B, retorne 0.
     * Versão usando comandos de repetição.
     */
    fun somaParesIntervaloRepeticao(a: Int, b: Int): Int {
        if (a>b) return 0
        var soma = 0
        var aux = a
        if(a % 2 != 0) aux = a + 1
        for(i in aux..b step 2){
            soma += i
        }
        return soma
    }

    // <INCLUA O TRECHO ABAIXO PARA TESTAR SUA SOLUÇÃO>

    assertEquals("somaParesIntervaloRepeticao(1, 10)", 30, somaParesIntervaloRepeticao(1, 10))
    assertEquals("somaParesIntervaloRepeticao(10, 20)", 90, somaParesIntervaloRepeticao(10, 20))
    assertEquals("somaParesIntervaloRepeticao(1, 100)", 2550, somaParesIntervaloRepeticao(1, 100))
    assertEquals("somaParesIntervaloRepeticao(10, 5)", 0, somaParesIntervaloRepeticao(10, 5))
    assertEquals("somaParesIntervaloRepeticao(10, 10)", 10, somaParesIntervaloRepeticao(10, 10))
    println("Todos os testes passaram para a função somaParesIntervaloRepeticao!")


    /*
     * Dado um inteiro n, retona o n-ésimo termo da sequência de Fibonacci.
     * Sequência de Fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
     * O termo 0 é o primeiro termo da sequência.
     * O n-ésimo termo é a soma dos dois termos anteriores.
     * Versão usando comandos de repetição.
     */
    fun fibonacci(n: Int): Int {
        var atual = 0
        var proximo = 1
        for(i in 0..n-1){
            var a = proximo
            proximo += atual
            atual = a
        }
        return atual
    }

    // <INCLUA O TRECHO ABAIXO PARA TESTAR SUA SOLUÇÃO>

    assertEquals("fibonacci(0)", 0, fibonacci(0))
    assertEquals("fibonacci(1)", 1, fibonacci(1))
    assertEquals("fibonacci(2)", 1, fibonacci(2))
    assertEquals("fibonacci(3)", 2, fibonacci(3))
    assertEquals("fibonacci(4)", 3, fibonacci(4))
    assertEquals("fibonacci(5)", 5, fibonacci(5))
    assertEquals("fibonacci(6)", 8, fibonacci(6))
    assertEquals("fibonacci(7)", 13, fibonacci(7))
    assertEquals("fibonacci(8)", 21, fibonacci(8))
    println("Todos os testes passaram para a função fibonacci!")

}
