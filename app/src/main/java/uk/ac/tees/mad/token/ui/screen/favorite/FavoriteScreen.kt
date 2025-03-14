package uk.ac.tees.mad.token.ui.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()) {
        Text("Favorite Screen")
    }
}