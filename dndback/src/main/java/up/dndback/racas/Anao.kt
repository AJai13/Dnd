package racas
import Personagem

class Anao : racas.iRacas {
    override val nome = "Anão"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.constituicao += 2
    }
}
