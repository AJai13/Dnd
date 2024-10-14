package racas

import up.dndback.Personagem


class Tiefling : iRacas {
    override val nome = "Tiefling"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.carisma += 2
        personagem.inteligencia += 1
    }
}
