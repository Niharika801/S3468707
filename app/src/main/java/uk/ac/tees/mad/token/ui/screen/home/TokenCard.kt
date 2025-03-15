package uk.ac.tees.mad.token.ui.screen.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.token.R
import uk.ac.tees.mad.token.ui.theme.TokenTheme

@Composable
fun TokenCard(modifier: Modifier = Modifier) {
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
                    Image(
                        painter = painterResource(R.drawable.bitcoin),
                        contentDescription = "TokenIcon"
                    )
                    Text("BTC",
                            style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                        )
                }

                Column(modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(3f)) {

                    Text("Bitcoin",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    HorizontalDivider()
                    Text(
                        text = "$97235",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF008000),
                        modifier = Modifier.padding(top = 6.dp)
                    )
                    DoubleTextRow("24h Change", "+2.16189%")
                    DoubleTextRow("Market Cap", "$19264B")
                    DoubleTextRow("Volume", "$272206B")
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TokenPrev() {
    TokenTheme {
        TokenCard()
    }
}