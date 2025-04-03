package uk.ac.tees.mad.token.ui.screen.favorite

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.token.data.model.TokenData
import uk.ac.tees.mad.token.ui.theme.TokenTheme

@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
    val token = TokenData(
        id = "bitcoin",
        name = "Bitcoin",
        symbol = "BTC",
        image = "dd",
        currentPrice = 12345.1,
        priceChange = 1.23343,
        marketCap = 123456789077,
        volume = 12345678765
    )
    Column(
        modifier = modifier
    ) {
        Text("Favorite Tokens",
            fontSize = 26.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn {
            items(7){
                FavoriteTokenCard(
                    token,
                    onClick = {},
                    onDelete = {},
                    onReload = {},
                )
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FavScreenPrev() {
    TokenTheme {
        FavoriteScreen()
    }
}