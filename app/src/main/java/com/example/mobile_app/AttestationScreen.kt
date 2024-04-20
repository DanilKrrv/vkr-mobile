package com.example.mobile_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

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
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ) {
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