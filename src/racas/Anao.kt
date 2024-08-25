package racas
import Personagem
import iRacas

class Anao : iRacas {
    override val nome = "Anão"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.constituicao += 2
    }
}
