package racas
import Personagem


class GnomoDasRochas : racas.iRacas {
    override val nome = "Gnomo das Rochas"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.inteligencia += 2
        personagem.constituicao += 1
    }
}
