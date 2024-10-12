package racas
import Personagem


class Draconato : racas.iRacas {
    override val nome = "Draconato"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.forca += 2
        personagem.carisma += 1
    }
}
