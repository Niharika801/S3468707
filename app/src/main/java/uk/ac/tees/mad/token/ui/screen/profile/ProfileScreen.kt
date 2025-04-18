package uk.ac.tees.mad.token.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uk.ac.tees.mad.token.R
import uk.ac.tees.mad.token.navigation.Screens

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier) {
    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    var isDark by remember { mutableStateOf(false) }
    var isFingerLock by remember { mutableStateOf(false) }
    var currentCurrency by remember { mutableStateOf("usd") }
    var showEditNameSheet by remember { mutableStateOf(false) }
    val context = LocalContext.current
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

        ProfileSection(name, email) {
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
            onClick ={
                viewModel.logOut()
                navController.navigate(Screens.AuthenticationScreen.route){
                    popUpTo(Screens.MainScreen.route){
                        inclusive = true
                    }
                }
            }
        )

    }

    if (showEditNameSheet) {
        EditNameBottomSheet(
            currentName = name,
            onSave = { newName ->
                viewModel.updateProfile(newName,context)
                showEditNameSheet = false
            },
            onDismiss = { showEditNameSheet = false }
        )
    }
}