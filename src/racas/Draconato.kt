package racas
import Personagem
import iRacas

class Draconato : iRacas {
    override val nome = "Draconato"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.forca += 2
        personagem.carisma += 1
    }
}
