package uk.ac.tees.mad.token.ui.screen.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uk.ac.tees.mad.token.navigation.Screens

@Composable
fun FavoriteScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel()
    ) {
    val favoriteDataList by viewModel.favoriteDataList.collectAsState()
    val selectedCurrency by viewModel.selectedCurrency.collectAsState()
    val context = LocalContext.current

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
            items(favoriteDataList){ item->
                FavoriteTokenCard(
                    item, selectedCurrency,
                    onClick = {navController.navigate(Screens.DetailScreen.route+ "/${item.id}")},
                    onDelete = {viewModel.deleteFavorite(item,context)},
                    onReload = {viewModel.updateFavorite(item,context)},
                )
            }
        }
    }
}