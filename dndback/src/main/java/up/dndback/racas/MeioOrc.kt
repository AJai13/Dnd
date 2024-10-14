package racas

import up.dndback.Personagem


class MeioOrc : iRacas {
    override val nome = "Meio-Orc"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.forca += 2
        personagem.constituicao += 1
    }
}
