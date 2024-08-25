package racas
import Personagem
import iRacas

class ElfoDaFloresta : iRacas {
    override val nome = "Elfo da Floresta"

    override fun aplicarBonus(personagem: Personagem) {
        personagem.destreza += 2
        personagem.sabedoria += 1
    }
}
