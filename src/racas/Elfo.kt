package racas
import Personagem
import iRacas

class Elfo : iRacas {
    override val nome = "Elfo"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.destreza += 2
    }
}
