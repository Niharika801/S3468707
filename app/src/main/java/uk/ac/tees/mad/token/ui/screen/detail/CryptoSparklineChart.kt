package uk.ac.tees.mad.token.ui.screen.detail

import androidx.compose.runtime.Composable
import com.github.mikephil.charting.data.Entry


@Composable
fun CryptoSparklineChart(prices: List<Double>) {
    val data = prices.mapIndexed { index, price -> Entry(index.toFloat(), price.toFloat()) }
    val dataset = LineDataSet(data, "Price Trend").apply {
        color = Color(0xFF2196F3).toArgb()
        valueTextColor = Color.White.toArgb()
        setDrawFilled(true)
        fillColor = Color(0xFF2196F3).toArgb()
        fillAlpha = 50
        lineWidth = 2f
    }

    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                this.data = LineData(dataset)
                description.isEnabled = false
                setTouchEnabled(false)
                xAxis.isEnabled = false
                axisLeft.isEnabled = false
                axisRight.isEnabled = false
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    )
}
