package com.example.myapphadil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapphadil.db.FeedReaderDbHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialisation de la base de données
        val dbHelper = FeedReaderDbHelper(this)

        setContent {
            MaterialTheme { // Utilisation de MaterialTheme à la place de SQLiteProjectTheme
                val navController = rememberNavController()
                AppNavigation(navController = navController, dbHelper = dbHelper)
            }
        }
    }
}

// Fonction de navigation
@Composable
fun AppNavigation(navController: NavHostController, dbHelper: FeedReaderDbHelper) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("forget_password") { ForgetPasswordScreen(navController) }
        composable("signup") { SignupScreen(navController) }
        composable("workesList") { WorkersEmployerScreen(navController, dbHelper) }
    }
}

// Écran de connexion
@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.connexion),
            contentDescription = "Logo",
            modifier = Modifier.size(120.dp)
        )

        Text(
            "Bienvenue!",
            fontSize = 24.sp,
            color = Color(0xFF6200EE),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        ClickableText(
            text = AnnotatedString("Mot de passe oublié ?"),
            onClick = { navController.navigate("forget_password") },
            modifier = Modifier.align(Alignment.End)
        )

        Button(
            onClick = { navController.navigate("workesList") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Connexion", color = Color.White)
        }

        TextButton(onClick = { navController.navigate("signup") }) {
            Text("Pas de compte ? Inscrivez-vous", color = Color(0xFF6200EE))
        }
    }
}

// Écran pour afficher la liste des employés
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkersEmployerScreen(navController: NavHostController, dbHelper: FeedReaderDbHelper) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Liste des Employés") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Contenu pour gérer la liste des employés
                Text("Contenu de la liste ici...", modifier = Modifier.padding(16.dp))
            }
        }
    )
}



// Prévisualisation de l'écran Login
@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    MaterialTheme {
        val navController = rememberNavController()
        LoginScreen(navController)
    }
}
