package uk.ac.tees.mad.token

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import uk.ac.tees.mad.token.navigation.TokenNavigation
import uk.ac.tees.mad.token.ui.theme.TokenTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
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