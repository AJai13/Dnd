package racas
import Personagem
import iRacas

class HalflingPesLeves : iRacas {
    override val nome = "Halfling Pés Leves"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.destreza += 2
        personagem.carisma += 1
    }
}
