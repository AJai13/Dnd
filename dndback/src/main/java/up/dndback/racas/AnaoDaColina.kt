package racas
import Personagem

class AnaoDaColina : racas.iRacas {
    override val nome = "Anão da Colina"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.constituicao += 2
        personagem.sabedoria += 1
    }
}
