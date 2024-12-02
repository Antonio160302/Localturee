/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.practica8.presentation

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.protolayout.LayoutElementBuilders
import com.example.practica8.R
import com.example.practica8.presentation.theme.Practica8Theme
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.sqrt
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            Navegador()
        }
    }

    @Composable
    fun Navegador(){
        val navController = rememberNavController()

        NavHost(navController, startDestination = "Portada") {
            composable("PaginaPrincipal") { PaginaPrincipal(navController) }
            composable("PaginaUno") { Juego(navController) }
            composable("PaginaDos") { PaginaDos(navController) }
            composable("PaginaTres") { PaginaTres(navController) }
            composable("PaginaCuatro") { PaginaCuatro(navController)}
            composable("PaginaCinco") { PaginaCinco(navController)}
            composable("PaginaSeis") { PaginaSeis(navController)}
            composable("Portada") { Portada(navController) }
            composable("PaginaAgua") { PaginaAgua(navController) }
        }
    }

    @Composable
    fun PaginaPrincipal(navController: NavHostController) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { PaginaUnoButton(navController) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { PaginaDosButton(navController) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { PaginaTresButton(navController) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { PaginaCuatroButton(navController) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { PaginaCincoButton(navController) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { PaginaseisButton(navController) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { PaginaAguaButton(navController) } // Nuevo botón

        }
    }
}



@Composable
fun PaginaDos(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MusicPlayerApp()
    }
}

@Composable
fun PaginaTres(navController: NavController) {
    var time by remember { mutableStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(100L)  // Cambiamos a 100 milisegundos para una actualización más rápida
            time += 100L  // Aumentamos en 100 milisegundos cada vez
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            Text(text = "Cronometro",color = Color.Green)
            Text(
                text = String.format(
                    "%02d:%02d:%02d.%03d",  // Añadimos .%03d para mostrar milisegundos
                    time / 3600000,         // Horas
                    (time % 3600000) / 60000, // Minutos
                    (time % 60000) / 1000,    // Segundos
                    time % 1000              // Milisegundos
                ),
                style = MaterialTheme.typography.display3,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { isRunning = true },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_play_arrow_24),
                        contentDescription = "Play",
                        tint = Color.Blue
                    )
                }

                Button(
                    onClick = { isRunning = false },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_pause_24),
                        contentDescription = "Pause",
                        tint = Color.Blue
                    )
                }

                Button(
                    onClick = {
                        isRunning = false
                        time = 0L
                    },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_replay_24),
                        contentDescription = "repet",
                        tint = Color.Blue
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(4.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
            ) {
                androidx.wear.compose.material.Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_circle_left_24),
                    contentDescription = "Back",
                    tint = Color.Blue
                )
            }
        }
    }
}

@Composable
fun PaginaCuatro(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Calculadora()
        VolverButton(navController)
    }
}



@Composable
fun VolverButton(navController: NavController) {
    Button(
        onClick = { navController.navigate("PaginaPrincipal") },
        modifier = Modifier
            .padding(16.dp)
            .size(50.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
    ) {
        Text("<",
            color = Color.Green)
    }
}

@Composable
fun Homebtn(navController: NavController) {
    Button(
        onClick = { navController.navigate("PaginaPrincipal") },
        modifier = Modifier
            .padding(16.dp)
            .size(50.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
    ) {
        Text("ACL",
            color = Color.DarkGray)
    }
}


@Composable
fun PaginaUnoButton(navController: NavController) {
    Button(
        onClick = { navController.navigate("PaginaUno") },
        modifier = Modifier
            .padding(10.dp) // Ajusta el padding alrededor del botón
            .width(150.dp), // Ajusta el ancho del botón según sea necesario
        shape = RoundedCornerShape(10.dp), // Ajusta el radio de las esquinas
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray) // Cambia el color del botón a azul
    ) {
        Text("Juego", color = Color.White) // Ajusta el color del texto si es necesario
    }
}

@Composable
fun PaginaDosButton(navController: NavController) {
    Button(
        onClick = { navController.navigate("PaginaDos") },
        modifier = Modifier
            .width(150.dp), // Ajusta el ancho del botón según sea necesario
        shape = RoundedCornerShape(16.dp), // Ajusta el radio de las esquinas
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray) // Cambia el color del botón a azul
    )  {
        Text("Reproductor")
    }
}

@Composable
fun PaginaTresButton(navController: NavController) {
    Button(
        onClick = { navController.navigate("PaginaTres") },
        modifier = Modifier
            .width(150.dp), // Ajusta el ancho del botón según sea necesario
        shape = RoundedCornerShape(16.dp), // Ajusta el radio de las esquinas
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
    ) {
        Text("Cronometro")
    }
}

@Composable
fun PaginaCuatroButton(navController: NavController) {
    Button(
        onClick = { navController.navigate("PaginaCuatro") },
        modifier = Modifier
            .width(150.dp), // Ajusta el ancho del botón según sea necesario
        shape = RoundedCornerShape(16.dp), // Ajusta el radio de las esquinas
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
    ) {
        Text("Calculadora")
    }
}
@Composable
fun PaginaCincoButton(navController: NavController) {
    Button(
        onClick = { navController.navigate("PaginaCinco") },
        modifier = Modifier
            .width(150.dp), // Ajusta el ancho del botón según sea necesario
        shape = RoundedCornerShape(16.dp), // Ajusta el radio de las esquinas
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
    ) {
        Text("Ritmo")
    }
}

@Composable
fun PaginaseisButton(navController: NavController) {
    Button(
        onClick = { navController.navigate("PaginaSeis") },
        modifier = Modifier
            .width(150.dp), // Ajusta el ancho del botón según sea necesario
        shape = RoundedCornerShape(16.dp), // Ajusta el radio de las esquinas
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
    ) {
        Text("Yoga")
    }
}
@Composable
fun PaginaHomeButton(navController: NavController) {
    Button(
        onClick = { navController.navigate("Portada") },
        modifier = Modifier
            .width(150.dp), // Ajusta el ancho del botón según sea necesario
        shape = RoundedCornerShape(16.dp), // Ajusta el radio de las esquinas
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
    ) {
        Text("Home")
    }
}



@Composable
fun MessageCard() {
    val name = "Luis Murrillo"
    val time = "12:33"
    val message = "Hola, Como Estas?"
    val date = "Thursday Now"
    val message2 = "Una Miches O Que?"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        Text(
            text = time,
            fontSize = 12.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(3.dp))
        Image(
            painter = painterResource(id = R.drawable.messi),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = name,
            fontSize = 12.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = date,
            fontSize = 10.sp,
            color = Color.Green
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f) // Ajusta la anchura de la caja del mensaje
                .background(Color.Transparent, RoundedCornerShape(12.dp))
                .padding(8.dp)
                .border(width = 2.dp, color = Color.Green, shape = RoundedCornerShape(12.dp))
        ) {
            Text(
                text = message,
                color = Color.Green,
                fontSize = 12.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f) // Ajusta la anchura de la caja del mensaje
                .background(Color.Transparent, RoundedCornerShape(12.dp))
                .padding(8.dp)
                .border(width = 2.dp, color = Color.Green, shape = RoundedCornerShape(12.dp))
        ){
            Text(
                text = message2,
                color = Color.Green,
                fontSize = 12.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}


@Composable
fun MusicPlayerApp() {
    var isReproductorOneActive by remember { mutableStateOf(true) }

    Column {
        if (isReproductorOneActive) {
            Reproductor(onNext = { isReproductorOneActive = false })
        } else {
            Reproductordos(onPrevious = { isReproductorOneActive = true })
        }
    }
}

@Composable
fun Reproductor(onNext: () -> Unit) {
    val context = LocalContext.current
    val name = "Sin ti"
    val duration = 3 * 60 + 42 // Duración total de la canción en segundos (3 minutos y 42 segundos)
    val artist = "Nanpa Basico"

    var currentTime by remember { mutableStateOf(0) } // Tiempo actual de reproducción en segundos
    var isPlaying by remember { mutableStateOf(false) } // Estado de reproducción

    // Usamos un state para manejar el MediaPlayer
    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.sinti).apply {
            setOnCompletionListener {
                isPlaying = false // Detener la reproducción al completar la canción
                currentTime = 0
                seekTo(0)
            }
        }
    }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            mediaPlayer.start()
            while (isActive && isPlaying && currentTime < duration) {
                delay(1000) // Simula 1 segundo de reproducción
                currentTime++
            }
        } else {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release() // Liberar MediaPlayer cuando el Composable se elimine
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen de perfil
        Image(
            painter = painterResource(id = R.drawable.sinti),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = name,
            fontSize = 12.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = artist,
            fontSize = 10.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Iconos de control de música
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { /* Acción de retroceder */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_skip_previous_24),
                    contentDescription = "Anterior",
                    tint = Color.Blue
                )
            }
            IconButton(onClick = {
                isPlaying = !isPlaying // Cambia el estado de reproducción
                if (isPlaying) {
                    mediaPlayer.start()
                } else {
                    mediaPlayer.pause()
                }
            }) {
                Icon(
                    painter = if (isPlaying) painterResource(id = R.drawable.baseline_pause_24) else painterResource(id = R.drawable.baseline_play_arrow_24),
                    contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                    tint = Color.Blue
                )
            }
            IconButton(onClick = onNext) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_skip_next_24),
                    contentDescription = "Siguiente",
                    tint = Color.Blue
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Progreso de tiempo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(0.8f),
        ) {
            Text(
                text = String.format("%02d:%02d", currentTime / 60, currentTime % 60),
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = String.format("%02d:%02d", duration / 60, duration % 60),
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        // Barra de progreso
        LinearProgressIndicator(
            progress = currentTime / duration.toFloat(),
            modifier = Modifier
                .fillMaxWidth(0.8f),
            color = Color.Blue,
        )
    }
}

@Composable
fun Reproductordos(onPrevious: () -> Unit) {
    val context = LocalContext.current
    val name = "A Mi"
    val duration = 3 * 60 + 30 // Duración total de la canción en segundos (3 minutos y 42 segundos)
    val artist = "Rels B"

    var currentTime by remember { mutableStateOf(0) } // Tiempo actual de reproducción en segundos
    var isPlaying by remember { mutableStateOf(false) } // Estado de reproducción

    // Usamos un state para manejar el MediaPlayer
    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.amirb).apply {
            setOnCompletionListener {
                isPlaying = false // Detener la reproducción al completar la canción
                currentTime = 0
                seekTo(0)
            }
        }
    }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            mediaPlayer.start()
            while (isActive && isPlaying && currentTime < duration) {
                delay(1000) // Simula 1 segundo de reproducción
                currentTime++
            }
        } else {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release() // Liberar MediaPlayer cuando el Composable se elimine
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen de perfil
        Image(
            painter = painterResource(id = R.drawable.ami),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = name,
            fontSize = 12.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = artist,
            fontSize = 10.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Iconos de control de música
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onPrevious) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_skip_previous_24),
                    contentDescription = "Anterior",
                    tint = Color.Blue
                )
            }
            IconButton(onClick = {
                isPlaying = !isPlaying // Cambia el estado de reproducción
                if (isPlaying) {
                    mediaPlayer.start()
                } else {
                    mediaPlayer.pause()
                }
            }) {
                Icon(
                    painter = if (isPlaying) painterResource(id = R.drawable.baseline_pause_24) else painterResource(id = R.drawable.baseline_play_arrow_24),
                    contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                    tint = Color.Blue
                )
            }
            IconButton(onClick = { /* Acción de avanzar */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_skip_next_24),
                    contentDescription = "Siguiente",
                    tint = Color.Blue
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Progreso de tiempo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(0.8f),
        ) {
            Text(
                text = String.format("%02d:%02d", currentTime / 60, currentTime % 60),
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = String.format("%02d:%02d", duration / 60, duration % 60),
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        // Barra de progreso
        LinearProgressIndicator(
            progress = currentTime / duration.toFloat(),
            modifier = Modifier.fillMaxWidth(0.8f),
            color = Color.Blue,
        )
    }
}




@Composable
fun Calculadora() {
    var caja by remember { mutableStateOf("0") }
    var resultado by remember { mutableStateOf(0.0) }
    var operador by remember { mutableStateOf("") }
    var dato1 by remember { mutableStateOf(0.0) }

    fun Presionarboton(button: String) {
        when (button) {
            in "0".."9", "." -> {
                if (caja == "0") caja = button else caja += button
            }
            in listOf("/", "*", "-", "+") -> {
                operador = button
                dato1 = caja.toDouble()
                caja = "0"
            }
            "=" -> {
                resultado = when (operador) {
                    "/" -> dato1 / caja.toDouble()
                    "*" -> dato1 * caja.toDouble()
                    "-" -> dato1 - caja.toDouble()
                    "+" -> dato1 + caja.toDouble()
                    else -> 0.0
                }
                caja = resultado.toString()
            }
            "C" -> {
                resultado = 0.0
                caja = "0"
            }
            "+/-" -> {
                resultado = -1.0 * caja.toDouble()
                caja = resultado.toString()
            }
            "X2" -> {
                resultado = caja.toDouble() * caja.toDouble()
                caja = resultado.toString()
            }
            "√" -> {
                resultado = kotlin.math.sqrt(caja.toDouble())
                caja = resultado.toString()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Calculadora")
        LazyColumn {
            item { Text(text = caja, style = MaterialTheme.typography.display3) }
            item { Renglones("7", "8", "9", "/", "C") { Presionarboton(it) } }
            item { Renglones("4", "5", "6", "*", "+/-") { Presionarboton(it) } }
            item { Renglones("1", "2", "3", "-", "X2") { Presionarboton(it) } }
            item { Renglones("0", ".", "=", "+", "√") { Presionarboton(it) } }
        }
    }
}

@Composable
fun Renglones(vararg buttons: String, onClick: (String) -> Unit) {
    Row {
        buttons.forEach { button ->
            Button(
                modifier = Modifier
                    .padding(all = 1.dp)
                    .size(width = 28.dp, height = 28.dp),
                onClick = { onClick(button) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                shape = CircleShape // Hace el botón circular
            ) {
                Text(text = button, color = Color.White) // Texto blanco para mejor visibilidad
            }
        }
    }
}


@Composable
fun PaginaCinco(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Distancia Recorrida",
            color = Color.Green,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Filas principales que contienen las dos columnas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Primera columna
            Column(
                modifier = Modifier.weight(1f), // Toma el 50% del ancho disponible
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Max Spd",
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp) // Ajusta el padding vertical
                )
                Text(
                    text = "46.5",
                    textAlign = TextAlign.Center,
                    color = Color.Green,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 2.dp) // Ajusta el padding vertical
                )
                Text(
                    text = "mph",
                    textAlign = TextAlign.Center,
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp) // Ajusta el padding vertical
                )
            }
            // Segunda columna
            Column(
                modifier = Modifier.weight(1f), // Toma el 50% del ancho disponible
                verticalArrangement = Arrangement.spacedBy(4.dp), //
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Distance",
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp) // Ajusta el padding vertical
                )
                Text(
                    text = "21.8",
                    textAlign = TextAlign.Center,
                    color = Color.Green,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 2.dp) // Ajusta el padding vertical
                )
                Text(
                    text = "mile",
                    textAlign = TextAlign.Center,
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp) // Ajusta el padding vertical
                )
            }
        }
        VolverButton(navController)
    }
}


@Composable
fun Boton2(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Int = 130,
    height: Int = 80,
    cornerRadius: Int = 30,
    fontSize: Int = 20
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Green
        ),
        modifier = modifier
            .size(width.dp, height.dp) // Ajusta el tamaño del botón según sea necesario
            .padding(top = 8.dp, bottom = 16.dp) // Ajusta el padding superior e inferior del botón
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            color = Color.Black // Color del texto en blanco
        )
    }
}


@Composable
fun PaginaSeis(navController: NavController){
    Column (

        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente
    ){
// Texto añadido
        Text(
            text = "Power Yoga",
            textAlign = TextAlign.Center,
            color = Color.Green,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp) // Ajusta el padding vertical según sea necesario
        )
        Boton2(
            text = "Start",
            onClick = { /* Acción del botón */ },
            modifier = Modifier.padding(vertical = 8.dp) // Ajusta el padding vertical del botón
        )
        Text(
            text = "Last session 45m",
            textAlign = TextAlign.Center,
            color = Color.LightGray,
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp) // Ajusta el padding vertical según sea necesario
        )
        VolverButton(navController)
    }
}


@Composable
fun Portada(navController: NavHostController) {
    var currentTime by remember {
        mutableStateOf(ZonedDateTime.now(ZoneId.of("America/Mexico_City")))
    }
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = ZonedDateTime.now(ZoneId.of("America/Mexico_City"))
            delay(1000)
        }
    }
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val dateFormatter = DateTimeFormatter.ofPattern("EEEE d 'de' MMMM yyyy", Locale("es", "MX"))


    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.puebla),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable { navController.navigate("steps") }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .align(Alignment.TopCenter)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Homebtn(navController)
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Centramos horizontalmente
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .align(Alignment.BottomCenter) // Centramos el Column en la parte inferior
                .padding(16.dp)
        ) {
            Text(
                text = currentTime.format(timeFormatter),
                style = MaterialTheme.typography.title3.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    color = Color.White
                ),
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = currentTime.format(dateFormatter).replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.White
                ),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun Juego(navController: NavController) {
    var snake by remember { mutableStateOf(listOf(Pair(5, 5))) }
    var direction by remember { mutableStateOf(Direction.RIGHT) }
    var food by remember { mutableStateOf(generateFood(snake)) }
    var score by remember { mutableStateOf(0) }
    var delayMillis by remember { mutableStateOf(300L) } // Inicial delay
    val boardSize = 10

    // Iniciar el ticker y manejar el ciclo de juego
    LaunchedEffect(Unit) {
        while (true) {
            delay(delayMillis)  // Usar el delay que cambia con el score

            var currentSnake = moveSnake(snake, direction, boardSize)
            var currentFood = food
            var currentScore = score

            if (currentSnake.first() == currentFood) {
                currentSnake = currentSnake + listOf(currentSnake.last())
                currentFood = generateFood(currentSnake)
                currentScore += 1
                delayMillis = maxOf(50L, delayMillis - 20L) // Incrementar la velocidad
            }

            // Actualizar el estado del juego
            snake = currentSnake
            food = currentFood
            score = currentScore
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Score: $score",
            color = Color.White,
            fontSize = 8.sp,
            modifier = Modifier.padding(4.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .background(Color.Black)
                .padding(4.dp) // Añadir padding para el contorno blanco
                .border(2.dp, Color.White) // Dibujar el contorno blanco
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawSnake(snake)
                drawFood(food)
            }
        }

        ControlButtons { newDirection ->
            if (newDirection != direction.opposite()) {
                direction = newDirection
            }
        }

        Button(
            onClick = { navController.navigate("PaginaPrincipal") },
            modifier = Modifier
                .padding(4.dp)
                .size(24.dp)
        ) {
            Text("Salir", fontSize = 6.sp)
        }
    }
}

@Composable
fun ControlButtons(onDirectionChange: (Direction) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)
    ) {
        Button(
            onClick = { onDirectionChange(Direction.UP) },
            modifier = Modifier.size(20.dp)
        ) {
            Text("↑", fontSize = 8.sp)
        }
        Row {
            Button(
                onClick = { onDirectionChange(Direction.LEFT) },
                modifier = Modifier.size(20.dp)
            ) {
                Text("←", fontSize = 8.sp)
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = { onDirectionChange(Direction.RIGHT) },
                modifier = Modifier.size(20.dp)
            ) {
                Text("→", fontSize = 8.sp)
            }
        }
        Button(
            onClick = { onDirectionChange(Direction.DOWN) },
            modifier = Modifier.size(20.dp)
        ) {
            Text("↓", fontSize = 8.sp)
        }
    }
}

enum class Direction {
    UP, DOWN, LEFT, RIGHT;

    fun opposite(): Direction {
        return when (this) {
            UP -> DOWN
            DOWN -> UP
            LEFT -> RIGHT
            RIGHT -> LEFT
        }
    }
}

fun moveSnake(snake: List<Pair<Int, Int>>, direction: Direction, boardSize: Int): List<Pair<Int, Int>> {
    val head = snake.first()
    val newHead = when (direction) {
        Direction.UP -> Pair(head.first, (head.second - 1 + boardSize) % boardSize)
        Direction.DOWN -> Pair(head.first, (head.second + 1) % boardSize)
        Direction.LEFT -> Pair((head.first - 1 + boardSize) % boardSize, head.second)
        Direction.RIGHT -> Pair((head.first + 1) % boardSize, head.second)
    }
    return listOf(newHead) + snake.dropLast(1)
}

fun generateFood(snake: List<Pair<Int, Int>>): Pair<Int, Int> {
    var food: Pair<Int, Int>
    val boardSize = 10
    do {
        food = Pair(Random.nextInt(0, boardSize), Random.nextInt(0, boardSize))
    } while (snake.contains(food))
    return food
}

fun DrawScope.drawSnake(snake: List<Pair<Int, Int>>) {
    for (segment in snake) {
        drawRect(
            color = Color.Green,
            topLeft = androidx.compose.ui.geometry.Offset(segment.first * 20f, segment.second * 20f),
            size = androidx.compose.ui.geometry.Size(20f, 20f)
        )
    }
}

fun DrawScope.drawFood(food: Pair<Int, Int>) {
    drawRect(
        color = Color.Red,
        topLeft = androidx.compose.ui.geometry.Offset(food.first * 20f, food.second * 20f),
        size = androidx.compose.ui.geometry.Size(20f, 20f)
    )
}



@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PaginaAguaButton(navController)
    }
}

@Composable
fun PaginaAgua(navController: NavController) {
    var cantidadAgua by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Recordatorio de Agua",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Has bebido $cantidadAgua vasos de agua hoy",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = { cantidadAgua++ },
            modifier = Modifier
                .padding(10.dp)
                .width(150.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
        ) {
            Text("Beber Agua", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
        VolverButton(navController)
    }
}

@Composable
fun PaginaAguaButton(navController: NavController) {
    val context = LocalContext.current

    Button(
        onClick = {
            navController.navigate("PaginaAgua")
            scheduleWaterReminder(context)
        },
        modifier = Modifier
            .padding(10.dp)
            .width(150.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
    ) {
        Text("Agua", color = Color.White)
    }
}

fun scheduleWaterReminder(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, WaterReminderReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
   // val triggerTime = System.currentTimeMillis() + 2 * 60 * 60 * 1000 // 2 horas
    val triggerTime = System.currentTimeMillis() + 5 * 1000 // 5 segundos

    alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
}

class WaterReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelId = "water_reminder_channel"
            val channelName = "Water Reminder"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(channel)
            }

            val notificationIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val notification = NotificationCompat.Builder(context, channelId)
                .setContentTitle("Recordatorio de Agua")
                .setContentText("Es hora de beber agua")
                .setSmallIcon(R.drawable.messi) // Asegúrate de tener un ícono
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

            notificationManager.notify(1, notification)
        }
    }
}