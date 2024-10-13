package up.dndfront

import up.dndback.Personagem
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import up.dndfront.data.AtributosDAO
import up.dndfront.data.AtributosDB
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import up.dndback.classes.iClasses

class MainActivity : ComponentActivity() {
    private var atributos = Atributos()
    private lateinit var atributosDAO: AtributosDAO
    lateinit var selectedRaca: racas.iRacas
    lateinit var selectedClasse: iClasses

    val personagem = Personagem(
        nome = "Adam o destruidor de fandangos", //nome.toString(),
        raca = selectedRaca!!,
        classe = selectedClasse!!,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AtributosDB.getDatabase(this)
        atributosDAO = db.atributosDAO()

        setContent {
            val personagem = remember {
                Personagem(
                    nome = "Meu Personagem",
                    raca = null,
                    classe = null
                )
            }

            DropDownRacasScreen() // Exibe a seleção de raças
            DropDownClassesScreen() // Exibe a seleção de classes

            AtributosScreen(personagem) { novosAtributos ->
                saveAtributos(personagem, novosAtributos)
            }

            ExibirAtributosScreen() // Exibe os atributos já salvos
        }


    fun saveAtributos(personagem: Personagem, novosAtributos: Map<String, Int>) {
    // Iterar sobre os novos atributos e atualizar o personagem
        for ((atributo, valor) in novosAtributos) {
            personagem.aumentarAtributo(atributo, valor)
        }

    // Após atualizar os atributos, salvar os dados no banco
//            lifecycleScope.launch {
//                atributosDAO.insert(atributosFromPersonagem(personagem))
//            }
//        }

//    fun atributosFromPersonagem(personagem: Personagem): AtributosEntity {
//        return AtributosEntity(
//            forca = personagem.forca,
//            destreza = personagem.destreza,
//            constituicao = personagem.constituicao,
//            inteligencia = personagem.inteligencia,
//            sabedoria = personagem.sabedoria,
//            carisma = personagem.carisma,
//            pontosDeVida = personagem.pontosDeVida,
//            pontosDisponiveis = personagem.pontosDisponiveis
//        )
    }


    @Composable
    fun DropDownRacasScreen() {

        val isDropDownExpanded = remember {
            mutableStateOf(false)
        }

        val itemPosition = remember {
            mutableStateOf(0)
        }

        val lista_de_racas = Racas.get_racas()

        // TODO: Mover a coluna do dropdown para cima, antes dos inputs
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        isDropDownExpanded.value = true
                    }
                ) {
                    Text(text = lista_de_racas[itemPosition.value].nome)
                }
                DropdownMenu(
                    expanded = isDropDownExpanded.value,
                    onDismissRequest = {
                        isDropDownExpanded.value = false
                    }) {
                    lista_de_racas.forEachIndexed { index, raca ->
                        DropdownMenuItem(text = {
                            Text(text = raca.nome)
                        },
                            onClick = {
                                isDropDownExpanded.value = false
                                itemPosition.value = index
                                selectedRaca = lista_de_racas[itemPosition.value]
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
            mutableStateOf(0)
        }

        val lista_de_classes = Classes.get_classes()

        // TODO: Mover a coluna do dropdown para cima, antes dos inputs
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        isDropDownExpanded.value = true
                    }
                ) {
                    Text(text = lista_de_classes[itemPosition.value].nome)
                }
                DropdownMenu(
                    expanded = isDropDownExpanded.value,
                    onDismissRequest = {
                        isDropDownExpanded.value = false
                    }) {
                    lista_de_classes.forEachIndexed { index, raca ->
                        DropdownMenuItem(text = {
                            Text(text = raca.nome)
                        },
                            onClick = {
                                isDropDownExpanded.value = false
                                itemPosition.value = index
                                selectedClasse = lista_de_classes[itemPosition.value]
                            })
                    }
                }
            }
        }
    }


    @Composable
    fun ExibirAtributosScreen(personagem: Personagem) {
        Column {
            Text(text = "Nome: ${personagem.nome}", fontSize = 18.sp)
            Text(text = "Raça: ${personagem.raca?.nome ?: "N/A"}", fontSize = 18.sp)
            Text(text = "Classe: ${personagem.classe?.nome ?: "N/A"}", fontSize = 18.sp)
            Text(text = "Força: ${personagem.forca}", fontSize = 18.sp)
            Text(text = "Destreza: ${personagem.destreza}", fontSize = 18.sp)
            Text(text = "Constituição: ${personagem.constituicao}", fontSize = 18.sp)
            Text(text = "Inteligência: ${personagem.inteligencia}", fontSize = 18.sp)
            Text(text = "Sabedoria: ${personagem.sabedoria}", fontSize = 18.sp)
            Text(text = "Carisma: ${personagem.carisma}", fontSize = 18.sp)
            Text(text = "Pontos de Vida: ${personagem.pontosDeVida}", fontSize = 18.sp)
        }
    }

    }

    @Composable
    fun SliderAtributo(nome: String, valorAtual: Int, onValueChange: (Int) -> Unit) {
        Column {
            // Exibe o nome do atributo e o valor atual
            Text(text = "$nome: $valorAtual", style = MaterialTheme.typography.headlineSmall)

            // Slider que permite alterar o valor
            Slider(
                value = valorAtual.toFloat(),
                onValueChange = { novoValor ->
                    onValueChange(novoValor.toInt()) // Passa o valor atualizado para a função de callback
                },
                valueRange = 8f..15f, // Define o intervalo do valor permitido
                steps = 7, // Define os passos (quantidade de valores permitidos entre o intervalo)
                modifier = Modifier.padding(horizontal = 16.dp) // Padding para afastar o slider das bordas
            )
        }
    }


    @Composable
    fun AtributosScreen(personagem: Personagem, onSaveClick: (Map<String, Int>) -> Unit) {
        var forca by remember { mutableStateOf(personagem.forca) }
        var destreza by remember { mutableStateOf(personagem.destreza) }
        var constituicao by remember { mutableStateOf(personagem.constituicao) }
        var inteligencia by remember { mutableStateOf(personagem.inteligencia) }
        var sabedoria by remember { mutableStateOf(personagem.sabedoria) }
        var carisma by remember { mutableStateOf(personagem.carisma) }

        Column {
            // Exibir sliders ou campos de texto para modificar os atributos
            SliderAtributo("Força", forca) { novoValor -> forca = novoValor }
            SliderAtributo("Destreza", destreza) { novoValor -> destreza = novoValor }
            SliderAtributo("Constituição", constituicao) { novoValor ->
                constituicao = novoValor
            }
            SliderAtributo("Inteligência", inteligencia) { novoValor ->
                inteligencia = novoValor
            }
            SliderAtributo("Sabedoria", sabedoria) { novoValor -> sabedoria = novoValor }
            SliderAtributo("Carisma", carisma) { novoValor -> carisma = novoValor }

            // Botão para salvar os atributos
            Button(onClick = {
                // Mapa com os valores de atributos atualizados
                val novosAtributos = mapOf(
                    "forca" to forca,
                    "destreza" to destreza,
                    "constituicao" to constituicao,
                    "inteligencia" to inteligencia,
                    "sabedoria" to sabedoria,
                    "carisma" to carisma
                )
                onSaveClick(novosAtributos) // Chama a função para salvar
            }) {
                Text("Salvar Atributos")
            }
        }
    }


    @Composable
    fun AtributoInputRow(
        label: String,
        atributos: Atributos,
        updatePontos: () -> Unit,
        onError: (String) -> Unit
    ) {
        var textValue by remember { mutableStateOf("") }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically // Alinhamento vertical centralizado
        ) {
            // Coluna de Atributos
            Text(text = label, modifier = Modifier.weight(1f))

            // Campo de entrada de número inteiro
            OutlinedTextField(
                value = textValue,
                onValueChange = { newValue ->
                    // Permitir apenas números
                    if (newValue.all { it.isDigit() }) {
                        textValue = newValue
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .weight(1f)
                    .width(50.dp)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused && textValue.isNotEmpty()) {
                            try {
                                val valor = textValue.toInt()
                                atributos.setAtributo(valor) // Envia o valor para setAtributo
                                updatePontos() // Atualiza os pontos
                            } catch (e: IllegalArgumentException) {
                                onError(e.message ?: "Erro desconhecido")
                                textValue = "" // Limpa o campo em caso de erro
                            } catch (e: NumberFormatException) {
                                onError("Por favor, insira um número válido.")
                                textValue = "" // Limpa o campo em caso de erro
                            }
                        }
                    }
            )

            // Coluna Bônus de Raça
            Text(
                text = "0",
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            // Coluna Mod
            Text(
                text = "0",
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }

    @Composable
    fun FilledButtonPersistencia(onClick: () -> Unit) {
        Button(onClick = { onClick() }) {
            Text("Persistência")

        }
    }

    fun showMessage(message: String, callback: (String) -> Unit) {
        callback(message)
    }
    }
