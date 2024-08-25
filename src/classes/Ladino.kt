package classes
import iClasses

class Ladino : iClasses {
    override val nome = "Ladino"
    override val dadoDeVida = 8 // D8 para Ladino

    override fun calcularPontosDeVida(constituicao: Int): Int {
        val modificadorConstituicao = (constituicao - 10) / 2
        return dadoDeVida + modificadorConstituicao
    }
}
