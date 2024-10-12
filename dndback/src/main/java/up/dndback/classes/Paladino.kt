package classes
import iClasses

class Paladino : iClasses {
    override val nome = "Paladino"
    override val dadoDeVida = 10 // D10 para Paladino

    override fun calcularPontosDeVida(constituicao: Int): Int {
        val modificadorConstituicao = (constituicao - 10) / 2
        return dadoDeVida + modificadorConstituicao
    }
}
