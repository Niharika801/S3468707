package uk.ac.tees.mad.token.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.token.R

@Composable
fun ProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier) {
    var isDark by remember { mutableStateOf(false) }
    var isFingerLock by remember { mutableStateOf(false) }
    var currentCurrency by remember { mutableStateOf("usd") }
    var showEditNameSheet by remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()
    Column(
        modifier = modifier
    ) {
        Text(
            "Profile",
            fontSize = 26.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        ProfileSection("Test Name", "test@gmail.com") {
            showEditNameSheet = true
        }

        SettingToggleButton(
            title = "Dark Mode",
            drawable = R.drawable.baseline_dark_mode_24,
            isValue = isDark,
            onToggle = {isDark = !isDark}
        )

        SettingToggleButton(
            title = "Fingerprint Lock",
            drawable = R.drawable.baseline_fingerprint_24,
            isValue = isFingerLock,
            onToggle = {isFingerLock = !isFingerLock}
        )
        
        CurrencySelector(
            currentCurrency = currentCurrency,
            onCurrencyChange = {currentCurrency = it}
        )
        
        SettingsOption(
            icon = Icons.AutoMirrored.Filled.ExitToApp,
            title = "Log Out",
            onClick ={}
        )

    }

    if (showEditNameSheet) {
        EditNameBottomSheet(
            currentName = "My Name",
            onSave = { newName ->
                showEditNameSheet = false
            },
            onDismiss = { showEditNameSheet = false }
        )
    }
}