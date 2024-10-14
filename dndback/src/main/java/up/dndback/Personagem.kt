package up.dndback

import up.dndback.classes.iClasses
import java.util.Locale

class Personagem {

    // Tabela de custo
    private val tabelaCusto = mapOf(
        8 to 0, 9 to 1, 10 to 2, 11 to 3,
        12 to 4, 13 to 5, 14 to 7, 15 to 9
    )

    var nome: String = ""

    var raca: racas.iRacas? = null

    var classe: iClasses? = null

    var forca: Int = 8

    var destreza: Int = 8

    var constituicao: Int = 8

    var inteligencia: Int = 8

    var sabedoria: Int = 8

    var carisma: Int = 8

    var pontosDeVida: Int = 8

    var pontosDisponiveis: Int = 27

    // Método para gastar pontos e aumentar o valor do atributo
    fun aumentarAtributo(atributo: String, novoValor: Int) {
        val atributoAtual = obterValorAtributo(atributo)
        val custoAtual = tabelaCusto[atributoAtual] ?: 0
        val custoNovo = tabelaCusto[novoValor] ?: 0
        val custoTotal = custoNovo - custoAtual

        if (pontosDisponiveis >= custoTotal && novoValor in 8..15) {
            pontosDisponiveis -= custoTotal
            definirValorAtributo(atributo, novoValor)
            println(
                "\nAtributo $atributo atualizado para $novoValor. " +
                        "Pontos restantes: $pontosDisponiveis"
            )
        } else {
            println("\nNão é possível atualizar $atributo para $novoValor. Pontos insuficientes ou valor inválido.")
        }
    }

    // Funções auxiliares
    private fun obterValorAtributo(atributo: String): Int {
        return when (atributo.lowercase(Locale.getDefault())) {
            "forca" -> forca
            "destreza" -> destreza
            "constituicao" -> constituicao
            "inteligencia" -> inteligencia
            "sabedoria" -> sabedoria
            "carisma" -> carisma
            "pontos de vida" -> pontosDeVida
            else -> 8
        }
    }

    private fun definirValorAtributo(atributo: String, valor: Int) {
        when (atributo.lowercase(Locale.getDefault())) {
            "forca" -> forca = valor
            "destreza" -> destreza = valor
            "constituicao" -> constituicao = valor
            "inteligencia" -> inteligencia = valor
            "sabedoria" -> sabedoria = valor
            "carisma" -> carisma = valor
            "pontos de vida" -> pontosDeVida = valor
        }
    }
}