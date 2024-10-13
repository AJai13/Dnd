/////
//import classes.Guerreiro
//import classes.Barbaro
//import classes.Paladino
//import classes.Ladino
//import classes.Mago
//import classes.Clerigo
//import classes.Druida
//import classes.Feiticeiro
//import classes.Bruxo
//import classes.Bardo
//import classes.Monge
//import classes.Patrulheiro
//
//import racas.AnaoDaMontanha
//import racas.AnaoDaColina
//import racas.Anao
//import racas.Elfo
//import racas.ElfoDaFloresta
//import racas.ElfoAlto
//import racas.MeioElfo
//import racas.Humano
//import racas.HalflingPesLeves
//import racas.HalflingRobusto
//import racas.Draconato
//import racas.Gnomo
//import racas.GnomoDaFloresta
//import racas.GnomoDasRochas
//import racas.MeioOrc
//import racas.Tiefling
//import racas.Drow
//
////escolher nome
////determinar valores de habilidade
////escolher classe
////aplicar bonus raciais e de classe
////calcular pontos de vida
////mostrar personagem
//
////criar repositório no github e subir projeto
//
//fun main() {
//    println("\n#===== Bem-vindo à criação de personagens de D&D!! =====#\n")
//
//    // 1. Escolher nome do personagen
//    var nome: String?
//    var confirmacao: String?
//
//    do {
//        println("\n* Escolha o nome do seu personagem:")
//        nome = readLine()
//        println("O nome do seu personagem: $nome")
//        println("Está correto? (s/n)")
//        confirmacao = readLine()
//    } while (confirmacao != "s")
//
//    println("Nome confirmado: $nome")
//
//    // 2. Escolha de Raça
//    val listaRacas = mapOf(
//        1 to Anao(),
//        2 to AnaoDaMontanha(),
//        3 to AnaoDaColina(),
//        4 to Elfo(),
//        5 to ElfoDaFloresta(),
//        6 to ElfoAlto(),
//        7 to MeioElfo(),
//        8 to Humano(),
//        9 to HalflingPesLeves(),
//        10 to HalflingRobusto(),
//        11 to Draconato(),
//        12 to Gnomo(),
//        13 to GnomoDaFloresta(),
//        14 to GnomoDasRochas(),
//        15 to MeioOrc(),
//        16 to Tiefling(),
//        17 to Drow()
//    )
//
//    var racaEscolhida: Int?
//    var raca: racas.iRacas?
//
//    while(true) {
//        println("\n* Escolha sua raça:")
//        println("1. Anão ")
//        println("2. Anão da Montanha")
//        println("3. Anão da Colina")
//        println("4. Elfo")
//        println("5. Elfo da Floresta")
//        println("6. Elfo Alto")
//        println("7. Meio-Elfo")
//        println("8. Humano")
//        println("9. Halfing Pés-Leves")
//        println("10. Halfing Robusto")
//        println("11. Draconato")
//        println("12. Gnomo")
//        println("13. Gnomo da Floresta")
//        println("14. Gnomo das Rochas")
//        println("15. Meio-Orc")
//        println("16. Tiefling")
//        println("17. Drow")
//
//        print("\nDigite o número correspondente à raça escolhida: ")
//        racaEscolhida = readLine()?.toIntOrNull()
//        raca = listaRacas[racaEscolhida]
//
//        if (raca != null) {
//            println("Raça escolhida com sucesso!")
//            break
//        } else {
//            println("Opção inválida. Escolha o número da raça desejada.\n")
//        }
//    }
//
//    // 3. Escolha de Classe
//    val listaClasse = mapOf(
//        1 to Guerreiro(),
//        2 to Barbaro(),
//        3 to Paladino(),
//        4 to Ladino(),
//        5 to Mago(),
//        6 to Clerigo(),
//        7 to Druida(),
//        8 to Feiticeiro(),
//        9 to Bruxo(),
//        10 to Bardo(),
//        11 to Monge(),
//        12 to Patrulheiro()
//    )
//    var classe: up.dndback.classes.iClasses?
//    var classeEscolhida: Int?
//
//    while(true) {
//        println("\n* Escolha sua classe:")
//        println("1. Guerreiro")
//        println("2. Bárbaro")
//        println("3. Paladino")
//        println("4. Ladino")
//        println("5. Mago")
//        println("6. Clérigo")
//        println("7. Druida")
//        println("8. Feiticeiro")
//        println("9. Bruxo")
//        println("10. Bardo")
//        println("11. Monge")
//        println("12. Patrulheiro")
//
//        print("\nDigite o número correspondente à classe escolhida: ")
//        classeEscolhida = readLine()?.toIntOrNull()
//        classe = listaClasse[classeEscolhida]
//
//        if (classe != null) {
//            println("Classe escolhida com sucesso!")
//            break
//        } else {
//            println("Opção inválida. Escolha o número da classe desejada.\n")
//        }
//    }
//
//    // 4. Determinar Valores de Habilidade
//    val personagem = Personagem(
//        nome = nome.toString(),
//        raca = raca!!,
//        classe = classe!!,
//    )
//
//    val listaAtributos = mapOf(
//        1 to "forca",
//        2 to "destreza",
//        3 to "constituicao",
//        4 to "inteligencia",
//        5 to "sabedoria",
//        6 to "carisma"
//    )
//
//    while(personagem.pontosDisponiveis > 0) {
//        println("\nVocê tem ${personagem.pontosDisponiveis} pontos para distribuir.")
//        println("Os valores iniciais são 8.")
//        println("Os atributos variam de 8 a 15.")
//        println("\n* Distribua os valores de atributos do personagem.")
//        println("Escolha o atributo que deseja aumentar:")
//        println("Digite 1. para o atributo Força")
//        println("Digite 2. para o atributo Destreza")
//        println("Digite 3. para o atributo Constituição")
//        println("Digite 4. para o atributo Inteligência")
//        println("Digite 5. para o atributo Sabedoria")
//        println("Digite 6. para o atributo Carisma")
//        val inputAtributo = readLine()?.toIntOrNull()
//
//        if (inputAtributo in 1..6) {
//           val atributoEscolhido = listaAtributos[inputAtributo]
//            println("Agora digite quais valores deseja atribuir a(o) ${atributoEscolhido}:")
//            val pontosAdicionados = readLine()?.toIntOrNull()
//            personagem.aumentarAtributo(atributoEscolhido!!, pontosAdicionados!!)
//        } else {
//            println("\nEscolha inválida. Tente novamente.")
//        }
//    }
//
//    // Aplicando bônus raciais
//    raca.aplicarBonus(personagem)
//
//    // Aplicando bônus de classe
//    personagem.pontosDeVida = classe.calcularPontosDeVida(personagem.constituicao)
//
//    // 5. Exibir as informações do personagem
//    println("\n##### Informações do Personagem ##### ")
//    println("       Nome: ${personagem.nome}, ")
//    println("       Raça: ${personagem.raca?.nome}, ")
//    println("       Classe: ${personagem.classe?.nome}, ")
//    println("       ${personagem.pontosDeVida} pontos de vida, ")
//    println("       ${personagem.forca} de força, ")
//    println("       ${personagem.destreza} de destreza, ")
//    println("       ${personagem.constituicao} de constituição, ")
//    println("       ${personagem.inteligencia} de inteligência, ")
//    println("       ${personagem.sabedoria} de sabedoria, ")
//    println("       ${personagem.carisma} de carisma.")
//}
