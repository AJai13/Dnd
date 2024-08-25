package classes
import iClasses

class Druida : iClasses {
    override val nome = "Druida"
    override val dadoDeVida = 8 // D8 para Druida

    override fun calcularPontosDeVida(constituicao: Int): Int {
        val modificadorConstituicao = (constituicao - 10) / 2
        return dadoDeVida + modificadorConstituicao
    }
}
