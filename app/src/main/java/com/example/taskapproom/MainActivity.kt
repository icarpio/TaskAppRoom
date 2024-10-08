package com.example.taskapproom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskapproom.addtasks.ui.TaskScreen
import com.example.taskapproom.addtasks.ui.TaskViewModel
import com.example.taskapproom.ui.theme.TaskAppRoomTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val taskViewModel:TaskViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskAppRoomTheme {
                window.statusBarColor = MaterialTheme.colorScheme.secondary.toArgb()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Task App",
                                    color = Color.White // Cambiamos el color del título a blanco
                                )
                            },
                            actions = {
                                // Si no necesitas un botón interactivo, puedes usar Image directamente
                                Image(
                                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                    contentDescription = "Icono",
                                    modifier = Modifier
                                        .size(100.dp) // Ajustamos el tamaño de la imagen
                                        .padding(end = 2.dp), // Agregamos padding si es necesario
                                    contentScale = ContentScale.Fit // Escalamos la imagen correctamente
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Red // Establecemos el color rojo de la barra
                            )
                        )
                    }
                ) { innerPadding ->
                    TaskScreen(
                        modifier = Modifier
                            .padding(innerPadding) // Aplicamos el padding interno generado por Scaffold
                            .fillMaxSize() // Para asegurarnos que el contenido ocupa el espacio disponible
                        ,taskViewModel
                    )
                }
            }
        }
    }
}





