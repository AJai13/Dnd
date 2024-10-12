package racas
import Personagem


class MeioElfo : racas.iRacas {
    override val nome = "Meio-Elfo"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.carisma += 2
        personagem.destreza += 1
        personagem.constituicao += 1
    }
}