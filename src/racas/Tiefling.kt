package racas
import Personagem
import iRacas

class Tiefling : iRacas {
    override val nome = "Tiefling"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.carisma += 2
        personagem.inteligencia += 1
    }
}
