package racas
import Personagem
import iRacas

class HalflingRobusto : iRacas {
    override val nome = "Halfling Robusto"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.destreza += 2
        personagem.constituicao += 1
    }
}