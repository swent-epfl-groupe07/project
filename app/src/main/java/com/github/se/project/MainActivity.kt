package com.github.se.bootcamp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.screens.DashboardScreen
import com.github.se.bootcamp.ui.authentication.SignInScreen
import com.github.se.bootcamp.ui.navigation.NavigationActions
import com.github.se.bootcamp.ui.navigation.Route
import com.github.se.bootcamp.ui.navigation.Screen
import com.github.se.bootcamp.ui.theme.BootcampTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        auth.currentUser?.let { auth.signOut() }

        setContent { BootcampTheme { Surface(modifier = Modifier.fillMaxSize()) { ProjectApp() } } }
    }
}

@Composable
fun ProjectApp() {
    val navController = rememberNavController()
    val navigationActions = NavigationActions(navController)

    NavHost(navController = navController, startDestination = Route.AUTH) {
        navigation(
            startDestination = Screen.AUTH,
            route = Route.AUTH,
        ) {
            composable(Screen.AUTH) { SignInScreen(navigationActions) }
        }

        navigation(
            startDestination = Screen.DASHBOARD,
            route = Route.DASHBOARD,
        ) {
            composable(Screen.DASHBOARD) { DashboardScreen() }
        }
    }
}
