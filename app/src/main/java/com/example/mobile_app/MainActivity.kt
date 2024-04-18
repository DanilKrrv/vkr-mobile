package com.example.mobile_app

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_app.ui.theme.ThemeBlue
import com.example.mobile_app.R


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colorScheme.background) {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") { LoginScreen(navController) }
            composable("main") { MainScreen(navController) }
            composable("attestation") { AttestationScreen(navController) }
            composable("active_shift") { ShiftScreen(navController) }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Image(painter = painterResource(id = R.drawable.secure_icon), contentDescription = "Login Image",
            modifier = Modifier.size(200.dp))

        Text(text = "Добро пожаловать!", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = username, onValueChange = {username = it}, label = {
            Text(text = "Логин")
        })

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = password, onValueChange = {password = it}, label = {
            Text(text = "Пароль")
        }, visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(18.dp))


        Button(
            onClick = {
                if (username == "test" && password == "test") {
                    navController.navigate("main")
                } else {
                    showErrorDialog = true
                }
            },
            modifier = Modifier.height(50.dp).width(120.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,       // цвет текста
                containerColor = Color(0xFF0357A6))     // цвет фона
        ) {
            Text(text = "Войти", fontSize = 18.sp)
        }
    }

    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            title = { Text("Ошибка") },
            text = { Text("Неправильный логин или пароль") },
            confirmButton = {
                Button(
                    onClick = { showErrorDialog = false },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("OK", style = TextStyle(fontSize = 16.sp))
                }
            }
        )
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Header(navController)
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("attestation") },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0357A6),
                    contentColor = Color.White
                ),
                shape = MaterialTheme.shapes.medium,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(
                    "Аттестация",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("active_shift") },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0357A6),
                    contentColor = Color.White
                ),
                shape = MaterialTheme.shapes.medium,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(
                    "Смена",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Composable
fun AttestationScreen(navController: NavController) {
    val attestations = listOf(
        Attestation("Удостоверение частного охранника", "22.01.2023", true),
        Attestation("Медосмотр", "15.03.2023", true),
        Attestation("Разрешение на владение служебным оружием", "05.11.2022", false),
        Attestation("Ежегодная аттестация по разряду", "18.08.2023", true)
    )

    AttestationsScreen(navController = navController, attestations = attestations)
}







@Composable
fun Header(navController: NavController){
    Box(modifier = Modifier.fillMaxWidth().background(ThemeBlue)) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(55.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            //            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        ){
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate("main") }
            )
            Text(text = "Security", color = Color.White, fontSize = 24.sp)
            Icon(
                imageVector = Icons.Filled.ExitToApp,
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
                    .padding(5.dp)
                    .clickable { navController.navigate("login") },
            )
        }
    }
}

@Composable
fun ShiftScreen(navController: NavController) {
    val isWorking = false
    Column {
        Header(navController)

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        ){
            Column (modifier = Modifier
                .padding(15.dp)) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Объект: ", fontSize = 24.sp)
                    Text(text = "ТЦ Европа", fontSize = 24.sp)
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Адрес: ", fontSize = 24.sp)
                    Text(text = "Ленина, 7", fontSize = 24.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Дата: ", fontSize = 24.sp)
                    Text(text = "11.04.2024", fontSize = 24.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Начало смены: ", fontSize = 24.sp)
                    Text(text = "09:00", fontSize = 24.sp)
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Конец смены: ", fontSize = 24.sp)
                    Text(text = "20:00", fontSize = 24.sp)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val checked = rememberSaveable { mutableStateOf(isWorking) }
                    MyButton(checked)

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(onClick = { navController.navigate("main") },
                        modifier = Modifier
                            .height(50.dp)
                            .width(120.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,       // цвет текста
                            containerColor = Color(0xFF0357A6))     // цвет фона
                    ) {
                        Text(text = "Назад", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}


@Composable
fun MyButton(checked: MutableState<Boolean>){
    val label = remember{mutableStateOf("Начать\nсмену")}

    Box(modifier = Modifier
        .padding(10.dp)
        .size(180.dp)
        .clip(CircleShape)
        .background(
            if (checked.value) Color(0xFFBA0000)
            else Color(0xff007f00)
        )
        .toggleable(value = checked.value, onValueChange = { checked.value = it }),
        contentAlignment = Alignment.Center
    ){
        label.value = if (checked.value) "Завершить\nсмену"
        else "Начать\nсмену"
        Text(label.value, fontSize = 25.sp, textAlign = TextAlign.Center, color = Color.White)
    }
}

@Composable
fun AttestationsScreen(navController: NavController, attestations: List<Attestation>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Header(navController)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(attestations) { attestation ->
                AttestationCard(attestation = attestation)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0357A6),
                contentColor = Color.White
            )
        ) {
            Text("Назад")
        }
    }
}

@Composable
fun AttestationCard(attestation: Attestation) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(
        defaultElevation = 4.dp
    ),) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = attestation.title, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Дата прохождения: ${attestation.datePassed}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Действителен: ${if (attestation.isValid) "Да" else "Нет"}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

data class Attestation(
    val title: String,
    val datePassed: String,
    val isValid: Boolean
)