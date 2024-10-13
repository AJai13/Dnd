package racas
import up.dndback.Personagem

class GnomoDaFloresta : racas.iRacas {
    override val nome = "Gnomo da Floresta"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.inteligencia += 2
        personagem.destreza += 1
    }
}
