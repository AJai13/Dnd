package racas
import Personagem
import iRacas

class GnomoDasRochas : iRacas {
    override val nome = "Gnomo das Rochas"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.inteligencia += 2
        personagem.constituicao += 1
    }
}
