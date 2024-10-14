package racas

import up.dndback.Personagem

class Elfo : iRacas {
    override val nome = "Elfo"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.destreza += 2
    }
}
