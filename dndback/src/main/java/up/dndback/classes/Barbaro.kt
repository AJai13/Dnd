package classes

import up.dndback.classes.iClasses

class Barbaro : iClasses {
    override val nome = "Bárbaro"
    override val dadoDeVida = 12 // D12 para Bárbaro

    override fun calcularPontosDeVida(constituicao: Int): Int {
        val modificadorConstituicao = (constituicao - 10) / 2
        return dadoDeVida + modificadorConstituicao
    }
}
