package racas
import Personagem

class Elfo : racas.iRacas {
    override val nome = "Elfo"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.destreza += 2
    }
}