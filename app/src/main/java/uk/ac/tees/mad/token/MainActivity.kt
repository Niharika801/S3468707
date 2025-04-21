package uk.ac.tees.mad.token

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import uk.ac.tees.mad.token.data.DataStoreManager
import uk.ac.tees.mad.token.navigation.TokenNavigation
import uk.ac.tees.mad.token.ui.theme.TokenTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var dataStoreManager: DataStoreManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkMode by dataStoreManager.isDarkModeFlow.collectAsState(false)
            TokenTheme(darkTheme = isDarkMode) {
                TokenNavigation()
            }
        }
    }
}