package racas
import Personagem


class MeioOrc : racas.iRacas {
    override val nome = "Meio-Orc"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.forca += 2
        personagem.constituicao += 1
    }
}
