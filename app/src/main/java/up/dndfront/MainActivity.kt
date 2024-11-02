package up.dndfront

import Classes
import Racas
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import racas.iRacas
import up.dndback.Personagem
import up.dndback.classes.iClasses
import up.dndfront.data.PersonagemDAO
import up.dndfront.data.PersonagemDB


class MainActivity : ComponentActivity() {
    var personagem_selecionado_dropdown: Personagem? = null
    var personagem = Personagem()

    private lateinit var personagemDAO: PersonagemDAO
    var nome_do_personagem: String = ""
    var raca_do_personagem: iRacas? = null
    var classe_do_personagem: iClasses? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = PersonagemDB.getDatabase(this)
        personagemDAO = db.personagemDAO()
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        Column {
            UsernameInputScreen()
            DropDownRacasScreen() // Exibe a seleção de raças
            DropDownClassesScreen() // Exibe a seleção de classes
//            DropDownPersonagensScreen()
        }
        AtributosScreen(
            onSaveClick = { novosAtributos -> saveAtributos(novosAtributos) },
            onUpdateClick = { novosAtributos -> updatePersonagem(novosAtributos) }
        )
    }

    fun resetAtributos() {
        personagem.forca = 8
        personagem.destreza = 8
        personagem.constituicao = 8
        personagem.inteligencia = 8
        personagem.sabedoria = 8
        personagem.carisma = 8
        personagem.pontosDisponiveis = 27
    }


    @Composable
    fun DropDownPersonagensScreen() {
        val isDropDownExpanded = remember { mutableStateOf(false) }
        val itemPosition = remember { mutableIntStateOf(0) }
        var lista_de_personagens = personagemDAO.getAllPersonagem()
        Log.println(Log.INFO, "lista de personagens", lista_de_personagens.toString())

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Personagens criados: ", fontSize = 22.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Box {
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        isDropDownExpanded.value = true
                    }) {
                    Text(text = lista_de_personagens[itemPosition.value].nome, fontSize = 22.sp)

                    Spacer(modifier = Modifier.width(8.dp))

                    // Caret icon (down arrow)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "DropDown Icon"
                    )
                }

                DropdownMenu(expanded = isDropDownExpanded.value, onDismissRequest = {
                    isDropDownExpanded.value = false
                }) {
                    lista_de_personagens.forEachIndexed { index, personagem ->
                        DropdownMenuItem(text = { Text(text = personagem.nome) }, onClick = {
                            isDropDownExpanded.value = false
                            itemPosition.value = index
                            personagem_selecionado_dropdown = personagem

                            // Logando os atributos do personagem selecionado
                            Log.d("Personagem Selecionado", personagem.toString()) // Exibe o objeto do personagem
                            Log.d("Atributos", """
                            Nome: ${personagem.nome}
                            Raça: ${personagem.raca?.nome ?: "N/A"}
                            Classe: ${personagem.classe?.nome ?: "N/A"}
                            Força: ${personagem.forca}
                            Destreza: ${personagem.destreza}
                            Constituição: ${personagem.constituicao}
                            Inteligência: ${personagem.inteligencia}
                            Sabedoria: ${personagem.sabedoria}
                            Carisma: ${personagem.carisma}
                            Pontos de Vida: ${personagem.pontosDeVida}
                        """.trimIndent())
                        })
                    }
                }
            }
        }
    }

    @Composable
    fun DropDownRacasScreen() {
        val isDropDownExpanded = remember { mutableStateOf(false) }
        val itemPosition = remember { mutableIntStateOf(0) }
        val lista_de_racas = Racas.get_racas()

        personagem.raca = lista_de_racas[itemPosition.value]

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Escolha sua raça: ", fontSize = 22.sp)

            Box {
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        isDropDownExpanded.value = true
                    }) {
                    Text(text = lista_de_racas[itemPosition.value].nome, fontSize = 22.sp)

                    Spacer(modifier = Modifier.width(8.dp))

                    // Caret icon (down arrow)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "DropDown Icon"
                    )
                }

                DropdownMenu(expanded = isDropDownExpanded.value, onDismissRequest = {
                    isDropDownExpanded.value = false
                }) {
                    lista_de_racas.forEachIndexed { index, raca ->
                        DropdownMenuItem(text = { Text(text = raca.nome) }, onClick = {
                            isDropDownExpanded.value = false
                            itemPosition.value = index
                            personagem.raca = lista_de_racas[itemPosition.value]
                            raca_do_personagem = lista_de_racas[itemPosition.value]
                        })
                    }
                }
            }
        }
    }

    @Composable
    fun DropDownClassesScreen() {
        val isDropDownExpanded = remember {
            mutableStateOf(false)
        }

        val itemPosition = remember {
            mutableIntStateOf(0)
        }

        val lista_de_classes = Classes.get_classes()

        personagem.classe = lista_de_classes[itemPosition.value]

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "   Escolha sua classe: ", fontSize = 22.sp)

            Box {
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { isDropDownExpanded.value = true }
                        .padding(start = 8.dp)) {
                    Text(text = lista_de_classes[itemPosition.value].nome, fontSize = 22.sp)

                    Spacer(modifier = Modifier.width(12.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "DropDown Icon"
                    )
                }

                DropdownMenu(expanded = isDropDownExpanded.value, onDismissRequest = {
                    isDropDownExpanded.value = false
                }) {
                    lista_de_classes.forEachIndexed { index, classe ->
                        DropdownMenuItem(text = { Text(text = classe.nome) }, onClick = {
                            isDropDownExpanded.value = false
                            itemPosition.value = index
                            personagem.classe = lista_de_classes[itemPosition.value]
                            classe_do_personagem = lista_de_classes[itemPosition.value]
                        })
                    }
                }
            }
        }
    }

    @Composable
    fun UsernameInputScreen() {
        var nome by remember { mutableStateOf("") }
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Digite seu nome") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        personagem.nome = nome
        nome_do_personagem = nome
    }

    @Composable
    fun SliderAtributo(nome: String, valorAtual: Int, onValueChange: (Int) -> Unit) {
        Column {

            Text(text = "$nome: $valorAtual", style = MaterialTheme.typography.headlineSmall)

            Slider(
                value = valorAtual.toFloat(), onValueChange = { novoValor ->
                    onValueChange(novoValor.toInt())
                }, valueRange = 8f..15f,
                steps = 7,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }


    private fun saveAtributos(novosAtributos: Map<String, Int>) {
        personagem.pontosDeVida = 8
        for ((atributo, valor) in novosAtributos) {
            personagem.aumentarAtributo(atributo, valor)
        }

        var pontos_de_vida = personagem.classe?.calcularPontosDeVida(personagem.constituicao)
        if (pontos_de_vida != null) {
            personagem.pontosDeVida += pontos_de_vida
        }

        lifecycleScope.launch {
            personagemDAO.insert(personagem)
            Log.d("Personagem", personagemDAO.getAllPersonagem().toString())
        }

    }

    private fun deleteClick() {
        lifecycleScope.launch {
            personagemDAO.delete(personagem_selecionado_dropdown!!)
        }
    }

    private fun updatePersonagem(novosAtributos: Map<String, Int>) {
        personagem_selecionado_dropdown!!.pontosDeVida = 8
        for ((atributo, valor) in novosAtributos) {
            personagem_selecionado_dropdown!!.aumentarAtributo(atributo, valor)
        }

        var pontos_de_vida = personagem_selecionado_dropdown!!.classe?.calcularPontosDeVida(
            personagem_selecionado_dropdown!!.constituicao
        )
        if (pontos_de_vida != null) {
            personagem_selecionado_dropdown!!.pontosDeVida += pontos_de_vida
        }

        personagem_selecionado_dropdown!!.nome = nome_do_personagem
        personagem_selecionado_dropdown!!.raca = raca_do_personagem
        personagem_selecionado_dropdown!!.classe = classe_do_personagem

        lifecycleScope.launch {
            personagemDAO.update(personagem_selecionado_dropdown!!)
        }
    }

    @Composable
    fun AtributosScreen(
        onSaveClick: (Map<String, Int>) -> Unit,
        onUpdateClick: (Map<String, Int>) -> Unit
    ) {
        var forca by remember { mutableIntStateOf(personagem.forca) }
        var destreza by remember { mutableIntStateOf(personagem.destreza) }
        var constituicao by remember { mutableIntStateOf(personagem.constituicao) }
        var inteligencia by remember { mutableIntStateOf(personagem.inteligencia) }
        var sabedoria by remember { mutableIntStateOf(personagem.sabedoria) }
        var carisma by remember { mutableIntStateOf(personagem.carisma) }
        var pontosDeVida by remember { mutableIntStateOf(personagem.pontosDeVida) }
        var showDialog by remember { mutableStateOf(false) }

        var pontosRestantes by remember { mutableStateOf(personagem.pontosDisponiveis) }

        // Função que redefine os valores locais ao resetar o personagem
        fun resetarValoresLocais() {
            forca = personagem.forca
            destreza = personagem.destreza
            constituicao = personagem.constituicao
            inteligencia = personagem.inteligencia
            sabedoria = personagem.sabedoria
            carisma = personagem.carisma
            pontosRestantes = personagem.pontosDisponiveis
        }

        // Observa mudanças e redefine os sliders após o reset
        LaunchedEffect(Unit) {
            resetarValoresLocais()
        }

        fun aumentarAtributo(atributo: String, novoValor: Int) {
            personagem.aumentarAtributo(atributo, novoValor)

            pontosRestantes = personagem.pontosDisponiveis

            forca = personagem.forca
            destreza = personagem.destreza
            constituicao = personagem.constituicao
            inteligencia = personagem.inteligencia
            sabedoria = personagem.sabedoria
            carisma = personagem.carisma
        }

        Column(modifier = Modifier.padding(start = 16.dp, top = 164.dp)) {
            DropDownPersonagensScreen()
            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "Pontos restantes: $pontosRestantes", fontSize = 22.sp)
            Spacer(modifier = Modifier.height(12.dp))

            SliderAtributo("Força", forca) { novoValor ->
                aumentarAtributo("forca", novoValor)
                resetarValoresLocais()
            }

            SliderAtributo("Destreza", destreza) { novoValor ->
                aumentarAtributo("destreza", novoValor)
                resetarValoresLocais()
            }

            SliderAtributo("Constituição", constituicao) { novoValor ->
                aumentarAtributo("constituicao", novoValor)
                resetarValoresLocais()
            }

            SliderAtributo("Inteligência", inteligencia) { novoValor ->
                aumentarAtributo("inteligencia", novoValor)
                resetarValoresLocais()
            }

            SliderAtributo("Sabedoria", sabedoria) { novoValor ->
                aumentarAtributo("sabedoria", novoValor)
                resetarValoresLocais()
            }

            SliderAtributo("Carisma", carisma) { novoValor ->
                aumentarAtributo("carisma", novoValor)
                resetarValoresLocais()
            }

            Button(onClick = {
                val novosAtributos = mapOf(
                    "forca" to forca,
                    "destreza" to destreza,
                    "constituicao" to constituicao,
                    "inteligencia" to inteligencia,
                    "sabedoria" to sabedoria,
                    "carisma" to carisma

                )
                onSaveClick(novosAtributos)
                resetAtributos()
                resetarValoresLocais()
                showDialog = true
            }, Modifier.align(Alignment.CenterHorizontally)) {
                Text("Salvar")
            }

            Button(onClick = {
                deleteClick()
                resetAtributos()
                resetarValoresLocais()
            }, Modifier.align(Alignment.CenterHorizontally)) {
                Text("Deletar")
            }

            Button(onClick = {
                val novosAtributos = mapOf(
                    "forca" to forca,
                    "destreza" to destreza,
                    "constituicao" to constituicao,
                    "inteligencia" to inteligencia,
                    "sabedoria" to sabedoria,
                    "carisma" to carisma,
                )
                onUpdateClick(novosAtributos)
                resetAtributos()
                resetarValoresLocais()
            }, Modifier.align(Alignment.CenterHorizontally)) {
                Text("Atualizar")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Informações do Personagem") },
                    text = {
                        Column {
                            Text(text = "Nome: ${personagem.nome}", fontSize = 18.sp)
                            Text(text = "Raça: ${personagem.raca?.nome ?: "N/A"}", fontSize = 18.sp)
                            Text(
                                text = "Classe: ${personagem.classe?.nome ?: "N/A"}",
                                fontSize = 18.sp
                            )
                            Text(text = "Força: $forca", fontSize = 18.sp)
                            Text(text = "Destreza: $destreza", fontSize = 18.sp)
                            Text(text = "Constituição: $constituicao", fontSize = 18.sp)
                            Text(text = "Inteligência: $inteligencia", fontSize = 18.sp)
                            Text(text = "Sabedoria: $sabedoria", fontSize = 18.sp)
                            Text(text = "Carisma: $carisma", fontSize = 18.sp)
                            Text(
                                text = "Pontos de Vida: ${personagem.pontosDeVida}",
                                fontSize = 18.sp
                            )
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }

}
