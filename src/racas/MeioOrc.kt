package racas
import Personagem
import iRacas

class MeioOrc : iRacas {
    override val nome = "Meio-Orc"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.forca += 2
        personagem.constituicao += 1
    }
}
