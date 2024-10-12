package racas
import Personagem


class HalflingPesLeves : racas.iRacas {
    override val nome = "Halfling Pés Leves"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.destreza += 2
        personagem.carisma += 1
    }
}
