package com.example.navexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navexample.Guess.Gamezone
import com.example.navexample.Memory.MemPreview
import com.example.navexample.Quiz.GameScreen
import com.example.navexample.ui.theme.NavExampleTheme

//sealed class Screen(val route: String) {
//    object Home: Screen("home")
//    object Greet: Screen("greet")
//}

enum class Screen {
    Home, Guess, Quiz, Mem
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = Screen.Home.name) {
                        composable(Screen.Home.name) {
                            HomeScreen(navController = navController)
                        }
                        composable(Screen.Guess.name) {
                            Gamezone(
                                navController = navController
                            )
                        }
                        composable(Screen.Quiz.name) {
                            GameScreen(
                                navController = navController
                            )
                        }
                        composable(Screen.Mem.name) {
                            MemPreview(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(name: String, navController: NavController, modifier: Modifier = Modifier) {
    Column {
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Select Game",
            fontSize = 36.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = { navController.navigate("Guess") }) {
            Text(
                text = "Guessing Game",
                fontSize = 24.sp
            )
        }
        Spacer(Modifier.height(20.dp))
        Button(onClick = { navController.navigate("Quiz") }) {
            Text(
                text = "Quiz Game",
                fontSize = 32.sp
            )
        }
        Spacer(Modifier.height(20.dp))
        Button(onClick = { navController.navigate("Mem") }) {
            Text(
                text = "Memory Game",
                fontSize = 32.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavExampleTheme {
        val navController = rememberNavController()
        HomeScreen(navController, modifier = Modifier)
    }
}