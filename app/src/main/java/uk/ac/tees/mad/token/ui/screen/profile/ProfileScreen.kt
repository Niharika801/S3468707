package uk.ac.tees.mad.token.ui.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.token.navigation.Screens

@Composable
fun ProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier) {
    val auth = FirebaseAuth.getInstance()
    Box(contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()) {
        Column {
            Text("Profile Screen")
            Spacer(Modifier.height(12.dp))
            Button({
                auth.signOut()
                navController.navigate(Screens.AuthenticationScreen.route) {
                    popUpTo(Screens.MainScreen.route) {
                        inclusive = true
                    }
                }
            }) {
                Text("Log out")
            }
        }
    }
}