package uk.ac.tees.mad.token.ui.screen.favorite

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.token.data.DataStoreManager
import uk.ac.tees.mad.token.data.local.FavoriteEntity
import uk.ac.tees.mad.token.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: Repository,
    private val auth: FirebaseAuth,
    private val dataStoreManager: DataStoreManager
):ViewModel() {

    private val _favoriteDataList = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val favoriteDataList:StateFlow<List<FavoriteEntity>> get() = _favoriteDataList

    private val userId = auth.currentUser?.uid?:""

    private val _selectedCurrency = MutableStateFlow("usd")
    val selectedCurrency:StateFlow<String> get() = _selectedCurrency

    init {
        viewModelScope.launch{
            repository.getFavoriteTokens(userId).collect {
                _favoriteDataList.value = it
            }
        }

        viewModelScope.launch {
            dataStoreManager.selectedCurrencyFlow.collect{_selectedCurrency.value = it}
        }
    }

    fun deleteFavorite(entity: FavoriteEntity, context: Context){
        viewModelScope.launch {
            repository.deleteFavoriteData(entity)
            Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateFavorite(entity: FavoriteEntity, context: Context){
        viewModelScope.launch {
            val newData = repository.getTokenDetails(entity.id,_selectedCurrency.value)
            repository.updateFavoriteData(entity.copy(
                currentPrice = newData.market_data.current_price[_selectedCurrency.value]?:0.0,
                priceChange = newData.market_data.price_change_percentage_24h,
                marketCap = newData.market_data.market_cap[_selectedCurrency.value]?.toLong()?:0,
                volume = newData.market_data.total_volume[_selectedCurrency.value]?.toLong()?:0
            ))
            Toast.makeText(context, "Data updated", Toast.LENGTH_SHORT).show()
        }
    }
}