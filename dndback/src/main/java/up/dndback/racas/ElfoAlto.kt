package racas
import Personagem

class ElfoAlto : racas.iRacas {
    override val nome = "Elfo Alto"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.destreza += 2
        personagem.inteligencia += 1
    }
}
