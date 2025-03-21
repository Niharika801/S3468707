package uk.ac.tees.mad.token.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val cryptoList by viewModel.cryptoList.collectAsState()
    Column(
        modifier = modifier
    ) {
        Text("Marketplace",
            fontSize = 26.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn {
            items(cryptoList){ token->
                TokenCard(token)
            }
        }
    }
}