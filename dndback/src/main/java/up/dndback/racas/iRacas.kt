package racas

import Personagem

interface iRacas {
    val nome: String
    fun aplicarBonus(personagem: Personagem)
}