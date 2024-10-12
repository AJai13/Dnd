package classes
import iClasses

class Mago : iClasses {
    override val nome = "Mago"
    override val dadoDeVida = 6 // D6 para Mago

    override fun calcularPontosDeVida(constituicao: Int): Int {
        val modificadorConstituicao = (constituicao - 10) / 2
        return dadoDeVida + modificadorConstituicao
    }
}
