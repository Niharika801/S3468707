package uk.ac.tees.mad.token.ui.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import uk.ac.tees.mad.token.ui.screen.favorite.FavoriteScreen
import uk.ac.tees.mad.token.ui.screen.home.HomeScreen
import uk.ac.tees.mad.token.ui.screen.profile.ProfileScreen

@Composable
fun MainScreen(navController: NavController) {
    var selectedScreen by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        bottomBar = { MainBottomBar(
            selectedScreen,
            onClick = {selectedScreen=it}
        )}
    ) { paddingValues->
        when(selectedScreen){
            0-> HomeScreen(modifier = Modifier.padding(paddingValues))
            1-> FavoriteScreen(modifier = Modifier.padding(paddingValues))
            2-> ProfileScreen(navController = navController, modifier = Modifier.padding(paddingValues))
        }
    }
}