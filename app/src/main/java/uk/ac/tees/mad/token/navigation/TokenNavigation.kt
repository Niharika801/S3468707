package uk.ac.tees.mad.token.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.token.ui.screen.authentication.AuthenticationScreen
import uk.ac.tees.mad.token.ui.screen.main.MainScreen
import uk.ac.tees.mad.token.ui.screen.splash.SplashScreen

@Composable
fun TokenNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {

        composable(Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(Screens.AuthenticationScreen.route){
            AuthenticationScreen(navController)
        }

        composable(Screens.MainScreen.route) {
            MainScreen(navController)
        }
    }
}