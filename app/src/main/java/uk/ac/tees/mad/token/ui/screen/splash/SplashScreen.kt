package uk.ac.tees.mad.token.ui.screen.splash

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.token.R
import uk.ac.tees.mad.token.navigation.Screens
import uk.ac.tees.mad.token.ui.screen.home.HomeViewModel

@Composable
fun SplashScreen(
    homeViewModel: HomeViewModel,
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavController) {

    val cryptoList by homeViewModel.cryptoList.collectAsState()

    val authSuccess by viewModel.authSuccess.collectAsState()
    val authError by viewModel.authError.collectAsState()

    val isFingerLock by viewModel.isFingerprintLock.collectAsState()

    val infiniteTransition = rememberInfiniteTransition()
    val activity = LocalActivity.current as? FragmentActivity

    val animatedRadius by infiniteTransition.animateFloat(
        initialValue = 50f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val auth = FirebaseAuth.getInstance()
    LaunchedEffect(isFingerLock) {
        if (isFingerLock) {
            activity?.let {
                viewModel.authenticate(it)
            }
        }
    }

    LaunchedEffect(authSuccess, cryptoList.isNotEmpty()) {
        if (authSuccess && cryptoList.isNotEmpty()){
            navController.navigate(if(auth.currentUser!=null) Screens.MainScreen.route
            else Screens.AuthenticationScreen.route){
                popUpTo(Screens.SplashScreen.route){
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(authError) {
        if (authError){
            activity?.finish()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(R.drawable.splash_icon),
            contentDescription = "splash_icon",
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(alpha = 0.99f)
                .drawWithContent {
                    drawContent()
                    drawRect(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFFF5722),
                                Color(0xFFFFC107),
                                Color(0xFF2196F3)
                            ),
                            center = center,
                            radius = animatedRadius
                        ),
                        blendMode = BlendMode.SrcAtop
                    )
                }
        )
    }
}