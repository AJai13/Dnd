package classes
import up.dndback.classes.iClasses

class Feiticeiro : iClasses {
    override val nome = "Feiticeiro"
    override val dadoDeVida = 6 // D6 para Feiticeiro

    override fun calcularPontosDeVida(constituicao: Int): Int {
        val modificadorConstituicao = (constituicao - 10) / 2
        return dadoDeVida + modificadorConstituicao
    }
}
