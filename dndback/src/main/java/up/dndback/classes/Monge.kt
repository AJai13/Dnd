package classes

import up.dndback.classes.iClasses


class Monge : iClasses {
    override val nome = "Monge"
    override val dadoDeVida = 8 // D8 para Monge

    override fun calcularPontosDeVida(constituicao: Int): Int {
        val modificadorConstituicao = (constituicao - 10) / 2
        return dadoDeVida + modificadorConstituicao
    }
}
