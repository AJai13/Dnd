package racas

import Personagem

class AnaoDaMontanha : racas.iRacas {
    override val nome = "Anão da Montanha"

    override fun aplicarBonus(personagem: Personagem) {
            personagem.forca += 2
            personagem.destreza -= 2
            personagem.constituicao += 2
            personagem.inteligencia -= 2
            personagem.sabedoria += 2
            personagem.carisma -= 2
    }
}