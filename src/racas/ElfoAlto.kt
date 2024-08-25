package racas
import Personagem
import iRacas

class ElfoAlto : iRacas {
    override val nome = "Elfo Alto"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.destreza += 2
        personagem.inteligencia += 1
    }
}
