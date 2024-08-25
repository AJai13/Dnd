package racas
import Personagem
import iRacas

class Gnomo : iRacas {
    override val nome = "Gnomo"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.inteligencia += 2
    }
}
