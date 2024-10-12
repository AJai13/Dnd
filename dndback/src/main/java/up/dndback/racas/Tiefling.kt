package racas
import Personagem


class Tiefling : racas.iRacas {
    override val nome = "Tiefling"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.carisma += 2
        personagem.inteligencia += 1
    }
}
