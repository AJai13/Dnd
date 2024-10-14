package racas

import up.dndback.Personagem

class AnaoDaColina : iRacas {
    override val nome = "Anão da Colina"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.constituicao += 2
        personagem.sabedoria += 1
    }
}
