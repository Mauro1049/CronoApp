package com.example.roomcronoapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomcronoapp.R
import com.example.roomcronoapp.ViewModels.CronometroViewModel
import com.example.roomcronoapp.ViewModels.CronosViewModel
import com.example.roomcronoapp.components.CircleButton
import com.example.roomcronoapp.components.FloatButton
import com.example.roomcronoapp.components.MainIconButton
import com.example.roomcronoapp.components.MainTextField
import com.example.roomcronoapp.components.MainTitle
import com.example.roomcronoapp.components.formatTiempo
import com.example.roomcronoapp.model.Cronos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, cronometroVM: CronometroViewModel, cronosVM: CronosViewModel){
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "Add Crono") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        ContentAddView(it, navController, cronometroVM, cronosVM)
    }
}

@Composable
fun ContentAddView(it : PaddingValues, navController: NavController, cronometroVM: CronometroViewModel, cronosVM: CronosViewModel){

    val state = cronometroVM.state
    
    LaunchedEffect(state.cronometroActivo) {
        cronometroVM.crono()
    }

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = formatTiempo(cronometroVM.tiempo),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )

        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(vertical = 16.dp)
        ){
            CircleButton(
                icon = painterResource(id = R.drawable.play),
                enabale = !state.cronometroActivo
            ) {
                cronometroVM.iniciar()
            }
            CircleButton(
                icon = painterResource(id = R.drawable.pause),
                enabale = state.cronometroActivo
            ) {
                cronometroVM.pausar()
            }

            CircleButton(
                icon = painterResource(id = R.drawable.stop),
                enabale = !state.cronometroActivo
            ) {
                cronometroVM.detener()
            }

            CircleButton(
                icon = painterResource(id = R.drawable.save),
                enabale = state.showSaveButton
            ) {
                cronometroVM.showTextField()
            }
        }
        if(state.showTextField){
            MainTextField(
                value = state.title,
                onValueChange = { cronometroVM.onValue(it) },
                label = "Title"
            )
            Button(onClick = {
                cronosVM.addCrono(
                    Cronos(
                        title = state.title,
                        crono = cronometroVM.tiempo
                    )
                )
                cronometroVM.detener()
                navController.popBackStack()
            }) {
                Text(text = "Guardar")
            }
        }
    }
}