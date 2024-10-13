package racas

import up.dndback.Personagem

interface iRacas {
    val nome: String
    fun aplicarBonus(personagem: Personagem)
}