package uk.ac.tees.mad.token.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uk.ac.tees.mad.token.R
import uk.ac.tees.mad.token.data.model.TokenData

@Composable
fun TokenCard(
    tokenData: TokenData,
    modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .padding(16.dp)
        .fillMaxWidth()) {

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1.5f)) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(tokenData.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = "TokenIcon",
                        placeholder = painterResource(R.drawable.splash_icon),
                        error = painterResource(R.drawable.splash_icon)
                    )
                    Text(tokenData.symbol.uppercase(),
                            style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                        )
                }

                Column(modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(3f)) {

                    Text(tokenData.name,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    HorizontalDivider()
                    Text(
                        text = "$${tokenData.currentPrice}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF008000),
                        modifier = Modifier.padding(top = 6.dp)
                    )
                    DoubleTextRow("24h Change", "${"%.2f".format(tokenData.priceChange)}%")
                    DoubleTextRow("Market Cap", "$${tokenData.marketCap/1_000_000}M")
                    DoubleTextRow("Volume", "$${tokenData.volume/1_000_000}M")
                }
            }
        }
    }
}