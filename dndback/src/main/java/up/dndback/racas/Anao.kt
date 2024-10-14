package racas

import up.dndback.Personagem

class Anao : iRacas {
    override val nome = "Anão"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.constituicao += 2
    }
}
