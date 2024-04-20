package com.example.mobile_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

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
            modifier = Modifier
                .height(50.dp)
                .width(120.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,       // цвет текста
                containerColor = Color(0xFF0357A6)
            )     // цвет фона
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