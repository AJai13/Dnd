package classes

import up.dndback.classes.iClasses

class Bruxo : iClasses {
    override val nome = "Bruxo"
    override val dadoDeVida = 8 // D8 para Bruxo

    override fun calcularPontosDeVida(constituicao: Int): Int {
        val modificadorConstituicao = (constituicao - 10) / 2
        return dadoDeVida + modificadorConstituicao
    }
}
