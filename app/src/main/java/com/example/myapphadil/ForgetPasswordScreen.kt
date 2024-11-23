package com.example.myapphadil


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


// Écran "Mot de passe oublié"
@Composable
fun ForgetPasswordScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Réinitialiser le mot de passe", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6200EE), modifier = Modifier.padding(bottom = 16.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp))
        Button(onClick = { /* Réinitialiser mot de passe */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Réinitialiser", color = Color.White)
        }
        Button(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
            Text("Retour", color = Color.White)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ForgetPasswordPreview() {
    val navController = rememberNavController()
    ForgetPasswordScreen(navController)
}