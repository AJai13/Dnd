package up.dndfront

import Personagem
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
//import com.example.mylibrary.teste

class MainActivity : ComponentActivity() {
    private var atributos = Atributos()
    private lateinit var atributosDAO: AtributosDAO
    lateinit var selectedRaca: racas.iRacas
    val personagem = Personagem(
        nome = "Adam o destruidor de fandangos", //nome.toString(),
        raca = selectedRaca!!,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // DAO não é necessário, pode ser removido
        val db = AtributosDB.getDatabase(this)
        atributosDAO = db.atributosDAO()

        setContent {
            DropDownScreen()
            AtributosScreen(atributos) {
                saveAtributos()
            }
            ExibirAtributosScreen()
        }
    }

    private fun saveAtributos() {
        // Vai ser alterado pela sua lógica de salvar atributos no personagem
        // Personagem.aumentarAtributo (para cada atributo)
        lifecycleScope.launch {
            atributosDAO.insert(atributos)
        }
    }

    @Composable
    fun DropDownScreen() {

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
    fun ExibirAtributosScreen() {
        // Exibir essas informações utilizando o Text
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

        // Text(text = "Nome: ${personagem.nome}", fontSize = 18.sp)
    }
}

@Composable
fun AtributosScreen(atributos: Atributos, saveClick: () -> Unit) {
    var pontosRestantes by remember { mutableStateOf(atributos.getPontosDisponiveis()) }
    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    // Mover essa porra toda um pouco pra baixo, pro dropdown ficar lá em cimão
    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Criação de Personagem", fontSize = 18.sp)

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Pontos restantes:", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "$pontosRestantes", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // colunas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Atributo", modifier = Modifier.weight(1f))
            Text(
                text = "Valor",
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                text = "Raça",
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                text = "Mod",
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Atributos com colunas adicionais
        AtributoInputRow(
            "Força",
            atributos,
            { pontosRestantes = atributos.getPontosDisponiveis() },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) })
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Destreza",
            atributos,
            { pontosRestantes = atributos.getPontosDisponiveis() },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) })
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Constituição",
            atributos,
            { pontosRestantes = atributos.getPontosDisponiveis() },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) })
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Sabedoria",
            atributos,
            { pontosRestantes = atributos.getPontosDisponiveis() },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) })
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Inteligência",
            atributos,
            { pontosRestantes = atributos.getPontosDisponiveis() },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) })
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Carisma",
            atributos,
            { pontosRestantes = atributos.getPontosDisponiveis() },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) })

        if (snackbarVisible) {
            Snackbar(
                action = {
                    Button(onClick = { snackbarVisible = false }) {
                        Text("Fechar")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(snackbarMessage)
            }
        }

        Button(onClick = saveClick) {
            Text("Salvar atributos")
            // lógica para colocar o atributo (chamar o back)
            // mostrar o texto (Text)
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
