package classes
import up.dndback.classes.iClasses

class Clerigo : iClasses {
    override val nome = "Clérigo"
    override val dadoDeVida = 8 // D8 para Clérigo

    override fun calcularPontosDeVida(constituicao: Int): Int {
        val modificadorConstituicao = (constituicao - 10) / 2
        return dadoDeVida + modificadorConstituicao
    }
}
