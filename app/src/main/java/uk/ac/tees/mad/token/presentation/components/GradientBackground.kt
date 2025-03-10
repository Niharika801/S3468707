package uk.ac.tees.mad.token.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun GradientBackground(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition()

    val color1 by transition.animateColor(
        initialValue = Color(0xFF6A11CB),
        targetValue = Color(0xFFF9D423),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val color2 by transition.animateColor(
        initialValue = Color(0xFFFC466B),
        targetValue = Color(0xFF2575FC),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        drawRect(
            brush = Brush.linearGradient(
                colors = listOf(color1, color2),
                start = Offset.Zero,
                end = Offset(size.width, size.height)
            ),
            size = size
        )
    }
}
