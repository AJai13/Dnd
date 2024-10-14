package classes

import up.dndback.classes.iClasses

class Guerreiro : iClasses {
    override val nome = "Guerreiro"
    override val dadoDeVida = 10 // D10 para Guerreiro

    override fun calcularPontosDeVida(constituicao: Int): Int {
        val modificadorConstituicao = (constituicao - 10) / 2
        return 10 + modificadorConstituicao
    }
}