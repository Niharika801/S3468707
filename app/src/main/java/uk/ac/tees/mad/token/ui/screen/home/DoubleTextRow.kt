package uk.ac.tees.mad.token.ui.screen.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DoubleTextRow(
    title:String,
    value:String,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    Row(modifier = Modifier.padding(top = 4.dp)) {
        Text("$title: ",
            color = color)
        Text(value,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}