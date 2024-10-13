package up.dndback.classes

interface iClasses {
    val nome: String
    val dadoDeVida: Int

    fun calcularPontosDeVida(constituicao: Int): Int
}