package com.example.mobile_app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ShiftScreen(navController: NavController) {
    val isWorking = rememberSaveable { mutableStateOf(false) }
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
                    MyButton(checked = isWorking)

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(onClick = { navController.navigate("main") },
                        modifier = Modifier
                            .height(50.dp)
                            .width(120.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color(0xFF0357A6)
                        )
                    ) {
                        Text(text = "Назад", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}


@Composable
fun MyButton(checked: MutableState<Boolean>) {
    val label = remember { mutableStateOf("Начать\nсмену") }

    Button(
        onClick = { checked.value = !checked.value },
        modifier = Modifier
            .padding(10.dp)
            .size(180.dp)
            .clip(CircleShape),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (checked.value) Color(0xFFBA0000) else Color(0xff007f00)
        )
    ) {
        label.value = if (checked.value) "Завершить\nсмену"
        else "Начать\nсмену"
        Text(label.value, fontSize = 25.sp, textAlign = TextAlign.Center, color = Color.White)
    }
}