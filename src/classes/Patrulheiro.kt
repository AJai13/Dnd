package classes
import iClasses

class Patrulheiro : iClasses {
    override val nome = "Patrulheiro"
    override val dadoDeVida = 10 // D10 para Patrulheiro

    override fun calcularPontosDeVida(constituicao: Int): Int {
        val modificadorConstituicao = (constituicao - 10) / 2
        return dadoDeVida + modificadorConstituicao
    }
}
