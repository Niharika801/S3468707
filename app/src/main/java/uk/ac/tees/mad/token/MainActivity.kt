package uk.ac.tees.mad.token

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import uk.ac.tees.mad.token.navigation.TokenNavigation
import uk.ac.tees.mad.token.ui.theme.TokenTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TokenTheme {
                TokenNavigation()
            }
        }
    }
}