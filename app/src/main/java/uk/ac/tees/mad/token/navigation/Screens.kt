package uk.ac.tees.mad.token.navigation

sealed class Screens(val route:String) {

    object SplashScreen:Screens("splash_screen")
    object MainScreen:Screens("main_screen")
}