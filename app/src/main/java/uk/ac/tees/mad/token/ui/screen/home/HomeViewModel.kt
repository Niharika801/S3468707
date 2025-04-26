package uk.ac.tees.mad.token.ui.screen.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.token.data.DataStoreManager
import uk.ac.tees.mad.token.data.local.FavoriteEntity
import uk.ac.tees.mad.token.data.model.TokenData
import uk.ac.tees.mad.token.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
    private val auth: FirebaseAuth,
    private val dataStoreManager: DataStoreManager
):ViewModel() {

    private val _cryptoList = MutableStateFlow<List<TokenData>>(emptyList())
    val cryptoList:StateFlow<List<TokenData>> get() = _cryptoList

    private val _selectedCurrency = MutableStateFlow("usd")
    val selectedCurrency:StateFlow<String> get() = _selectedCurrency

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val userId = auth.currentUser?.uid?:""

    init {
        viewModelScope.launch {
            dataStoreManager.selectedCurrencyFlow.collect{
                _selectedCurrency.value = it
                _cryptoList.value = repository.getTokenData(_selectedCurrency.value)
            }
        }
    }

    private fun fetchCryptoData() {
        viewModelScope.launch {
            try {
                _cryptoList.value = repository.getTokenData(_selectedCurrency.value)
            } catch (e: Exception) {
                _error.value = "Error fetching data: ${e.message}"
            }
        }
    }

    fun addToFavorite(token:TokenData, context: Context){
        viewModelScope.launch {
            repository.addFavoriteData(entity = FavoriteEntity(
                id = token.id,
                userId = userId,
                name = token.name,
                symbol = token.symbol,
                image = token.image,
                currentPrice = token.currentPrice,
                priceChange = token.priceChange,
                marketCap = token.marketCap,
                volume = token.volume
            ))

            Toast.makeText(context, "Token Added to favorite", Toast.LENGTH_SHORT).show()
        }
    }
}