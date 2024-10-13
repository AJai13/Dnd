package racas
import up.dndback.Personagem

class Gnomo : racas.iRacas {
    override val nome = "Gnomo"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.inteligencia += 2
    }
}
