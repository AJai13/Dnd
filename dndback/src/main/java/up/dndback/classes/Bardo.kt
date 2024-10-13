package classes
import up.dndback.classes.iClasses

class Bardo : iClasses {
    override val nome = "Bardo"
    override val dadoDeVida = 8 // D8 para Bardo

    override fun calcularPontosDeVida(constituicao: Int): Int {
        val modificadorConstituicao = (constituicao - 10) / 2
        return dadoDeVida + modificadorConstituicao
    }
}
