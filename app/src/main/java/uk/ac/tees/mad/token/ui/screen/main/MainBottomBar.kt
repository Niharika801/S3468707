package uk.ac.tees.mad.token.ui.screen.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainBottomBar(
    isSelected: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier,
        containerColor = Color.Transparent
        ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home_icon"
                )
            },
            label = {
                Text("Home")
            },
            selected = isSelected == 0,
            onClick = { onClick(0) },
            alwaysShowLabel = false
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favorite_icon"
                )
            },
            label = {
                Text("Favorite")
            },
            selected = isSelected == 1,
            onClick = { onClick(1) },
            alwaysShowLabel = false
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "person_icon"
                )
            },
            label = {
                Text("Profile")
            },
            selected = isSelected == 2,
            onClick = { onClick(2) },
            alwaysShowLabel = false
        )
    }
}
