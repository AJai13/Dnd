package racas
import up.dndback.Personagem


class HalflingRobusto : racas.iRacas {
    override val nome = "Halfling Robusto"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.destreza += 2
        personagem.constituicao += 1
    }
}